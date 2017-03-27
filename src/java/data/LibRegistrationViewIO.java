/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.RegistrationView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author yoji_salut
 */
public class LibRegistrationViewIO {
    
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

    public static int createRegistrationView() {
        int result = -1;
        
        try {
            String stmStr = "CREATE OR REPLACE VIEW registration_view AS "
                    + "SELECT r.customerID AS customerID, r.productCode AS productCode, r.registrationDate AS registrationDate, "
                    + "c.firstName AS customerFirstName, c.lastName AS customerLastName, c.email AS customerEmail, "
                    + "p.name AS productName "
                    + "FROM registrations AS r "
                    + "INNER JOIN customers AS c ON r.customerID = c.customerID "
                    + "INNER JOIN products AS p ON r.productCode = p.productCode";
            Statement stm = con.createStatement();
            result = stm.executeUpdate(stmStr);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public static int dropRegistrationView() {

        int result = -1;
        
        try {
            String stmStr = "DROP VIEW registration_view";
            Statement stm = con.createStatement();
            result = stm.executeUpdate(stmStr);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public static ArrayList<RegistrationView> getRegistrationsByCustomerEmail(String customerEmail) {

        ArrayList<RegistrationView> registrationViews = new ArrayList<RegistrationView>();
        
        try {
            String query = "SELECT * FROM registration_view WHERE customerEmail=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customerEmail);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                RegistrationView rv = new RegistrationView();
                rv.setCustomerID(rs.getInt(1));
                rv.setProductCode(rs.getString(2));
                
                Timestamp ts = rs.getTimestamp(3);
                rv.setRegistrationDate(new Date(ts.getTime()));
                
                rv.setCustomerFirstName(rs.getString(4));
                rv.setCustomerLastName(rs.getString(5));
                rv.setCustomerEmail(rs.getString(6));
                rv.setProductName(rs.getString(7));
                
                registrationViews.add(rv);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return registrationViews;
    }
}
