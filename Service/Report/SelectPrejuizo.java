package Service.Report;

import Credentials.Credentials;
import DTO.PrejuizoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectPrejuizo {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public List<PrejuizoDTO> getPrejuizoList() {
        List<PrejuizoDTO> prejuizoList = new ArrayList<>();
        String query = "SELECT data_calculo, prejuizoTotal FROM PrejuizoTotal";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Date dataCalculo = resultSet.getDate("data_calculo");
                float prejuizoTotal = resultSet.getFloat("prejuizoTotal");
                prejuizoList.add(new PrejuizoDTO(dataCalculo, prejuizoTotal));
            }
        } catch (Exception e) {
            System.out.println("ERRO AO RETORNAR O LOG DE PREJUÍZOS");
        }
        return prejuizoList;
    }
}
