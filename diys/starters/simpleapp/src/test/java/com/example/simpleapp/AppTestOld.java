package com.example.simpleapp;

import com.example.simpleapp.exceptions.CarLotFullException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTestOld
{
    static ParkingLot parkingLot;


    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(3);
    }

    @After
    public void tearDown() throws Exception {
        parkingLot.reset();
    }

    @Test
    public void simplePark() {
        Exception ex = null;

        Exception exception = doTest(() -> {
            parkingLot.parkCar(new Car("1234", "John"));
            assertTrue(parkingLot.isParked("1234"));
        });

        assertTrue(exception == null);
    }

    @Test
    public void parkToFull() {
        Exception ex = null;

        Exception exception = doTest(() -> {
            parkingLot.parkCar(new Car("1234", "John"));
            assertTrue(parkingLot.isParked("1234"));
            parkingLot.parkCar(new Car("1235", "John"));
            assertTrue(parkingLot.isParked("1235"));
            parkingLot.parkCar(new Car("1236", "John"));
            assertTrue(parkingLot.isParked("1236"));
        });

        assertTrue(exception == null);
    }

    @Test
    public void testLotOverflow() {
        Exception ex = null;
        Random rand = new Random();

        Exception exception = doTest(() -> {
            for(int i=0; i<3; i++) {
                String license = String.valueOf(Calendar.getInstance().getTime().getTime())+String.valueOf(rand.nextInt());
                parkingLot.parkCar(new Car(license, "John"));
                assertTrue(parkingLot.isParked(license));
            }
        });

        assertTrue(exception == null);

        exception = doTest(() -> {
            String license = String.valueOf(Calendar.getInstance().getTime().getTime());
            parkingLot.parkCar(new Car(license, "John"));
            assertTrue(parkingLot.isParked(license));
        });

        assertTrue(exception instanceof CarLotFullException);
    }




    private Exception doTest(MyConsumer consumer) {
        try {
            consumer.accept();
        } catch (Exception e) {
            return e;
        }

        return null;
    }

}
