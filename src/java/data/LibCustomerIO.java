/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yoji_salut
 */
public class LibCustomerIO {
    
    private static Connection con = DBConnection.getConnection();
    
    
    
    
    public static ArrayList<Customer> get_customers_by_lastName(String lastName) {
        
        ArrayList<Customer> customers = new ArrayList<Customer>();
        
        try {
            String stm = "SELECT * FROM customers WHERE lastName = ?";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setString(1, lastName);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                Customer customer = new Customer();

                customer.setCustomerID(rs.getInt(1));
                customer.setFirstName(rs.getString(2));
                customer.setLastName(rs.getString(3));
                customer.setAddress(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setState(rs.getString(6));
                customer.setPostalCode(rs.getString(7));
                customer.setCountryCode(rs.getString(8));
                customer.setPhone(rs.getString(9));
                customer.setEmail(rs.getString(10));
                customer.setPassword(rs.getString(11));
                
                customers.add(customer);
            }
        }
        catch(SQLException e)
        {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }
        
        return customers;
    }
    
    public static Customer get_customer_by_id(int customerID) {
        
        Customer customer = new Customer();
        
        try {
            String stm = "SELECT * FROM customers WHERE customerID = ?";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                customer.setCustomerID(rs.getInt(1));
                customer.setFirstName(rs.getString(2));
                customer.setLastName(rs.getString(3));
                customer.setAddress(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setState(rs.getString(6));
                customer.setPostalCode(rs.getString(7));
                customer.setCountryCode(rs.getString(8));
                customer.setPhone(rs.getString(9));
                customer.setEmail(rs.getString(10));
                customer.setPassword(rs.getString(11));
            }
        }
        catch(SQLException e)
        {
            for(Throwable t: e) {
                t.printStackTrace();
            }
            System.out.println("error: get customers by ID");
        }
        
        return customer;
    }
    
    public static int update_customer(Customer c) {
        int result = -1;
        
        try {
            String stm = "UPDATE customers "
                    + "SET "
                    + "firstName = ?, "
                    + "lastName = ?, "
                    + "address = ?, "
                    + "city = ?, "
                    + "state = ?, "
                    + "postalCode = ?, "
                    + "countryCode = ?, "
                    + "phone = ?, "
                    + "email = ?, "
                    + "password = ? "
                    + "WHERE customerID=?";
            PreparedStatement ps = con.prepareStatement(stm);
            
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getCity());
            ps.setString(5, c.getState());
            ps.setString(6, c.getPostalCode());
            ps.setString(7, c.getCountryCode());
            ps.setString(8, c.getPhone());
            ps.setString(9, c.getEmail());
            ps.setString(10, c.getPassword());
            
            ps.setInt(11, c.getCustomerID());
            
            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            for(Throwable t: e) {
                t.printStackTrace();
            }
            System.out.println("error: Update failed");
        }
        return result;
    }
    
    
    public static Customer get_customer_by_email(String email) {
        
        Customer customer = new Customer();
        
        try {
            
            String stm = "SELECT * FROM customers WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                customer.setCustomerID(rs.getInt(1));
                customer.setFirstName(rs.getString(2));
                customer.setLastName(rs.getString(3));
                customer.setAddress(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setState(rs.getString(6));
                customer.setPostalCode(rs.getString(7));
                customer.setCountryCode(rs.getString(8));
                customer.setPhone(rs.getString(9));
                customer.setEmail(rs.getString(10));
                customer.setPassword(rs.getString(11));
            }
        }
        catch(SQLException e)
        {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }

        return customer;
    }
}
