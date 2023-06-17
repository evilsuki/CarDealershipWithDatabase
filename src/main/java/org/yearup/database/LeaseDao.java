package org.yearup.database;

import org.yearup.models.Vehicle;

public interface LeaseDao
{
    Vehicle create(Vehicle vehicle);
    void update(int leaseId, Vehicle vehicle);
}
