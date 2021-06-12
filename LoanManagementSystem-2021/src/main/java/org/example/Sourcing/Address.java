package org.example.Sourcing;

public class Address {
    String houseno;
    String landmark;
    String City;
    int pinCode;

    public Address(String houseno, String landmark, String city, int pinCode) {
        this.houseno = houseno;
        this.landmark = landmark;
        this.City = city;
        this.pinCode = pinCode;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

}
