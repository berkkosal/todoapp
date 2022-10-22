package com.berk.todoapp.controller;

import com.berk.todoapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField emailRegisterTextField;

    @FXML
    private PasswordField passwordRegisterPassField;

    @FXML
    private Button signUpButton;

    public void signUpButtonOnAction() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            User user = new User();

            user.setEmail(emailRegisterTextField.getText());
            user.setPassword(passwordRegisterPassField.getText());
            DatabaseAccessController.getInstance().saveUser(user);
            MailSenderController.getInstance().sendMail(user.getEmail());
            alert.setContentText("Başarıyla eklendi.");
            alert.setTitle("Helal kayıt oldun");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("MUHTEŞEMSİN");
            alert.show();

        } catch (Exception e){
            alert.setContentText("Bu kullanıcı adı kullanımda.");
            alert.setTitle("Hata");
            alert.show();

        }

    }

}
