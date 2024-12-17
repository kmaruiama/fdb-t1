package GUI.Location;

import DTO.LocationDTO;
import Service.Location.DeleteLocation;
import Service.Location.SelectLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LocationMenu {

    private static Long selectedLocationId;
    private static JList<Object> locationList;
    private static DefaultListModel<Object> locationListModel;

    private static void loadLocationList() {
        SelectLocation selectLocation = new SelectLocation();
        locationListModel.clear();
        for (LocationDTO location : selectLocation.execute()) {
            locationListModel.addElement(location);
        }
    }

    private static void initLocationMenu() {
        JFrame frame = new JFrame("Gerenciamento de Salas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BorderLayout());

        locationListModel = new DefaultListModel<>();
        locationList = new JList<>(locationListModel);
        locationList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedItem = locationList.getSelectedValue();
                if (selectedItem != null) {
                    selectedLocationId = ((LocationDTO) selectedItem).getId();
                }
            }
        });

        JScrollPane locationScrollPane = new JScrollPane(locationList);
        locationScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Salas"));

        painelEsquerdo.add(locationScrollPane, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridLayout(4, 1, 10, 10));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton novoButton = new JButton("NOVO");
        JButton editarButton = new JButton("EDITAR");
        JButton excluirButton = new JButton("EXCLUIR");
        JButton recarregarButton = new JButton("RECARREGAR LISTA");

        novoButton.addActionListener(e -> {
            LocationAdd.main();
            selectedLocationId = null;
        });

        editarButton.addActionListener(e -> {
            if (selectedLocationId != null) {
                LocationEdit.main(selectedLocationId);
                selectedLocationId = null;
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione uma sala.");
            }
        });

        excluirButton.addActionListener(e -> {
            if (selectedLocationId != null) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza de que deseja excluir a sala?", "Excluir Sala",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DeleteLocation deleteLocation = new DeleteLocation();
                    if (deleteLocation.execute(selectedLocationId)) {
                        JOptionPane.showMessageDialog(frame, "Sucesso ao deletar a sala");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Essa sala está ligada a um equipamento, logo não pode ser deletada");
                    }
                    selectedLocationId = null;
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione uma sala.");
            }
        });

        recarregarButton.addActionListener(e -> {
            loadLocationList();
            selectedLocationId = null;
        });

        painelDireito.add(novoButton);
        painelDireito.add(editarButton);
        painelDireito.add(excluirButton);
        painelDireito.add(recarregarButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);

        frame.add(splitPane, BorderLayout.CENTER);

        loadLocationList();
        frame.setVisible(true);
    }

    public static void main() {
        initLocationMenu();
    }
}
