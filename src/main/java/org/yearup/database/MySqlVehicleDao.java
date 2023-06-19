package org.yearup.database;

import org.yearup.models.Vehicle;
import org.yearup.utilities.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVehicleDao implements VehicleDao
{
    private final DataSource dataSource;

    public MySqlVehicleDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> listByPriceRange(BigDecimal minPrice, BigDecimal maxPrice)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = "CALL VehicleByPriceRange(?, ?);";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql))
        {
            statement.setBigDecimal(1, minPrice);
            statement.setBigDecimal(2, maxPrice);

            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = mapRow(row);
                vehicles.add(vehicle);
            }
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return vehicles;
    }


    @Override
    public List<Vehicle> listAllVehicle()
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
                SELECT v.*
                     , d.name
                FROM vehicles AS v
                	, dealerships AS d
                    , inventory AS i
                WHERE i.vin = v.vin
                	AND i.dealership_id = d.dealership_id;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Vehicle vehicle = mapRow(row);
                vehicles.add(vehicle);
            }
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> listByMakeModel(String make, String model)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        return vehicles;
    }

    @Override
    public List<Vehicle> listByYearRange(int minYear, int maxYear)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        return vehicles;
    }

    @Override
    public List<Vehicle> listByColor(String color)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        return vehicles;
    }

    @Override
    public List<Vehicle> listByMileageRange(int minMiles, int maxMiles)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        return vehicles;
    }

    @Override
    public List<Vehicle> listByType(String type)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        return vehicles;
    }

    @Override
    public Vehicle create(Vehicle vehicle, int dealershipId)
    {
        String sql = "CALL VehiclesInsert(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql))
        {
            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setString(4, vehicle.getColor());
            statement.setString(5, vehicle.getType());
            statement.setInt(6, vehicle.getYear());
            statement.setInt(7, vehicle.getMiles());
            statement.setBigDecimal(8, vehicle.getPrice());
            statement.setBoolean(9, vehicle.isSold());
            statement.setInt(10, dealershipId);

            statement.execute();
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return vehicle;
    }

    @Override
    public void update(String vin, Vehicle vehicle)
    {
        String sql = """
                UPDATE vehicles
                SET make = ?
                    , model = ?
                    , color = ?
                    , type = ?
                    , year = ?
                    , miles = ?
                    , price = ?
                    , sold = ?
                WHERE vin = ?;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, vehicle.getMake());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getColor());
            statement.setString(4, vehicle.getType());
            statement.setInt(5, vehicle.getYear());
            statement.setInt(6, vehicle.getMiles());
            statement.setBigDecimal(7, vehicle.getPrice());
            statement.setBoolean(8, vehicle.isSold());
            statement.setString(9, vin);

            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }
    }

    @Override
    public void delete(String vin)
    {
        String sql = """
                DELETE FROM vehicles
                WHERE vin = ?;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, vin);

            statement.executeUpdate();

        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }
    }

    private Vehicle mapRow(ResultSet row)
    {
        try
        {
            String vin = row.getString("vin");
            String make = row.getString("make");
            String model = row.getString("model");
            String color = row.getString("color");
            String type = row.getString("type");
            int year = row.getInt("year");
            int miles = row.getInt("miles");
            BigDecimal price = row.getBigDecimal("price");
            boolean isSold = row.getBoolean("sold");

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

            return vehicle;
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return null;
    }
}
