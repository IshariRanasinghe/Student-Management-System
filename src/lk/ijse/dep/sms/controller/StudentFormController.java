package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep.sms.business.BOFactory;
import lk.ijse.dep.sms.business.BOTypes;
import lk.ijse.dep.sms.business.custom.StudentBO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.entity.Gender;
import lk.ijse.dep.sms.util.StudentTM;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentFormController implements Initializable {

    public Button btnUpdate;
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colGuardian;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private Button btnNew;

    @FXML
    private DatePicker dtpBirth;

    @FXML
    private RadioButton rdbMale;

    @FXML
    private RadioButton rdbFemale;

    @FXML
    private JFXTextField txtGuardian;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtSearch;


    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblStudent.setFocusTraversable(false);

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("guardian"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));

        List<String> userRoles = LoginFormController.userRoles;
        if(!userRoles.contains("Admin")){
            btnDelete.setVisible(false);
        }
        if(!(userRoles.contains("Admin")||userRoles.contains("Director of Studies")||userRoles.contains("Receptionist"))){
            btnNew.setVisible(false);
            btnAdd.setVisible(false);
        }

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtGuardian.setDisable(true);
        rdbFemale.setDisable(true);
        rdbMale.setDisable(true);
        dtpBirth.setDisable(true);
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        loadAllStudents();

       /* dtpBirth.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

            }
        });*/

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                loadAllStudents();
            }
        });

        tblStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {

                try {
                    StudentDTO studentDTO = studentBO.find(newValue.getId());
                    reset();
                    btnDelete.setDisable(false);
                    txtId.setText(studentDTO.getId());
                    txtName.setText(studentDTO.getName());
                    dtpBirth.setValue(studentDTO.getDobirth().toLocalDate());
                    txtAddress.setText(studentDTO.getAddress());
                    txtGuardian.setText(studentDTO.getGuardian());
                    txtContact.setText(studentDTO.getContact());
                    Gender gender = studentDTO.getGender();
                    //System.out.println(gender);

                    if (gender.getValue().equalsIgnoreCase("FEMALE")) {
                        rdbFemale.setSelected(true);
                    } else {
                        rdbMale.setSelected(true);
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
                    Logger.getLogger("sms.StudentController").log(Level.SEVERE, null, e);
                }
            }
        });
    }

    public void loadAllStudents() {

        ObservableList<StudentTM> students = FXCollections.observableArrayList();
        try {
            List<StudentDTO> studentDTOS = studentBO.findAllStudents("%" + txtSearch.getText() + "%");
            if (studentDTOS != null) {
                for (StudentDTO student : studentDTOS) {
                    students.add(new StudentTM(student.getId(), student.getName(), student.getAddress(), student.getGuardian(), student.getContact()));
                }
            }

            tblStudent.setItems(students);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("sms.StudentController").log(Level.SEVERE, null, e);
        }
    }

    public void generateStudentId() {

        try {
            int maxId;
            String lastId = studentBO.getLastId();
            if (lastId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastId.replace("S", ""));
            }
            maxId++;
            String id = "";
            if (maxId < 10) {
                id = "S000" + maxId;
            } else if (maxId < 100) {
                id = "S00" + maxId;
            } else if (maxId < 1000) {
                id = "S0" + maxId;
            } else {
                id = "S" + maxId;
            }
            txtId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("sms.StudentController").log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void btnAdd_OnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String guardian = txtGuardian.getText();

        if (!name.matches("^[A-Za-z]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name", ButtonType.OK).show();

        } else if (dtpBirth.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please Select Date of Birth", ButtonType.OK).show();
        } else if (!address.matches("^([\\w\\/\\-,\\s]{2,})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address", ButtonType.OK).show();

        } else if (!guardian.matches("^[A-Za-z]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Guardian Name", ButtonType.OK).show();

        } else if (!contact.matches("^\\d{10}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number", ButtonType.OK).show();

        } else {
            StudentDTO student = null;
            if (rdbMale.isSelected()) {
                student = new StudentDTO(txtId.getText(), name,
                        java.sql.Date.valueOf(dtpBirth.getValue()), Gender.MALE, address, txtGuardian.getText(), contact);
            } else {
                student = new StudentDTO(txtId.getText(), name,
                        java.sql.Date.valueOf(dtpBirth.getValue()), Gender.FEMALE, address, txtGuardian.getText(), contact);
            }
            try {
                boolean result = studentBO.saveStudent(student);
                if (result) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Sucessfully Saved", ButtonType.OK).show();
                    btnNew_OnAction(event);
                    loadAllStudents();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
                Logger.getLogger("sms.StudentController").log(Level.SEVERE, null, e);
            }
        }
    }


    @FXML
    void btnDelete_OnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            try {
                boolean result = studentBO.deleteStudent(selectedItem.getId());
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted", ButtonType.OK).show();
                    btnNew_OnAction(event);
                    loadAllStudents();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
                Logger.getLogger("sms.StudentController").log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void btnNew_OnAction(ActionEvent event) {
        tblStudent.getSelectionModel().clearSelection();
        reset();

    }

    public void reset() {
        btnAdd.setDisable(false);
        txtName.clear();
        txtAddress.clear();
        txtGuardian.clear();
        txtContact.clear();
        txtSearch.clear();
        dtpBirth.setValue(null);
        txtName.setDisable(false);
        txtName.requestFocus();
        txtContact.setDisable(false);
        txtGuardian.setDisable(false);
        txtAddress.setDisable(false);
        dtpBirth.setDisable(false);
        rdbMale.setDisable(false);
        rdbFemale.setDisable(false);
        generateStudentId();
    }


    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String guardian = txtGuardian.getText();

        if (!name.matches("^[A-Za-z]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name", ButtonType.OK).show();

        } else if (dtpBirth.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please Select Date of Birth", ButtonType.OK).show();
        } else if (!address.matches("^([\\w\\/\\-,\\s]{2,})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address", ButtonType.OK).show();

        } else if (!guardian.matches("^[A-Za-z]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Guardian Name", ButtonType.OK).show();

        } else if (!contact.matches("^\\d{10}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number", ButtonType.OK).show();

        } else {
            StudentDTO student;
            if (rdbMale.isSelected()) {
                student = new StudentDTO(txtId.getText(), name,
                        java.sql.Date.valueOf(dtpBirth.getValue()), Gender.MALE, address, txtGuardian.getText(), contact);
            } else {
                student = new StudentDTO(txtId.getText(), name,
                        java.sql.Date.valueOf(dtpBirth.getValue()), Gender.FEMALE, address, txtGuardian.getText(), contact);
            }
            try {
                boolean result = studentBO.updateStudent(student);
                if (result) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Sucessfully Updated", ButtonType.OK).show();
                    btnNew_OnAction(actionEvent);
                    loadAllStudents();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
                Logger.getLogger("sms.StudentController").log(Level.SEVERE, null, e);
            }
        }
    }

}
