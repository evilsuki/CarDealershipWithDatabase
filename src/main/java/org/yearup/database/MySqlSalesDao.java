package org.yearup.database;

import org.yearup.models.SalesContract;
import org.yearup.utilities.Logger;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlSalesDao implements SalesDao
{
    private final DataSource dataSource;

    public MySqlSalesDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    @Override
    public SalesContract create(SalesContract salesContract)
    {
        String sql = "CALL AddSalesContract(?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql))
        {
            statement.setString(1, salesContract.getVin());
            statement.setString(2, salesContract.getCustomerName());
            statement.setString(3, salesContract.getCustomerEmail());
            statement.setBigDecimal(4, salesContract.getSalesPrice());
            statement.setBigDecimal(5, salesContract.getProcessingFee());
            statement.setBigDecimal(6, salesContract.getSalesTax());

            statement.execute();
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return salesContract;
    }
}
