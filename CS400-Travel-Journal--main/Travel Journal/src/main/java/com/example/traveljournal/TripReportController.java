package com.example.traveljournal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TripReportController {

    private String username;

    private String trip;

    private String tripStart;

    private String tripEnd;
    @FXML
    private Button backButton;

    @FXML
    private TableView<FullTrip> tripReportTable;

    TripReportController(String username, String trip) {
        this.username = username;
        this.trip = trip;
    }

    TripReportController(String username) {
        this.username = username;
    }

    @FXML
    void initialize() {
        TableColumn<FullTrip, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setMinWidth(75);

        TableColumn<FullTrip, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityColumn.setMinWidth(75);

        TableColumn<FullTrip, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn.setMinWidth(75);

        TableColumn<FullTrip, Double> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingColumn.setMinWidth(75);

        TableColumn<FullTrip, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        tripReportTable.getColumns().addAll(dateColumn, cityColumn, countryColumn, ratingColumn, noteColumn);
        noteColumn.setMinWidth(200);
        String pullTrip;
        if (trip != null) {
            String dates =
                    ("SELECT sDate, eDate FROM trip WHERE username = '%s' AND tripName = '%s';").
                            formatted(username, trip);

            try {
                Connector connection = new Connector();
                Connection connectDB = connection.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet result = statement.executeQuery(dates);
                while (result.next()) {
                    tripStart = result.getString(1);
                    tripEnd = result.getString(2);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            pullTrip =
                    ("SELECT entryDate, city, country, rating, note FROM journal_entry AS JE WHERE JE.username = '%s'" +
                            " AND JE.entryDate BETWEEN '%s' AND '%s' ORDER BY JE.entryDate;").
                            formatted(username, tripStart, tripEnd);
        } else {
            pullTrip =
                    ("SELECT entryDate, city, country, rating, note FROM journal_entry AS JE" +
                            " WHERE JE.username = '%s' ORDER BY JE.entryDate;").
                            formatted(username);
        }



        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(pullTrip);
            ObservableList<FullTrip> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(new FullTrip(result.getString(1), result.getString(2),
                        result.getString(3), result.getDouble(4), result.getString(5)));
            }
            tripReportTable.setItems(list);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void viewEntry(MouseEvent event) {
        try {
            String city =  tripReportTable.getSelectionModel().getSelectedItem().getCity();
            String country =  tripReportTable.getSelectionModel().getSelectedItem().getCountry();
            String date =  tripReportTable.getSelectionModel().getSelectedItem().getDate();

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("13 - My City Entry.fxml"));
            MyCityEntryController controller = new MyCityEntryController(username, trip, date, city, country);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) tripReportTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("11 - View My Trips.fxml"));
            ViewTripController controller = new ViewTripController(username);
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

