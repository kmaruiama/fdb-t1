package GUI.Reports;

import DTO.PrejuizoDTO;
import Service.Report.SelectPrejuizo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Prejuizo {

    private static void initPrejuizo() {
        JFrame frame = new JFrame("Relatório de Prejuízo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        SelectPrejuizo selectPrejuizo = new SelectPrejuizo();
        List<PrejuizoDTO> prejuizoList = selectPrejuizo.getPrejuizoList();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Relatório de Prejuízo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));

        double totalPrejuizo = 0;

        for (PrejuizoDTO prejuizo : prejuizoList) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            rowPanel.setBackground(Color.WHITE);

            String dateString = prejuizo.getDataCalculo().toString();
            String valueString = "R$ " + String.format("%.2f", prejuizo.getPrejuizoTotal());

            JLabel dateLabel = new JLabel(dateString);
            dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            dateLabel.setPreferredSize(new Dimension(200, 20));

            JLabel valueLabel = new JLabel(valueString);
            valueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            valueLabel.setForeground(new Color(200, 0 ,0));
            valueLabel.setPreferredSize(new Dimension(100, 20));

            rowPanel.add(dateLabel);
            rowPanel.add(valueLabel);
            panel.add(rowPanel);

            totalPrejuizo += prejuizo.getPrejuizoTotal();
        }

        JPanel totalRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalRowPanel.setBackground(Color.WHITE);

        JLabel totalLabel = new JLabel("PREJUÍZO TOTAL:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalRowPanel.add(totalLabel);

        JLabel totalValueLabel = new JLabel("R$ " + String.format("%.2f", totalPrejuizo));
        totalValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalValueLabel.setForeground(new Color(200, 0, 0));
        totalRowPanel.add(totalValueLabel);

        panel.add(Box.createVerticalStrut(20));
        panel.add(totalRowPanel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main() {
        initPrejuizo();
    }

}
