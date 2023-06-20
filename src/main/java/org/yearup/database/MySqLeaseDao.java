package org.yearup.database;

import org.yearup.models.LeaseContract;
import org.yearup.utilities.Logger;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqLeaseDao implements LeaseDao
{
    private final DataSource dataSource;

    public MySqLeaseDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    @Override
    public LeaseContract create(LeaseContract leaseContract)
    {
        String sql = "CALL AddLeaseContract(?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql))
        {
            statement.setString(1, leaseContract.getVin());
            statement.setString(2, leaseContract.getCustomerName());
            statement.setString(3, leaseContract.getCustomerEmail());
            statement.setBigDecimal(4, leaseContract.getSalesPrice());
            statement.setBigDecimal(5, leaseContract.getEndingValue());
            statement.setBigDecimal(6, leaseContract.getSalesTax());

            statement.execute();
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return leaseContract;
    }
}
