package com.example.simplewebapp;

import com.example.simplewebapp.domain.Car;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping("/lot/cars")
    private String parkCar(@RequestBody Car car) {
        return parkingLotService.parkCar(car);
    }

    @GetMapping("/lot/car/{licenseNo}")
    private String isParked(@PathVariable String licenseNo) {
        return parkingLotService.isParked(licenseNo);
    }
}
