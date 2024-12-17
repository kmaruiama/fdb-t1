package GUI.Department;

import DTO.ResponsibleDepartmentDTO;
import Service.Department.DeleteDepartment;
import Service.Department.SelectDepartment;

import javax.swing.*;
import java.awt.*;

public class ResponsibleDepartmentMenu {

    private static Long selectedDepartmentId;
    private static JList<Object> departmentList;
    private static DefaultListModel<Object> departmentListModel;

    private static void loadDepartmentList() {
        SelectDepartment selectDepartment = new SelectDepartment();
        departmentListModel.clear();
        for (ResponsibleDepartmentDTO department : selectDepartment.execute()) {
            departmentListModel.addElement(department);
        }
    }

    private static void initDepartmentMenu() {
        JFrame frame = new JFrame("Gerenciamento de Setor Responsável");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BorderLayout());

        departmentListModel = new DefaultListModel<>();
        departmentList = new JList<>(departmentListModel);
        departmentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedItem = departmentList.getSelectedValue();
                if (selectedItem != null) {
                    selectedDepartmentId = ((ResponsibleDepartmentDTO) selectedItem).getId();
                }
            }
        });

        JScrollPane departmentScrollPane = new JScrollPane(departmentList);
        departmentScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de setores"));

        painelEsquerdo.add(departmentScrollPane, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridLayout(4, 1, 10, 10));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton novoButton = new JButton("NOVO");
        JButton editarButton = new JButton("EDITAR");
        JButton excluirButton = new JButton("EXCLUIR");
        JButton recarregarButton = new JButton("RECARREGAR LISTA");

        novoButton.addActionListener(e -> {
            ResponsibleDepartmentAdd.main();
            selectedDepartmentId = null;
        });

        editarButton.addActionListener(e -> {
            if (selectedDepartmentId != null) {
                ResponsibleDepartmentEdit.main(selectedDepartmentId);
                selectedDepartmentId = null;
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um setor.");
            }
        });

        excluirButton.addActionListener(e -> {
            if (selectedDepartmentId != null) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza de que deseja excluir o setor?", "Excluir setor",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DeleteDepartment deleteDepartment = new DeleteDepartment();
                    if (deleteDepartment.execute(selectedDepartmentId)) {
                        JOptionPane.showMessageDialog(frame, "Sucesso ao deletar o setor");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Esse setor está ligado a um equipamento, logo não será deletado");
                    }
                    selectedDepartmentId = null;
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um setor.");
            }
        });

        recarregarButton.addActionListener(e -> {
            loadDepartmentList();
            selectedDepartmentId = null;
        });

        painelDireito.add(novoButton);
        painelDireito.add(editarButton);
        painelDireito.add(excluirButton);
        painelDireito.add(recarregarButton);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);

        frame.add(splitPane, BorderLayout.CENTER);

        loadDepartmentList();
        frame.setVisible(true);
    }

    public static void main() {
        initDepartmentMenu();
    }
}
