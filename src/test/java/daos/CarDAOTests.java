package daos;

import models.Car;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CarDAOTests {

    @Test
    public void testFindById(){
        int id = 1;
        CarDAO carDAO = new CarDAO();

        Car actual = carDAO.findById(id);

        Assert.assertNotNull(actual);
        Assert.assertEquals(id, actual.getId());
    }

    @Test
    public void testFindAll(){
        CarDAO carDAO = new CarDAO();

        List<Car> cars = carDAO.findAll();

        Assert.assertFalse(cars.isEmpty());
        Assert.assertTrue(cars.size() >=4);
    }

    @Test
    public void testUpdate(){
        Car car = new Car(1, "Honda", "Accord", 2014, 123456);
        CarDAO carDAO = new CarDAO();

        Car updatedCar = carDAO.update(car);

        Assert.assertEquals(updatedCar.getYear(), car.getYear());
    }

    @Test
    public void testCreate(){
        Car car = new Car(7, "Honda", "Accord", 2022, 987661);
        CarDAO carDAO = new CarDAO();

        Car newCar = carDAO.create(car);

        Assert.assertEquals(car.getId(), newCar.getId());
        Assert.assertEquals(car.getMake(), newCar.getMake());
        Assert.assertEquals(car.getModel(), newCar.getModel());
        Assert.assertEquals(car.getYear(), newCar.getYear());
        Assert.assertEquals(car.getVin(), newCar.getVin());
    }

    @Test
    public void testDelete(){
        int id = 7;
        Car car = new Car(7, "Honda", "Accord", 2022, 987661);
        CarDAO carDAO = new CarDAO();

        Car created = carDAO.create(car);

        boolean deleted = carDAO.delete(id);

        Assert.assertNotNull(created);
        Assert.assertTrue(deleted);
    }
}
