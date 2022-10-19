module com.berk.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.berk.todoapp to javafx.fxml;
    exports com.berk.todoapp;
    exports com.berk.todoapp.controller;
    opens com.berk.todoapp.controller to javafx.fxml;
}