package lk.ijse.dep.sms.dto;

public class UserDTO2 {
    private String id;
    private String username;
    private String empName;
    private String role;

    public UserDTO2() {
    }

    public UserDTO2(String id, String username, String empName, String role) {
        this.id = id;
        this.username = username;
        this.empName = empName;
        this.role = role;
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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO2{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", empName='" + empName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
