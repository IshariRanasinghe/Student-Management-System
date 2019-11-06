package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
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
import lk.ijse.dep.sms.business.custom.EmployeeBO;
import lk.ijse.dep.sms.business.custom.RoleBO;
import lk.ijse.dep.sms.dao.custom.RoleDAO;
import lk.ijse.dep.sms.dto.EmployeeDTO;
import lk.ijse.dep.sms.dto.RoleDTO;
import lk.ijse.dep.sms.entity.Gender;
import lk.ijse.dep.sms.util.EmployeeTM;
import lk.ijse.dep.sms.util.StudentTM;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeFormController implements Initializable{

    @FXML
    private JFXComboBox<String> cmbDesignation;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDesignation;

    @FXML
    private JFXRadioButton rdoMale;

    @FXML
    private ToggleGroup t1;

    @FXML
    private JFXRadioButton rdoFemale;

    @FXML
    private JFXTextField txtSearch;



    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private RoleBO roleBO = BOFactory.getInstance().getBO(BOTypes.ROLE);


    @FXML
    Button btnNew;
    @FXML
    Button btnAdd;
    @FXML
    Button btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> userRoles = LoginFormController.userRoles;
        if(!userRoles.contains("Admin")){
            btnDelete.setVisible(false);
            if(!(userRoles.contains("Admin")||userRoles.contains("Director of Studies"))){
                btnNew.setVisible(false);
                btnAdd.setVisible(false);
            }
        }

        tblEmployee.setFocusTraversable(false);

        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("designation"));

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        cmbDesignation.setDisable(true);
        rdoFemale.setDisable(true);
        rdoMale.setDisable(true);
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        loadAllEmployees();

        //load designations
        ObservableList<String> deignations = cmbDesignation.getItems();
        try {
            List<String> designationDTOS = roleBO.roleswithoutAdmin();
            ObservableList<String> designations1 = FXCollections.observableArrayList(designationDTOS);
            cmbDesignation.setItems(designations1);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.EmployeeFormController").log(Level.SEVERE,null,e);
        }


        tblEmployee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EmployeeTM>() {
            @Override
            public void changed(ObservableValue<? extends EmployeeTM> observable, EmployeeTM oldValue, EmployeeTM newValue) {
              //  String selectedEmployee = tblEmployee.getSelectionModel().getSelectedItem().getId();

                if (newValue == null) {
                    btnAdd.setText("Save");
                    btnDelete.setDisable(true);
                } else {
                    btnAdd.setText("Update");
                    btnDelete.setDisable(false);
                    btnAdd.setDisable(false);

                    try {
                        EmployeeDTO employee = employeeBO.findEmployee(newValue.getId());
                        txtId.setText(employee.getId());
                        txtName.setText(employee.getName());
                        txtAddress.setText(employee.getAddress());
                        txtContact.setText(employee.getContact());
                        cmbDesignation.getSelectionModel().select(employee.getDesignationId() - 2);
                        Gender gender =employee.getGender();
                        if (gender.getValue().equalsIgnoreCase("FEMALE")){
                            rdoFemale.setSelected(true);
                        }else{
                            rdoMale.setSelected(true);
                        }
                        txtName.setDisable(false);
                        txtContact.setDisable(false);
                        txtAddress.setDisable(false);
                        rdoMale.setDisable(false);
                        rdoFemale.setDisable(false);
                        cmbDesignation.setDisable(false);
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
                        Logger.getLogger("controller.EmployeeFormController").log(Level.SEVERE,null,e);
                    }
                }
            }
        });

        //Search Employee
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                loadAllEmployees();
            }
        });
    }

    public void loadAllEmployees(){
        try {
            ObservableList<EmployeeTM> employees = FXCollections.observableArrayList();
            List<EmployeeDTO> employeeDTOS = employeeBO.findAllEmployees("%"+txtSearch.getText()+"%");
            if(employeeDTOS != null) {
                for (EmployeeDTO employee : employeeDTOS) {
                    employees.add(new EmployeeTM(employee.getId(), employee.getName(), employee.getAddress(), employee.getContact(), employee.getDesignation()));
                }
                tblEmployee.setItems(employees);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateEmployeeId(){
        try {
            int maxId;
            String lastId = employeeBO.getLastId();
            if(lastId==null){
                maxId=0;
            }
            maxId =Integer.parseInt( lastId.replace("E",""));
            maxId++;
            String id="";
            if(maxId<10){
                id="E000"+maxId;
            }else if(maxId<100){
                id="E00"+maxId;
            }else if (maxId<1000){
                id="E0"+maxId;
            }else{
                id="E"+maxId;
            }
            txtId.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAdd_OnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        if(!name.matches("^([A-Z][a-z]*[.]?[\\\\s]?)*([A-Z][a-z]*)$")){
            new Alert(Alert.AlertType.ERROR,"Inavalid Name", ButtonType.OK).show();

        }else if(!address.matches("^([\\w\\/\\-,\\s]{2,})$")){
            new Alert(Alert.AlertType.ERROR,"Inavalid Address", ButtonType.OK).show();

        }else if(!contact.matches("^\\d{10}$")){
            new Alert(Alert.AlertType.ERROR,"Inavalid Contact Number", ButtonType.OK).show();

        }else if(cmbDesignation.getSelectionModel().getSelectedIndex()==-1) {
            new Alert(Alert.AlertType.ERROR,"Please Select Designation", ButtonType.OK).show();

        }else{
            EmployeeDTO employeeDTO;
            if(btnAdd.getText().equals("Save")){
                if(rdoMale.isSelected()) {
                    employeeDTO = new EmployeeDTO(txtId.getText(), name, Gender.MALE, address, contact, cmbDesignation.getSelectionModel().getSelectedIndex() + 2);
                }else{
                    employeeDTO = new EmployeeDTO(txtId.getText(), name, Gender.FEMALE, address, contact, cmbDesignation.getSelectionModel().getSelectedIndex() + 2);
                }
                try {
                    boolean result = employeeBO.saveEmployee(employeeDTO);
                    if(result){
                        new Alert(Alert.AlertType.CONFIRMATION,"Sucessfully Saved",ButtonType.OK).show();
                        reset();
                        loadAllEmployees();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Couldn't save Employe, Please Retry",ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
                    Logger.getLogger("controller.EmployeeFormController").log(Level.SEVERE,null,e);
                }
            }else{
                if(rdoMale.isSelected()) {
                    employeeDTO = new EmployeeDTO(txtId.getText(), name, Gender.MALE, address, contact, cmbDesignation.getSelectionModel().getSelectedIndex() + 2);
                }else{
                    employeeDTO = new EmployeeDTO(txtId.getText(), name, Gender.FEMALE, address, contact, cmbDesignation.getSelectionModel().getSelectedIndex() + 2);
                }
                try {
                    boolean result = employeeBO.updateEmployee(employeeDTO);
                    if(result){
                        new Alert(Alert.AlertType.CONFIRMATION,"Sucessfully Updated",ButtonType.OK).show();
                        reset();
                        loadAllEmployees();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Couldn't update Employe, Please Retry",ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
                    Logger.getLogger("controller.EmployeeFormController").log(Level.SEVERE,null,e);
                }
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
            EmployeeTM selectedEmployee = tblEmployee.getSelectionModel().getSelectedItem();
            try {
                boolean result = employeeBO.deleteEmployee(selectedEmployee.getId());
                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted",ButtonType.OK).show();
                    loadAllEmployees();
                    reset();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Couldn't delete Employe, Please Retry",ButtonType.OK).show();
                }
            }catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
                Logger.getLogger("controller.EmployeeFormController").log(Level.SEVERE,null,e);
            }
        }
    }

    @FXML
    void btnNew_OnAction(ActionEvent event) {

        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        cmbDesignation.setDisable(false);
        rdoFemale.setDisable(false);
        rdoMale.setDisable(false);
        btnAdd.setDisable(false);
        reset();

    }

    void reset(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        rdoMale.setSelected(true);
        cmbDesignation.getSelectionModel().clearSelection();
        txtName.requestFocus();
        generateEmployeeId();
        tblEmployee.getSelectionModel().clearSelection();
    }


}
