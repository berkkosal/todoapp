package com.berk.todoapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class WelcomePageController {

    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    PageController pc= new PageController();

    public void loginButtonOnAction() throws IOException {
        pc.switchToLoginPage();
    }


    public void signUpButtonOnAction() throws IOException {
        pc.switchToSignUpPage();
    }

}
