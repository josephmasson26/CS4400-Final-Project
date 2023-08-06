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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CitySearchController {

    private boolean tablePopulated = false;
    private final String username;

    @FXML
    private Button backButton;

    @FXML
    private TextField citySearch;

    @FXML
    private TableView<SearchData> cityTable;

    @FXML
    private Button resetButton;

    @FXML
    private ToggleButton searchButton;

    CitySearchController(String username) {
        this.username = username;
    }

    @FXML
    void initialize() {
        TableColumn<SearchData, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityColumn.setMinWidth(100);

        TableColumn<SearchData, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn.setMinWidth(150);

        TableColumn<SearchData, Double> avgRating = new TableColumn<>("Rating");
        avgRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        avgRating.setMinWidth(50);

        cityTable.getColumns().addAll(cityColumn, countryColumn, avgRating);

        String populate = "SELECT * FROM avg_rating;";
        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(populate);

            ObservableList<SearchData> list = FXCollections.observableArrayList();
            while (result.next()) {
                SearchData data = new SearchData(result.getString(1),
                        result.getString(2), result.getDouble(3));
                list.add(data);
            }
            cityTable.setItems(list);
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
    void reset(ActionEvent event) {
        citySearch.setText("");
        citySearch.setPromptText("Search for City");

        String populate = "SELECT * FROM avg_rating;";
        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(populate);

            ObservableList<SearchData> list = FXCollections.observableArrayList();
            while (result.next()) {
                SearchData data = new SearchData(result.getString(1),
                        result.getString(2), result.getDouble(3));
                list.add(data);
            }
            cityTable.setItems(list);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void search(ActionEvent event) {
        String average =
                ("SELECT city, country, AVG(rating) FROM journal_entry AS JE" +
                        " WHERE JE.city = '%s' AND privLvl = 'public' GROUP BY city, country;").
                        formatted(citySearch.getText());
        try {
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(average);

            ObservableList<SearchData> list = FXCollections.observableArrayList();
            while (result.next()) {
                SearchData data = new SearchData(result.getString(1),
                        result.getString(2), result.getDouble(3));
                list.add(data);
            }
            cityTable.setItems(list);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void citySelect(javafx.scene.input.MouseEvent mouseEvent) {
            try {
                String city =  cityTable.getSelectionModel().getSelectedItem().getCity();
                String country = cityTable.getSelectionModel().getSelectedItem().getCountry();
                FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("7 - City Journal Entries.fxml"));
                CityJournalEntriesController controller = new CityJournalEntriesController(username, city, country);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = (Stage) cityTable.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }
}




