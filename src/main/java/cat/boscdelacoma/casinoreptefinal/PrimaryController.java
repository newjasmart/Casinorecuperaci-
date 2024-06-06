package cat.boscdelacoma.casinoreptefinal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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