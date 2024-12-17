package Service.Asset;

import DTO.AssetDTO;
import Credentials.Credentials;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectAsset {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public List<AssetDTO> execute() {
        List<AssetDTO> assetList = new ArrayList<>();
        String sql = "SELECT id, status, descricao, valorAquisicao, valorDepreciado, estadoConservacao, " +
                "dataEntrada, dataCarga, departamentoResponsavel_id, campus_id, fornecedor_id, sala_id, notaFiscal_id " +
                "FROM Asset";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                AssetDTO assetDTO = new AssetDTO();
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
                assetList.add(assetDTO);
            }
        } catch (Exception e) {
            System.out.println("ERRO AO RETORNAR A LISTA DE EQUIPAMENTOS: " + e);
        }
        return assetList;
    }
}
