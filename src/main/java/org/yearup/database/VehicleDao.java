package org.yearup.database;

import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleDao
{
    Vehicle getByVin(String vin);
    List<Vehicle> listByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, int dealershipId);

    List<Vehicle> listAllVehicle(int dealershipId);

    List<Vehicle> listByMakeModel(String make, String model, int dealershipId);

    List<Vehicle> listByYearRange(int minYear, int maxYear, int dealershipId);

    List<Vehicle> listByColor(String color, int dealershipId);
    List<Vehicle> listByMileageRange(int minMiles, int maxMiles, int dealershipId);
    List<Vehicle> listByType(String type, int dealershipId);

    Vehicle create(Vehicle vehicle, int dealershipId);
    void update(String vin, Vehicle vehicle);
    void delete(String vin);
}