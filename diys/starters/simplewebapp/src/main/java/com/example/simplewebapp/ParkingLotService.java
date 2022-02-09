package com.example.simplewebapp;

import com.example.simplewebapp.domain.Car;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotService {

    ConcurrentHashMap<String, Car> plot = new ConcurrentHashMap<>();

    public String parkCar(Car car) {
        plot.put(car.getLicenseNo(), car);
        return "Parked!";
    }

    public String isParked(String licenseNo) {
        if(!plot.containsKey(licenseNo)) {
            return "Not found";
        }
        return "Parked by "+ plot.get(licenseNo).getDriver();
    }
}
