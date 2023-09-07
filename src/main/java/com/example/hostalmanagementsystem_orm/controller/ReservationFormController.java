package com.example.hostalmanagementsystem_orm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblReservations;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<?> tblRoom;

    @FXML
    private TableColumn<?, ?> colRoomID;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private JFXButton btnViewUnpaidStudent;

    @FXML
    private JFXButton btnAddReservation;

    @FXML
    private JFXButton btnMarkAsPaid;

    @FXML
    private JFXButton btnDelete;

    @FXML
    void btnAddReservationOnAction(ActionEvent event) throws IOException {
        pane.setDisable(true);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.requestFocus();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddReservationForm.fxml"))));
        stage.setTitle("Booking");
        stage.showAndWait();
        pane.setDisable(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnMarkAsPaidOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewUnpaidStudentOnAction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/UnpaidCaseForm.fxml")));
    }

    @FXML
    void tblReservationsOnMouseClicked(MouseEvent event) {

    }

}
