package com.berk.todoapp.controller;

import com.berk.todoapp.model.User;
import javafx.scene.chart.PieChart;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccessController {
    //***************************Singeleton***************************//
    private static DatabaseAccessController instance;
    private DatabaseAccessController() {
        init();
    }
    public static DatabaseAccessController getInstance() {
        if (instance == null) {
            instance = new DatabaseAccessController();
        }
        return instance;
    }

    //***************************Değişkenler***************************//
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final String dataSource = "jdbc:postgresql://localhost:5432/postgres";
    private final String dataUsername = "postgres";
    private final String dataPassword = "123";
    private Connection connection = null;
    private Statement statement = null;


//select todo,iscompleted from todo where userid = (select id from users where email = 'berkko@gmail.com');


    private void init() {
        try {
            //Bağlantı nesnesi
            connection = DriverManager.getConnection(dataSource, dataUsername, dataPassword);
            System.out.println("Connected to the database");
            //Baglanti nesnesi uzerinden islem yapmaya yarayan nesne "Statement"
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addUser(User user) throws SQLException {
        String sql = "insert into users (email, password) values ('" + user.getEmail() + "','" + user.getPassword() + "')";
        statement.addBatch(sql);
        statement.executeBatch();
    }


    public void saveUser(User user) throws SQLException {

        user.setPassword(encoder.encode(user.getPassword()));
        sendMail(user.getEmail());
        addUser(user);

    }


    public void sendMail(String email) {


    }


}
