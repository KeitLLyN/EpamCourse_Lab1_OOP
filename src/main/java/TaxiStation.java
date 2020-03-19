import car.Car;
import carTypes.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TaxiStation {
    private int totalCost;
    private String fileName;
    private List<Car> carPool;
    private static final Logger LOG = LogManager.getLogger(TaxiStation.class);

    public TaxiStation() {
        fileName = null;
        totalCost = 0;
        carPool = new ArrayList<>();
    }

    public TaxiStation(String fileName) throws ParseException, IOException {
        this.fileName = fileName;
        setCarPool(makeListOfCars(this.fileName));
        LOG.info("Taxi station was created");
    }

    public List<Car> getCarPool() {
        return carPool;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void printList() {
        for (var car : carPool) {
            System.out.println(car);
        }
    }

    public void printList(List<Car> cars) {
        for (var car : cars) {
            System.out.println(car);
        }
    }

    public List<Car> sortListByFuelConsumption() {
        List<Car> sortedList = new ArrayList<>(carPool);
        sortedList.sort(Comparator.comparing(Car::getFuelConsumption));
        LOG.info("Sorting carpool by fuel consumption");
        return sortedList;
    }

    public List<Car> findCarsBySpeedRange(int start, int finish) {
        if (start > finish) return null;
        List<Car> speedCars = new ArrayList<>();
        for (var car : carPool) {
            if (car.getSpeed() >= start && car.getSpeed() <= finish) speedCars.add(car);
        }
        LOG.info(String.format("Cars were found by speed range from %s to %s", start, finish));
        return speedCars;
    }

    public int findMinSpeed() throws NoSuchElementException {
        return Collections.min(carPool, Comparator.comparing(Car::getSpeed)).getSpeed();
    }

    public int findMaxSpeed() throws NoSuchElementException {
        return Collections.max(carPool, Comparator.comparing(Car::getSpeed)).getSpeed();
    }

    private List<Car> makeListOfCars(String fileName) throws ParseException, IOException {
        List<Car> cars = new ArrayList<>();
        Car currentCar;
        JSONArray jsonArray = (JSONArray) readJson(fileName);
        LOG.info(String.format("%s was opened", fileName));
        for (Object car : jsonArray) {
            JSONObject json = (JSONObject) car;

            String brand = (String) json.get("brand");
            String color = (String) json.get("color");
            int enginePower = Integer.parseInt(String.valueOf(json.get("enginePower")));
            int fuelConsumption = Integer.parseInt(String.valueOf(json.get("fuelConsumption")));
            int price = Integer.parseInt(String.valueOf(json.get("price")));
            int speed = Integer.parseInt(String.valueOf(json.get("speed")));
            int doors = Integer.parseInt(String.valueOf(json.get("doors")));
            int passengerCapacity = Integer.parseInt(String.valueOf(json.get("passengerCapacity")));
            currentCar = carBuild(color, price, enginePower, fuelConsumption, brand, speed, doors, passengerCapacity);
            cars.add(currentCar);
            LOG.info(String.format("%s(%s) was added to car list", currentCar.getType(), currentCar.getBrand()));
            totalCost += price;
        }
        return cars;
    }

    private void setCarPool(List<Car> cars) {
        if (cars != null) {
            carPool = new ArrayList<>(cars);
            LOG.info("Carpool was created");
        }
    }


    protected Car carBuild(String color, int price, int enginePower,
                           int fuelConsumption, String brand, int speed, int doors, int passengerCapacity) {
        if (doors < 4 && passengerCapacity <= 5)
            return new Hatchback(color, price, enginePower, fuelConsumption, brand, speed, doors, passengerCapacity);
        if (passengerCapacity < 9 && doors == 4)
            return new Sedan(color, price, enginePower, fuelConsumption, brand, speed, doors, passengerCapacity);
        if (passengerCapacity >= 9)
            return new MiniBus(color, price, enginePower, fuelConsumption, brand, speed, doors, passengerCapacity);
        return null;
    }

    protected Object readJson(String filename) throws ParseException, IOException {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }
}
