package lk.ijse.dep.sms.util;

public class EmployeeTM {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String designation;

    public EmployeeTM(String id) {
        this.id = id;
    }

    public EmployeeTM(String id, String name, String address, String contact, String designation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.designation = designation;
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

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
