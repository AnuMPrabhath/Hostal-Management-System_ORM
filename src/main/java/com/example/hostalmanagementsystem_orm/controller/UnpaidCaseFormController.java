package com.example.hostalmanagementsystem_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UnpaidCaseFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblStudents;

    @FXML
    private TableColumn<?, ?> colStdId;

    @FXML
    private TableColumn<?, ?> colStdName;

    @FXML
    private TableColumn<?, ?> colStdAddress;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/reservationForm.fxml")));
    }

    @FXML
    void tblStudentsOnMouseClicked(MouseEvent event) {

    }

}
