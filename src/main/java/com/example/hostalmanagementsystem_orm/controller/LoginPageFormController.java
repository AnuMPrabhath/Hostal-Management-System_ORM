package com.example.hostalmanagementsystem_orm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageFormController {

    @FXML
    private AnchorPane login;

    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private RadioButton rBtnShowPassword;

    @FXML
    private Label lblPassword;

    public void txtPasswordOnKeyReleased(KeyEvent keyEvent) {

    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        //        if (checkRegEx()) {
            try {
//                UserDTO dto = new UserDTO(txtUserName.getText(), txtPassword.getText());
//                UserDTO user = userBo.view(dto.getUserName());
//                if (user.getUserName().equals(txtUserName.getText()) && user.getPassword().equals(txtPassword.getText())) {
        Stage window = (Stage) login.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBordForm.fxml"))));
//                }
            } catch (RuntimeException | IOException exception) {
                new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
            }
//        } else {
//            new Alert(Alert.AlertType.INFORMATION, "Invalid Input!").show();
//        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        login.setFocusTraversable(false);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.centerOnScreen();
        stage.requestFocus();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignUpPage.fxml"))));
        stage.setTitle("Signup");
        stage.showAndWait();
        login.setFocusTraversable(true);
        login.setDisable(false);
    }

    public void rBtnOnMouseClicked(MouseEvent mouseEvent) {

    }
}
