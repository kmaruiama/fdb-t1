package GUI.Document;

import DTO.DocumentDTO;
import Service.Document.InsertDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentAdd {
    private static void initDocumentAdd() {

        JFrame frame = new JFrame("Nova nota fiscal");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 120);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Insira o número da nota fiscal:");
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
                    JOptionPane.showMessageDialog(frame, "O NÚMERO DA NOTA NÃO DEVE EXCEDER 255 CARACTERES");
                }
                else {
                    DocumentDTO documentDTO = new DocumentDTO();
                    documentDTO.setDocumentName(textField.getText());
                    InsertDocument insertDocument = new InsertDocument();
                    insertDocument.execute(documentDTO);
                    JOptionPane.showMessageDialog(frame, "SUCESSO.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main (){
        initDocumentAdd();
    }
}
