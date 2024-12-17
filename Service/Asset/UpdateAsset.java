package Service.Asset;

import DTO.AssetDTO;
import Credentials.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAsset {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(AssetDTO assetDTO) {

        String sql = "UPDATE Asset SET status = ?, descricao = ?, valorAquisicao = ?, valorDepreciado = ?, " +
                "estadoConservacao = ?, departamentoResponsavel_id = ?, campus_id = ?, fornecedor_id = ?, " +
                "sala_id = ?, notaFiscal_id = ?, dataEntrada = ?, dataCarga = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, assetDTO.getStatus());
            statement.setString(2, assetDTO.getDescricao());
            statement.setFloat(3, assetDTO.getValorAquisicao());
            statement.setFloat(4, assetDTO.getValorDepreciado());
            statement.setString(5, assetDTO.getEstadoConservacao());
            statement.setLong(6, assetDTO.getDepartamentoResponsavelId());
            statement.setLong(7, assetDTO.getCampusId());
            statement.setLong(8, assetDTO.getFornecedorId());
            statement.setLong(9, assetDTO.getSalaId());
            statement.setLong(10, assetDTO.getDocumentId());
            statement.setDate(11, assetDTO.getEntrada());
            statement.setDate(12, assetDTO.getCarga());
            statement.setLong(13, assetDTO.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("SUCESSO!");
            }

        } catch (SQLException e) {
            System.out.println("ERRO AO ATUALIZAR EQUIPAMENTO " + e);
        }
    }
}
