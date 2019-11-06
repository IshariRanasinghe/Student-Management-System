package lk.ijse.dep.sms.entity;

public class User implements SuperEntity {
    private String id;
    private String username;
    private String password;
    private String empId;

    public User() {
    }



    public User(String id, String username, String password, String empId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.empId = empId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}
