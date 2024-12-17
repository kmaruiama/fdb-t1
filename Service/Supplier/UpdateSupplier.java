package Service.Supplier;

import Credentials.Credentials;
import DTO.SupplierDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSupplier {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(SupplierDTO supplierDTO) {
        String sql = "UPDATE Supplier SET nome = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, supplierDTO.getSupplierName());
            statement.setLong(2, supplierDTO.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("SUCESSO!");
            }
        } catch (SQLException e) {
            System.out.println("ERRO AO ATUALIZAR O FORNECEDOR NO BANCO DE DADOS: " + e);
        }
    }
}
