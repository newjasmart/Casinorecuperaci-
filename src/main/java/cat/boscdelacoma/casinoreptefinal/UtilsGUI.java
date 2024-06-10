/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.casinoreptefinal;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;

/**
 *
 * @author Pierre
 */
public class UtilsGUI {
    public static ButtonBar.ButtonData showAlert(String titol, String message, AlertType tipus) {
        Alert alert = new Alert(tipus);
        alert.setTitle(titol);
        alert.setContentText(message);
        alert.show();
        return alert.getResult().getButtonData();
    }
}
