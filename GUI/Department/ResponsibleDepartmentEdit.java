package GUI.Department;

import DTO.CampusDTO;
import DTO.ResponsibleDepartmentDTO;
import Service.Department.InsertDepartment;
import Service.Department.UpdateDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResponsibleDepartmentEdit {
    private static void initResponsibleDepartmentEdit(Long id) {

        JFrame frame = new JFrame("Editar setor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 120);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Insira o novo nome do setor:");
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
                    responsibleDepartmentDTO.setId(id);
                    responsibleDepartmentDTO.setResponsibleDepartmentName(textField.getText());
                    UpdateDepartment updateDepartment = new UpdateDepartment();
                    updateDepartment.execute(responsibleDepartmentDTO);
                    JOptionPane.showMessageDialog(frame, "SUCESSO.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(Long id){
        initResponsibleDepartmentEdit(id);
    }
}
