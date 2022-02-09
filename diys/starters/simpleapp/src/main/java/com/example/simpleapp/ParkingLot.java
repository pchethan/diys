package com.example.simpleapp;

import com.example.simpleapp.exceptions.CarDoesNotExistInLotException;
import com.example.simpleapp.exceptions.CarExistsInLotException;
import com.example.simpleapp.exceptions.CarLotFullException;
import com.example.simpleapp.exceptions.InvalidArgumentException;
import com.sun.tools.corba.se.idl.InvalidArgument;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    ConcurrentHashMap<String, Car> plot = new ConcurrentHashMap<>();
    int capacity = 0;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void parkCar(Car car) throws CarExistsInLotException, CarLotFullException {
//        if(car == null || car.getLicenseNo() == null) {
//            throw new InvalidArgumentException();
//        }
        //check if exists
        if(plot.containsKey(car.getLicenseNo())) {
            //car is already in plot
            throw new CarExistsInLotException();
        }

        if(plot.entrySet().size() >= capacity) {
            throw new CarLotFullException();
        }

        plot.put(car.getLicenseNo(), car);
    }

    public boolean isParked(String licenseNo) {
//        if(car == null || car.getLicenseNo() != null) {
//            throw new InvalidArgumentException();
//        }

        if(plot.containsKey(licenseNo)) {
            return true;
        }
        return false;
    }

    public void exitCar(Car car)
            throws CarDoesNotExistInLotException {
//        if(car == null || car.getLicenseNo() != null) {
//            throw new InvalidArgumentException();
//        }
        //check if it does not exist
        if(!plot.containsKey(car.getLicenseNo())) {
            //car is already in plot
            throw new CarDoesNotExistInLotException();
        }

        plot.remove(car.getLicenseNo());
    }

    public void reset() {
        plot.clear();
    }

}
