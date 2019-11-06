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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.dep.sms.business.BOFactory;
import lk.ijse.dep.sms.business.BOTypes;
import lk.ijse.dep.sms.business.custom.UserBO;
import lk.ijse.dep.sms.business.custom.impl.UserBOImpl;
import lk.ijse.dep.sms.db.DBConnection;
import lk.ijse.dep.sms.dto.UserDTO;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFormController {

    @FXML
    private AnchorPane loginForm;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    public JFXPasswordField txtPassword;

    private UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);

    public static List<String> userRoles;


    @FXML
    void btnClear_OnAction(ActionEvent event) {
        txtUsername.clear();
        txtPassword.clear();
        txtUsername.requestFocus();

    }

    @FXML
    void btnLogin_OnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDTO user = new UserDTO(username,password);

        try {
            String result = userBO.isUser(user);
            if(result==null){
                new Alert(Alert.AlertType.ERROR, "Please enter valid login credentials!", ButtonType.OK).show();
            }else{
                userRoles= userBO.getUserRoles(result);
                URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/MainForm.fxml");
                Parent root = FXMLLoader.load(resource);
                Scene scene = new Scene(root);
                Stage primaryStage1 = (Stage)loginForm.getScene().getWindow();
                primaryStage1.close();
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
                System.out.println(userRoles);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("lk.ijse.dep.sms").log(Level.SEVERE, null, e);
        }


    }

    /*public void btnBackup_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Backup DB");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL File","*.sql"));
        File file = fileChooser.showSaveDialog(loginForm.getScene().getWindow());
        if(file!=null){
            try {
                Process process = Runtime.getRuntime().exec("mysqldump -h"+DBConnection.host+" -u"+DBConnection.username+
                        " -p"+ DBConnection.password+" --port "+DBConnection.port+" "+DBConnection.database+" --result-file "+file.getAbsolutePath()
                        +((file.getAbsolutePath().endsWith(".sql"))?"":".sql"));

                int exitCode=process.waitFor();
                if(exitCode !=0){
                    InputStream errorStream = process.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(errorStream);
                    BufferedReader br = new BufferedReader(isr);
                    String out ="";
                    String line=null;
                    while((line=br.readLine())!=null){
                        out+=line+"\n";
                    }
                    System.out.println(out);
                }else{
                    new Alert(Alert.AlertType.CONFIRMATION,"Success", ButtonType.OK).show();
                }
                System.out.println(exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnRestore_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Restore DB");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showOpenDialog(loginForm.getScene().getWindow());
        if (file != null) {
            try {
                String [] commands;
                commands = new String[]{"mysql","-h", DBConnection.host,"-u",DBConnection.username,"--port",DBConnection.port,"-p"+DBConnection.password,DBConnection.database,
                        "-e","source "+file.getAbsolutePath()};
                Process process = Runtime.getRuntime().exec(commands);
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    InputStream errorStream = process.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(errorStream);
                    BufferedReader br = new BufferedReader(isr);
                    String out = "";
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        out += line + "\n";
                    }
                    System.out.println(out);
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Success", ButtonType.OK).show();
                }
                System.out.println(exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
