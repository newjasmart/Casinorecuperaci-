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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
            loadJocTable();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }    
    
    private void loadJocTable() throws IOException, SQLException {


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
