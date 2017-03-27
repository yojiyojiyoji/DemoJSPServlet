/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Product;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author yuno
 */
public class DBH {
    
    private static Connection connection;
    
    public static void init() {
        try
        {
            if (connection==null){
                new Driver();
            }
            //String dbURL = "jdbc:mysql://localhost:3306/music";
            //String username = "msc_user";
            //String password = "pass";

            String dbURL = "jdbc:mysql://localhost:3306/tech_support";
            String username = "ts_user";
            String password = "pa55word";
            connection = DriverManager.getConnection(
                dbURL, username, password);
        }
        catch(SQLException e){
            for (Throwable t : e) {
                t.printStackTrace();
            }
            System.out.println("Error: unable to connect to DB.");
        }
    }
    
    public static ArrayList<Product> getAllProducts() {
        
        ArrayList<Product> products = new ArrayList<Product>();
        
        try {
            Statement stm = connection.createStatement();
            String query = "SELECT * FROM products";
            ResultSet rs  = stm.executeQuery(query);

            while(rs.next()) {
                
                String productCode = rs.getString("productCode");
                String name = rs.getString("name");
                double version = rs.getDouble("version");
                Date releaseDate = rs.getDate("releaseDate");
                
                //Product p = new Product(productCode, name, version, releaseDate);
                //products.add(p);
            }
        }
        catch(SQLException e){
            for (Throwable t : e) {
                t.printStackTrace();
            }
            System.out.println("Error: unable to get data.");
        }
        
        return products;
    }
    
}
