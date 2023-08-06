package com.example.traveljournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UserHomeScreenController {

    private String username;
    @FXML
    private Rectangle createEntryButton;

    @FXML
    private Rectangle createTripButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Rectangle searchCitiesButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Rectangle tripentryButton;

    UserHomeScreenController(String username) {
        this.username = username;
    }
    @FXML
    void createEntry(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(Start.class.getResource("10 - Create City Journal Entry.fxml"));
            CreateEntryController controller = new CreateEntryController(username);
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
    void createTrip(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("5 - Create Trip.fxml"));
            CreateTripController controller = new CreateTripController(username);
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
    void logout(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("1 - Login.fxml"));
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
    void openSettings(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("4 - User Settings.fxml"));
            UserSettingsController controller = new UserSettingsController(username);
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
    void openTripEntry(MouseEvent event) {
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

    @FXML
    void searchCities(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("6 - City Search Result.fxml"));
            CitySearchController controller = new CitySearchController(username);
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
