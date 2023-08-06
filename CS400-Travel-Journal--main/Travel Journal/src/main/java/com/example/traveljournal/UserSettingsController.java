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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserSettingsController {

    private String username;
    private String _email;
    private String _fName;
    private String _lName;
    private String _password;
    private String privLvl;
    @FXML
    private Button backButton;

    @FXML
    private ToggleButton deleteButton;

    @FXML
    private TextField email;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField password;

    @FXML
    private ToggleButton privateButton;

    @FXML
    private ToggleButton publicButton;

    @FXML
    private ToggleButton updateButton;

    UserSettingsController(String username) {
        Connector connection = new Connector();
        Connection connectDB = connection.getConnection();
        this.username = username;
        String accountQuery =
                "SELECT email, fName, lName, password FROM account WHERE username = '%s';".formatted(username);
        String userQuery =
                "SELECT privLvl FROM user WHERE username = '%s';".formatted(username);

        try {
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery(accountQuery);

            if (result.next()) {
                _email = result.getString(1);
                _fName = result.getString(2);
                _lName = result.getString(3);
                _password = result.getString(4);
            }

            ResultSet result2 = statement.executeQuery(userQuery);

            if (result2.next()) {
                privLvl = result2.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void initialize() {
        email.setText(_email);
        fName.setText(_fName);
        lName.setText(_lName);
        password.setText(_password);

//        if (privLvl.equals("public")) {
//            publicButton.setSelected(true);
//        } else {
//            privateButton.setSelected(false);
//        }

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
    void delete(ActionEvent event) {
        String delete = ("DELETE FROM account WHERE username = '%s';").formatted(username);
        Connector connection = new Connector();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("1 - Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            System.out.println("Could not delete: " + e.toString());
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void setPrivate(ActionEvent event) {
        privLvl = "private";
    }

    @FXML
    void setPublic(ActionEvent event) {
        privLvl = "public";
    }

    @FXML
    void update(ActionEvent event) {
        String update =
                ("UPDATE account SET email = '%s', fName = '%s', lName = '%s', password = '%s' WHERE username = '%s';").
                        formatted(email.getText(), fName.getText(), lName.getText(), password.getText(), username);
        String updateTwo = ("UPDATE user SET privLvl = '%s' WHERE username = '%s';").formatted(privLvl, username);
        Connector connection = new Connector();

        try {
            Connection connectDB = connection.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(update);
            Statement statementTwo = connectDB.createStatement();
            statementTwo.executeUpdate(updateTwo);
        } catch (SQLException e) {
            System.out.println("Could not update: " + e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
