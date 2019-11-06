package lk.ijse.dep.sms.util;

import lk.ijse.dep.sms.entity.Gender;

import java.sql.Date;

public class StudentTM {
    private String id;
    private String name;
    private String address;
    private String guardian;
    private String contact;

    public StudentTM() {
    }


    public StudentTM(String id, String name, String address, String guardian, String contact) {
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
                ", address='" + address + '\'' +
                ", guardian='" + guardian + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
