package com.berk.todoapp.controller;

import com.berk.todoapp.model.ToDo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ToDoController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private TextField todoTextField;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<ToDo> tableView;
    @FXML
    private TableColumn<ToDo, String> todoColumn;
    @FXML
    private TableColumn<ToDo, CheckBox> statusColumn;

    private ObservableList<ToDo> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todoColumn.setCellValueFactory(new PropertyValueFactory<ToDo, String>("todo"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<ToDo, CheckBox>("checkBox"));
        tableView.setItems(observableList);

        try {
            ArrayList<ToDo> list = DatabaseAccessController.getInstance().getToDoListByLoggedUser();
            observableList.addAll(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void addButtonOnAction() throws SQLException {
        ToDo toDo = new ToDo();
        toDo.setTodo(todoTextField.getText());
        toDo.setIscompleted(false);
        observableList.add(toDo);
        DatabaseAccessController.getInstance().addToDoDataBase(todoTextField.getText());
    }

    @FXML
    public void deleteButtonOnAction() throws SQLException {
        ToDo toDo = tableView.getSelectionModel().getSelectedItem();
        observableList.remove(toDo);
        DatabaseAccessController.getInstance().removeToDo(toDo.getId());

    }






}
