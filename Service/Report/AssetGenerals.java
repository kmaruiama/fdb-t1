package Service.Report;

import Credentials.Credentials;
import DTO.ViewGeneralsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssetGenerals {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public List<ViewGeneralsDTO> getAssetSummary() {
        List<ViewGeneralsDTO> assetSummaryList = new ArrayList<>();
        String query = "SELECT a.id AS AssetNumber, a.descricao AS Description, a.valorAquisicao AS AcquisitionValue, " +
                "d.nome AS Department, c.nome AS Campus FROM Asset a " +
                "LEFT JOIN ResponsibleDepartment d ON a.departamentoResponsavel_id = d.id " +
                "LEFT JOIN Campus c ON a.campus_id = c.id";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ViewGeneralsDTO ViewGeneralsDTO = new ViewGeneralsDTO();
                ViewGeneralsDTO.setAssetNumber(resultSet.getInt("AssetNumber"));
                ViewGeneralsDTO.setDescription(resultSet.getString("Description"));
                ViewGeneralsDTO.setAcquisitionValue(resultSet.getFloat("AcquisitionValue"));
                ViewGeneralsDTO.setDepartment(resultSet.getString("Department"));
                ViewGeneralsDTO.setCampus(resultSet.getString("Campus"));

                assetSummaryList.add(ViewGeneralsDTO);
            }
        } catch (Exception e) {
            System.out.println("ERRO AO CRIAR NOVA VIEW: " + e);
        }
        return assetSummaryList;
    }
}