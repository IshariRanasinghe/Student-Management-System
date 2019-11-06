package lk.ijse.dep.sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lk.ijse.dep.sms.db.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    Connection connection = DBConnection.getInstance().getConnection();

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //Root Logger
            Logger rootLogger = Logger.getLogger("");
            FileHandler fileHandler = new FileHandler("error.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/ConfigurationForm.fxml");
                Parent root = FXMLLoader.load(resource);
                Scene mainScene = new Scene(root);
                primaryStage.setScene(mainScene);
                primaryStage.setTitle("School of English, Mattegoda");
                primaryStage.centerOnScreen();
                primaryStage.setResizable(false);
                primaryStage.show();

            }else{
                URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/LoginForm.fxml");
                Parent root = FXMLLoader.load(resource);
                Scene mainscene = new Scene(root);
                primaryStage.setScene(mainscene);
                primaryStage.setTitle("School of English, Mattegoda");
                primaryStage.centerOnScreen();
                primaryStage.setResizable(false);
                primaryStage.show();

            }



        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("lk.ijse.dep.sms").log(Level.SEVERE,null,e);
        }
    }

}
