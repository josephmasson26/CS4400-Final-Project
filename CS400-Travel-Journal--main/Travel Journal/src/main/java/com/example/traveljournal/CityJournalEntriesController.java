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
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CityJournalEntriesController {

    private String username;

    private String city;

    private String country;
    @FXML
    private Button backButton;

    @FXML
    private TableView<EntryData> entryTable;

    @FXML
    private Button createButton;


    CityJournalEntriesController(String username, String city, String country) {
        this.username = username;
        this.city = city;
        this.country = country;
    }

    @FXML
    void initialize() {
        TableColumn<EntryData, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setMinWidth(100);

        TableColumn<EntryData, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingColumn.setMinWidth(50);

        TableColumn<EntryData, Integer> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        noteColumn.setMinWidth(150);

        entryTable.getColumns().addAll(dateColumn, ratingColumn, noteColumn);

        String create =
                ("SELECT entryDate, note, rating, username FROM journal_entry AS JE" +
                        " WHERE JE.city = '%s' AND privLvl = 'public';").
                        formatted(city);
        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(create);

            ObservableList<EntryData> list = FXCollections.observableArrayList();
            while (result.next()) {
                EntryData data = new EntryData(result.getString(1),
                        result.getInt(3), result.getString(2), result.getString(4));
                list.add(data);
            }
            entryTable.setItems(list);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void back(ActionEvent event) {
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

    public void viewEntry(javafx.scene.input.MouseEvent mouseEvent) {
            try {
                String date =  entryTable.getSelectionModel().getSelectedItem().getDate();
                String note = entryTable.getSelectionModel().getSelectedItem().getNote();
                Integer rating = entryTable.getSelectionModel().getSelectedItem().getRating();
                String viewedUsername = entryTable.getSelectionModel().getSelectedItem().getViewedUsername();

                FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("8 - View City Entry.fxml"));
                ViewCityEntryController controller = new ViewCityEntryController(username, viewedUsername, city,
                        date, rating, note, country);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = (Stage) entryTable.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }

    @FXML
    void create(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("10 - Create City Journal Entry.fxml"));
            CreateEntryController controller = new CreateEntryController(username, city, country);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) entryTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}

