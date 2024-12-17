package Credentials;

public class Credentials {
    static String url = "jdbc:mysql://localhost:3306/fdb";
    static String user = "root";
    static String password = "admin";

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
