package com.berk.todoapp.controller;

import com.berk.todoapp.model.ToDo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ToDoController implements Initializable {
    @FXML
    private TableView<ToDo> tableView;
    @FXML
    private TableColumn<ToDo, String> todoColumn;
    @FXML
    private TableColumn<ToDo, Boolean> statusColumn;



    private ObservableList<ToDo> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todoColumn.setCellValueFactory(new PropertyValueFactory<ToDo, String>("todo"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<ToDo, Boolean>("iscompleted"));
        tableView.setItems(observableList);

        try {
            ArrayList<ToDo> list = DatabaseAccessController.getInstance().getToDoListByLoggedUser();
            observableList.addAll(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }






}
