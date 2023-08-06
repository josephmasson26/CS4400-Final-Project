package com.example.traveljournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class ReportController {

    private String username;

    private String viewedUser;

    private String entryDate;

    private String city;

    private String country;

    private String note;

    private Integer rating;

    ReportController(String username, String viewedUser, String entryDate, String city,
                     String country, String note, Integer rating) {
        this.username = username;
        this.viewedUser = viewedUser;
        this.entryDate = entryDate;
        this.city = city;
        this.country = country;
        this.note = note;
        this.rating = rating;
    }
    @FXML
    private Button backButton;

    @FXML
    private CheckBox expCheck;

    @FXML
    private CheckBox harassCheck;

    @FXML
    private CheckBox offCheck;

    @FXML
    private Button reportButton;

    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("8 - View City Entry.fxml"));
            ViewCityEntryController controller = new ViewCityEntryController(username, viewedUser, city,
                    entryDate, rating, note, country);
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

        int reasonLookup = 0;
        if (harassCheck.isSelected()) {
            reasonLookup += 4;
        }

        if (expCheck.isSelected()) {
            reasonLookup += 2;
        }

        if (offCheck.isSelected()) {
            reasonLookup += 1;
        }

        String reasonQuery =
            ("SELECT reasonString FROM reasons_lookup WHERE reasonID = %d").formatted(reasonLookup);


        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();

            ResultSet reasonSet = statement.executeQuery(reasonQuery);

            String reason = "";
            if (reasonSet.next()) {
                reason = reasonSet.getString(1);
            }
            String report =
                ("INSERT INTO can_flag VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');").
                    formatted(username, viewedUser, (new Date(Calendar.getInstance().getTimeInMillis())).toString(),
                        entryDate, city, city, country, reason, note);

            statement.executeUpdate(report);

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("8 - View City Entry.fxml"));
            ViewCityEntryController controller = new ViewCityEntryController(username, viewedUser, city,
                    entryDate, rating, note, country);
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

