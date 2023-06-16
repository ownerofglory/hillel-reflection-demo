package ua.ithillel.reflect.vehicle;

import ua.ithillel.reflect.anno.MyAnnotation;

@MyAnnotation
public final class Car extends Vehicle {
    private int seats;
    private final int example = 1;
    private boolean leftSide; // steering wheel on the left

    {
        // dynamic init block
    }

    static {
        // static init block
    }

    public Car(String vin) {
        super(VehicleType.CAR, vin);
    }

    public Car() {
        super(VehicleType.CAR, "");
    }

    @MyAnnotation(count = 1)
    public int getSeats() {
        return seats;
    }

    @MyAnnotation("setSeats")
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @MyAnnotation(value = "isLeftSide", count = 2)
    public boolean isLeftSide() {
        return leftSide;
    }

    public void setLeftSide(boolean leftSide) {
        this.leftSide = leftSide;
    }

    private void repair() {
        System.out.println("Repairing a car");
    }
}
