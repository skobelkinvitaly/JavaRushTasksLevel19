package com.javarush.task.task29.task2909.car;

public class Truck extends Car{
    public Truck( int numberOfPassengers) {
        super(TRUCK, numberOfPassengers);
//        super(type, numberOfPassengers);
//        create(TRUCK, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
//        return super.getMaxSpeed();
        return 80;
    }
}
