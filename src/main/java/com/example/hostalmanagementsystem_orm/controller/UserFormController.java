package com.example.hostalmanagementsystem_orm.controller;

import com.example.hostalmanagementsystem_orm.bo.BOFactory;
import com.example.hostalmanagementsystem_orm.bo.custom.UserBO;
import com.example.hostalmanagementsystem_orm.dto.UserDto;
import com.example.hostalmanagementsystem_orm.tm.UserTm;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<UserTm> tblUsers;

    @FXML
    private TableColumn<UserTm, String> colId;

    @FXML
    private TableColumn<UserTm, String> colPassword;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPassword;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        pane.setDisable(true);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.requestFocus();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignUpPage.fxml"))));
        stage.setTitle("Sign-up");
        stage.showAndWait();
        refreshTable();
        pane.setFocusTraversable(true);
        pane.setDisable(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            UserTm selectedItem = tblUsers.getSelectionModel().getSelectedItem();
            userBO.delete(selectedItem.getId());
            new Alert(Alert.AlertType.ERROR, "User Deleted").showAndWait();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
        tblUsers.getItems().clear();
        btnAdd.setDisable(false);
        refreshTable();
        clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            UserDto userDto = new UserDto();
            userDto.setId(txtId.getText());
            userDto.setPassword(txtPassword.getText());
            userBO.update(userDto);
            new Alert(Alert.AlertType.INFORMATION, "User Updated").showAndWait();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
        tblUsers.getItems().clear();
        btnAdd.setDisable(false);
        refreshTable();
        clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void tblUsersOnMouseClicked(MouseEvent event) {
        UserTm selectedItem = tblUsers.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnAdd.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

            txtId.setText(selectedItem.getId());
            txtPassword.setText(selectedItem.getPassword());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        refreshTable();
    }

    private void refreshTable() {
        try {
            List<UserDto> all = userBO.getAll();
            ObservableList<UserTm> observableList = FXCollections.observableArrayList();
            all.forEach(dto -> observableList.add(new UserTm(dto.getId(), dto.getPassword())));
            tblUsers.setItems(observableList);
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clear(){
        txtId.clear();
        txtPassword.clear();
    }
}
