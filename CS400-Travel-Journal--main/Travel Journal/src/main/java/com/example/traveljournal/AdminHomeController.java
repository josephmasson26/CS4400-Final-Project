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

public class AdminHomeController {

    private String username;

    AdminHomeController(String username) {
        this.username = username;
    }
    @FXML
    private TableView<FlagData> flagTable;

    @FXML
    private Button logoffButton;

    @FXML
    void initialize() {
        TableColumn<FlagData, String> userColumn = new TableColumn<>("User");
        userColumn.setCellValueFactory(new PropertyValueFactory<>("viewedUser"));
        userColumn.setMinWidth(75);

        TableColumn<FlagData, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityColumn.setMinWidth(75);

        TableColumn<FlagData, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        noteColumn.setMinWidth(150);

        TableColumn<FlagData, String> reasonsColumn = new TableColumn<>("Reasons");
        reasonsColumn.setCellValueFactory(new PropertyValueFactory<>("reasons"));
        reasonsColumn.setMinWidth(75);

        TableColumn<FlagData, String> flaggerColumn = new TableColumn<>("Flagger");
        flaggerColumn.setCellValueFactory(new PropertyValueFactory<>("flagger"));
        flaggerColumn.setMinWidth(75);

        flagTable.getColumns().addAll(userColumn, cityColumn, noteColumn, reasonsColumn, flaggerColumn);

        String getReport = "SELECT flaggedUser, city, note, reason, username FROM can_flag;";

        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(getReport);
            ObservableList<FlagData> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(new FlagData(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getString(5)));
            }
            flagTable.setItems(list);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    @FXML
    void logoff(ActionEvent event) {
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
    void toEntry(MouseEvent event) {
        try {
            String flaggedUser =  flagTable.getSelectionModel().getSelectedItem().getViewedUser();
            String flagger =  flagTable.getSelectionModel().getSelectedItem().getFlagger();

            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("15 - Review Flagged Entry.fxml"));
            ReviewFlagController controller = new ReviewFlagController(username, flagger, flaggedUser);
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
