package org.yearup.ui;

import org.yearup.database.DealershipDao;
import org.yearup.database.VehicleDao;
import org.yearup.models.Dealership;
import org.yearup.models.Vehicle;
import org.yearup.utilities.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    private final Scanner sc = new Scanner(System.in);
    private final VehicleDao vehicleDao;
    private final DealershipDao dealershipDao;

    public UserInterface(VehicleDao vehicleDao, DealershipDao dealershipDao)
    {
        this.vehicleDao = vehicleDao;
        this.dealershipDao = dealershipDao;
    }


    public void run()
    {
        while(true)
        {
            System.out.println();
            displayHomeScreen();

            try
            {
                int selection = getUserInputInt("Make a selection:");
                System.out.println();

                switch (selection)
                {
                    case 1 -> processGetByPrice();
                    case 2 -> processGetByMakeModel();
                    case 3 -> processGetByYear();
                    case 4 -> processGetByColor();
                    case 5 -> processGetByMileage();
                    case 6 -> processGetByVehicleType();
                    case 7 -> processGetAllVehicles();
                    case 8 -> processAddVehicle();
                    case 9 -> processDeleteVehicle();
                    case 0 -> processSaleOrLeaseVehicle();
                    case 99 ->
                    {
                        System.out.println("Exiting....");
                        System.out.println("Good Bye!");
                        return;
                    }
                    default -> System.out.println("Not a valid selection.");
                }
            }
            catch(Exception ex)
            {
                System.out.println();
                Logger.logError(ex);
            }
        }
    }


    public String getUserInputString(String message)
    {
        System.out.print(message + " ");
        return sc.nextLine().strip();
    }

    public int getUserInputInt(String message)
    {
        return Integer.parseInt(getUserInputString(message));
    }

    public BigDecimal getUserInputBigDecimal(String message)
    {
        return new BigDecimal(getUserInputString(message));
    }

    public void displayHomeScreen()
    {
        System.out.println();
        System.out.println("Menu");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("\t 1) Search by price range");
        System.out.println("\t 2) Search by make / model");
        System.out.println("\t 3) Search by year range");
        System.out.println("\t 4) Search by color");
        System.out.println("\t 5) Search by mileage range");
        System.out.println("\t 6) Search by type (sedan, truck , SUV, coupe)");
        System.out.println("\t 7) List all vehicles");
        System.out.println("\t 8) Add vehicle");
        System.out.println("\t 9) Remove vehicle");
        System.out.println("\t 0) Sell/Lease vehicle");
        System.out.println("\t 99) Exit");
    }


    private void processSaleOrLeaseVehicle()
    {
    }

    private void processDeleteVehicle()
    {
        System.out.println("Remove Vehicle");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println();

        String vin = getUserInputString("Enter vin of the car to remove: ");

        Vehicle vehicle = vehicleDao.getByVin(vin);

        printVehicle(vehicle);
        System.out.println();
        String answer = getUserInputString("Are you sure? (y/n):");

        if(answer.equalsIgnoreCase("y"))
        {
            vehicleDao.delete(vin);

            System.out.printf("Vehicle %s was successfully removed.\n", vin);
        }
    }

    private void processAddVehicle()
    {
        System.out.println("Add New Vehicle");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println();

        int dealershipId = getUserInputInt("Enter ID of dealership you want to add vehicle:");
        String vin = getUserInputString("Enter vin:");
        String make = getUserInputString("Enter make:");
        String model = getUserInputString("Enter model:");
        String color = getUserInputString("Enter color:");
        String type = getUserInputString("Enter type:");
        int year = getUserInputInt("Enter year:");
        int miles = getUserInputInt("Enter miles:");
        BigDecimal price = getUserInputBigDecimal("Enter price:");
        boolean isSold = false;

        Vehicle vehicle = new Vehicle()
        {{
            setVin(vin);
            setMake(make);
            setModel(model);
            setColor(color);
            setType(type);
            setYear(year);
            setMiles(miles);
            setPrice(price);
            setSold(isSold);
        }};

        vehicleDao.create(vehicle, dealershipId);

        System.out.println();
        System.out.printf("%s %s has been added.\n", vehicle.getMake(),vehicle.getModel());
        System.out.println();
    }

    private void processGetAllVehicles()
    {
        displayDealership();

        int dealershipId = getUserInputInt("Enter ID for Dealership you want:");

        List<Vehicle> results = vehicleDao.listAllVehicle(dealershipId);

        System.out.println();
        System.out.println("List All Vehicles ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    private void processGetByVehicleType()
    {
        displayDealership();

        int dealershipId = getUserInputInt("Enter ID for Dealership you want:");
        String type = getUserInputString("Enter the vehicle type: ");

        // get all cars from the dealership
        List<Vehicle> results = vehicleDao.listByType(type, dealershipId);

        // display the vehicles
        System.out.println("Search by Vehicle Type: " + type);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    private void processGetByMileage()
    {
        displayDealership();

        int dealershipId = getUserInputInt("Enter ID for Dealership you want:");
        int min = getUserInputInt("Enter the lowest mileage:");
        int max = getUserInputInt("Enter the highest mileage:");

        // get all cars from the dealership
        List<Vehicle> results = vehicleDao.listByMileageRange(min, max, dealershipId);

        // display the vehicles
        System.out.println("Search by Mileage: " + min + " - " + max);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    private void processGetByColor()
    {
        displayDealership();

        int dealershipId = getUserInputInt("Enter ID for Dealership you want:");
        String color = getUserInputString("Enter the color:");

        // get all cars from the dealership
        List<Vehicle> results = vehicleDao.listByColor(color, dealershipId);

        // display the vehicles
        System.out.println("Search by Color: " + color);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    private void processGetByYear()
    {
        displayDealership();

        int dealershipId = getUserInputInt("Enter ID for Dealership you want:");
        int min = getUserInputInt("Enter the lowest year:");
        int max = getUserInputInt("Enter the highest year:");

        // get all cars from the dealership
        List<Vehicle> results = vehicleDao.listByYearRange(min, max, dealershipId);

        // display the vehicles
        System.out.println("Search by Year: " + min + " - " + max);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    private void processGetByMakeModel()
    {
        displayDealership();

        int dealershipId = getUserInputInt("Enter ID for Dealership you want:");
        String make = getUserInputString("Enter the make:");
        String model = getUserInputString("Enter the model:");

        // get all cars from the dealership
        List<Vehicle> results = vehicleDao.listByMakeModel(make, model, dealershipId);

        // display the vehicles
        System.out.println("Search by Make / Model: " + make + " / " + model);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    private void processGetByPrice()
    {
        displayDealership();

        int dealershipId = getUserInputInt("Enter ID for Dealership you want:");
        BigDecimal min = getUserInputBigDecimal("Enter the lowest price:");
        BigDecimal max = getUserInputBigDecimal("Enter the highest price:");

        // get all cars from the dealership
        List<Vehicle> results = vehicleDao.listByPriceRange(min, max, dealershipId);

        // display the vehicles
        System.out.println("Search by Price: " + min + " - " + max);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    private void displaySearchResults(List<Vehicle> results)
    {
        System.out.printf("%-20s %-8s %-11s %-11s %-10s %-10s %-13s %s \n", "VIN", "Make", "Model", "Color", "Type", "Year", "Odometer", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        if(results.size() == 0)
        {
            System.out.println("No search results.");
            return;
        }

        for(Vehicle vehicle: results)
        {
            System.out.printf("%-20s %-8s %-11s %-11s %-10s %-10d %-13d $ %.2f \n", vehicle.getVin(), vehicle.getMake(), vehicle.getModel(), vehicle.getColor(), vehicle.getType(), vehicle.getYear(), vehicle.getMiles(), vehicle.getPrice());
        }

        System.out.println("============================================================================================================================");
        System.out.println();
    }

    private void displayDealership()
    {
        List<Dealership> dealerships = dealershipDao.getDealership();
        System.out.println();
        System.out.println("Dealerships List");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-40s %s \n", "ID", "Name", "Address", "Phone");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        for (Dealership dealership : dealerships)
        {
            System.out.printf("%-5d %-20s %-40s %s \n", dealership.getDealershipId(), dealership.getName(), dealership.getAddress(), dealership.getPhone());
        }

        System.out.println("============================================================================================================================");
        System.out.println();
    }

    private void printVehicle(Vehicle vehicle)
    {
        System.out.printf("%-20s %-8s %-11s %-11s %-10s %-10s %-13s %s \n", "VIN", "Make", "Model", "Color", "Type", "Year", "Odometer", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-8s %-11s %-11s %-10s %-10d %-13d $ %.2f \n", vehicle.getVin(), vehicle.getMake(), vehicle.getModel(), vehicle.getColor(), vehicle.getType(), vehicle.getYear(), vehicle.getMiles(), vehicle.getPrice());
    }
}
