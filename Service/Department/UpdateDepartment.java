package Service.Department;

import Credentials.Credentials;
import DTO.ResponsibleDepartmentDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateDepartment {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(ResponsibleDepartmentDTO responsibleDepartmentDTO) {
        String sql = "UPDATE ResponsibleDepartment SET nome = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, responsibleDepartmentDTO.getResponsibleDepartmentName());
            preparedStatement.setLong(2, responsibleDepartmentDTO.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("SUCESSO!");
            }

        } catch (Exception e) {
            System.out.println("ERRO AO ATUALIZAR SETOR: " + e);
        }
    }
}
