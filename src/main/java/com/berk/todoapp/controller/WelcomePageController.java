package com.berk.todoapp.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;


public class WelcomePageController {
    @FXML
    private AnchorPane myAnchorPane;
    @FXML
    private ToggleButton darkModeToggleButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    PageController pc = new PageController();

    public static int darkMode;

    public void loginButtonOnAction() throws IOException {
        pc.switchToLoginPage();
    }


    public void signUpButtonOnAction() throws IOException {
        pc.switchToSignUpPage();
    }

    public void dModeToggleButtonOnAction() {
        Color color = new Color(0, 0, 0, 0.7);
        if (darkMode==0) {
            myAnchorPane.setBackground(new Background(new BackgroundFill(color, null, null)));
            darkMode++;
        } else {
            color = new Color(1,1,1,1);
            myAnchorPane.setBackground(new Background(new BackgroundFill(color, null, null)));
            darkMode--;
        }



    }
}
