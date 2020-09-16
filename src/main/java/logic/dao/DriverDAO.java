package logic.dao;

import logic.entities.Driver;
import logic.entities.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    private static final String GET_DRIVER_BY_ID = "select DP.first_name, DP.last_name, DP.login_name, DP.license_number, DP.license_state, O.organization_name, O.usdot_number" +
            " from fleet.driver_profile DP" +
            " join public.organization O on DP.org_id=O.organization_id where driver_id=";

    public static Driver getDriver(String driverId) throws SQLException {
        Driver driver = null;
        Statement st = null;
        try {
            st = DBConnection.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery(GET_DRIVER_BY_ID + driverId);
            while (resultSet.next()) {
                driver = new Driver(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("login_name"),
                        resultSet.getString("license_state"),
                        resultSet.getString("license_number"),
                        resultSet.getString("organization_name"),
                        resultSet.getString("usdot_number")
                );
            }
        } catch (SQLException e) {
            System.out.println(("Select is not successful " + e));
        } finally {
            if (st != null) st.close();
        }
        return driver;
    }
}
