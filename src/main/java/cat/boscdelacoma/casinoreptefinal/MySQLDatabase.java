package cat.boscdelacoma.casinoreptefinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDatabase {
    private SQLDBConnector dbConnector;

    public MySQLDatabase() {
        dbConnector = new SQLDBConnector();
    }

    public static void addClientData(String nomClient, String tipusDNI) {
        try (Connection connection = DriverManager.getConnection(db.url, db.user, db.password)) {
            String query = "INSERT INTO client (Nom, DNI, Punts de Fidelitat) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomClient);
                preparedStatement.setString(2, tipusDNI);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addJocData(String nomJoc, String tipusJoc) {
        try (Connection connection = DriverManager.getConnection(db.url, db.user, db.password)) {
            String query = "INSERT INTO joc (Nom, Tipus) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomJoc);
                preparedStatement.setString(2, tipusJoc);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addEmpleatData(String nomEmpleat, String puntsPosicio) {
        try (Connection connection = DriverManager.getConnection(db.url, db.user, db.password)) {
            String query = "INSERT INTO empleat (Nom, Punts_Posicio) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomEmpleat);
                preparedStatement.setString(2, puntsPosicio);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}