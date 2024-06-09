/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.casinoreptefinal;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pierre
 */
public class ClientController implements Initializable {

     @FXML
    private TableColumn<Client, String> Client_DNI;

    @FXML
    private TableColumn<Client, String> Client_Nom;

    @FXML
    private TableColumn<Client, Integer> Client_PuntsFidelitat;

    @FXML
    private TableView<Client> Taula_Client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadJocTable();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }    
    
    private void loadJocTable() throws IOException, SQLException {


        // Load data into TableView
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try (Connection connection = SQLDBConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Client")) {

            while (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                String dni = resultSet.getString("dni");
                Integer PuntsFidelitat = resultSet.getInt("PuntsFidelitat");
                clients.add(new Client(nom, dni, PuntsFidelitat));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Client_Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Client_DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        Client_PuntsFidelitat.setCellValueFactory(new PropertyValueFactory<>("PuntsFidelitat"));

        Taula_Client.setItems(clients);
    }      
    
}
