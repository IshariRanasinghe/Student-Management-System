package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import lk.ijse.dep.sms.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Map;

public class StudentInfoFormController {
    public JFXTextField txtStudentId;


    public void btnView_OnAction(ActionEvent actionEvent) {
        JasperDesign jasperDesign = null;
        try {
            jasperDesign = JRXmlLoader.load(this.getClass().getResourceAsStream("/lk/ijse/dep/sms/reports/StudentInfo.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> params = new HashMap<>();
            params.put("studentId", txtStudentId.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
