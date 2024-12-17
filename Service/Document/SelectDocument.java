package Service.Document;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Credentials.Credentials;
import DTO.DocumentDTO;

public class SelectDocument {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();
    public List<DocumentDTO> execute() {
        List<DocumentDTO> documents = new ArrayList<>();

        String query = "SELECT id, numeroNotaFiscal FROM Document";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                DocumentDTO document = new DocumentDTO();
                document.setDocumentName(rs.getString("numeroNotaFiscal"));
                document.setId(rs.getLong("id"));
                documents.add(document);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }
}
