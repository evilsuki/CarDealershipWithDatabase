package org.yearup.database;

import org.yearup.models.LeaseContract;

public interface LeaseDao
{
    LeaseContract create(LeaseContract leaseContract);
}
