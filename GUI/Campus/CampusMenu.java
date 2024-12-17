package GUI.Campus;

import DTO.CampusDTO;
import Service.Campus.DeleteCampus;
import Service.Campus.SelectCampus;

import javax.swing.*;
import java.awt.*;

public class CampusMenu {

    private static Long selectedCampusId;
    private static JList<Object> campusList;
    private static DefaultListModel<Object> campusListModel;

    private static void loadCampusList() {
        SelectCampus selectCampus = new SelectCampus();
        campusListModel.clear();
        for (CampusDTO campus : selectCampus.execute()) {
            campusListModel.addElement(campus);
        }
    }

    private static void initCampusMenu() {
        JFrame frame = new JFrame("Gerenciamento de Campus");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BorderLayout());

        campusListModel = new DefaultListModel<>();
        campusList = new JList<>(campusListModel);
        campusList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedItem = campusList.getSelectedValue();
                if (selectedItem != null) {
                    selectedCampusId = ((CampusDTO) selectedItem).getId();
                }
            }
        });

        JScrollPane campusScrollPane = new JScrollPane(campusList);
        campusScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Campus"));

        painelEsquerdo.add(campusScrollPane, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridLayout(4, 1, 10, 10));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton novoButton = new JButton("NOVO");
        JButton editarButton = new JButton("EDITAR");
        JButton excluirButton = new JButton("EXCLUIR");
        JButton recarregarButton = new JButton("RECARREGAR LISTA");

        novoButton.addActionListener(e -> {
            CampusAdd.main();
            selectedCampusId = null;
        });

        editarButton.addActionListener(e -> {
            if (selectedCampusId != null) {
                CampusEdit.main(selectedCampusId);
                selectedCampusId = null;
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um campus.");
            }
        });

        excluirButton.addActionListener(e -> {
            if (selectedCampusId != null) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza de que deseja excluir o campus?", "Excluir Campus",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DeleteCampus deleteCampus = new DeleteCampus();
                    if (deleteCampus.execute(selectedCampusId)) {
                        JOptionPane.showMessageDialog(frame, "Sucesso ao deletar o campus");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Esse campus está ligado a um equipamento, logo não será deletado");
                    }
                    selectedCampusId = null;
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um campus.");
            }
        });

        recarregarButton.addActionListener(e -> {
            loadCampusList();
            selectedCampusId = null;
        });

        painelDireito.add(novoButton);
        painelDireito.add(editarButton);
        painelDireito.add(excluirButton);
        painelDireito.add(recarregarButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);

        frame.add(splitPane, BorderLayout.CENTER);

        loadCampusList();
        frame.setVisible(true);
    }

    public static void main() {
        initCampusMenu();
    }
}
