package com.berk.todoapp.controller;

import com.berk.todoapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            User user = new User();
            user.setEmail(emailLoginTextField.getText());
            user.setPassword(passwordLoginPassField.getText());
            DatabaseAccessController.getInstance().validateLogin(user);
            PageController.getInstance().switchToDashboard();


        }catch (Exception e){
            alert.setTitle("HATA");
            alert.setContentText(e.getMessage());
            alert.show();

        }



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
