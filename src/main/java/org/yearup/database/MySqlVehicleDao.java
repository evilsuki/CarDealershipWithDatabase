package org.yearup.database;

import org.yearup.models.Vehicle;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

public class MySqlVehicleDao implements VehicleDao
{
    private DataSource dataSource;

    public MySqlVehicleDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> listByPriceRange(BigDecimal minPrice, BigDecimal maxPrice)
    {
        return null;
    }

    @Override
    public List<Vehicle> listAllVehicle()
    {
        return null;
    }

    @Override
    public List<Vehicle> listByMakeModel(String make, String model)
    {
        return null;
    }

    @Override
    public List<Vehicle> listByYearRange(int minYear, int maxYear)
    {
        return null;
    }

    @Override
    public List<Vehicle> listByColor(String color)
    {
        return null;
    }

    @Override
    public List<Vehicle> listByMileageRange(int minMiles, int maxMiles)
    {
        return null;
    }

    @Override
    public List<Vehicle> listByType(String type)
    {
        return null;
    }

    @Override
    public Vehicle create(Vehicle vehicle)
    {
        return null;
    }

    @Override
    public void update(String vin, Vehicle vehicle)
    {

    }

    @Override
    public void delete(String vin)
    {

    }
}
