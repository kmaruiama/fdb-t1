package Service.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Credentials.Credentials;
import DTO.ResponsibleDepartmentDTO;

public class SelectDepartment {
    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();
    public List<ResponsibleDepartmentDTO> execute() {
        List<ResponsibleDepartmentDTO> departmentList = new ArrayList<>();

        String query = "SELECT id, nome FROM ResponsibleDepartment";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                ResponsibleDepartmentDTO department = new ResponsibleDepartmentDTO();
                department.setResponsibleDepartmentName(rs.getString("nome"));
                department.setId(rs.getLong("id"));
                departmentList.add(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }
}
