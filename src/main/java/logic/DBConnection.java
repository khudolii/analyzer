package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection con = null;
    public static Connection getConnection(){
        if(con!=null) return con;
        return createConnection();
    }
    private static Connection createConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(("PostgreSQL JDBC Driver is not found. Include it in your library path: " + e));
        }
        try {
            con = DriverManager.getConnection(DBConstant.DB_URL, DBConstant.USER_DB, DBConstant.PASS_DB);
        } catch (SQLException e) {
            System.out.println(("Connection Failed: " + e));
        }
        System.out.println(("Connection is successful"));
        return con;
    }

    public static void closeConnection()  {
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
