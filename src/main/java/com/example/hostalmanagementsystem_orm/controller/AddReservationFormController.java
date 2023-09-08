package com.example.hostalmanagementsystem_orm.controller;

import com.example.hostalmanagementsystem_orm.bo.custom.ReservationBO;
import com.example.hostalmanagementsystem_orm.bo.custom.RoomBO;
import com.example.hostalmanagementsystem_orm.bo.custom.StudentBO;
import com.example.hostalmanagementsystem_orm.dto.ReservationDto;
import com.example.hostalmanagementsystem_orm.dto.RoomDto;
import com.example.hostalmanagementsystem_orm.dto.StudentDto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddReservationFormController implements Initializable {

    @FXML
    private AnchorPane floatingPane;

    @FXML
    private ComboBox<String> cmbRoomId;

    @FXML
    private ComboBox<String> cmbStdId;

    @FXML
    private Label txtRoomAvailableQty;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXButton btnPayNow;

    @FXML
    private JFXButton btnBook;

    @FXML
    private Label lblRoomPrice;

    @FXML
    private Label lblReservationId;

    private StudentBO studentBO;
    private RoomBO roomBO;
    private ReservationBO reservationBO;

    @FXML
    void btnBookOnAction(ActionEvent event) {
        saveReservation("un-paid");
    }

    @FXML
    void btnPayNowOnAction(ActionEvent event) {
        saveReservation("paid");
    }

    private void saveReservation(String status) {
        try {
            if (validateData()) {
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setRes_id(lblReservationId.getText());
                reservationDto.setStatus(status);
                reservationDto.setDate(Date.valueOf(txtDate.getValue()));

                StudentDto studentDto = studentBO.view(cmbStdId.getValue());
                reservationDto.setStudentDto(studentDto);

                RoomDto dto = roomBO.view(cmbRoomId.getValue());
                dto.setQty(dto.getQty() - 1);
                reservationDto.setRoomDto(dto);

                reservationBO.save(reservationDto);

                Stage stage = (Stage) floatingPane.getScene().getWindow();
                stage.setAlwaysOnTop(false);
                new Alert(Alert.AlertType.INFORMATION, "Added!").showAndWait();
                stage.setAlwaysOnTop(true);
                stage.close();
            }
        } catch (RuntimeException exception) {
            Stage stage = (Stage) floatingPane.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(false);
        }
    }

    private boolean validateData() throws RuntimeException {
        if (cmbStdId.getSelectionModel().getSelectedItem() != null) {
            if (cmbRoomId.getSelectionModel().getSelectedItem() != null) {
                if (txtDate.getValue() != null) {
                    if (!txtDate.getValue().isBefore(LocalDate.now())) {
                        System.out.println("Validation Done");
                        return true;
                    }
                    throw new RuntimeException("Date must be after today");
                }
                throw new RuntimeException("Select Date");
            }
            throw new RuntimeException("Select Room");
        }
        throw new RuntimeException("Select Student");
    }

    @FXML
    void cmbRoomIdOnAction(ActionEvent event) {
        try {
            String selectedItem = cmbRoomId.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                RoomDto room = roomBO.view(selectedItem);
                lblRoomPrice.setText(String.valueOf(room.getKey_money()));
                txtRoomAvailableQty.setText(String.valueOf(room.getQty()));

                if (room.getQty() != 0) {
                    btnPayNow.setDisable(false);
                    btnBook.setDisable(false);
                } else {
                    btnPayNow.setDisable(true);
                    btnBook.setDisable(true);
                    throw new RuntimeException("Room not available at the moment!");
                }
            } else {
                lblRoomPrice.setText("0");
                txtRoomAvailableQty.setText("0");
            }
        } catch (RuntimeException exception) {
            Stage stage = (Stage) floatingPane.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(false);
        }
    }

    @FXML
    void cmbStudentIdOnAction(ActionEvent event) {
         /*try {
            String selectedItem = cmbStdId.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                StudentDto studentDto = new StudentDto();
                studentDto.setStudent_id(selectedItem);
                StudentDto room = studentBO.view(studentDto, FactoryConfiguration.getInstance().getSession());
            } else {
            }
        }catch (RuntimeException exception){
        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setReservationId();
        setStudentList();
        setRoomList();
    }

    private void setRoomList() {
        try {
            List<RoomDto> roomDtoList = roomBO.getAll();
            ObservableList<String> roomIdObservableList = FXCollections.observableArrayList();
            roomDtoList.forEach(roomDto -> roomIdObservableList.add(roomDto.getRoom_type_id()));
            cmbRoomId.setItems(roomIdObservableList);
        } catch (RuntimeException ignored) {
        }
    }

    private void setStudentList() {
        try {
            List<StudentDto> studentDtoList = studentBO.getAll();
            ObservableList<String> studentIdObservableList = FXCollections.observableArrayList();
            studentDtoList.forEach(studentDto -> studentIdObservableList.add(studentDto.getStudent_id()));
            cmbStdId.setItems(studentIdObservableList);
        } catch (RuntimeException ignored) {
        }
    }

    private void setReservationId() {
        lblReservationId.setText(reservationBO.getLastId());
    }
}
