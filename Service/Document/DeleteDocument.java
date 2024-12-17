package Service.Document;

import Credentials.Credentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteDocument {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public boolean execute(Long documentId) {
        String sql = "DELETE FROM Document WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, documentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERRO AO EXCLUIR DOCUMENTO: " + e);
            return false;
        }
    }
}
