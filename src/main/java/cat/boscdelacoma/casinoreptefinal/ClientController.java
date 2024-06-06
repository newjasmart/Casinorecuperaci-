package cat.boscdelacoma.casinoreptefinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientController {

    @FXML
    private Button btn_mysql;

    @FXML
    private Button btn_objectdb;

    @FXML
    private TableView<Client> Taula_Client;

    @FXML
    private TableColumn<Client, String> Client_Nom;

    @FXML
    private TableColumn<Client, String> Client_DNI;

    @FXML
    private TableColumn<Client, Integer> Client_Punts_Fidelitat;

    private ObservableList<Client> clientData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Client_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Client_DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        Client_Punts_Fidelitat.setCellValueFactory(new PropertyValueFactory<>("puntsFidelitat"));

        Taula_Client.setItems(clientData);
    }

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
}