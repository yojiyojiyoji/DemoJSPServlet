/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author yoji_salut
 */
public class LibRegistrationIO {
    
    private static Connection con;
    
    public static void getConnection() {
        con = DBConnection.getConnection();
    }
    
    public static void closeConnection() {
        
        try {
            con.close();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static int register_product(int customerID, String productCode) {
        
        int result = -1;
        try {
            String stm = "INSERT INTO registrations (customerID, productCode, registrationDate) "
                    + "VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(stm);
            
            ps.setInt(1, customerID);
            ps.setString(2, productCode);
            
            Calendar cal = Calendar.getInstance();
            Timestamp ts = new Timestamp(cal.getTimeInMillis());
            ps.setTimestamp(3, ts);
            
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            for(Throwable t: e) {
                t.printStackTrace();
            }
            System.out.println("Error!");
        }
        return result;
    }
}
