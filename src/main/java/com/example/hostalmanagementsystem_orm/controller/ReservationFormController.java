package com.example.hostalmanagementsystem_orm.controller;

import com.example.hostalmanagementsystem_orm.bo.BOFactory;
import com.example.hostalmanagementsystem_orm.bo.custom.ReservationBO;
import com.example.hostalmanagementsystem_orm.bo.custom.RoomBO;
import com.example.hostalmanagementsystem_orm.dto.ReservationDto;
import com.example.hostalmanagementsystem_orm.dto.RoomDto;
import com.example.hostalmanagementsystem_orm.tm.ReservationTm;
import com.example.hostalmanagementsystem_orm.tm.RoomTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReservationFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<ReservationTm> tblReservations;

    @FXML
    private TableColumn<ReservationTm, String> colReservationId;

    @FXML
    private TableColumn<ReservationTm, String> colStudentId;

    @FXML
    private TableColumn<ReservationTm, Date> colDate;

    @FXML
    private TableColumn<ReservationTm, String> colRoomType;

    @FXML
    private TableColumn<ReservationTm, String> colStatus;

    @FXML
    private TableView<RoomTm> tblRoom;

    @FXML
    private TableColumn<RoomTm, String> colRoomID;

    @FXML
    private TableColumn<RoomTm, String> colType;

    @FXML
    private TableColumn<RoomTm, Double> colKeyMoney;

    @FXML
    private TableColumn<RoomTm, Integer> colQty;

    @FXML
    private JFXButton btnViewUnpaidStudent;

    @FXML
    private JFXButton btnAddReservation;

    @FXML
    private JFXButton btnMarkAsPaid;

    @FXML
    private JFXButton btnDelete;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    @FXML
    void btnAddReservationOnAction(ActionEvent event) throws IOException {
        try {
            pane.setDisable(true);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setAlwaysOnTop(true);
            stage.requestFocus();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddReservationForm.fxml"))));
            stage.setTitle("Booking");
            stage.showAndWait();
            pane.setDisable(false);

            refreshRoomTable();
            refreshReservationTable();
            } catch (RuntimeException exception) {
                new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            ReservationTm reservationTm = tblReservations.getSelectionModel().getSelectedItem();
            if (reservationTm != null) {
                btnDelete.setDisable(false);
                reservationBO.delete(reservationTm.getRes_id());
                new Alert(Alert.AlertType.ERROR, "Reservation Deleted : " + reservationTm.getRes_id()).show();
                refreshRoomTable();
                refreshReservationTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Select Item First").show();
            }
            btnDelete.setDisable(true);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    @FXML
    void btnMarkAsPaidOnAction(ActionEvent event) {
        try {
            ReservationTm selectedItem = tblReservations.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                ReservationDto dto = reservationBO.view(selectedItem.getRes_id());
                dto.setStatus("paid");
                reservationBO.update(dto);
                new Alert(Alert.AlertType.INFORMATION, "Payment updated").show();
                refreshReservationTable();
                refreshRoomTable();
            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    @FXML
    void btnViewUnpaidStudentOnAction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/UnpaidCaseForm.fxml")));
    }

    @FXML
    void tblReservationsOnMouseClicked(MouseEvent event) {
        ReservationTm reservationTm = tblReservations.getSelectionModel().getSelectedItem();
        if (reservationTm != null) btnDelete.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            colRoomID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

            colReservationId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
            colRoomType.setCellValueFactory(new PropertyValueFactory<>("room_id"));

            refreshRoomTable();
            refreshReservationTable();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    private void refreshReservationTable() throws RuntimeException {
        List<ReservationDto> all = reservationBO.getAll();
        ObservableList<ReservationTm> observableList = FXCollections.observableArrayList();
        all.stream().map(dto -> observableList.add(new ReservationTm(dto.getRes_id(), dto.getDate(), dto.getStatus(), dto.getStudentDto().getStudent_id(), dto.getRoomDto().getRoom_type_id()))).collect(Collectors.toList());
        tblReservations.setItems(observableList);
    }

    private void refreshRoomTable() throws RuntimeException {
        List<RoomDto> all = roomBO.getAll();
        ObservableList<RoomTm> roomObservableList = FXCollections.observableArrayList();
        all.stream().map(dto -> roomObservableList.add(new RoomTm(dto.getRoom_type_id(), dto.getType(), dto.getKey_money(), dto.getQty()))).collect(Collectors.toList());
        tblRoom.setItems(roomObservableList);
    }
}
