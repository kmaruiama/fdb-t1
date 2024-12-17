package GUI.Supplier;

import DTO.SupplierDTO;
import Service.Supplier.DeleteSupplier;
import Service.Supplier.SelectSupplier;

import javax.swing.*;
import java.awt.*;

public class SupplierMenu {

    private static Long selectedSupplierId;
    private static JList<Object> supplierList;
    private static DefaultListModel<Object> supplierListModel;

    private static void loadSupplierList() {
        SelectSupplier selectSupplier = new SelectSupplier();
        supplierListModel.clear();
        for (SupplierDTO supplier : selectSupplier.execute()) {
            supplierListModel.addElement(supplier);
        }
    }

    private static void initSupplierMenu() {
        JFrame frame = new JFrame("Gerenciamento de Fornecedores");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BorderLayout());

        supplierListModel = new DefaultListModel<>();
        supplierList = new JList<>(supplierListModel);
        supplierList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedItem = supplierList.getSelectedValue();
                if (selectedItem != null) {
                    selectedSupplierId = ((SupplierDTO) selectedItem).getId();
                }
            }
        });

        JScrollPane supplierScrollPane = new JScrollPane(supplierList);
        supplierScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Fornecedores"));

        painelEsquerdo.add(supplierScrollPane, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridLayout(4, 1, 10, 10));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton novoButton = new JButton("NOVO");
        JButton editarButton = new JButton("EDITAR");
        JButton excluirButton = new JButton("EXCLUIR");
        JButton recarregarButton = new JButton("RECARREGAR LISTA");

        novoButton.addActionListener(e -> {
            SupplierAdd.main();
            selectedSupplierId = null;
        });

        editarButton.addActionListener(e -> {
            if (selectedSupplierId != null) {
                SupplierEdit.main(selectedSupplierId);
                selectedSupplierId = null;
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um fornecedor.");
            }
        });

        excluirButton.addActionListener(e -> {
            if (selectedSupplierId != null) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza de que deseja excluir o fornecedor?", "Excluir Fornecedor",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DeleteSupplier deleteSupplier = new DeleteSupplier();
                    if (deleteSupplier.execute(selectedSupplierId)) {
                        JOptionPane.showMessageDialog(frame, "Sucesso ao deletar o fornecedor");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Erro ao deletar o fornecedor. Verifique se ele está ligado a algum registro.");
                    }
                    selectedSupplierId = null;
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um fornecedor.");
            }
        });

        recarregarButton.addActionListener(e -> {
            loadSupplierList();
            selectedSupplierId = null;
        });

        painelDireito.add(novoButton);
        painelDireito.add(editarButton);
        painelDireito.add(excluirButton);
        painelDireito.add(recarregarButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);

        frame.add(splitPane, BorderLayout.CENTER);

        loadSupplierList();
        frame.setVisible(true);
    }

    public static void main() {
        initSupplierMenu();
    }
}