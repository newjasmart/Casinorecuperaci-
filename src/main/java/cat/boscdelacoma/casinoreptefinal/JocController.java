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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class JocController implements Initializable {

    @FXML
    private TableView<Joc> Taula_Joc;
    @FXML
    private TableColumn<Joc, String> Joc_nom;
    @FXML
    private TableColumn<Joc, String> Joc_Tipus;

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
    
        @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        Joc selectedItem = Taula_Joc.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Modificació");
            alert.setHeaderText(null);
            alert.setContentText("Esta segur de voler modificar aquest Joc?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected item from the table
                Taula_Joc.getItems().remove(selectedItem);

                // Delete the item from the database
                deleteJocFromDatabase(selectedItem);

                // Load secondary.fxml with selected item data
                loadSecondaryScene(selectedItem);
            }
        } else {
            // Show error message
            UtilsGUI.showAlert("Cap joc seleccionat", "Siusplau selecciona el joc que vols modificar", Alert.AlertType.ERROR);
        }
    }

    private void deleteJocFromDatabase(Joc joc) {
        try (Connection connection = SQLDBConnector.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM Joc WHERE Nom = '" + joc.getNom() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            UtilsGUI.showAlert("Error", "No s'ha pogut eliminar el joc de la base de dades", Alert.AlertType.ERROR);
        }
    }

    
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        Joc selectedItem = Taula_Joc.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Eliminació");
            alert.setHeaderText(null);
            alert.setContentText("Esta segur de voler eliminar aquest joc?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteJoc(selectedItem);
            }
        } else {
            UtilsGUI.showAlert("Cap joc seleccionat", "Siusplau selecciona eljoc que vols eliminar",Alert.AlertType.ERROR);
        }
    }
    
    private void deleteJoc(Joc joc) {
        deleteJocFromDatabase(joc);
        Taula_Joc.getItems().remove(joc);
        
    }
    
    private void loadSecondaryScene(Joc joc) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();

            // Get the controller and set the item data
            SecondaryController controller = loader.getController();
            controller.setJocData(joc);

            Stage stage = (Stage) Taula_Joc.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            UtilsGUI.showAlert("Error", "No s'ha pogut modificar el joc",Alert.AlertType.ERROR);
        }
    }
    
    private void loadJocTable() throws IOException, SQLException {


        // Load data into TableView
        ObservableList<Joc> jocs = FXCollections.observableArrayList();
        try (Connection connection = SQLDBConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM joc")) {

            while (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                String tipus = resultSet.getString("Tipus");
                jocs.add(new Joc(nom, tipus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Joc_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Joc_Tipus.setCellValueFactory(new PropertyValueFactory<>("tipus"));

        Taula_Joc.setItems(jocs);
    } 
}
