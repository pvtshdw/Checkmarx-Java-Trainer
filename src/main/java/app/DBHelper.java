package app;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DBConfig.DB_HOST, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public List<User> select(String query) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();

        try {
            // Step 2 - Open connection
            conn = connect();

            // Step 3 - Execute statement
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            // iterate through the java resultset
            if(rs != null) {
                while (rs.next())
                {
                    User entry = new User();
                    entry.setUser(rs.getString("user"));
                    entry.setPassword(rs.getString("password"));
                    userList.add(entry);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Step 5 Close connection
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userList;
    }


    public void insert(String query) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1 - Load driver
            // Class.forName("com.mysql.cj.jdbc.Driver"); //Class.forName() is not needed since JDBC 4.0

            // Step 2 - Open connection
            conn = connect();

            // Step 3 - Execute statement
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Step 5 Close connection
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
