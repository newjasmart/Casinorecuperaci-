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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
