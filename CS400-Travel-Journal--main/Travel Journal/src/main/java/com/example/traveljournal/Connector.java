package com.example.traveljournal;

import java.sql.Connection;
import java.sql.DriverManager;
public class Connector {
    public Connection databaseLink;
    public Connection getConnection() {

        String dbName = "traveljournal";
        String dbUser = "root";
        String dbPass = "Abrakadabra7!";
        String url = "jdbc:mysql://localhost:3306/traveljournal";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, dbUser, dbPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
