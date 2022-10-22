package com.berk.todoapp.controller;

import com.berk.todoapp.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.*;

public class DatabaseAccessController {
    //***************************Singeleton***************************//
    private static DatabaseAccessController instance;
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




    //User registration
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


    //User login
    public void validateUsernameAndPassword(User user) throws SQLException {
       LoginController lc= new LoginController();
        String email =lc.getEmailLoginTextField().getText();
       // String pass = lc.getPasswordLoginPassField().getText();
        System.out.println(email);
     //   System.out.println(pass);



        sql ="select * from users where email= '"+ email +"'"; //and password ='"+ pass +"'";
        ResultSet rs = statement.executeQuery(sql);





    }
  //  public void validateUsername(User user){


    //Dashboard actions
    public void addToDo(User user,String todo) throws SQLException {
        sql = "insert into todo (userid,todo) values ('"+getUserIdFromDataBase(user)+"','"+todo+"')";
        statement.addBatch(sql);
        statement.executeBatch();

    }
    public void changeToDo(User user, String todo){



    }
    public void changeToDoStatus(User user,Boolean status){


    }
    private void removeToDo(){

    }
    private int getUserIdFromDataBase(User user) throws SQLException {
        int id = 0;
        sql = "select id from users where email =('"+user.getEmail()+"')";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            id = rs.getInt("id");
            System.out.println("User id : " + id);
        }
        return id;
    }
    public void getToDoListByUser(User user) throws SQLException {
        String todo=null;
        Boolean isCompleted = false;
        sql = "select todo,iscompleted from todo where userid = ('"+getUserIdFromDataBase(user)+"')";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            todo = rs.getString("todo");
            isCompleted = rs.getBoolean("iscompleted");
            System.out.println("ToDo: " + todo + "     Status: " + isCompleted);

        }
    }

    //

}
