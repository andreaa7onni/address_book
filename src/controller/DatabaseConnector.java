package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseConnector {
	private static Connection connection;

	public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
		if (connection == null) {
			Properties props = new Properties();
			try (InputStream input = new FileInputStream("credenziali_database.properties")) {
				props.load(input);
			}

			String url = "jdbc:mysql://" + props.getProperty("ip-server-mysql") + ":" + props.getProperty("porta")
					+ "/rubrica";
			String username = props.getProperty("username");
			String password = props.getProperty("password");

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}

}
