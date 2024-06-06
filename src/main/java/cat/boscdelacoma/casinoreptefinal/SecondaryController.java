package cat.boscdelacoma.casinoreptefinal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SecondaryController {
    
    private void openNewWindow(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private Button btnVeureTaulaEmpleat;
    @FXML
    private Button btnVeureTaulaClient;
    @FXML
    private Button btnVeureTaulaJoc;

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("handleButtonAction called");
        Button button = (Button) event.getSource();

        switch (button.getText()) {
            case "Veure Taula Empleat":
                openNewWindow("taulaempleat.fxml");
                break;
            case "Veure Taula Client":
                openNewWindow("taulaclient.fxml");
                break;
            case "Veure Taula Joc":
                openNewWindow("taulajoc.fxml");
                break;
            default:
                break;
        }
    }

    @FXML
    private TableView<Client> Taula_Client;
    
    @FXML
    private TableColumn<Client, String> Client_Nom;

    @FXML
    private TableColumn<Client, String> Client_DNI;

    @FXML
    private TableColumn<Client, Integer> Client_Punts_Fidelitat;

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
}