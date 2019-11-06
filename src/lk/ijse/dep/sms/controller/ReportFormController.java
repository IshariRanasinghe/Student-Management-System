package lk.ijse.dep.sms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ReportFormController {

    public AnchorPane reportView;

    public void btnStuInfo_OnAction(ActionEvent actionEvent) {

        try {
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/StudentInfoForm.fxml");
            Parent p=FXMLLoader.load(resource);
            reportView.getChildren().clear();
            reportView.getChildren().add(p);
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
