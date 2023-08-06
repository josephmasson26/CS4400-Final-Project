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

public class ViewTripController {

    private String username;
    @FXML
    private Button backButton;
    @FXML
    private Button showEntriesButton;

    @FXML
    private TableView<Trips> tripsTable;

    ViewTripController(String username) {
        this.username = username;
    }

    @FXML
    void initialize() {
        TableColumn<Trips, String> tripsTableColumn = new TableColumn<>("Trips");
        tripsTableColumn.setCellValueFactory(new PropertyValueFactory<>("trip"));
        tripsTableColumn.setMinWidth(200);

        tripsTable.getColumns().add(tripsTableColumn);
        String show =
                ("SELECT tripName FROM trip WHERE username = '%s';").formatted(username);

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(show);
            ObservableList<Trips> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(new Trips(result.getString(1)));
            }
            tripsTable.setItems(list);
        } catch (Exception e) {
            System.out.println(e.toString());
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
    void showEntries(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("12 - My Trip Report.fxml"));
            TripReportController controller = new TripReportController(username);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) tripsTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void viewTrip(MouseEvent event) {
        try {
            String trip =  tripsTable.getSelectionModel().getSelectedItem().getTrip();

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("12 - My Trip Report.fxml"));
            TripReportController controller = new TripReportController(username, trip);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) tripsTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
