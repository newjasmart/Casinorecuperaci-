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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
            loadClientTable();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        Client selectedItem = Taula_Client.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Load secondary.fxml with selected item data
            loadSecondaryScene(selectedItem);
        } else {
            // Show error message
            UtilsGUI.showAlert("Cap entrada seleccionada", "Siusplau selecciona l'entrada que vols modificar",Alert.AlertType.ERROR);
        }
    } 
    
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        Client selectedItem = Taula_Client.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Eliminació");
            alert.setHeaderText(null);
            alert.setContentText("Esta segur de voler eliminar aquesta entrada?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteClient(selectedItem);
            }
        } else {
            UtilsGUI.showAlert("Cap entrada seleccionada", "Siusplau selecciona l'entrada que vols eliminar",Alert.AlertType.ERROR);
        }
    }
    
    private void deleteClient(Client client) {
        
        Taula_Client.getItems().remove(client);
        
    }
    
    private void loadSecondaryScene(Client client) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        // Get the controller and set the item data
        SecondaryController controller = loader.getController();
        controller.setClientData(client);

        Stage stage = (Stage) Taula_Client.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
        UtilsGUI.showAlert("Error", "No s'ha pogut modificar l'entrada",Alert.AlertType.ERROR);
    }
}
    
    private void loadClientTable() throws IOException, SQLException {


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
