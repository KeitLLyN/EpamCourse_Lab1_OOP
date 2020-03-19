import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import car.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger LOG = LogManager.getLogger(TaxiStation.class);
    private static final String fileName = "cars.json";

    public static void main(String[] args) throws Exception {
        TaxiStation taxiStation = new TaxiStation();

        try {
            taxiStation = new TaxiStation(fileName);
        } catch (IOException ex) {
            LOG.info(String.format("Could't read file {%s}: %s", fileName, ex));
        }

        taxiStation.printList();

        System.out.println("Total cost of taxi station : " + taxiStation.getTotalCost());

        System.out.println("\nSorted list by fuel consumption : ");
        List<Car> sortedList = taxiStation.sortListByFuelConsumption();
        taxiStation.printList(sortedList);

        int minSpeed = 0;
        int maxSpeed = 0;
        try {
            minSpeed = taxiStation.findMinSpeed();
            maxSpeed = taxiStation.findMaxSpeed();
        } catch (NoSuchElementException ex) {
            LOG.error("Could't find right number " + ex);
        }

        System.out.println(String.format("Input speed between %d and %d", minSpeed, maxSpeed));
        int start = 0;
        int finish = 0;
        try {
            start = getInput();
            finish = getInput();
        } catch (IOException ex) {
            LOG.info("Wrong input " + ex);
        }

        List<Car> speedCars = taxiStation.findCarsBySpeedRange(start, finish);
        if (speedCars != null) {
            for (var car : speedCars) {
                System.out.println(car);
            }
        }
    }

    public static int getInput() throws IOException {
        int inputNumber;
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                inputNumber = Integer.parseInt(reader.readLine());
                return inputNumber;
            } catch (NumberFormatException e) {
                System.out.println("Enter an Integer number");
            }
        }
    }
}
