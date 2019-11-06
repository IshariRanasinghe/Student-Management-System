package lk.ijse.dep.sms.db;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.dep.crypto.DEPDecoder;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;
    public  static  String host;
    public  static  String username;
    public  static  String password;
    public  static  String database;
    public  static  String port;


    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Properties properties = new Properties();
            File file = new File("resources/application.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            fileInputStream.close();
            String ip = properties.getProperty("sms.ip");
            DBConnection.host=ip;
            String port = properties.getProperty("sms.port");
            DBConnection.port=port;
            String db = properties.getProperty("sms.db");
            DBConnection.database=db;
            String username = DEPDecoder.decode(properties.getProperty("sms.username"),"12345");
            DBConnection.username=username;
            String password = DEPDecoder.decode(properties.getProperty("sms.password"),"12345");
            DBConnection.password=password;

            connection = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/"+db+"?createDatabaseIfNotExist=true&allowMultiQueries=true", username, password);
            PreparedStatement ps = connection.prepareStatement("SHOW TABLES");
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                        String sql ="CREATE TABLE role(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, role VARCHAR(30))ENGINE=INNODB;\n" +
                                "\n" +
                                "CREATE TABLE employee(id VARCHAR(10) PRIMARY KEY, name VARCHAR(100),gender VARCHAR(20),address VARCHAR(150),contact VARCHAR(10),designationId int, FOREIGN KEY (designationId) REFERENCES role(id))ENGINE=INNODB;\n" +
                                "\n" +
                                "\n" +
                                "CREATE TABLE user(id VARCHAR(10) PRIMARY KEY, username VARCHAR(100),password VARCHAR(300),empId VARCHAR(10), FOREIGN KEY (empId) REFERENCES employee(id))ENGINE=INNODB;\n" +
                                "\n" +
                                "CREATE TABLE user_role(userId VARCHAR(10),roleId INT,\n" +
                                "\tCONSTRAINT PRIMARY KEY(userId,roleId),\n" +
                                "\tCONSTRAINT FOREIGN  KEY(userId) REFERENCES user(id),\n" +
                                "\tCONSTRAINT FOREIGN  KEY(roleId)\tREFERENCES role(id))ENGINE=INNODB;\n" +
                                "\n" +
                                "CREATE TABLE student(id VARCHAR(10) PRIMARY KEY,name VARCHAR(100),dobirth DATE,gender VARCHAR(20),address VARCHAR(150),guardian VARCHAR(60),contact VARCHAR(10))ENGINE=INNODB;\n" +
                                "\n" +
                                "INSERT INTO role(role) VALUES('Admin'),('Teacher'),('Receptionist'),('Director of Studies');";
                ps = connection.prepareStatement(sql);
                ps.execute();

            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong,please contact IT team", ButtonType.OK).show();
            Logger.getLogger("lk.ijse.dep.sms").log(Level.SEVERE, null, e);
        }
    }

        public static DBConnection getInstance(){
        return (dbConnection==null) ? (dbConnection=new DBConnection()):dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
