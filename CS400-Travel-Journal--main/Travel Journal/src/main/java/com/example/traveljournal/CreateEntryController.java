package com.example.traveljournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class CreateEntryController {

    private String username;

    private String privLvl;

    private String prevCity;

    private String prevCountry;
    @FXML
    private Button backButton;

    @FXML
    private TextField cityField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField noteField;

    @FXML
    private Button privateButton;

    @FXML
    private Button publicButton;

    @FXML
    private Rating ratingField;

    @FXML
    private Button saveButton;

    CreateEntryController(String username) {
        this.username = username;
    }

    CreateEntryController(String username, String city, String country) {
        this.username = username;
        this.prevCity = city;
        this.prevCountry = country;
    }

    @FXML
    void initialize() {
        String defaultPriv =
                ("SELECT privLvl FROM user WHERE username = '%s';").
                        formatted(username);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(defaultPriv);
            if (result.next()) {
                privLvl = result.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (prevCity != null) {
            cityField.setText(prevCity);
            countryField.setText(prevCountry);
        }
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
    void save(ActionEvent event) {
        String save =
                ("INSERT INTO journal_entry VALUES('%s', '%s', '%s', '%s',  '%s', '%s', '%s', '%d');").
                        formatted(username, dateField.getText(),
                                cityField.getText(), countryField.getText(),
                                cityField.getText(), privLvl, noteField.getText(), (int) ratingField.getRating());

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(save);

            cityField.setText("");
            countryField.setText("");
            noteField.setText("");
            dateField.setText("");
            ratingField.setRating(0);

            cityField.setPromptText("City");
            countryField.setPromptText("Country");
            noteField.setPromptText("Note");
            dateField.setPromptText("Date");
        } catch (Exception e) {
            cityField.setText("");
            countryField.setText("");
            noteField.setText("");
            dateField.setText("");
            ratingField.setRating(0);
            cityField.setPromptText("Please Retry Entry");
            System.out.println(e.toString());

        }
    }

    @FXML
    void setPriv(ActionEvent event) {
        privLvl = "private";
    }

    @FXML
    void setPublic(ActionEvent event) {
        privLvl = "public";
    }

}
