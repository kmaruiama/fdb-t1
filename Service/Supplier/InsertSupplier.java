package Service.Supplier;
import Credentials.Credentials;
import DTO.SupplierDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSupplier {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(SupplierDTO supplierDTO) {

        String sql = "INSERT INTO Supplier (nome) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, supplierDTO.getSupplierName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("SUCESSO!");
            }

        } catch (SQLException e) {
            System.out.println("ERRO AO INSERIR NOVO FORNECEDOR NO BANCO DE DADOS: " + e);
        }
    }
}
