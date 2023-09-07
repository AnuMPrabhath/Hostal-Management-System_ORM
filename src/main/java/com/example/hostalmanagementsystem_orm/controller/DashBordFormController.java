package com.example.hostalmanagementsystem_orm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashBordFormController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane sidePane;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnStudents;

    @FXML
    private JFXButton btnRooms;

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnUser;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblTime;
    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/UserForm.fxml")));
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReservationForm.fxml")));
    }

    public void btnRoomsOnAction(ActionEvent actionEvent) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RoomForm.fxml")));
    }

    public void btnStudentsOnAction(ActionEvent actionEvent) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/StudentForm.fxml")));
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage)  mainPane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPageForm.fxml"))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> lblTime.setText("" + new SimpleDateFormat("EEEE - MMM-dd-yyyy  HH:mm:ss").format(Calendar.getInstance().getTime()))), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
