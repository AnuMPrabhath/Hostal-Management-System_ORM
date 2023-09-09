package com.example.hostalmanagementsystem_orm.controller;

import com.example.hostalmanagementsystem_orm.bo.BOFactory;
import com.example.hostalmanagementsystem_orm.bo.custom.UserBO;
import com.example.hostalmanagementsystem_orm.dto.UserDto;
import com.example.hostalmanagementsystem_orm.util.regex.RegExFactory;
import com.example.hostalmanagementsystem_orm.util.regex.RegExType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignupPageController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRptPassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        Stage window = (Stage) pane.getScene().getWindow();
        try {
            window.setAlwaysOnTop(false);
           /* if (checkRegEx()) {*/
                UserDto userDto = new UserDto(txtUsername.getText(), txtPassword.getText());
                userBO.save(userDto);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration Success! ");
                alert.showAndWait();
                clear();
            /*} else {
                new Alert(Alert.AlertType.INFORMATION, "Pattern not match!").showAndWait();
            }*/
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();
        }
        window.setAlwaysOnTop(true);
    }

    private void clear() {
        txtUsername.clear();
        txtPassword.clear();
        txtRptPassword.clear();
    }

    private boolean checkRegEx() throws RuntimeException {
        return RegExFactory.getInstance().getPattern(RegExType.NAME).matcher(txtUsername.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.PASSWORD).matcher(txtPassword.getText()).matches() && txtPassword.getText().equals(txtRptPassword.getText());
    }

}
