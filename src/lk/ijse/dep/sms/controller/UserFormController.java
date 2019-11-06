package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.sms.business.BOFactory;
import lk.ijse.dep.sms.business.BOTypes;
import lk.ijse.dep.sms.business.custom.EmployeeBO;
import lk.ijse.dep.sms.business.custom.RoleBO;
import lk.ijse.dep.sms.business.custom.UserBO;
import lk.ijse.dep.sms.dto.*;
import lk.ijse.dep.sms.entity.User;
import lk.ijse.dep.sms.util.EmployeeTM;
import lk.ijse.dep.sms.util.UserTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserFormController implements Initializable {

    public TableColumn colRole;
    @FXML
    public JFXTextField txtSearch;
    public Button btnAddToList;
    @FXML
    private AnchorPane userForm;

    @FXML
    private JFXComboBox<String> cmbRoles;

    @FXML
    private JFXListView<String> lstViewRoles;

    @FXML
    private JFXComboBox<String> cmbEmployee;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colEmployee;

    @FXML
    private TableColumn<?, ?> colUsername;


    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnNew;

    private UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private RoleBO roleBO = BOFactory.getInstance().getBO(BOTypes.ROLE);

    List<RoleDTO> rolesList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblUser.setFocusTraversable(false);

        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("username"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("empName"));
        tblUser.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("role"));


        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        btnRemove.setDisable(true);
        txtId.setDisable(true);
        txtUsername.setDisable(true);
        txtConfirmPassword.setDisable(true);
        txtPassword.setDisable(true);
        cmbEmployee.setDisable(true);
        cmbRoles.setDisable(true);
        lstViewRoles.setDisable(true);
        btnAddToList.setDisable(true);

        loadAllUsers();

        //Get Employees

        ObservableList<String> employees = null;
        try {
            employees = FXCollections.observableArrayList(employeeBO.employeesNotUsers());
            cmbEmployee.setItems(employees);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.UserFormController").log(Level.SEVERE,null,e);
        }


        try {
            List<RoleDTO> roles1 = roleBO.getRoles();
            cmbRoles.getItems().clear();
            ObservableList<String> cmbItems = cmbRoles.getItems();
            for (RoleDTO dto : roles1) {
                cmbItems.add(dto.getRole());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.UserFormController").log(Level.SEVERE,null,e);
        }

        //Search
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                loadAllUsers();
            }
        });

        cmbRoles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnAddToList.setDisable(false);
            }
        });

        lstViewRoles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnRemove.setDisable(false);
            }
        });

      /*  txtRePassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(txtRePassword.getText()!= txtPassword.getText()){
                    lblInfo.setText("Password doesn't match");
                }else if(txtRePassword.getText()== txtPassword.getText()){
                    lblInfo.setText("Password matches");
                }

            }
        });*/


    }

    public void generateUserId(){
        try {
            int maxId;
            String lastId = userBO.getLastId();
            if(lastId==null){
                maxId=0;
            }
            maxId =Integer.parseInt( lastId.replace("U",""));
            maxId++;
            String id="";
            if(maxId<10){
                id="U000"+maxId;
            }else if(maxId<100){
                id="U00"+maxId;
            }else if (maxId<1000){
                id="U0"+maxId;
            }else{
                id="U"+maxId;
            }
            txtId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.UserFormController").log(Level.SEVERE,null,e);
        }
    }

    public void loadAllUsers(){
        try {
            ObservableList<UserTM> users = FXCollections.observableArrayList();
            List<UserDTO2> userDTOS = userBO.usersList("%"+txtSearch.getText()+"%");
            System.out.println(userDTOS);
            if(userDTOS != null) {
                for (UserDTO2 user : userDTOS) {
                    users.add(new UserTM(user.getId(), user.getUsername(), user.getEmpName(), user.getRole()));
                }
                tblUser.setItems(users);
                tblUser.refresh();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.UserFormController").log(Level.SEVERE,null,e);
        }
    }

    @FXML
    void btnDelete_OnAction(ActionEvent event) {

    }

    @FXML
    void btnNew_OnAction(ActionEvent event) {
        btnAdd.setDisable(false);
        txtUsername.setDisable(false);
        txtConfirmPassword.setDisable(false);
        cmbEmployee.setDisable(false);
        cmbRoles.setDisable(false);
        txtPassword.setDisable(false);
        reset();
    }

    public void reset(){
        txtId.clear();
        txtUsername.clear();
        txtConfirmPassword.clear();
        txtPassword.clear();
        cmbRoles.getSelectionModel().clearSelection();
        cmbEmployee.getSelectionModel().clearSelection();
        lstViewRoles.getItems().clear();
        cmbEmployee.requestFocus();
        generateUserId();
    }

    public void btnAddToList_OnAction(ActionEvent actionEvent) {

        lstViewRoles.setDisable(false);
        String selectedRole = cmbRoles.getSelectionModel().getSelectedItem();
        lstViewRoles.getItems().add(selectedRole);
        try {
            Integer roleId = roleBO.getRoleId(selectedRole);
            rolesList.add(new RoleDTO(roleId,selectedRole));
            System.out.println(rolesList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.UserFormController").log(Level.SEVERE,null,e);
        }
        cmbRoles.getItems().remove(selectedRole);
    }

    @FXML
    void btnRemove_OnAction(ActionEvent event) {
        String selectedRole = lstViewRoles.getSelectionModel().getSelectedItem();
        cmbRoles.getItems().add(selectedRole);
        lstViewRoles.getItems().remove(selectedRole);
        for(RoleDTO role :rolesList){
            if (role.getRole().equals(selectedRole)){
                rolesList.remove(role);
                //System.out.println(rolesList.size());
                return;
            }
        }

    }

    @FXML
    void btnSave_OnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String selectedEmployee = cmbEmployee.getSelectionModel().getSelectedItem();
        String employeeId="";
        try {
             employeeId = employeeBO.getEmployeeId(selectedEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(cmbEmployee.getSelectionModel().getSelectedIndex()==-1){
            new Alert(Alert.AlertType.ERROR,"Please select employee", ButtonType.OK).show();
        }
        else if(!username.matches("^[a-z]{5,}$")){
            new Alert(Alert.AlertType.ERROR,"Inavalid Username", ButtonType.OK).show();

        }else if(!password.matches("[A-Za-z0-9#$%^&*_]{5,}")){
            new Alert(Alert.AlertType.ERROR,"Inavalid password! You can use only letters,numbers and #$%^&*_ only.", ButtonType.OK).show();

        }else if(!confirmPassword.equals(password)){
            new Alert(Alert.AlertType.ERROR,"Password doesn't match", ButtonType.OK).show();

        }else if(lstViewRoles.getItems()==null){
            new Alert(Alert.AlertType.ERROR,"Please select roles", ButtonType.OK).show();

        }else{
            UserDTO3 userDTO = new UserDTO3(txtId.getText(),employeeId,username,password,rolesList);
            System.out.println("lll");
            if(btnAdd.getText().equals("Save")){
                try {
                    boolean result = userBO.createUserAccount(userDTO);
                    System.out.println(result);
                    if(result){
                        new Alert(Alert.AlertType.ERROR,"Success", ButtonType.OK).show();
                        loadAllUsers();
                        reset();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{

            }
        }
    }




}
