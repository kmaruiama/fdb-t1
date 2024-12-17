package Service.Document;

import Credentials.Credentials;
import DTO.DocumentDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateDocument {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(DocumentDTO documentDTO) {
        String sql = "UPDATE Document SET numeroNotaFiscal = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, documentDTO.getDocumentName());
            preparedStatement.setLong(2, documentDTO.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("SUCESSO!");
            }

        } catch (Exception e) {
            System.out.println("ERRO AO ATUALIZAR DOCUMENTO: " + e);
        }
    }
}
