package Service.Location;

import Credentials.Credentials;
import DTO.LocationDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateLocation {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public void execute(LocationDTO locationDTO) {
        String sql = "UPDATE Location SET sala = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, locationDTO.getLocationName());
            preparedStatement.setLong(2, locationDTO.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("SUCESSO!");
            }
        } catch (Exception e) {
            System.out.println("ERRO AO ATUALIZAR SALA: " + e);
        }
    }
}
