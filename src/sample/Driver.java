package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    private static String userName = "root";
    private static String password = "root";
    private static String URL = "jdbc:mysql://localhost:3306/calcoperations_db";
    private static Connection connection;
    private static PreparedStatement statement;

    public Driver() {
        try {
            connection = DriverManager.getConnection(URL, userName, password);
            statement = connection.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLineToDB (String data) {
        try {
            String insertQuery = "INSERT INTO operations (line) VALUES ( ? )";
            statement = connection.prepareStatement(insertQuery);
            statement.setString(1, data);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLinesFromDB () {
        List<String> lines = new ArrayList<>();
        try {
            // get last 10 rows from DB
            ResultSet myResult = statement.executeQuery("select * from operations order by id desc limit 10");
            while (myResult.next()){
                lines.add(myResult.getString("line"));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

    public static void closeConnections () {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
