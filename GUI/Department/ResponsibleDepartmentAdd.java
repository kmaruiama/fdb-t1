package GUI.Department;

import DTO.ResponsibleDepartmentDTO;
import Service.Department.InsertDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResponsibleDepartmentAdd {
    private static void initResponsibleDepartment() {

        JFrame frame = new JFrame("Novo setor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 120);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Insira o nome do setor:");
        frame.add(label);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 40));
        frame.add(textField);

        JButton button = new JButton("Salvar");
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textField.getText().length()>255){
                    JOptionPane.showMessageDialog(frame, "O NOME DO SETOR NÃO DEVE EXCEDER 255 CARACTERES");
                }
                else {
                    ResponsibleDepartmentDTO responsibleDepartmentDTO = new ResponsibleDepartmentDTO();
                    responsibleDepartmentDTO.setResponsibleDepartmentName(textField.getText());
                    InsertDepartment insertDepartment = new InsertDepartment();
                    insertDepartment.execute(responsibleDepartmentDTO);
                    JOptionPane.showMessageDialog(frame, "SUCESSO.");
                }
            }
        });

        frame.setVisible(true);
    }
    public static void main(){
        initResponsibleDepartment();
    }
}
