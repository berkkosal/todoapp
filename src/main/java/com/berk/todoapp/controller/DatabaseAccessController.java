package com.berk.todoapp.controller;

import com.berk.todoapp.model.ToDo;
import com.berk.todoapp.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccessController {
    //***************************Lazy Singeleton***************************//
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
    private static User loggedUser;





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
    public boolean validateUsername(User user) throws SQLException {
        boolean flag = false;
        sql ="select * from users where email= '"+ user.getEmail() +"'";
        ResultSet rs = statement.executeQuery(sql);
       while (rs.next()){
           flag = true;
       }
       return flag;
    }
    public void validateLogin(User user) throws Exception {

        User dbUser = getUserByEmail(user.getEmail());
        if (dbUser==null || ! encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new Exception("Kullanıcı adı veya şifre yanlış.");
        } loggedUser = dbUser;


        /*if (validateUsername(user)){
            sql = "select password from users where id = '"+getUserIdFromDataBase(user)+"'";
            ResultSet rs = statement.executeQuery(sql);
             ;
            if (rs.next() && encoder.matches(user.getPassword(),rs.getString("password"))){
                System.out.println("Successfully logged in.");
            } else System.out.println("Password is wrong.");
        } else {
            System.out.println("Username is wrong.");
        } */
    }




    //Dashboard actions
    public void addToDo(User user,String todo) throws SQLException {
        sql = "insert into todo (userid,todo) values ('"+getUserIdFromDataBase(user)+"','"+todo+"')";
        statement.addBatch(sql);
        statement.executeBatch();

    }

    public void changeToDoStatus(Boolean status) throws SQLException {
        status = false;
        sql = "update todo set iscompleted = false where userid = 1 ";
        statement.executeQuery(sql);


    }
    private void removeToDo(){

    }

    public void changeToDo(User user, String todo){


    }

    private User getUserByEmail(String email) throws SQLException {
        sql = "select * from users where email = '"+email+"'";
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }

        return null;

    }
    private int getUserIdFromDataBase(User user) throws SQLException {
        int id = 0;
        sql = "select id from users where email =('"+user.getEmail()+"')";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            id = rs.getInt("id");
        }
        return id;
    }
    public ArrayList<ToDo> getToDoListByLoggedUser() throws SQLException {
        ArrayList<ToDo> list = new ArrayList<>();
        sql = "select * from todo where userid = ('"+getUserIdFromDataBase(loggedUser)+"')";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            ToDo toDo = new ToDo();
            toDo.setId(rs.getInt("id"));
            toDo.setUserid(loggedUser.getId());
            toDo.setTodo(rs.getString("todo"));
            toDo.setIscompleted(rs.getBoolean("iscompleted"));
            list.add(toDo);
        }
        return list;
    }
    public static User getLoggedUser() {
        return loggedUser;
    }
}
