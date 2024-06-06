package cat.boscdelacoma.casinoreptefinal;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryController {
    
    ObservableList<Empleat> empleatList = FXCollections.observableArrayList();
    ObservableList<Client> clientList = FXCollections.observableArrayList();
    ObservableList<Joc> jocList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Client_DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
            Client_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Client_Punts_Fidelitat.setCellValueFactory(new PropertyValueFactory<>("puntsFidelitat"));
            Empleat_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Empleat_DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
            Empleat_Posició.setCellValueFactory(new PropertyValueFactory<>("posicio"));
            Joc_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Joc_Tipus.setCellValueFactory(new PropertyValueFactory<>("tipus"));

            Taula_Client.setItems(clientList);
            Taula_Empleat.setItems(empleatList);
            Taula_Joc.setItems(jocList);
        } catch (Exception e) {
            System.err.println("Error initializing table views: " + e.getMessage());
        }
    }
    
    private void loadClientTable() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("path_to_your_fxml_file.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        // Load data into TableView
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try (Connection connection = SQLDBConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clients")) {

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String dni = resultSet.getString("dni");
                int puntsFidelitat = resultSet.getInt("punts_fidelitat");
                clients.add(new Client(nom, dni, puntsFidelitat));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Client_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Client_DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        Client_Punts_Fidelitat.setCellValueFactory(new PropertyValueFactory<>("puntsFidelitat"));

        Taula_Client.setItems(clients);
    }

    
    @FXML
    private TableView<Client> Taula_Client;

    @FXML
    private TableView<Empleat> Taula_Empleat;

    @FXML
    private TableView<Joc> Taula_Joc;
    
     @FXML
    private TableColumn<Persona, String> Client_DNI;

    @FXML
    private TableColumn<Persona, String> Client_Nom;

    @FXML
    private TableColumn<Persona, Integer> Client_Punts_Fidelitat;

    @FXML
    private TableColumn<Persona, String> Empleat_DNI;

    @FXML
    private TableColumn<Persona, String> Empleat_Nom;

    @FXML
    private TableColumn<Persona, Integer> Empleat_Posició;

    @FXML
    private TableColumn<Joc, String> Joc_Tipus;

    @FXML
    private TableColumn<Joc, String> Joc_nom;
    
    @FXML
    private Button  btn_crear;

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_login;
  
    @FXML
    private Button btn_mysql;

    @FXML
    private Button btn_objectdb;

    @FXML
    private ComboBox<?> combobox_taules;

    @FXML
    private AnchorPane panel_login;

    @FXML
    private AnchorPane panel_taules;

    @FXML
    private TextField txt_nom;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_punts_posicio;

    @FXML
    private TextField txt_tipus_dni;

    @FXML
    private Text txt_titol;

    @FXML
    private TextField txt_user;

    private String selectedDatabase;
    
    private Connection connection;

   @FXML
    public void handleDatabaseSelection(ActionEvent event) {
        Button button = (Button) event.getSource();
        String database = null;
        if (button.getId().equals("btn_mysql")) {
            database = "MySQL";
            selectedDatabase = database;
            connectToMySQL();
        } else if (button.getId().equals("btn_objectdb")) {
            database = "ObjectDB";
            selectedDatabase = database;
            // Implement ObjectDB connection logic
        }
        showAlert(AlertType.INFORMATION, "Database Selection", "Selected database: " + selectedDatabase);
    }
    
    private void connectToMySQL() {
        String username = txt_user.getText();
        String password = txt_password.getText();

        try {
            connection = SQLDBConnector.getConnection();
            showAlert(AlertType.INFORMATION, "Database Connection", "Connected to MySQL database");
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Database Connection", "Failed to connect to MySQL database: " + e.getMessage());
        }
    }
    
    @FXML
    private void switchToSecondary(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secondary.fxml"));
            AnchorPane secondaryPane = loader.load();
            Scene secondaryScene = new Scene(secondaryPane);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(secondaryScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}