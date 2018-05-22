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
            System.out.println("[*] Start 'Select' method");
            // Step 2 - Open connection
            System.out.println("Open connection");
            conn = connect();

            // Step 3 - Execute statement
            System.out.println("Create statment");
            stmt = conn.createStatement();
            System.out.println("Execute query");
            rs = stmt.executeQuery(query);

            // iterate through the java resultset
            System.out.println("Iterate through results");
            if(rs != null) {
                while (rs.next())
                {
                    User entry = new User();
//                System.out.println("[-] Set user");
                    entry.setUser(rs.getString("user"));
//                System.out.println("[-] Set password");
                    entry.setPassword(rs.getString("password"));
//                System.out.println("[-] Add to list");
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
        System.out.println("[*] End 'Select' method");
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
