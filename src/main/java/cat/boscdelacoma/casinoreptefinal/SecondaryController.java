package cat.boscdelacoma.casinoreptefinal;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML
    private Button btn_mysql;

    @FXML
    private Button btn_objectdb;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_tipus_dni;

    @FXML
    private TextField txt_punts_posicio;

    @FXML
    private ComboBox<String> combobox_taules;

    @FXML
    private void initialize() {
        ObservableList<String> options = 
            FXCollections.observableArrayList(
                "Client",
                "Joc",
                "Empleat"
            );
        combobox_taules.setItems(options);
    }

    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_crear;

    @FXML
    private ImageView img;

    @FXML
    private void handleMySQLButtonAction(ActionEvent event) {
        // Handle MySQL button click event
        System.out.println("MySQL button clicked");
    }

    @FXML
    private void handleObjectDBButtonAction(ActionEvent event) {
        // Handle ObjectDB button click event
        System.out.println("ObjectDB button clicked");
    }

    @FXML
    private void handleEliminarButtonAction(ActionEvent event) {
        // Handle Eliminar button click event
        System.out.println("Eliminar button clicked");
    }

    @FXML
    private void handleEditarButtonAction(ActionEvent event) {
        // Handle Editar button click event
        System.out.println("Editar button clicked");
    }

    @FXML
    private void handleCrearButtonAction(ActionEvent event) {
        // Handle Crear button click event
        System.out.println("Crear button clicked");
    }

    @FXML
    private void handleComboBoxAction(ActionEvent event) {
        // Handle ComboBox selection event
        String selectedTable = combobox_taules.getSelectionModel().getSelectedItem();
        System.out.println("Selected table: " + selectedTable);
    }
    
    
    @FXML
    private Button btnVeureTaulaEmpleat;
    @FXML
    private Button btnVeureTaulaClient;
    @FXML
    private Button btnVeureTaulaJoc;

    @FXML
    public void handleButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String fxmlFile = "";

        if (button == btnVeureTaulaEmpleat) {
            fxmlFile = "taulaempleat.fxml";
        } else if (button == btnVeureTaulaClient) {
            fxmlFile = "taulaclient.fxml";
        } else if (button == btnVeureTaulaJoc) {
            fxmlFile = "taulajoc.fxml";
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}