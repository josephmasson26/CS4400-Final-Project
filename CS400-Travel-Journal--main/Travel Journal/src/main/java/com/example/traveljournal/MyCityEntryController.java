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

public class MyCityEntryController {

    private String username;

    private String trip;
    private String entryDate;
    private String city;

    private String country;

    MyCityEntryController(String username, String trip, String entryDate, String city, String country) {
        this.username = username;
        this.trip = trip;
        this.entryDate = entryDate;
        this.city = city;
        this.country = country;
    }

    @FXML
    void initialize() {
        String getEntry =
                ("SELECT city, entryDate, rating, note FROM journal_entry WHERE username = '%s' AND" +
                        " entryDate = '%s' AND city = '%s' AND country = '%s';").
                        formatted(username, entryDate, city, country);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(getEntry);
            if (result.next()) {
                cityField.setText(result.getString(1));
                dateField.setText(result.getString(2));
                ratingField.setRating(result.getDouble(3));
                noteField.setText(result.getString(4));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    @FXML
    private Button backButton;

    @FXML
    private TextField cityField;

    @FXML
    private TextField dateField;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea noteField;

    @FXML
    private Rating ratingField;

    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("12 - My Trip Report.fxml"));
            TripReportController controller = new TripReportController(username, trip);
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
        String delete =
                ("DELETE FROM journal_entry WHERE username = '%s' AND" +
                        " entryDate = '%s' AND city = '%s' AND country = '%s';").
                        formatted(username, entryDate, city, country);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(delete);

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("12 - My Trip Report.fxml"));
            TripReportController controller = new TripReportController(username, trip);
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
