package lk.ijse.dep.sms.entity;

public enum Gender {

    MALE("male"),FEMALE("female"),NO("no");

   String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getValue(){
        return this.gender;
    }
}
