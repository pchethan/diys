package com.example.simplewebapp.domain;

public class Car {
    String licenseNo;
    String driver;

    public Car() {
    }

    public Car(String licenseNo, String driver) {
        this.licenseNo = licenseNo;
        this.driver = driver;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
