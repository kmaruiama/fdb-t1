package Service.Campus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Credentials.Credentials;
import DTO.CampusDTO;

public class SelectCampus {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public List<CampusDTO> execute() {
        List<CampusDTO> campusList = new ArrayList<>();

        String query = "SELECT id, nome FROM Campus";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                CampusDTO campus = new CampusDTO();
                campus.setCampusName(rs.getString("nome"));
                campus.setId(rs.getLong("id"));
                campusList.add(campus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campusList;
    }
}
