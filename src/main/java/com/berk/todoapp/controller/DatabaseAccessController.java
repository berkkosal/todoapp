package com.berk.todoapp.controller;

import com.berk.todoapp.model.User;
import javafx.scene.chart.PieChart;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

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
    private String sql;

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
        sql = "insert into users (email, password) values ('" + user.getEmail() + "','" + user.getPassword() + "')";
        statement.addBatch(sql);
        statement.executeBatch();
    }


    public void saveUser(User user) throws SQLException {

        user.setPassword(encoder.encode(user.getPassword()));
        sendMail(user.getEmail());
        addUser(user);

    }


    private void sendMail(String email) {


    }

    private void addToDo(){


    }

    private void removeToDo(){

    }

    public int getUserIdFromDataBase(User user) throws SQLException {
        int id = 0;
        sql = "select id from users where email =('"+user.getEmail()+"')";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            id = rs.getInt("id");
            System.out.println("User id : " + id);
        }
        return id;
    }

    public void showAllToDo(User user) throws SQLException {
        String todo=null;
        String isCompleted = null;
        sql = "select todo,iscompleted from todo where userid = ('"+getUserIdFromDataBase(user)+"')";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            todo = rs.getString("todo");
            isCompleted = rs.getString("iscompleted");
            System.out.println(todo + isCompleted);

        }

    }

}
