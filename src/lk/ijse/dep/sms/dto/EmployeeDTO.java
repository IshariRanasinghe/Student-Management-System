package lk.ijse.dep.sms.dto;

import lk.ijse.dep.sms.entity.Gender;

public class EmployeeDTO {
    private String id;
    private String name;
    private Gender gender;
    private String address;
    private String contact;
    private String designation;
    private Integer designationId;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String name, Gender gender, String address, String contact, String designation) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.designation = designation;
    }

    public EmployeeDTO(String id, String name, Gender gender, String address, String contact, Integer designationId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.designationId = designationId;
    }

    public EmployeeDTO(String id, String name, String address, String contact, String designation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.designation = designation;
    }

    public EmployeeDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmployeeDTO(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public Integer getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Integer designationId) {
        this.designationId = designationId;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", designation=" + designation +
                '}';
    }
}
