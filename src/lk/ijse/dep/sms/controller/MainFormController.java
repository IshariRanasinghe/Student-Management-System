package lk.ijse.dep.sms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFormController {



    @FXML
    public AnchorPane formSpace;
    public AnchorPane mainWindow;
    public TitledPane tpnDatabase;
    public TitledPane tpnPayment;
    public Button btnUser;

    public void initialize(){
        tpnDatabase.setDisable(true);
        try {
            Parent p = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/sms/view/HomeForm.fxml"));
            formSpace.getChildren().add(p);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.LoginFormController").log(Level.SEVERE,null,e);
        }
        List<String> userRoles = LoginFormController.userRoles;
        if(!(userRoles.contains("Admin")||userRoles.contains("Director of Studies")||userRoles.contains("Receptionist"))){
            tpnPayment.setDisable(true);
        }
        if(!(userRoles.contains("Admin")||userRoles.contains("Director of Studies"))){
            btnUser.setDisable(true);
        }
        if(userRoles.contains("Admin")){
            tpnDatabase.setDisable(false);
        }

    }

    public void btnEmployee_OnAction(ActionEvent actionEvent) {

        try {
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/EmployeeForm.fxml");
            Parent p=  FXMLLoader.load(resource);
            formSpace.getChildren().clear();
            formSpace.getChildren().add(p);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.LoginFormController").log(Level.SEVERE,null,e);
        }
    }

    public void btnUser_OnAction(ActionEvent actionEvent) {
        try {
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/UserForm.fxml");
            Parent p=  FXMLLoader.load(resource);
            formSpace.getChildren().clear();
            formSpace.getChildren().add(p);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.LoginFormController").log(Level.SEVERE,null,e);
        }
    }

    public void tpnHome_MC(MouseEvent mouseEvent) {
        try {
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/HomeForm.fxml");
            Parent p=  FXMLLoader.load(resource);
            formSpace.getChildren().clear();
            formSpace.getChildren().add(p);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.LoginFormController").log(Level.SEVERE,null,e);
        }
    }

    public void tpnStudent_MC(MouseEvent mouseEvent) {
        try {
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/StudentForm.fxml");
            Parent p=  FXMLLoader.load(resource);
            formSpace.getChildren().clear();
            formSpace.getChildren().add(p);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.LoginFormController").log(Level.SEVERE,null,e);
        }
    }

    public void iconLogout_MC(MouseEvent mouseEvent) {
        try {
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/LoginForm.fxml");
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            Stage primaryStage1 = (Stage) mainWindow.getScene().getWindow();
            primaryStage1.close();
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.LoginFormController").log(Level.SEVERE,null,e);
        }
    }

    public void tpnReports_MC(MouseEvent mouseEvent) {
        try {
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/ReportForm.fxml");
            Parent p=  FXMLLoader.load(resource);
            formSpace.getChildren().clear();
            formSpace.getChildren().add(p);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("controller.LoginFormController").log(Level.SEVERE,null,e);
        }
    }
}
