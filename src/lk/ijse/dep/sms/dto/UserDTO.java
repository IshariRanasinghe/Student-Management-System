package lk.ijse.dep.sms.dto;

import java.util.List;

public class UserDTO {
    private String id;
    private String employeeId;
    private String username;
    private String password;
    List<RoleDTO> roleDTOS;



    public UserDTO() {
    }

    public UserDTO(String id, String username, String password, String employeeId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(String id, String employeeId, String username, String password, List<RoleDTO> roleDTOS) {
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
