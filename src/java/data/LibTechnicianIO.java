/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Technician;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author yoji_salut
 */
public class LibTechnicianIO {
    
    private static Connection con = DBConnection.getConnection();
    
    
        
    public static ArrayList<Technician> getAllTechnicians() 
    {
        
        ArrayList<Technician> technicians = new ArrayList<Technician>();
        
        try {
            String query = "SELECT * FROM technicians";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()) {
                Technician t = new Technician();
                t.setTechID(rs.getInt("techID"));
                t.setFirstName(rs.getString("firstName"));
                t.setLastName(rs.getString("lastName"));
                t.setEmail(rs.getString("email"));
                t.setPhone(rs.getString("phone"));
                t.setPassword(rs.getString("password"));
                technicians.add(t);
            }
        }
        catch(SQLException e) {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }
        
        return technicians;
        
    }
    
    public static int addTechnician(Technician technician) {
        
        int result = -1;
        
        try {
            String stm = "INSERT INTO technicians (firstName, lastName, email, phone, password) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setString(1, technician.getFirstName());
            ps.setString(2, technician.getLastName());
            ps.setString(3, technician.getEmail());
            ps.setString(4, technician.getPhone());
            ps.setString(5, technician.getPassword());
            
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }
        
        return result;
    }
    
    public static int deleteTechnician(int techID) {
        
        int result = -1;
        
        try {
            String stm = "DELETE FROM technicians WHERE techID=?";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setInt(1, techID);
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }
        return result;
    }
    
    public static Technician getTechnicianByTechID(int techID) {
        Technician t = new Technician();
        
        try {
            String query = "SELECT * FROM technicians WHERE techID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, techID);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                t.setTechID(rs.getInt("techID"));
                t.setFirstName(rs.getString("firstName"));
                t.setLastName(rs.getString("lastName"));
                t.setEmail(rs.getString("email"));
                t.setPhone(rs.getString("phone"));
                t.setPassword(rs.getString("password"));
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return t;
    }
}
