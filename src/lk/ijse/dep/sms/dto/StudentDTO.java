package lk.ijse.dep.sms.dto;

import lk.ijse.dep.sms.entity.Gender;

import java.sql.Date;

public class StudentDTO {
    private String id;
    private String name;
    private Date dobirth;
    private Gender gender;
    private String address;
    private String guardian;
    private String contact;

    public StudentDTO() {
    }

    public StudentDTO(String id, String name, Date dobirth, Gender gender, String address, String guardian, String contact) {
        this.id = id;
        this.name = name;
        this.dobirth = dobirth;
        this.gender = gender;
        this.address = address;
        this.guardian = guardian;
        this.contact = contact;
    }

    public StudentDTO(String id, String name, String address, String guardian, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.guardian = guardian;
        this.contact = contact;
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

    public Date getDobirth() {
        return dobirth;
    }

    public void setDobirth(Date dobirth) {
        this.dobirth = dobirth;
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

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dobirth=" + dobirth +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", guardian='" + guardian + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
