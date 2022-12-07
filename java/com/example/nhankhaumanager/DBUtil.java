package com.example.nhankhaumanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{
    public static Connection getConnection() throws ClassNotFoundException {
        Connection conn;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String url = "jdbc:sqlserver://LAPTOP-T77BEFIG\\SQLEXPRESS:1433;" +
                "databaseName=NhanKhauManager;user=sa;password=thehuy21;encrypt=true;trustServerCertificate=true";

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("succc");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static void Close(Connection c){
        if(c!=null){
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
