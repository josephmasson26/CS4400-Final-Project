module com.example.traveljournal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.example.traveljournal to javafx.fxml;
    exports com.example.traveljournal;
}