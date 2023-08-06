package com.example.traveljournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

public class ViewCityEntryController {

    private String username;

    private String viewedUser;

    private String city;

    private String country;

    private String date;

    private Integer rating;

    private String note;
    @FXML
    private Button backButton;

    @FXML
    private TextField cityField;

    @FXML
    private TextField dateField;

    @FXML
    private TextArea noteField;

    @FXML
    private Rating ratingField;

    @FXML
    private Button reportButton;

    ViewCityEntryController(String username, String viewedUser, String city, String date, Integer rating, String note, String country) {
        this.username = username;
        this.viewedUser = viewedUser;
        this.city = city;
        this.date = date;
        this.rating = rating;
        this.note = note;
        this.country = country;
    }

    @FXML
    void initialize() {
        cityField.setText(city);
        dateField.setText(date);
        ratingField.setRating((double) rating);
        noteField.setText(note);
    }
    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("7 - City Journal Entries.fxml"));
            CityJournalEntriesController controller = new CityJournalEntriesController(username, city, country);
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
    void report(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("9 - Report.fxml"));
            ReportController controller = new ReportController(username, viewedUser, date, city, country, note, rating);
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

