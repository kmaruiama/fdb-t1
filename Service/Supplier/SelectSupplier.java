package Service.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Credentials.Credentials;
import DTO.SupplierDTO;

public class SelectSupplier {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();
    public List<SupplierDTO> execute() {
        List<SupplierDTO> supplierList = new ArrayList<>();
        String query = "SELECT id, nome FROM Supplier";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierName(rs.getString("nome"));
                supplier.setId(rs.getLong("id"));
                supplierList.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }
}
