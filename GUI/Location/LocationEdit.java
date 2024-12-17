package GUI.Location;

import DTO.LocationDTO;
import Service.Location.InsertLocation;
import Service.Location.UpdateLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationEdit {
    public static void initLocationEdit(Long id) {

        JFrame frame = new JFrame("Editar sala");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 120);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Insira o nome da sala:");
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
                    JOptionPane.showMessageDialog(frame, "O NOME DA SALA NÃO DEVE EXCEDER 255 CARACTERES");
                }
                else {
                    LocationDTO locationDTO = new LocationDTO();
                    locationDTO.setLocationName(textField.getText());
                    locationDTO.setId(id);
                    UpdateLocation updateLocation = new UpdateLocation();
                    updateLocation.execute(locationDTO);
                    JOptionPane.showMessageDialog(frame, "SUCESSO.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main (Long id){
        initLocationEdit(id);
    }
}
