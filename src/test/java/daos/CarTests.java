package daos;

import models.Car;
import models.CarDtoInterface;
import org.junit.Assert;
import org.junit.Test;

public class CarTests {

    @Test
    public void testCarInstanceOf(){
        Car car = new Car();
        Assert.assertTrue(car instanceof CarDtoInterface);
    }

    @Test
    public void testCarConstructor(){
        Car car = new Car();
        Assert.assertNotNull(car);
    }

    @Test
    public void testCarConstructorIdMakeModelParam(){
        int id = 1;
        String make = "Toyota";
        String model = "Camry";

        Car car = new Car(id, make, model);
        Assert.assertNotNull(car);
    }

    @Test
    public void testCarConstructorAllParams(){
        int id = 1;
        String make = "Toyota";
        String model = "Camry";
        int year = 2013;
        int vin = 123424;

        Car car = new Car(id, make, model, year, vin);
        Assert.assertNotNull(car);
    }

}
