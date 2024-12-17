package Service.Department;
import Credentials.Credentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteDepartment {

    Credentials credentials = new Credentials();
    String url = credentials.getUrl();
    String user = credentials.getUser();
    String password = credentials.getPassword();

    public boolean execute(Long departmentId) {
        String sql = "DELETE FROM ResponsibleDepartment WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, departmentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERRO AO EXCLUIR SETOR: " + e);
            return false;
        }
    }
}
