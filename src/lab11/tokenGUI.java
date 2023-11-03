package lab11;

import java.sql.*;

public class tokenGUI {

    private static final String URL = "jdbc:mysql://localhost:3306/scd_lab_11";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT VerseText FROM verses");

            while (resultSet.next()) {
                String verse = resultSet.getString("verse");
                String[] tokens = verse.split(" ");
                for (String token : tokens) {
                    System.out.println(token);
                }
            }

          
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}