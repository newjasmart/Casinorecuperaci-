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
public class EmpleatController implements Initializable {

    @FXML
    private TableView<Empleat> Taula_Empleat;
    @FXML
    private TableColumn<Empleat, String> Empleat_Nom;
    @FXML
    private TableColumn<Empleat, String> Empleat_DNI;
    @FXML
    private TableColumn<Empleat, Integer> Empleat_Posició;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadEmpleatTable();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }    
    
    @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        Empleat selectedItem = Taula_Empleat.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Modificació");
            alert.setHeaderText(null);
            alert.setContentText("Esta segur de voler modificar aquest empleat?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected item from the table
                Taula_Empleat.getItems().remove(selectedItem);

                // Delete the item from the database
                deleteEmpleatFromDatabase(selectedItem);

                // Load secondary.fxml with selected item data
                loadSecondaryScene(selectedItem);
            }
        } else {
            // Show error message
            UtilsGUI.showAlert("Cap empleat seleccionat", "Siusplau selecciona l'empleat que vols modificar", Alert.AlertType.ERROR);
        }
    }

    private void deleteEmpleatFromDatabase(Empleat empleat) {
        try (Connection connection = SQLDBConnector.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM Empleat WHERE dni = '" + empleat.getDni() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            UtilsGUI.showAlert("Error", "No s'ha pogut eliminar l'empleat de la base de dades", Alert.AlertType.ERROR);
        }
    }

    
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        Empleat selectedItem = Taula_Empleat.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Eliminació");
            alert.setHeaderText(null);
            alert.setContentText("Esta segur de voler eliminar aquest empleat?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteEmpleat(selectedItem);
            }
        } else {
            UtilsGUI.showAlert("Cap empleat seleccionat", "Siusplau selecciona l'empleat que vols eliminar",Alert.AlertType.ERROR);
        }
    }
    
    private void deleteEmpleat(Empleat empleat) {
        deleteEmpleatFromDatabase(empleat);
        Taula_Empleat.getItems().remove(empleat);
        
    }
    
    private void loadSecondaryScene(Empleat empleat) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();

            // Get the controller and set the item data
            SecondaryController controller = loader.getController();
            controller.setEmpleatData(empleat);

            Stage stage = (Stage) Taula_Empleat.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            UtilsGUI.showAlert("Error", "No s'ha pogut modificar l'empleat",Alert.AlertType.ERROR);
        }
    }
    
    private void loadEmpleatTable() throws IOException, SQLException {


        // Load data into TableView
        ObservableList<Empleat> empleats = FXCollections.observableArrayList();
        try (Connection connection = SQLDBConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM empleat")) {

            while (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                String dni = resultSet.getString("dni");
                Integer Posicio = resultSet.getInt("Posicio");
                empleats.add(new Empleat(nom, dni, Posicio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Empleat_Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Empleat_DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        Empleat_Posició.setCellValueFactory(new PropertyValueFactory<>("posicio"));

        Taula_Empleat.setItems(empleats);
    }    
    
}
