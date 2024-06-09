package cat.boscdelacoma.casinoreptefinal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.stage.Stage;

public class SecondaryController {
    
    @FXML
    private Button btnVeureTaulaEmpleat;
    
    @FXML
    private Button btnVeureTaulaClient;
    
    @FXML
    private Button btnVeureTaulaJoc;
    
    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_tipus_dni;

    @FXML
    private TextField txt_punts_posicio;

    @FXML
    private ComboBox<String> combobox_taules;
    
    @FXML
    private Button btn_crear;
    
    private void openNewWindow(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    

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
    private void handleCrearButtonAction(ActionEvent event) {
        // Handle Crear button click event
        String selectedTable = combobox_taules.getSelectionModel().getSelectedItem();
        switch (selectedTable) {
            case "Client":
                // Add client data to the database
                String nomClient = txt_nom.getText();
                String clientDNI = txt_tipus_dni.getText();
                String puntsFidelitat = txt_punts_posicio.getText();
                addClientData(nomClient, clientDNI, puntsFidelitat);
                break;
            case "Joc":
                // Add joc data to the database
                String nomJoc = txt_nom.getText();
                String tipusJoc = txt_tipus_dni.getText();
                addJocData(nomJoc, tipusJoc);
                break;
            case "Empleat":
                // Add empleat data to the database
                String nomEmpleat = txt_nom.getText();
                String empleatDNI = txt_tipus_dni.getText();
                String puntsPosicio = txt_punts_posicio.getText();
                addEmpleatData(nomEmpleat, empleatDNI, puntsPosicio);
                break;
            default:
                System.out.println("Please select a table");
                break;
        }
    }

    private void addClientData(String nomClient, String clientDNI, String puntsFidelitat) {
        try (Connection connection = DriverManager.getConnection(db.url, db.user, db.password)) {
            String query = "INSERT INTO client (Nom, DNI, PuntsFidelitat) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomClient);
                preparedStatement.setString(2, clientDNI);
                preparedStatement.setString(3, puntsFidelitat);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Client data added: " + nomClient + ", " + clientDNI + ", " + puntsFidelitat);
    }

    private void addJocData(String nomJoc, String tipusJoc) {
        try (Connection connection = DriverManager.getConnection(db.url, db.user, db.password)) {
            String query = "INSERT INTO joc (Nom, Tipus) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomJoc);
                preparedStatement.setString(2, tipusJoc);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Joc data added: " + nomJoc + ", " + tipusJoc);
    }

    private void addEmpleatData(String nomEmpleat, String empleatDNI, String puntsPosicio) {
        try (Connection connection = DriverManager.getConnection(db.url, db.user, db.password)) {
            String query = "INSERT INTO empleat (Nom, DNI, PuntsPosicio) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomEmpleat);
                preparedStatement.setString(2, empleatDNI);
                preparedStatement.setString(3, puntsPosicio);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Empleat data added: " + nomEmpleat + ", " + empleatDNI + ", " + puntsPosicio);
    }
    
    @FXML
    private void handleComboBoxAction(ActionEvent event) {
        // Handle ComboBox selection event
        String selectedTable = combobox_taules.getSelectionModel().getSelectedItem();
        switch (selectedTable) {
            case "Client":
                // Clear text fields and set prompts for Client table
                txt_nom.setPromptText("Nom Client");
                txt_tipus_dni.setPromptText("DNI Client");
                txt_punts_posicio.setPromptText("Punts Fidelitat");
                break;
            case "Joc":
                // Clear text fields and set prompts for Joc table
                txt_nom.setPromptText("Nom Joc");
                txt_tipus_dni.setPromptText("Tipus Joc");
                txt_punts_posicio.setPromptText("");
                txt_punts_posicio.setVisible(false); // or setDisable(true)
                break;
            case "Empleat":
                // Clear text fields and set prompts for Empleat table
                txt_nom.setPromptText("Nom Empleat");
                txt_tipus_dni.setPromptText("DNI Empleat");
                txt_punts_posicio.setPromptText("Punts Posicio");
                break;
            default:
                System.out.println("Please select a table");
                break;
        }
    }
}