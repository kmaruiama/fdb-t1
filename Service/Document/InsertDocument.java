package Service.Document;

import DTO.DocumentDTO;
import Credentials.Credentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDocument {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(DocumentDTO documentDTO) {

        String sql = "INSERT INTO Document (numeroNotaFiscal) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, documentDTO.getDocumentName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("SUCESSO!");
            }

        } catch (SQLException e) {
            System.out.println("ERRO AO INSERIR NOVO DOCUMENTO NO BANCO DE DADOS: " + e);
        }
    }
}
