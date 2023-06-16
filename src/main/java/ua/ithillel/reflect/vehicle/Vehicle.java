package ua.ithillel.reflect.vehicle;

public abstract class Vehicle {
    private VehicleType type;
    private String vin;

    public Vehicle(VehicleType type, String vin) {
        this.type = type;
        this.vin = vin;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
