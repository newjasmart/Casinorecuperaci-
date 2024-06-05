package cat.boscdelacoma.casinoreptefinal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PrimaryController {

    @FXML
    private Button btn_mysql;

    @FXML
    private Button btn_objectdb;

    @FXML
    private TextField txt_user;

    @FXML
    private PasswordField txt_password;

    private String selectedDatabase;

    @FXML
    private void initialize() {
        btn_mysql.setOnAction(event -> handleDatabaseSelection("MySQL"));
        btn_objectdb.setOnAction(event -> handleDatabaseSelection("ObjectDB"));
    }

    private void handleDatabaseSelection(String database) {
        selectedDatabase = database;
        showAlert(AlertType.INFORMATION, "Database Selection", "Selected database: " + selectedDatabase);
    }

    @FXML
    private void mostrarInformacio() {
        String username = txt_user.getText();
        String password = txt_password.getText();

        if (selectedDatabase == null || username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error!", "Please enter all details and select a database");
            return;
        }

        boolean loginSuccessful = false;

        if ("MySQL".equals(selectedDatabase)) {
            loginSuccessful = authenticateMySQL(username, password);
        } else if ("ObjectDB".equals(selectedDatabase)) {
            loginSuccessful = authenticateObjectDB(username, password);
        }

        if (loginSuccessful) {
            // Proceed to the next page
            showAlert(AlertType.INFORMATION, "Login Successful!", "Welcome " + username);
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid credentials or database connection error.");
        }
    }

    private boolean authenticateMySQL(String username, String password) {
        // Implement MySQL authentication logic
        // This might involve connecting to a MySQL database and validating the credentials
        // Return true if authentication is successful, false otherwise
        return false; // Placeholder
    }

    private boolean authenticateObjectDB(String username, String password) {
        // Implement ObjectDB authentication logic
        // This might involve connecting to an ObjectDB database and validating the credentials
        // Return true if authentication is successful, false otherwise
        return false; // Placeholder
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


/*package cat.boscdelacoma.casinoreptefinal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class PrimaryController {
    
     ObservableList<Empleat> empleatList = FXCollections.observableArrayList();
    ObservableList<Client> clientList = FXCollections.observableArrayList();
    ObservableList<Joc> jocList = FXCollections.observableArrayList();
    
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
    private ImageView img;

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

    @FXML
    private void switchToSecondary() throws IOException {
        CasinoApp.setRoot("secondary");
    }
    
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

            empleatList.addAll(
                new Empleat("Tim", "12345678C", 1),
                new Empleat("Guillem", "22423478K",2),
                new Empleat("Jona", "58296591H",3),
                new Empleat("Xino", "KNJCJWLJ0",4),
                new Empleat("Romano", "12ROBA34", 5)
            );

            clientList.addAll(
                new Client("Marc", "12345678C", 1),
                new Client("Sergi", "22423478K",2),
                new Client("Josep-Maria", "58296591H",3),
                new Client("Joan", "KNJCJWLJ0",4),
                new Client("Romano", "12ROBA34", 5)
            );

            jocList.addAll(
                new Joc("blackjack","cartes"),
                new Joc("poker", "cartes"),
                new Joc("ruleta", "ruleta"),
                new Joc("daus", "daus")
            );

            Taula_Client.setItems(clientList);
            Taula_Empleat.setItems(empleatList);
            Taula_Joc.setItems(jocList);
        } catch (Exception e) {
            System.err.println("Error initializing table views: " + e.getMessage());
        }
    }
}

*/