package org.yearup.database;

import org.yearup.models.Dealership;
import org.yearup.utilities.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDealershipDao implements DealershipDao
{
    private final DataSource dataSource;

    public MySqlDealershipDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    @Override
    public List<Dealership> getDealership()
    {
        List<Dealership> dealerships = new ArrayList<>();

        String sql = """
                SELECT *
                FROM dealerships;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Dealership dealership = mapRow(row);
                dealerships.add(dealership);
            }
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return dealerships;
    }

    private Dealership mapRow(ResultSet row)
    {
        try
        {
            int id = row.getInt("dealership_id");
            String name = row.getString("name");
            String address = row.getString("address");
            String phone = row.getString("phone");

            return new Dealership()
            {{
               setDealershipId(id);
               setName(name);
               setAddress(address);
               setPhone(phone);
            }};
        }
        catch (SQLException ex)
        {
            Logger.logError(ex);
        }

        return null;
    }
}
