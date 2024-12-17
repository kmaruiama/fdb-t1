package GUI.Reports;

import DTO.ViewGeneralsDTO;
import Service.Report.AssetGenerals;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class AssetSummaryReport {

    private static void initAssetSummary() {
        JFrame frame = new JFrame("Relatório de Equipamentos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        AssetGenerals assetGenerals = new AssetGenerals();
        List<ViewGeneralsDTO> assetSummaryList = assetGenerals.getAssetSummary();

        String[] columnNames = {"ID", "DESCRIÇÃO", "VALOR DE COMPRA", "DEPARTAMENTO", "CAMPUS"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (ViewGeneralsDTO asset : assetSummaryList) {
            Object[] rowData = {
                    asset.getAssetNumber(),
                    asset.getDescription(),
                    "R$ " + String.format("%.2f", asset.getAcquisitionValue()),
                    asset.getDepartment(),
                    asset.getCampus()
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Relatório de Equipamentos", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main() {
        initAssetSummary();
    }
}
