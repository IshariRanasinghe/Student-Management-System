package lk.ijse.dep.sms.entity;

import java.util.List;

public class CustomEntity implements SuperEntity {
    private String empId;
    private String name;
    private String gender;
    private String address;
    private String contact;
    private String designation;
    private String userId;
    private String empName;
    private String roles;
    private String username;






    public CustomEntity() {
    }

    public CustomEntity(String empId, String name, String address, String contact, String designation) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.designation = designation;
    }

    public CustomEntity(String name) {
        this.name = name;
    }

    public CustomEntity(String empId, String name, String gender, String address, String contact, String designation) {
        this.empId = empId;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.designation = designation;
    }

    public CustomEntity(String userId, String username,String empName, String roles) {
        this.userId = userId;
        this.username=username;
        this.empName = empName;
        this.roles = roles;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
