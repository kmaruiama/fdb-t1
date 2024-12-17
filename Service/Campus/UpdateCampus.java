package Service.Campus;

import Credentials.Credentials;
import DTO.CampusDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateCampus {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(CampusDTO campusDTO) {
        String sql = "UPDATE Campus SET nome = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, campusDTO.getCampusName());
            preparedStatement.setLong(2, campusDTO.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("SUCESSO!");
            }

        } catch (Exception e) {
            System.out.println("ERRO AO ATUALIZAR CAMPUS " + e);
        }
    }
}
