package com.berk.todoapp.controller;

import com.berk.todoapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField emailLoginTextField;
    @FXML
    private PasswordField passwordLoginPassField;
    @FXML
    private Button loginButton;



    public void loginButtonOnAction() throws SQLException {

        try {
            User user = new User();
            user.setEmail(emailLoginTextField.getText());
            user.setPassword(passwordLoginPassField.getText());
            DatabaseAccessController.getInstance().validateUsernameAndPassword(user);


        }catch (Exception e){

            System.out.println("hata ");
        }

        //System.out.println(emailLoginTextField.getText());
        //System.out.println(passwordLoginPassField.getText());

    }

    public TextField getEmailLoginTextField() {
        return emailLoginTextField;
    }

    public void setEmailLoginTextField(TextField emailLoginTextField) {
        this.emailLoginTextField = emailLoginTextField;
    }

    public PasswordField getPasswordLoginPassField() {

        return passwordLoginPassField;
    }

    public void setPasswordLoginPassField(PasswordField passwordLoginPassField) {
        this.passwordLoginPassField = passwordLoginPassField;
    }
}
