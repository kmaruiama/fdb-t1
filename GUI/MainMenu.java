package GUI;

import GUI.Asset.AssetMenu;
import GUI.Campus.CampusMenu;
import GUI.Department.ResponsibleDepartmentMenu;
import GUI.Document.DocumentMenu;
import GUI.Location.LocationMenu;
import GUI.Supplier.SupplierMenu;
import GUI.Reports.ReportMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    public static void initMainMenu(){

        JFrame frame = new JFrame("SISTEMA DE ESTOQUE DO PARQUE COMPUTACIONAL DO IFAP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 350);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("SISTEMA DE ESTOQUE DO PARQUE COMPUTACIONAL DO IFAP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setPreferredSize(new Dimension(480, 50));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton assetButton = new JButton("Menu de equipamentos");
        JButton campusButton = new JButton("Menu de Campi");
        JButton documentButton = new JButton("Menu de Notas Fiscais");
        JButton locationButton = new JButton("Menu de Salas");
        JButton departmentButton = new JButton("Menu do Setores responsáveis");
        JButton supplierButton = new JButton("Menu de Fornecedores");
        JButton reportButton = new JButton("Menu de Relatórios");

        assetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssetMenu.main();
            }
        });

        campusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CampusMenu.main();
            }
        });

        documentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentMenu.main();
            }
        });

        locationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocationMenu.main();
            }
        });

        departmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResponsibleDepartmentMenu.main();
            }
        });

        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierMenu.main();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportMenu.main();
            }
        });

        buttonPanel.add(assetButton);
        buttonPanel.add(campusButton);
        buttonPanel.add(documentButton);
        buttonPanel.add(locationButton);
        buttonPanel.add(departmentButton);
        buttonPanel.add(supplierButton);
        buttonPanel.add(reportButton);

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main() {
        initMainMenu();
    }
}
