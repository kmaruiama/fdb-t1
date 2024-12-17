package Service.Location;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Credentials.Credentials;
import DTO.LocationDTO;

public class SelectLocation {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();
    public List<LocationDTO> execute() {
        List<LocationDTO> LocationList = new ArrayList<>();
        String query = "SELECT id, sala FROM Location";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                LocationDTO location = new LocationDTO();
                location.setLocationName(rs.getString("sala"));
                location.setId(rs.getLong("id"));
                LocationList.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return LocationList;
    }
}

