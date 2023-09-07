package com.example.hostalmanagementsystem_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartFormController {

    @FXML
    private Button startBtn;

    @FXML
    private AnchorPane main;

    @FXML
    void onAction(ActionEvent event) throws IOException {
        Stage window = (Stage) main.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPageForm.fxml"))));
    }

}
