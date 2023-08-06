package com.example.traveljournal;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField passtext;

    @FXML
    private TextField usertext;

    @FXML
    void createAccount(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("2 - CreateAccount.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    void login(MouseEvent event) {
        String username = usertext.getText();
        Connector connection = new Connector();
        Connection connectDB = connection.getConnection();
        String connectQuery =
                "SELECT username FROM account WHERE username = '%s' AND password = '%s' AND NOT EXISTS(SELECT uUsername FROM bans WHERE username = uUsername);".formatted(username, passtext.getText());
        if (!username.equals("")){
            try {
                Statement statement = connectDB.createStatement();
                ResultSet result = statement.executeQuery(connectQuery);

                if (!result.next()) {
                    usertext.setText("");
                    passtext.setText("");
                    usertext.setPromptText("Please Re-enter Username");
                    passtext.setPromptText("Please Re-enter Password");
                } else {
                    String checkUser =
                            ("SELECT username FROM user WHERE username = '%s';").formatted(username);
                    ResultSet result2 = statement.executeQuery(checkUser);
                    if (result2.next()) {
                        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("3 - UserHomeScreen.fxml"));
                        UserHomeScreenController controller = new UserHomeScreenController(username);
                        fxmlLoader.setController(controller);
                        Scene scene = new Scene(fxmlLoader.load());
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        FXMLLoader fxmlLoader =
                                new FXMLLoader(Start.class.getResource("14 - Admin Flags Home Page.fxml"));
                        AdminHomeController controller = new AdminHomeController(username);
                        fxmlLoader.setController(controller);
                        Scene scene = new Scene(fxmlLoader.load());
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}