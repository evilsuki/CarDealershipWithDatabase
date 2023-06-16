package org.yearup.application;

public class CarDealershipApplication
{

    public void run()
    {

    }

    public void displayHomeScreen()
    {
        System.out.println();
        System.out.println("Menu");
        System.out.println("-------------------------------------------------------------------------------------------");
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
}
