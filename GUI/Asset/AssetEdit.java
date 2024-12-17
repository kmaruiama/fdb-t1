package GUI.Asset;

import DTO.*;
import Service.Asset.SelectIndividualAsset;
import Service.Asset.UpdateAsset;
import Service.Campus.SelectCampus;
import Service.Department.SelectDepartment;
import Service.Document.SelectDocument;
import Service.Location.SelectLocation;
import Service.Supplier.SelectSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;

public class AssetEdit {

    private static Long selectedCampusId;
    private static Long selectedDocumentId;
    private static Long selectedLocationId;
    private static Long selectedDepartmentId;
    private static Long selectedSupplierId;

    private static JFormattedTextField dataEntradaField;
    private static JFormattedTextField dataCargaField;
    private static JComboBox<String> statusComboBox;
    private static JComboBox<String> estadoConservacaoComboBox;
    private static JTextField valorAquisicaoField;
    private static JTextField valorDepreciadoField;
    private static JTextArea descricaoArea;

    private static JButton enviarButton = new JButton("ENVIAR");

    private static void initAsset(Long assetId) {
        SelectCampus selectCampus = new SelectCampus();
        SelectDocument selectDocument = new SelectDocument();
        SelectLocation selectLocation = new SelectLocation();
        SelectDepartment selectDepartment = new SelectDepartment();
        SelectSupplier selectSupplier = new SelectSupplier();
        SelectIndividualAsset selectIndividualAsset = new SelectIndividualAsset();

        CampusDTO[] campusArray = selectCampus.execute().toArray(new CampusDTO[0]);
        DocumentDTO[] documentArray = selectDocument.execute().toArray(new DocumentDTO[0]);
        LocationDTO[] locationArray = selectLocation.execute().toArray(new LocationDTO[0]);
        ResponsibleDepartmentDTO[] departmentArray = selectDepartment.execute().toArray(new ResponsibleDepartmentDTO[0]);
        SupplierDTO[] supplierArray = selectSupplier.execute().toArray(new SupplierDTO[0]);

        AssetDTO assetDTO = selectIndividualAsset.execute(assetId);

        if (assetDTO == null) {
            JOptionPane.showMessageDialog(null, "Ativo não encontrado.");
            return;
        }

        JFrame frame = new JFrame("Editar Informações do Ativo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new GridLayout(5, 1, 10, 10));
        painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelEsquerdo.add(createScrollPane("Campus", campusArray, "Campus"));
        painelEsquerdo.add(createScrollPane("Nota Fiscal", documentArray, "Document"));
        painelEsquerdo.add(createScrollPane("Sala", locationArray, "Location"));
        painelEsquerdo.add(createScrollPane("Setor Responsável", departmentArray, "Department"));
        painelEsquerdo.add(createScrollPane("Fornecedor", supplierArray, "Supplier"));

        JPanel painelDireito = new JPanel(new GridBagLayout());
        painelDireito.setBorder(BorderFactory.createTitledBorder("Detalhes do Ativo"));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        painelDireito.add(new JLabel("Status:"), gridBagConstraints);
        statusComboBox = new JComboBox<>(new String[]{"Ativo", "Inativo"});
        statusComboBox.setSelectedItem(assetDTO.getStatus());
        gridBagConstraints.gridx = 1;
        painelDireito.add(statusComboBox, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        painelDireito.add(new JLabel("Estado de Conservação:"), gridBagConstraints);
        estadoConservacaoComboBox = new JComboBox<>(new String[]{"Bom", "Irreversível", "Recuperável"});
        estadoConservacaoComboBox.setSelectedItem(assetDTO.getEstadoConservacao());
        gridBagConstraints.gridx = 1;
        painelDireito.add(estadoConservacaoComboBox, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        painelDireito.add(new JLabel("Valor Aquisição:"), gridBagConstraints);
        valorAquisicaoField = new JTextField(String.valueOf(assetDTO.getValorAquisicao()));
        gridBagConstraints.gridx = 1;
        painelDireito.add(valorAquisicaoField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        painelDireito.add(new JLabel("Valor Depreciado:"), gridBagConstraints);
        valorDepreciadoField = new JTextField(String.valueOf(assetDTO.getValorDepreciado()));
        gridBagConstraints.gridx = 1;
        painelDireito.add(valorDepreciadoField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        painelDireito.add(new JLabel("Descrição:"), gridBagConstraints);
        descricaoArea = new JTextArea(4, 20);
        descricaoArea.setText(assetDTO.getDescricao());
        JScrollPane descricaoScrollPane = new JScrollPane(descricaoArea);
        gridBagConstraints.gridx = 1;
        painelDireito.add(descricaoScrollPane, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        painelDireito.add(new JLabel("Data Aquisição:"), gridBagConstraints);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        dataEntradaField = new JFormattedTextField(dateFormatter);
        dataEntradaField.setColumns(10);
        dataEntradaField.setText(dateFormat.format(assetDTO.getEntrada()));
        gridBagConstraints.gridx = 1;
        painelDireito.add(dataEntradaField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        painelDireito.add(new JLabel("Data Compra:"), gridBagConstraints);
        dataCargaField = new JFormattedTextField(dateFormatter);
        dataCargaField.setColumns(10);
        dataCargaField.setText(dateFormat.format(assetDTO.getCarga()));
        gridBagConstraints.gridx = 1;
        painelDireito.add(dataCargaField, gridBagConstraints);

        JButton enviarButton = new JButton("Enviar");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        painelDireito.add(enviarButton, gridBagConstraints);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkNull()) {
                    assetDTO.setCampusId(selectedCampusId);
                    assetDTO.setDepartamentoResponsavelId(selectedDepartmentId);
                    assetDTO.setFornecedorId(selectedSupplierId);
                    assetDTO.setSalaId(selectedLocationId);
                    assetDTO.setDocumentId(selectedDocumentId);
                    assetDTO.setDescricao(descricaoArea.getText());
                    assetDTO.setValorAquisicao(Float.parseFloat(valorAquisicaoField.getText()));
                    assetDTO.setValorDepreciado(Float.parseFloat(valorDepreciadoField.getText()));
                    assetDTO.setStatus(statusComboBox.getSelectedItem().toString());
                    assetDTO.setEstadoConservacao(estadoConservacaoComboBox.getSelectedItem().toString());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date entradaDate = dateFormat.parse(dataEntradaField.getText());
                        assetDTO.setEntrada(new java.sql.Date(entradaDate.getTime()));
                        Date cargaDate = dateFormat.parse(dataCargaField.getText());
                        assetDTO.setCarga(new java.sql.Date(cargaDate.getTime()));
                    } catch (Exception e2) {
                        System.out.println("Erro no parse da data: " + e2);
                        JOptionPane.showMessageDialog(null, "Data inválida.");
                    }
                    UpdateAsset updateAsset = new UpdateAsset();
                    updateAsset.execute(assetDTO);
                }
            }
        });

        frame.add(splitPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static boolean checkNull() {
        if (statusComboBox.getSelectedItem() == null || estadoConservacaoComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione o status e o estado de conservação.");
            return true;
        }

        if (valorAquisicaoField.getText().trim().isEmpty() || valorDepreciadoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha os campos de valor.");
            return true;
        }

        if (descricaoArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha a descrição.");
            return true;
        }

        if (selectedCampusId == null || selectedDocumentId == null || selectedLocationId == null || selectedDepartmentId == null || selectedSupplierId == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione todos os campos.");
            return true;
        }

        return false;
    }

    private static JScrollPane createScrollPane(String title, Object[] array, String type) {
        JList<Object> list = new JList<>();
        DefaultListModel<Object> listModel = new DefaultListModel<>();

        for (Object item : array) {
            listModel.addElement(item);
        }
        list.setModel(listModel);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<Object> source = (JList<Object>) e.getSource();
                Object selectedValue = source.getSelectedValue();

                if (selectedValue != null) {
                    switch (type) {
                        case "Campus":
                            selectedCampusId = ((CampusDTO) selectedValue).getId();
                            break;
                        case "Document":
                            selectedDocumentId = ((DocumentDTO) selectedValue).getId();
                            break;
                        case "Location":
                            selectedLocationId = ((LocationDTO) selectedValue).getId();
                            break;
                        case "Department":
                            selectedDepartmentId = ((ResponsibleDepartmentDTO) selectedValue).getId();
                            break;
                        case "Supplier":
                            selectedSupplierId = ((SupplierDTO) selectedValue).getId();
                            break;
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder(title));
        return scrollPane;
    }


    public static void main(Long assetId){
        initAsset(assetId);
    }
}
