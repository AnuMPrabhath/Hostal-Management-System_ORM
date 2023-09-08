package com.example.hostalmanagementsystem_orm.controller;

import com.example.hostalmanagementsystem_orm.bo.BOFactory;
import com.example.hostalmanagementsystem_orm.bo.custom.RoomBO;
import com.example.hostalmanagementsystem_orm.dto.RoomDto;
import com.example.hostalmanagementsystem_orm.tm.RoomTm;
import com.example.hostalmanagementsystem_orm.util.regex.RegExFactory;
import com.example.hostalmanagementsystem_orm.util.regex.RegExType;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {

    @FXML
    private TableView<RoomTm> tblRooms;

    @FXML
    private TableColumn<RoomTm, String> colRoomTypeId;

    @FXML
    private TableColumn<RoomTm, String> colRoomType;

    @FXML
    private TableColumn<RoomTm, Double> colKeyMoney;

    @FXML
    private TableColumn<RoomTm, Integer> colQty;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtSearch;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (validateData()) {
                RoomDto roomDto = new RoomDto();
                roomDto.setRoom_type_id(txtId.getText());
                roomDto.setType(txtType.getText());
                roomDto.setKey_money(Double.valueOf(txtKeyMoney.getText()));
                roomDto.setQty(Integer.valueOf(txtQty.getText()));
                roomBO.save(roomDto);

                new Alert(Alert.AlertType.INFORMATION, "Room Added").show();

                refreshTable();
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
            }
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            clearAll();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            if (tblRooms.getSelectionModel().getSelectedItem() != null) {
                roomBO.delete(txtId.getText());
                new Alert(Alert.AlertType.INFORMATION, "Room Deleted").show();

                refreshTable();
                clearAll();
            } else {
                throw new RuntimeException("Select Room First");
            }
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            clearAll();
        }
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (tblRooms.getSelectionModel().getSelectedItem() != null) {
                if (validateData()) {
                    RoomDto roomDto = new RoomDto();
                    roomDto.setRoom_type_id(txtId.getText());
                    roomDto.setType(txtType.getText());
                    roomDto.setKey_money(Double.valueOf(txtKeyMoney.getText()));
                    roomDto.setQty(Integer.valueOf(txtQty.getText()));

                    roomBO.update(roomDto);
                    new Alert(Alert.AlertType.INFORMATION, "Room Updated").show();

                    refreshTable();
                    clearAll();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
                }
            } else {
                throw new RuntimeException("Select Room First");
            }
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void tblRoomsOnMouseClicked(MouseEvent event) {
        RoomTm selectedItem = tblRooms.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnAdd.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            txtId.setText(selectedItem.getRoom_type_id());
            txtType.setText(selectedItem.getType());
            txtQty.setText(String.valueOf(selectedItem.getQty()));
            txtKeyMoney.setText(String.valueOf(selectedItem.getKey_money()));
        } else {
            btnAdd.setDisable(false);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnKeyReleased(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        refreshTable();

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void clearAll() {
        txtQty.clear();
        txtType.clear();
        txtKeyMoney.clear();
    }

    private boolean validateData() {
        return RegExFactory.getInstance().getPattern(RegExType.DOUBLE).matcher(txtKeyMoney.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.NAME).matcher(txtType.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.INTEGER).matcher(txtQty.getText()).matches();
    }

    private void refreshTable() {
        try {
            txtId.setText(roomBO.getLastId());
            List<RoomDto> all = roomBO.getAll();
            ObservableList<RoomTm> roomObservableList = FXCollections.observableArrayList();
            all.forEach(dto -> roomObservableList.add(new RoomTm(dto.getRoom_type_id(), dto.getType(), dto.getKey_money(), dto.getQty())));
            tblRooms.setItems(roomObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            tblRooms.getItems().clear();
        }
    }

}
