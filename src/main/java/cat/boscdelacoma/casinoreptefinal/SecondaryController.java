package cat.boscdelacoma.casinoreptefinal;

import static cat.boscdelacoma.casinoreptefinal.MySQLDatabase.getConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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
        ObservableList<String> options
                = FXCollections.observableArrayList(
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
                try {
                    int puntsFidelitat = Integer.parseInt(txt_punts_posicio.getText());
                    addClientData(nomClient, clientDNI, puntsFidelitat);
                } catch (NumberFormatException ex) {
                    UtilsGUI.showAlert("Error", ex.getMessage(), AlertType.ERROR);
                }
                break;
            case "Joc":
                // Add joc data to the database
                String nomJoc = txt_nom.getText();
                String tipusJoc = txt_tipus_dni.getText();
                try {
                addJocData(nomJoc, tipusJoc);
                } catch (NumberFormatException ex) {
                    UtilsGUI.showAlert("Error", ex.getMessage(), AlertType.ERROR);
                }
                break;
            case "Empleat":
                // Add empleat data to the database
                String nomEmpleat = txt_nom.getText();
                String empleatDNI = txt_tipus_dni.getText();
                try {
                int Posicio = Integer.parseInt(txt_punts_posicio.getText());
                addEmpleatData(nomEmpleat, empleatDNI, Posicio);
                } catch (NumberFormatException ex) {
                    UtilsGUI.showAlert("Error", ex.getMessage(), AlertType.ERROR);
                }
                break;
            default:
                System.out.println("Please select a table");
                break;
        }
    }

    public static void addClientData(String nomClient, String clientDNI, Integer PuntsFidelitat) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO client (Nom, DNI, PuntsFidelitat) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, nomClient);
                pstmt.setString(2, clientDNI);
                pstmt.setInt(3, PuntsFidelitat);
                pstmt.executeUpdate();
                UtilsGUI.showAlert("Afegir client", "Afegit ", AlertType.INFORMATION);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addJocData(String nomJoc, String tipusJoc) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO joc (Nom, Tipus) VALUES (?,?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, nomJoc);
                pstmt.setString(2, tipusJoc);
                pstmt.executeUpdate();
                UtilsGUI.showAlert("Afegir joc", "Afegit ", AlertType.INFORMATION);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    private void addEmpleatData(String nomEmpleat, String empleatDNI, int Posicio) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO empleat (Nom, DNI, Posicio) VALUES (?,?,?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, nomEmpleat);
                pstmt.setString(2, empleatDNI);
                pstmt.setInt(3, Posicio);
                pstmt.executeUpdate();
                UtilsGUI.showAlert("Afegir empleat", "Afegit ", AlertType.INFORMATION);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Empleat data added: " + nomEmpleat + ", " + empleatDNI + ", " + Posicio);
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
                txt_punts_posicio.setVisible(true); // or setDisable(true)

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
                txt_punts_posicio.setPromptText("Posicio");
                txt_punts_posicio.setVisible(true); // or setDisable(true)
                
                break;
            default:
                System.out.println("Please select a table");
                break;
        }
    }
}
