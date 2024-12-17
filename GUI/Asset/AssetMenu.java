package GUI.Asset;

import DTO.AssetDTO;
import Service.Asset.DeleteAsset;
import Service.Asset.SelectAsset;
import javax.swing.*;
import java.awt.*;

public class AssetMenu {

    private static Long selectedAssetId;
    private static JList<Object> assetList;
    private static DefaultListModel<Object> assetListModel;

    private static void loadAssetList() {
        SelectAsset selectAsset = new SelectAsset();
        assetListModel.clear();
        for (AssetDTO asset : selectAsset.execute()) {
            assetListModel.addElement(asset);
        }
    }

    private static void initAssetMenu() {
        JFrame frame = new JFrame("Gerenciamento de Equipamentos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BorderLayout());

        assetListModel = new DefaultListModel<>();
        assetList = new JList<>(assetListModel);
        assetList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedItem = assetList.getSelectedValue();
                if (selectedItem != null) {
                    selectedAssetId = ((AssetDTO) selectedItem).getId();
                }
            }
        });

        JScrollPane assetScrollPane = new JScrollPane(assetList);
        assetScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Equipamentos"));

        painelEsquerdo.add(assetScrollPane, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridLayout(4, 1, 10, 10));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton novoButton = new JButton("NOVO");
        JButton editarButton = new JButton("EDITAR");
        JButton excluirButton = new JButton("EXCLUIR");
        JButton recarregarButton = new JButton("RECARREGAR LISTA");

        novoButton.addActionListener(e -> {
            AssetAdd.main();
            selectedAssetId = null;
        });

        editarButton.addActionListener(e -> {
            if (selectedAssetId != null) {
                AssetEdit.main(selectedAssetId);
                selectedAssetId = null;
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um equipamento.");
            }
        });

        excluirButton.addActionListener(e -> {
            if (selectedAssetId != null) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza de que deseja excluir o equipamento?", "Excluir Equipamento",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DeleteAsset deleteAsset = new DeleteAsset();
                    if (deleteAsset.execute(selectedAssetId)) {
                        JOptionPane.showMessageDialog(frame, "Sucesso ao deletar o equipamento");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Não foi possível deletar o equipamento");
                    }
                    selectedAssetId = null;
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um equipamento.");
            }
        });

        recarregarButton.addActionListener(e -> {
            loadAssetList();
            selectedAssetId = null;
        });

        painelDireito.add(novoButton);
        painelDireito.add(editarButton);
        painelDireito.add(excluirButton);
        painelDireito.add(recarregarButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);

        frame.add(splitPane, BorderLayout.CENTER);

        loadAssetList();
        frame.setVisible(true);
    }

    public static void main() {
        initAssetMenu();
    }
}
