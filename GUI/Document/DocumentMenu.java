package GUI.Document;
import DTO.DocumentDTO;
import Service.Document.DeleteDocument;
import Service.Document.SelectDocument;
import javax.swing.*;
import java.awt.*;

public class DocumentMenu {

    private static Long selectedDocumentId;
    private static JList<Object> documentList;
    private static DefaultListModel<Object> documentListModel;

    private static void loadDocumentList() {
        SelectDocument selectDocument = new SelectDocument();
        documentListModel.clear();
        for (DocumentDTO document : selectDocument.execute()) {
            documentListModel.addElement(document);
        }
    }

    private static void initDocumentMenu() {
        JFrame frame = new JFrame("Gerenciamento de Notas Fiscais");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BorderLayout());

        documentListModel = new DefaultListModel<>();
        documentList = new JList<>(documentListModel);
        documentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedItem = documentList.getSelectedValue();
                if (selectedItem != null) {
                    selectedDocumentId = ((DocumentDTO) selectedItem).getId();
                }
            }
        });

        JScrollPane documentScrollPane = new JScrollPane(documentList);
        documentScrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Notas Fiscais"));

        painelEsquerdo.add(documentScrollPane, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridLayout(4, 1, 10, 10));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton novoButton = new JButton("NOVO");
        JButton editarButton = new JButton("EDITAR");
        JButton excluirButton = new JButton("EXCLUIR");
        JButton recarregarButton = new JButton("RECARREGAR LISTA");

        novoButton.addActionListener(e -> {
            DocumentAdd.main();
            selectedDocumentId = null;
        });

        editarButton.addActionListener(e -> {
            if (selectedDocumentId != null) {
                DocumentEdit.main(selectedDocumentId);
                selectedDocumentId = null;
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione uma nota fiscal.");
            }
        });

        excluirButton.addActionListener(e -> {
            if (selectedDocumentId != null) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza de que deseja excluir a nota fiscal?", "Excluir Nota Fiscal",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DeleteDocument deleteDocument = new DeleteDocument();
                    if (deleteDocument.execute(selectedDocumentId)) {
                        JOptionPane.showMessageDialog(frame, "Sucesso ao deletar a nota fiscal");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Essa nota fiscal está ligada a um equipamento, logo não pode ser deletada");
                    }
                    selectedDocumentId = null;
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione uma nota fiscal.");
            }
        });

        recarregarButton.addActionListener(e -> {
            loadDocumentList();
            selectedDocumentId = null;
        });

        painelDireito.add(novoButton);
        painelDireito.add(editarButton);
        painelDireito.add(excluirButton);
        painelDireito.add(recarregarButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);

        frame.add(splitPane, BorderLayout.CENTER);

        loadDocumentList();
        frame.setVisible(true);
    }

    public static void main() {
        initDocumentMenu();
    }
}
