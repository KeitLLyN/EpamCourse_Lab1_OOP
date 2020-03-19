package carTypes;

import car.Car;

public class MiniBus extends Car {
    public MiniBus() {
    }

    public MiniBus(String color, int price, int enginePower,
                   int fuelConsumption, String brand, int speed, int doors, int passengerCapacity) {
        super(color, price, enginePower, fuelConsumption, brand, speed, doors, passengerCapacity);
    }
    @Override
    public String toString(){
        return getType() + super.toString();
    }
    public String getType(){ return "MiniBus";}
}
