package GUI.Reports;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportMenu {

    public static void initReportMenu() {
        JFrame frame = new JFrame("Menu de Relatórios");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton prejuizoButton = new JButton("Relatório de Prejuízo");
        JButton resumoButton = new JButton("Relatório de Resumo de Equipamentos");

        prejuizoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Prejuizo.main();
            }
        });

        resumoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssetSummaryReport.main();
            }
        });

        panel.add(prejuizoButton);
        panel.add(resumoButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main() {
        initReportMenu();
    }
}
