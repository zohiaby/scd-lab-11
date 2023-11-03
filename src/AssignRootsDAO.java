

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssignRootsDAO {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/scd_lab_11";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	
	public void assignRootsToVerse(String rootName, String token) {

		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			java.sql.Statement statement = connection.createStatement();

			if (connection != null) {
				System.out.println("Connected to Database");

				String createBookSQLSquery = "INSERT INTO roots_assignmnets(root_name, token) VALUES('" + rootName
						+ "', '" + token + "')";
				int rowsAffected = statement.executeUpdate(createBookSQLSquery);

				System.out.println("Rows affected: " + rowsAffected);
			}

			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("Connection error: " + e.getMessage());
		}
	}

	
	
	public ArrayList<String> getTokensFromDB(){
		
		ArrayList<String> tokens = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			java.sql.Statement statement = connection.createStatement();
			
			String selectQuery = "SELECT token FROM tokenize_verse";
			
			ResultSet resultSet = statement.executeQuery(selectQuery);
			
			if (connection != null) {
				System.out.println("Connected to Database");
				
				int i = 0;
				while (resultSet.next()) {
					// Retrieve data from the result set
					String tokenWord = resultSet.getString("token");
					tokens.add(tokenWord);
					i++;
				}

				return tokens;
			}
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch(SQLException e) {
				System.err.println("Connection error: " + e.getMessage());
			}
		return tokens;
	}
	
	
	
	
	public ArrayList<String> getRootsFromDB(){
		
		ArrayList<String> roots = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			java.sql.Statement statement = connection.createStatement();
			
			String selectQuery = "SELECT root FROM roots";
			
			ResultSet resultSet = statement.executeQuery(selectQuery);
			
			if (connection != null) {
				System.out.println("Connected to Database");
				
				int i = 0;
				while (resultSet.next()) {
					// Retrieve data from the result set
					String rootName = resultSet.getString("root");
					roots.add(rootName);
					i++;
				}

				return roots;
			}
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch(SQLException e) {
				System.err.println("Connection error: " + e.getMessage());
			}
		return roots;
	}
	
	
	
	
	public void displayRootsWithTokens(){
		
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			java.sql.Statement statement = connection.createStatement();
			
			String selectQuery = "SELECT * FROM roots_assignment";
			
			ResultSet resultSet = statement.executeQuery(selectQuery);
			
			if (connection != null) {
				System.out.println("Connected to Database");
				
				int i = 0;
				while (resultSet.next()) {
					// Retrieve data from the result set
					String rootName = resultSet.getString("root_name");
					String token = resultSet.getString("token");
					
					System.out.println("Root_Name : " + rootName + " & Token : " + token);
					i++;
				}
			}
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch(SQLException e) {
				System.err.println("Connection error: " + e.getMessage());
			}
	}
}