package com.example.traveljournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTripController {

    private String username;
    @FXML
    private Button backButton;

    @FXML
    private Button createTripButton;

    @FXML
    private TextField enddate;

    @FXML
    private TextField startdate;

    @FXML
    private TextField tripname;

    CreateTripController(String username) {
        this.username = username;
    }
    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("3 - UserHomeScreen.fxml"));
            UserHomeScreenController controller = new UserHomeScreenController(username);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void createTrip(ActionEvent event) {
        String create =
                ("INSERT INTO trip VALUES('%s', '%s', '%s', '%s');").
                        formatted(username, tripname.getText(), startdate.getText(), enddate.getText());
        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(create);
        } catch (SQLException e) {
            System.out.println("Cannot Create Trip: " + e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        tripname.setText("");
        startdate.setText("");
        enddate.setText("");

        tripname.setPromptText("Trip Name");
        startdate.setPromptText("Start Date (YYYY-MM-DD)");
        enddate.setPromptText("End Date (YYYY-MM-DD)");
    }

}
