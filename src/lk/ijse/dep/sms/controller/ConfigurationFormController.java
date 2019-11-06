package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.sms.business.BOFactory;
import lk.ijse.dep.sms.business.BOTypes;
import lk.ijse.dep.sms.business.custom.EmployeeBO;
import lk.ijse.dep.sms.business.custom.UserBO;
import lk.ijse.dep.sms.dto.EmployeeDTO;
import lk.ijse.dep.sms.dto.UserDTO;
import lk.ijse.dep.sms.dto.UserRoleDTO;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationFormController {

    public AnchorPane configurationForm;
    public JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtUsername;

    

    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);

    @FXML
    void btnClear_OnAction(ActionEvent event) {
        txtUsername.clear();
        txtPassword.clear();
        txtUsername.requestFocus();
    }

    @FXML
    void btnConfigure_OnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password =txtPassword.getText();
        if(!username.matches("^([A-Z][a-z]+\\s?)+$")){
            new Alert(Alert.AlertType.ERROR,"Invalid User Name", ButtonType.OK).show();
        }else if(!password.matches("([A-Z]|[a-z]|[0-9]){5,}")){
            new Alert(Alert.AlertType.ERROR,"Invalid Password", ButtonType.OK).show();
        }else{
            UserDTO userDTO =new UserDTO (username,password);
            /* EmployeeDTO employee= new EmployeeDTO("E0001",username);
             UserDTO user = new UserDTO("U0001","admin",password,"E0001");
             UserRoleDTO userRoles = new UserRoleDTO("U0001",1);*/
            try {
                boolean b = userBO.configureUser(userDTO);
                if(b){
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Configured", ButtonType.OK).show();

                    URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/LoginForm.fxml");
                    Parent root = FXMLLoader.load(resource);
                    Scene scene = new Scene(root);
                    Stage primaryStage =(Stage) configurationForm.getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.centerOnScreen();
                    primaryStage.setResizable(false);
                    primaryStage.show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
                Logger.getLogger("lk.ijse.dep.sms").log(Level.SEVERE, null, e);
            }
        }



    }

}
