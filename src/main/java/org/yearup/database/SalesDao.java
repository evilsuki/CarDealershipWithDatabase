package org.yearup.database;

import org.yearup.models.Vehicle;

public interface SalesDao
{
    Vehicle create(Vehicle vehicle);
    void update(int salesId, Vehicle vehicle);
}
