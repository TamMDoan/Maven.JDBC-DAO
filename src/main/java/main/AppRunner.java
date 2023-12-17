package main;

import daos.CarDAO;
import models.Car;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class AppRunner {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do{
            System.out.println("What would you like to do?\n" +
                    "1. Create\n" +
                    "2. FindById\n" +
                    "3. GetAllCars\n" +
                    "4. Update\n" +
                    "5. Delete\n" +
                    "6. Quit\n");

            input = scanner.nextLine();
            crudOperations(input);

        } while(!input.equals("6"));

    }

    public static void crudOperations(String input){
        CarDAO carDAO = new CarDAO();

        switch (input){
            case "1":
                createCar(carDAO);
                break;
            case "2":
                findCarById(carDAO);
                break;
            case "3":
                getAllCars(carDAO);
                break;
            case "4":
                updateCar(carDAO);
                break;
            case "5":
                deleteCar(carDAO);
                break;
            case "6":
                System.out.println("Goodbye ~");
                break;
            default:
                System.out.println("Try another number.");
                break;

        }
    }

    public static void createCar(CarDAO carDAO){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> carDetails = new ArrayList<>();
        carDetails.add("id");
        carDetails.add("make");
        carDetails.add("model");
        carDetails.add("year");
        carDetails.add("vin");
        LinkedList<String> inputs = new LinkedList<>();

        for(String s: carDetails){
            System.out.printf("Enter the car's %s: ", s);
            inputs.add(scanner.nextLine());
        }

        Car car = new Car(Integer.parseInt(inputs.get(0)), inputs.get(1), inputs.get(2), Integer.parseInt(inputs.get(3)), Integer.parseInt(inputs.get(4)));
        carDAO.create(car);
    }

    public static void findCarById(CarDAO carDAO){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");

        System.out.println(carDAO.findById(Integer.parseInt(scanner.nextLine())).toString());
    }

    public static void getAllCars(CarDAO carDAO){
        System.out.println("\t---- CARS ----");
        for(Car car: carDAO.findAll()){
            System.out.println(car.toString());
        }
    }

    public static void updateCar(CarDAO carDAO){
        // ask for id of car to update
        // get the car
        // ask what they'd like to update
        // update it in the car
        // once done, car dao
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the car to update: ");
        int id = scanner.nextInt();

        if(carDAO.findById(id) != null){
            Car car = carDAO.findById(id);
            int input = 0;

            while (input != 4) {
                System.out.println("What would you like to update?\n" +
                        "1. Make\t2. Model\t3. Year\t4. Exit\n");
                input = scanner.nextInt();

                switch (input) {
                    case 1:
                        // ask for new make, set car make as that
                        car.setMake(getValueForCar("Make"));
                        break;
                    case 2:
                        car.setModel(getValueForCar("Model"));
                        break;
                    case 3:
                        car.setYear(Integer.parseInt(getValueForCar("Year")));
                        break;
                    case 4:
                        System.out.println("Update finished...");
                        break;
                    default:
                        System.out.println("Try a valid number!");
                        break;
                }
            }

            carDAO.update(car);
        }
        else{
            System.out.println("Invalid car id, try again!");
        }

    }

    public static String getValueForCar(String field){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter new %s value for car: ", field);
        String input = scanner.nextLine();

        return input;
    }

    public static void deleteCar(CarDAO carDAO){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of car ");
        int id = scanner.nextInt();

        carDAO.delete(id);
        System.out.printf("Car with id %d deleted...\n", id);
    }
}
