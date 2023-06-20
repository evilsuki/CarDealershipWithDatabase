package org.yearup.application;

import org.apache.commons.dbcp2.BasicDataSource;
import org.yearup.database.*;
import org.yearup.ui.UserInterface;

public class CarDealershipApplication
{
    public static void main(String[] args)
    {
        String baseUrl = "jdbc:mysql://localhost:3306/car_dealership";
        String username = "root";
        String password = "P@ssw0rd";

        BasicDataSource dataSource = new BasicDataSource()
        {{
            setUrl(baseUrl);
            setUsername(username);
            setPassword(password);
        }};

        VehicleDao vehicleDao = new MySqlVehicleDao(dataSource);
        DealershipDao dealershipDao = new MySqlDealershipDao(dataSource);
        LeaseDao leaseDao = new MySqLeaseDao(dataSource);
        SalesDao salesDao = new MySqlSalesDao(dataSource);

        UserInterface app = new UserInterface(vehicleDao, dealershipDao, salesDao, leaseDao);
        app.run();
    }
}