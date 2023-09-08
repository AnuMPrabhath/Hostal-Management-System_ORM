package com.example.hostalmanagementsystem_orm.controller;

import com.example.hostalmanagementsystem_orm.bo.BOFactory;
import com.example.hostalmanagementsystem_orm.bo.custom.StudentBO;
import com.example.hostalmanagementsystem_orm.dto.StudentDto;
import com.example.hostalmanagementsystem_orm.tm.StudentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UnpaidCaseFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<StudentTm> tblStudents;

    @FXML
    private TableColumn<StudentTm, String> colStdId;

    @FXML
    private TableColumn<StudentTm, String> colStdName;

    @FXML
    private TableColumn<StudentTm, String> colStdAddress;

    @FXML
    private TableColumn<StudentTm, String> colMobile;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/reservationForm.fxml")));
    }

    @FXML
    void tblStudentsOnMouseClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStdId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colStdAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        refreshUnpaidStudentTable();
    }

    private void refreshUnpaidStudentTable() {
        try {
            List<StudentDto> list = studentBO.getUnpaidStudents();
            ObservableList<StudentTm> stdTmList = FXCollections.observableArrayList();
            for (StudentDto ele : list) {
                StudentTm studentTm = new StudentTm();
                studentTm.setStudent_id(ele.getStudent_id());
                studentTm.setName(ele.getName());
                studentTm.setAddress(ele.getAddress());
                studentTm.setContact_no(ele.getContact_no());
                stdTmList.add(studentTm);
            }
            tblStudents.setItems(stdTmList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
        }
    }
}
