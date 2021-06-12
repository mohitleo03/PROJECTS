package org.example.Sourcing;


import org.example.DataEntry.Adhaar;
import org.example.DataEntry.Pan;

public class SourcingData {

    String fname;
    String lname;
    int age;
    Long phoneNumber;
    String email;
    private Address address;
    Adhaar adhaarId;
    Pan panId;

    public void setAdhaarId(Adhaar adhaarId) {
        this.adhaarId = adhaarId;
    }

    public void setPanId(Pan panId) {
        this.panId = panId;
    }

    public SourcingData(String fname, String lname, int age, Long phoneNumber,
                        String email, String loanType, Address address,
                        Adhaar adhaarId, Pan panId) {

        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.address = address;
        this.adhaarId = adhaarId;
        this.panId = panId;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(String houseNumber, String landmark, String city, int pinCode) {
        this.address = new Address(houseNumber,landmark,city,pinCode);
    }



    public Adhaar getAdhaarId() {
        return adhaarId;
    }

    public void setAdhaarId(Long adhaarId) {
        this.adhaarId = new Adhaar(adhaarId);
    }

    public Pan getPanId() {
        return panId;
    }

    public void setPanId(String panId) {
        this.panId = new Pan(panId);
    }

}
