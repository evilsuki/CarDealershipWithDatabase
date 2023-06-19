package org.yearup.database;

import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleDao
{
    List<Vehicle> listByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<Vehicle> listAllVehicle();

    List<Vehicle> listByMakeModel(String make, String model);

    List<Vehicle> listByYearRange(int minYear, int maxYear);

    List<Vehicle> listByColor(String color);
    List<Vehicle> listByMileageRange(int minMiles, int maxMiles);
    List<Vehicle> listByType(String type);

    Vehicle create(Vehicle vehicle, int dealershipId);
    void update(String vin, Vehicle vehicle);
    void delete(String vin);
}