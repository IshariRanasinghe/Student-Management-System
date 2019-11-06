package lk.ijse.dep.sms.dto;

import java.util.List;

public class UserDTO3 {
    private String id;
    private String  employeeId;
    private String username;
    private String  password;
    private List<RoleDTO> roleDTOS;

    public UserDTO3() {
    }

    public UserDTO3(String id, String employeeId, String username, String password, List<RoleDTO> roleDTOS) {
        this.id = id;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.roleDTOS = roleDTOS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public List<RoleDTO> getRoleDTOS() {
        return roleDTOS;
    }

    public void setRoleDTOS(List<RoleDTO> roleDTOS) {
        this.roleDTOS = roleDTOS;
    }

    @Override
    public String toString() {
        return "UserDTO3{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleDTOS=" + roleDTOS +
                '}';
    }
}
