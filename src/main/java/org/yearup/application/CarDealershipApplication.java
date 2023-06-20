package org.yearup.application;

import org.apache.commons.dbcp2.BasicDataSource;
import org.yearup.database.DealershipDao;
import org.yearup.database.MySqlDealershipDao;
import org.yearup.ui.UserInterface;
import org.yearup.database.MySqlVehicleDao;
import org.yearup.database.VehicleDao;

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

        UserInterface app = new UserInterface(vehicleDao, dealershipDao);
        app.run();
    }
}