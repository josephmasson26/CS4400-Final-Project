package com.example.traveljournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Calendar;


public class CreateAccountController {

    private boolean admin = false;
    @FXML
    private ToggleButton adminButtonNo;

    @FXML
    private ToggleButton adminButtonYes;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField email;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField passtext;

    @FXML
    private TextField usertext;

    @FXML
    void adminNo(ActionEvent event) {
        admin = false;
    }

    @FXML
    void adminYes(ActionEvent event) {
        admin = true;
    }

    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("1 - Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void createAccount(ActionEvent event) {
        if (!usertext.getText().equals("")) {
            boolean cont = true;
            Connector connection = new Connector();
            Connection connectDB = connection.getConnection();
            String accountInsert =
                    ("INSERT INTO account VALUES ('%s', '%s', '%s', '%s', '%s');").
                            formatted(usertext.getText(), email.getText(), fName.getText(),
                                    lName.getText(), passtext.getText());
            try {
                Statement statement = connectDB.createStatement();
                int accValue = statement.executeUpdate(accountInsert);
            } catch (SQLException e) {
                cont = false;
                usertext.setText("");
                passtext.setText("");
                usertext.setPromptText("Please Reenter");
                email.setPromptText("Please Reenter");
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (admin) {
                String adminInsert =
                        ("INSERT INTO admin VALUES ('%s', '%s');").
                                formatted(usertext.getText(),
                                        new Date(Calendar.getInstance().getTimeInMillis()).toString());

                try {
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(adminInsert);
                } catch (SQLException e) {
                    cont = false;
                    usertext.setText("");
                    passtext.setText("");
                    usertext.setPromptText("Please Reenter");
                    email.setPromptText("Please Reenter");
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                String userInsert =
                        ("INSERT INTO user VALUES ('%s', '%s', '%s');").
                                formatted(usertext.getText(),
                                        new Date(Calendar.getInstance().getTimeInMillis()).toString(), "public");
                try {
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(userInsert);
                } catch (SQLException e) {
                    cont = false;
                    usertext.setText("");
                    passtext.setText("");
                    usertext.setPromptText("Please Reenter");
                    email.setPromptText("Please Reenter");
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            if (admin) {
                try {
                    if (cont) {
                        FXMLLoader fxmlLoader =
                                new FXMLLoader(Start.class.getResource("14 - Admin Flags Home Page.fxml"));
                        AdminHomeController controller = new AdminHomeController(usertext.getText());
                        fxmlLoader.setController(controller);
                        Scene scene = new Scene(fxmlLoader.load());
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                try {
                    if (cont) {
                        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("3 - UserHomeScreen.fxml"));
                        UserHomeScreenController controller = new UserHomeScreenController(usertext.getText());
                        fxmlLoader.setController(controller);
                        Scene scene = new Scene(fxmlLoader.load());
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
