package Service.Department;

import DTO.ResponsibleDepartmentDTO;
import Credentials.Credentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDepartment {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(ResponsibleDepartmentDTO responsibleDepartmentDTO) {

        String sql = "INSERT INTO ResponsibleDepartment (nome) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, responsibleDepartmentDTO.getResponsibleDepartmentName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("SUCESSO!");
            }

        } catch (SQLException e) {
            System.out.println("ERRO AO INSERIR NOVO SETOR NO BANCO DE DADOS: " + e);
        }
    }
}
