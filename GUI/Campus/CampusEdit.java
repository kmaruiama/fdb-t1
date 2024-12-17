package GUI.Campus;

import DTO.CampusDTO;
import Service.Campus.InsertCampus;
import Service.Campus.UpdateCampus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CampusEdit {
    private static void initCampusEdit(Long id) {

        JFrame frame = new JFrame("Editar campus");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 120);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Insira o novo nome do campus:");
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
                    JOptionPane.showMessageDialog(frame, "O NOME DO CAMPUS NÃO DEVE EXCEDER 255 CARACTERES");
                }
                else {
                    CampusDTO campusDTO = new CampusDTO();
                    campusDTO.setCampusName(textField.getText());
                    campusDTO.setId(id);
                    UpdateCampus updateCampus = new UpdateCampus();
                    updateCampus.execute(campusDTO);
                    JOptionPane.showMessageDialog(frame, "SUCESSO.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main (Long id){
        initCampusEdit(id);
    }
}
