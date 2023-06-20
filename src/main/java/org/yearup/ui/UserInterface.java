package org.yearup.ui;

import org.yearup.models.Dealership;
import org.yearup.models.Vehicle;
import org.yearup.utilities.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface
{
    private final Scanner sc = new Scanner(System.in);
    public void run()
    {
        while(true)
        {
            System.out.println();
            displayHomeScreen();

            try
            {
                int selection = getUserInputInt("Make a selection: ");
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
        System.out.print(message);
        return sc.nextLine().strip();
    }

    public int getUserInputInt(String message)
    {
        return Integer.parseInt(getUserInputString(message));
    }

    public double getUserInputDouble(String message)
    {
        return Double.parseDouble(getUserInputString(message));
    }

    public void displayHomeScreen()
    {
        System.out.println();
        System.out.println("Menu");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
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
    }

    private void processAddVehicle()
    {
    }

    private void processGetAllVehicles()
    {
    }

    private void processGetByVehicleType()
    {
    }

    private void processGetByMileage()
    {
    }

    private void processGetByColor()
    {
    }

    private void processGetByYear()
    {
    }

    private void processGetByMakeModel()
    {
    }

    private void processGetByPrice()
    {
    }

    private void displaySearchResults(ArrayList<Vehicle> results)
    {
        System.out.printf("%-9s %-8s %-11s %-11s %-10s %-10s %-13s %s \n", "VIN", "Make", "Model", "Color", "Type", "Year", "Odometer", "Price");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

        if(results.size() == 0)
        {
            System.out.println("No search results.");
            return;
        }

        for(Vehicle vehicle: results)
        {
            System.out.printf("%-9s %-8s %-11s %-11s %-10s %-10d %-13d $ %.2f \n", vehicle.getVin(), vehicle.getMake(), vehicle.getModel(), vehicle.getColor(), vehicle.getType(), vehicle.getYear(), vehicle.getMiles(), vehicle.getPrice());
        }

        System.out.println("=======================================================================================================================================");
        System.out.println();
    }
}
