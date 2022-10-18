module com.berk.todoapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.berk.todoapp to javafx.fxml;
    exports com.berk.todoapp;
}