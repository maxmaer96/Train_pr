module com.example.mvp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mvp to javafx.fxml;
    exports com.example.mvp;
    exports com.example.mvp.gui;
    exports com.example.mvp.models;
    opens com.example.mvp.gui to javafx.fxml;
}