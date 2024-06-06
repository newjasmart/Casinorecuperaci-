///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package cat.boscdelacoma.casinoreptefinal;
//
//import cat.boscdelacoma.projecte.data.ObjectDBConnector;
//import cat.boscdelacoma.projecte.entity.Rank;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import javax.persistence.EntityManager;
//
//public class ClientController {
//    
//
//    @FXML
//    private TextField dniClientfield;
//    @FXML
//    private TextField nomClientfield;
//    @FXML
//    private TextField puntsdeFidelitafieldt;
//
//     private static final String URL = "db.url";
//    private static final String USER = "db.user";
//    private static final String PASSWORD = "db.password";
//
//    
//    @FXML
//    private void handleAddRankToObjectDB(ActionEvent event) {
//        Integer id = Integer.parseInt(rankIdField.getText());
//        String rankName = rankNameField.getText();
//        int points = Integer.parseInt(rankPointsField.getText());
//
//        Rank rank = new Rank();
//        rank.setId(id);
//        rank.setRankName(rankName);
//        rank.setPoints(points);
//
//        ObjectDBConnector.addEntity(rank, ObjectDBConnector.getEntityManager());
//        ObjectDBConnector.getRanks(ObjectDBConnector.getEntityManager());
//       
////        Ranking.addRankToObjectDB(rank);
//    }
//}
