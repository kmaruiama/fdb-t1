package GUI.Document;

import DTO.DocumentDTO;
import Service.Document.InsertDocument;
import Service.Document.UpdateDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentEdit {
    private static void initDocumentEdit(Long id) {

        JFrame frame = new JFrame("Editar nota fiscal");
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
                    documentDTO.setId(id);
                    documentDTO.setDocumentName(textField.getText());
                    UpdateDocument updateDocument = new UpdateDocument();
                    updateDocument.execute(documentDTO);
                    JOptionPane.showMessageDialog(frame, "SUCESSO.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main (Long id){
        initDocumentEdit(id);
    }
}
