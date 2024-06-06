package cat.boscdelacoma.casinoreptefinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDatabase {
    private SQLDBConnector dbConnector;

    public MySQLDatabase() {
        dbConnector = new SQLDBConnector();
    }

    public void inserirClient(Client client) {
        String sql = "INSERT INTO clients(nom, dni, puntsFidelitat) VALUES(?,?,?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getDni());
            pstmt.setInt(3, client.getPuntsFidelitat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Client> obtenirClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Client client = new Client(rs.getString("nom"), rs.getString("dni"), rs.getInt("puntsFidelitat"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }

    public void actualitzarClient(Client client) {
        String sql = "UPDATE clients SET nom =?, puntsFidelitat =? WHERE dni =?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getNom());
            pstmt.setInt(2, client.getPuntsFidelitat());
            pstmt.setString(3, client.getDni());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarClient(String dni) {
        String sql = "DELETE FROM clients WHERE dni =?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserirJoc(Joc joc) {
        String sql = "INSERT INTO jocs(nom, tipus) VALUES(?,?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, joc.getNom());
            pstmt.setString(2, joc.getTipus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarJoc(String nomJoc) {
        String sql = "DELETE FROM jocs WHERE nom =?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomJoc);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Joc> obtenirJocs() {
        List<Joc> jocs = new ArrayList<>();
        String sql = "SELECT * FROM jocs";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Joc joc = new Joc(rs.getString("nom"), rs.getString("tipus"));
                jocs.add(joc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jocs;
    }
}