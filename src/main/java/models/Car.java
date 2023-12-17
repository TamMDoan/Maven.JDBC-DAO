package models;

public class Car implements CarDtoInterface {
    private int id;
    private String make;
    private String model;
    private int year;
    private int vin;

    public Car(){}
    public Car(int id, String make, String model) {
        this.id = id;
        this.make = make;
        this.model = model;
    }

    public Car(int id, String make, String model, int year, int vin) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    @Override
    public String toString(){
        String formattedString = "Id: %3d\tMake: %6s\tModel: %6s\tYear: %4d\t VIN: %7d\n";

        return String.format(formattedString, this.id, this.make, this.model, this.year, this.vin);
    }
}
