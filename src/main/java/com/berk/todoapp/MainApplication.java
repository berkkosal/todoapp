package com.berk.todoapp;

import com.berk.todoapp.controller.DatabaseAccessController;
import com.berk.todoapp.controller.MailSenderController;
import com.berk.todoapp.controller.ToDoController;
import com.berk.todoapp.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Giriş Ekranı");
        stage.setScene(scene);
        stage.show();

        User user = new User();
        user.setEmail("said");

        DatabaseAccessController.getInstance().showAllToDo(user);





    }

    public static void main(String[] args) {
        launch();
    }


}