package com.example.traveljournal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReviewFlagController {

    private String username;

    private String entryDate;

    private String city;

    private String country;

    private String flagger;

    private String flaggedUser;

    ReviewFlagController(String username, String flagger, String flaggedUser) {
        this.username = username;
        this.flagger = flagger;
        this.flaggedUser = flaggedUser;
    }
    @FXML
    private Button backButton;

    @FXML
    private Button banUserButton;

    @FXML
    private TextField cityField;

    @FXML
    private Button clearFlagButton;

    @FXML
    private TextField dateField;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea noteField;

    @FXML
    private Rating ratingField;

    @FXML
    void initialize() {
        String viewFlag =
                ("SELECT JE.city, JE.entryDate, JE.rating, JE.note, JE.country" +
                        " FROM can_flag AS CF, journal_entry AS JE" +
                        " WHERE CF.username = '%s' AND CF.flaggedUser = '%s' AND JE.username = CF.flaggedUser" +
                        " AND JE.entryDate = CF.entryDate AND JE.locName = CF.locName AND JE.country = CF.country;").
                        formatted(flagger, flaggedUser);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(viewFlag);
            if (result.next()) {
                city = result.getString(1);
                cityField.setText(city);
                entryDate = result.getString(2);
                dateField.setText(entryDate);
                ratingField.setRating(result.getDouble(3));
                noteField.setText(result.getString(4));
                country = result.getString(5);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("14 - Admin Flags Home Page.fxml"));
            AdminHomeController controller = new AdminHomeController(username);
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
    void ban(ActionEvent event) {
        String deleteEntry =
                ("DELETE FROM journal_entry WHERE username = '%s';").formatted(flaggedUser);
        String ban =
                ("INSERT INTO bans(uUsername, aUsername) VALUES ('%s', '%s');").
                        formatted(flaggedUser, username);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteEntry);
            statement.executeUpdate(ban);

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("14 - Admin Flags Home Page.fxml"));
            AdminHomeController controller = new AdminHomeController(username);
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
    void clearFlag(ActionEvent event) {
        String clear =
                ("DELETE FROM can_flag WHERE username = '%s' AND flaggedUser = '%s' " +
                        "AND entryDate = '%s' AND locName = '%s' AND country = '%s';").
                        formatted(flagger, flaggedUser, entryDate, city, country);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(clear);

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("14 - Admin Flags Home Page.fxml"));
            AdminHomeController controller = new AdminHomeController(username);
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
    void delete(ActionEvent event) {
        String deleteFlag =
                ("DELETE FROM can_flag WHERE username = '%s' AND flaggedUser = '%s'" +
                        " AND entryDate = '%s' AND locName = '%s' AND country = '%s';").
                        formatted(flagger, flaggedUser, entryDate, city, country);
        String deleteEntry =
                ("DELETE FROM journal_entry AS JE WHERE JE.username = '%s' AND entryDate = '%s' " +
                        "AND locName = '%s' AND country = '%s';").
                        formatted(flaggedUser, entryDate, city, country);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteFlag);
            statement.executeUpdate(deleteEntry);

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("14 - Admin Flags Home Page.fxml"));
            AdminHomeController controller = new AdminHomeController(username);
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

}
