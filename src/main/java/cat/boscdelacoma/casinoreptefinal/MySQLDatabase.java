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

public class MySQLDatabase {
    
    private static final Properties pro = loadConfig();

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.user"), pro.getProperty("db.password"));
    }
    

    public MySQLDatabase() {
        dbConnector = new SQLDBConnector();
    }
    private SQLDBConnector dbConnector;
    
    public static void addClientData(String nomClient, String clientDNI, Integer puntsFidelitat) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO client (Nom, DNI, Punts de Fidelitat) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, nomClient);
                pstmt.setString(2, clientDNI);
                pstmt.setInt(3, puntsFidelitat);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addJocData(String nomJoc, String tipusJoc) {
       try (Connection connection = getConnection()) {
            String sql = "INSERT INTO client (Nom, tipus) VALUES (?, ?,)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, nomJoc);
                pstmt.setString(2, tipusJoc);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addEmpleatData(String nomEmpleat, String EmpleatDNI, Integer posicio) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO client (Nom, DNI, posicio) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, nomEmpleat);
                pstmt.setString(2, EmpleatDNI);
                pstmt.setInt(3, posicio);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static Properties loadConfig() {
        Properties pro = new Properties();
        try (DataInputStream input = new DataInputStream(new FileInputStream("config.properties"))) {
            pro.load(input);
        } catch (FileNotFoundException ex) {
            System.out.println("Config file not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error reading config file: " + ex.getMessage());
        }
        return pro;
    }
}