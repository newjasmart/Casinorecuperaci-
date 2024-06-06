package cat.boscdelacoma.casinoreptefinal;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SQLDBConnector {

    private static final Properties prop = loadConfig();

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
    }

    static Properties loadConfig() {
        Properties prop = new Properties();
        try (DataInputStream input = new DataInputStream(new FileInputStream("config.properties"))) {
            prop.load(input);
        } catch (FileNotFoundException ex) {
            System.out.println("Config file not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error reading config file: " + ex.getMessage());
        }
        return prop;
    }
}