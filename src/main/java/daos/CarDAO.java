package daos;

import main.ConnectionFactory;
import models.Car;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CarDAO implements CarDaoInterface{
    @Override
    public Car findById(int id) {
        // making connection with mysql
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE id=" + id);

            if(rs.next())
            {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setVin(rs.getInt("vin"));

                return car;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        List<Car> cars = new LinkedList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars");

            while(rs.next()){
                Car car = new Car();

                car.setId(rs.getInt("id"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setVin(rs.getInt("vin"));

                cars.add(car);
            }
        }
        catch(SQLException e){
            System.out.println("Can't find all!");
        }
        return cars;
    }

    @Override
    public Car update(Car dto) {
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE cars SET make=?, model=?, year=?, vin=? WHERE id=?");
            ps.setString(1, dto.getMake());
            ps.setString(2, dto.getModel());
            ps.setInt(3, dto.getYear());
            ps.setInt(4, dto.getVin());
            ps.setInt(5, dto.getId());


            int i = ps.executeUpdate();

            if(i == 1){
                return findById(dto.getId());
            }
        }
        catch(SQLException e){
            System.out.println("Couldn't update!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car create(Car dto) {
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO cars VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, dto.getId());
            ps.setString(2, dto.getMake());
            ps.setString(3, dto.getModel());
            ps.setInt(4, dto.getYear());
            ps.setInt(5, dto.getVin());

            int i = ps.executeUpdate();

            if(i == 1){
                return findById(dto.getId());
            }
        }
        catch(SQLException e){
            System.out.println("Couldn't create!");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = ConnectionFactory.getConnection();

        try{
            Statement stmt = connection.createStatement();;
            int i = stmt.executeUpdate("DELETE FROM cars WHERE id=" + id);

            if(i > 0){
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
