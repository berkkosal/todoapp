module com.berk.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.core;
    requires spring.security.crypto;
    requires spring.boot.starter.mail;
    requires spring.core;
    requires spring.context;
    requires spring.context.support;



    opens com.berk.todoapp to javafx.fxml;
    exports com.berk.todoapp;
    exports com.berk.todoapp.controller;
    opens com.berk.todoapp.controller to javafx.fxml;
}