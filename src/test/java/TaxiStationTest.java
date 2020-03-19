import car.Car;
import carTypes.Hatchback;
import carTypes.MiniBus;
import carTypes.Sedan;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.beans.Expression;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaxiStationTest extends TaxiStation{
    private TaxiStation taxiStation;


    @Before
    public void setUp()throws ParseException,IOException {
        taxiStation = new TaxiStation("test.json");
    }

    @Test
    public void shouldReturnNull_WhenGetCarPool(){
        List<Car> car = new ArrayList<>();
        car.add(null);
        Assert.assertEquals(car,taxiStation.getCarPool());
    }

    @Test
    public void shouldReturnMiniBus_WhenGetCarPool(){
        var actual = new MiniBus().getType();
        var expected = taxiStation.carBuild(null,0,0,
                0,null,0,2,9).getType();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnSedan_WhenGetCarPool(){
        var actual = new Sedan().getType();
        var expected = taxiStation.carBuild(null,0,0,
                0,null,0,4,5).getType();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnHatchback_WhenGetCarPool(){
        var actual = new Hatchback().getType();
        var expected = taxiStation.carBuild(null,0,0,
                0,null,0,2,5).getType();

        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void shouldThrowException_WhenReadJson()throws Exception {
        taxiStation.readJson("");
    }

    @Test
    public void shouldReturnZero_WhenGetTotalCost(){
        TaxiStation ts = new TaxiStation();
        int expected = 0;
        int actual = ts.getTotalCost();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnNull_WhenFindCarsBySpeedRange(){
        Assert.assertNull(taxiStation.findCarsBySpeedRange(100, 50));
    }
}
