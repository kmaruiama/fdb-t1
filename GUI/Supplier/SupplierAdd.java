package GUI.Supplier;
import DTO.SupplierDTO;
import Service.Supplier.InsertSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupplierAdd {
    public static void initSuplierAdd() {

        JFrame frame = new JFrame("Novo fornecedor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 120);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Insira o nome do fornecedor:");
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
                    JOptionPane.showMessageDialog(frame, "O NOME DO FORNECEDOR NÃO DEVE EXCEDER 255 CARACTERES");
                }
                else {
                    SupplierDTO supplierDTO = new SupplierDTO();
                    supplierDTO.setSupplierName(textField.getText());
                    InsertSupplier insertSupplier = new InsertSupplier();
                    insertSupplier.execute(supplierDTO);
                    JOptionPane.showMessageDialog(frame, "SUCESSO.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(){
        initSuplierAdd();
    }
}
