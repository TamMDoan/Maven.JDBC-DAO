package daos;

import models.Car;

import java.util.List;

public interface CarDaoInterface {
    Car findById(int id);
    List<Car> findAll();
    Car update(Car dto);
    Car create(Car dto);
    boolean delete(int id);
}
