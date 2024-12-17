package Service.Asset;

import DTO.AssetDTO;
import Credentials.Credentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectIndividualAsset {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public AssetDTO execute(Long assetId) {
        AssetDTO assetDTO = null;
        String sql = "SELECT id, status, descricao, valorAquisicao, valorDepreciado, estadoConservacao, " +
                "dataEntrada, dataCarga, departamentoResponsavel_id, campus_id, fornecedor_id, sala_id, notaFiscal_id " +
                "FROM Asset WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, assetId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    assetDTO = new AssetDTO();
                    assetDTO.setId(resultSet.getLong("id"));
                    assetDTO.setStatus(resultSet.getString("status"));
                    assetDTO.setDescricao(resultSet.getString("descricao"));
                    assetDTO.setValorAquisicao(resultSet.getFloat("valorAquisicao"));
                    assetDTO.setValorDepreciado(resultSet.getFloat("valorDepreciado"));
                    assetDTO.setEstadoConservacao(resultSet.getString("estadoConservacao"));
                    assetDTO.setEntrada(resultSet.getDate("dataEntrada"));
                    assetDTO.setCarga(resultSet.getDate("dataCarga"));
                    assetDTO.setDepartamentoResponsavelId(resultSet.getLong("departamentoResponsavel_id"));
                    assetDTO.setCampusId(resultSet.getLong("campus_id"));
                    assetDTO.setFornecedorId(resultSet.getLong("fornecedor_id"));
                    assetDTO.setSalaId(resultSet.getLong("sala_id"));
                    assetDTO.setDocumentId(resultSet.getLong("notaFiscal_id"));
                }
            }

        } catch (Exception e) {
            System.out.println("ERRO AO ENCONTRAR EQUIPAMENTO: " + e);
        }

        return assetDTO;
    }
}
