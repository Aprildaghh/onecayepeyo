package Db;

import java.sql.*;
import java.time.LocalDate;

public class Dao {

    private static Dao uniqueInstance;
    private final Connection con;

    private Dao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/se3313_project","root","zxcasd45");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Dao getInstance() {
        if(uniqueInstance == null)
        {
            uniqueInstance = new Dao();
        }
        return uniqueInstance;
    }

    public int getTotalCups(){
        Statement stmt = null;
        ResultSet rs = null;
        int totalCups = 0;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select total_cups from coffee_maker where day=date('"+ Date.valueOf(LocalDate.now()) +"')");
            if(rs.next())
                totalCups = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalCups;
    }

    public void insertCups(int numberOfCups)
    {
        Statement stmt = null;
        Date date = Date.valueOf(LocalDate.now());
        int n = getTotalCups() + numberOfCups;
        try {
            stmt = con.createStatement();
            String sql = "insert into coffee_maker(day, total_cups) values(date('"+date+"'),"+n+")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            try {
                String sql = "update coffee_maker set total_cups="+n+" where coffee_maker.day = date('"+date+"');";
                stmt.executeUpdate(sql);
            } catch (SQLException exc)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
