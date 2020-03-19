package car;

public abstract class Car {
    private String brand;
    private String color;
    private int enginePower;
    private int fuelConsumption;
    private int price;
    private int speed;
    private int doors;
    private int passengerCapacity;

    protected Car(){
        this.fuelConsumption = 0;
        this.enginePower = 0;
        this.color = null;
        this.price = 0;
        this.brand = null;
        this.speed = 0;
        this.doors = 0;
        this.passengerCapacity = 0;
    }

    protected Car(String color, int price, int enginePower,
                  int fuelConsumption, String brand, int speed, int doors, int passengerCapacity){
        this.fuelConsumption = fuelConsumption;
        this.enginePower = enginePower;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.speed = speed;
        this.doors = doors;
        this.passengerCapacity = passengerCapacity;
    }

    public String getColor() { return color;}
    public String getBrand() { return brand;}
    public int getEnginePower() { return enginePower;}
    public int getFuelConsumption() { return fuelConsumption;}
    public int getPrice() { return price; }
    public int getSpeed() {return speed; }
    public int getDoors() {return doors;}
    public int getPassengerCapacity() {return passengerCapacity;}

    public void setBrand(String brand) {this.brand = brand;}
    public void setColor(String color) {this.color = color; }
    public void setEnginePower(int enginePower) {this.enginePower = enginePower;}
    public void setFuelConsumption(int fuelConsumption) {this.fuelConsumption = fuelConsumption;}
    public void setPrice(int price) {this.price = price;}
    public void setSpeed(int speed) {this.speed = speed;}
    public void setDoors(int doors) {this.doors = doors;}
    public void setPassengerCapacity(int passengerCapacity) {this.passengerCapacity = passengerCapacity;}

    @Override
    public String toString() {
        return "{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", enginePower=" + enginePower +
                ", fuelConsumption=" + fuelConsumption +
                ", price=" + price +
                ", speed=" + speed +
                ", doors=" + doors +
                ", passengerCapacity=" + passengerCapacity +
                '}';
    }

    public void move(){
        System.out.println(String.format("%s %s is moving to the target", color, brand));
    }

    public abstract String getType();

}
