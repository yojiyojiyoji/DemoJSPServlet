/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Incident;
import business.IncidentView;
import business.TechAvailability;
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
 * @author yuno
 */
public class LibIncidentIO {
    
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
    
    public static int creatIncidentView() {
        
        int result = -1;
        
        try{
            //String stmStr = "CREATE OR REPLACE VIEW test AS SELECT incidents.incidentID, incidents.customerID FROM incidents";
            
            String stmStr = "CREATE OR REPLACE VIEW incidents_view AS "
                    + "SELECT i.incidentID, i.customerID, i.productCode, "
                    + "i.techID, i.dateOpened, i.dateClosed, i.title, i.description, "
                    + "c.email AS customerEmail, c.firstName AS customerFirstName, c.lastName AS customerLastName, "
                    + "p.name AS productName, "
                    + "t.firstName AS technicianFirstName, t.lastName AS technicianLastName, t.email AS technicianEmail "
                    + "FROM incidents AS i "
                    + "INNER JOIN customers AS c ON i.customerID = c.customerID "
                    + "INNER JOIN products AS p ON i.productCode = p.productCode "
                    + "LEFT OUTER JOIN technicians AS t ON i.techID = t.techID";
                    
            Statement stm = con.createStatement();
            result = stm.executeUpdate(stmStr);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static int dropIncidentView() {
 
        int result = -1;
        
        try{
            String stmStr = "DROP VIEW incidents_view";
                    
            Statement stm = con.createStatement();
            result = stm.executeUpdate(stmStr);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public static int createNewIncident(int customerID, String productCode, Date dateOpened, String title, String description) {

        int result = -1;
        
        try{
            String stm = "INSERT INTO incidents(customerID, productCode, techID, dateOpened, dateClosed, title, description) "
                    + "VALUES(? ,? ,null ,? ,null ,? ,?)";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setInt(1, customerID);
            ps.setString(2, productCode);
            ps.setTimestamp(3, new Timestamp(dateOpened.getTime()));
            ps.setString(4, title);
            ps.setString(5, description);
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public static ArrayList<IncidentView> getAllUnassignedIncidentViews() {
        ArrayList<IncidentView> unassignedIncidents = new ArrayList<IncidentView>();
        
        try{
            String query = "SELECT * FROM incidents_view WHERE techID IS NULL";
            
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()) {
                IncidentView iv = new IncidentView();
                
                iv.setIncidentID(rs.getInt(1));
                iv.setCustomerID(rs.getInt(2));
                iv.setProductCode(rs.getString(3));
                iv.setTechID(rs.getInt(4));
                Timestamp tsOpened = rs.getTimestamp(5);
                if(tsOpened != null) {
                    iv.setDateOpened(new Date(tsOpened.getTime())); // no need if statement?
                }
                Timestamp tsClosed = rs.getTimestamp(6);
                if(tsClosed != null) {
                    iv.setDateClosed(new Date(tsClosed.getTime()));
                }
                iv.setTitle(rs.getString(7));
                iv.setDescription(rs.getString(8));
                iv.setCustomerEmail(rs.getString(9));
                iv.setCustomerFirstName(rs.getString(10));
                iv.setCustomerLastName(rs.getString(11));
                iv.setProductName(rs.getString(12));
                iv.setTechnicianFirstName(rs.getString(13));
                iv.setTechnicianLastName(rs.getString(14));
                iv.setTechnicianEmail(rs.getString(15));
                
                unassignedIncidents.add(iv);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return unassignedIncidents;
    }

    
    public static ArrayList<IncidentView> getAllAssignedIncidentViews() {
        ArrayList<IncidentView> unassignedIncidents = new ArrayList<IncidentView>();
        
        try{
            String query = "SELECT * FROM incidents_view WHERE techID IS NOT NULL";
            
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()) {
                IncidentView iv = new IncidentView();
                
                iv.setIncidentID(rs.getInt(1));
                iv.setCustomerID(rs.getInt(2));
                iv.setProductCode(rs.getString(3));
                iv.setTechID(rs.getInt(4));
                Timestamp tsOpened = rs.getTimestamp(5);
                if(tsOpened != null) {
                    iv.setDateOpened(new Date(tsOpened.getTime())); // no need if statement?
                }
                Timestamp tsClosed = rs.getTimestamp(6);
                if(tsClosed != null) {
                    iv.setDateClosed(new Date(tsClosed.getTime()));
                }
                iv.setTitle(rs.getString(7));
                iv.setDescription(rs.getString(8));
                iv.setCustomerEmail(rs.getString(9));
                iv.setCustomerFirstName(rs.getString(10));
                iv.setCustomerLastName(rs.getString(11));
                iv.setProductName(rs.getString(12));
                iv.setTechnicianFirstName(rs.getString(13));
                iv.setTechnicianLastName(rs.getString(14));
                iv.setTechnicianEmail(rs.getString(15));
                
                unassignedIncidents.add(iv);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return unassignedIncidents;
    }
    
    

    
    public static ArrayList<TechAvailability> getTechAvailabilities() {
        ArrayList<TechAvailability> techAvailabilities = new ArrayList<TechAvailability>();
        
        //should make sure if this query is correct
        try {
            String query = "SELECT t.techID, t.firstName, t.lastName, "
                    + "(SELECT COUNT(techID) FROM incidents i WHERE i.techID = t.techID AND i.dateClosed IS NULL) AS openIncidents "
                    + "FROM technicians t";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()) {
                TechAvailability ta = new TechAvailability();
                ta.setTechID(rs.getInt(1));
                ta.setTechFirstName(rs.getString(2));
                ta.setTechLastName(rs.getString(3));
                ta.setNumOpenIncidents(rs.getInt(4));
                techAvailabilities.add(ta);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return techAvailabilities;
    }
    
    public static IncidentView getIncidentViewByIncidentID(int incidentID) {
        
        IncidentView iv = new IncidentView();

        try{
            String query = "SELECT * FROM incidents_view WHERE incidentID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, incidentID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                iv.setIncidentID(rs.getInt(1));
                iv.setCustomerID(rs.getInt(2));
                iv.setProductCode(rs.getString(3));
                iv.setTechID(rs.getInt(4));
                Timestamp tsOpened = rs.getTimestamp(5);
                if(tsOpened != null) {
                    iv.setDateOpened(new Date(tsOpened.getTime())); // no need if statement?
                }
                Timestamp tsClosed = rs.getTimestamp(6);
                if(tsClosed != null) {
                    iv.setDateClosed(new Date(tsClosed.getTime()));
                }
                iv.setTitle(rs.getString(7));
                iv.setDescription(rs.getString(8));
                iv.setCustomerEmail(rs.getString(9));
                iv.setCustomerFirstName(rs.getString(10));
                iv.setCustomerLastName(rs.getString(11));
                iv.setProductName(rs.getString(12));
                iv.setTechnicianFirstName(rs.getString(13));
                iv.setTechnicianLastName(rs.getString(14));
                iv.setTechnicianEmail(rs.getString(15));
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return iv;
    }
    
    public static int assignTechToIncident(int techID, int incidentID) {
        int result = -1;
        
        try {
            String stm = "UPDATE incidents_view SET techID = ? WHERE incidentID = ?";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setInt(1, techID);
            ps.setInt(2, incidentID);
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return result;
    }

    public static ArrayList<IncidentView> getAssignedIncidentViewsByTechEmail(String technicianEmail) {
        ArrayList<IncidentView> assignedIncidents = new ArrayList<IncidentView>();
        
        try{
            String query = "SELECT * FROM incidents_view WHERE technicianEmail=? AND dateClosed IS NULL";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, technicianEmail);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                IncidentView iv = new IncidentView();
                
                iv.setIncidentID(rs.getInt(1));
                iv.setCustomerID(rs.getInt(2));
                iv.setProductCode(rs.getString(3));
                iv.setTechID(rs.getInt(4));
                Timestamp tsOpened = rs.getTimestamp(5);
                if(tsOpened != null) {
                    iv.setDateOpened(new Date(tsOpened.getTime())); // no need if statement?
                }
                Timestamp tsClosed = rs.getTimestamp(6);
                if(tsClosed != null) {
                    iv.setDateClosed(new Date(tsClosed.getTime()));
                }
                iv.setTitle(rs.getString(7));
                iv.setDescription(rs.getString(8));
                iv.setCustomerEmail(rs.getString(9));
                iv.setCustomerFirstName(rs.getString(10));
                iv.setCustomerLastName(rs.getString(11));
                iv.setProductName(rs.getString(12));
                iv.setTechnicianFirstName(rs.getString(13));
                iv.setTechnicianLastName(rs.getString(14));
                iv.setTechnicianEmail(rs.getString(15));
                
                assignedIncidents.add(iv);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return assignedIncidents;
    }
    
    public static int updateIncident(Date dateClosed, String description, int incidentID) {
        
        int result = -1;
        
        try {
            String stm = "UPDATE incidents_view "
                    + "SET dateClosed = ?, "
                    + "description = ? "
                    + "WHERE incidentID = ?";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setTimestamp(1, new Timestamp(dateClosed.getTime()));
            ps.setString(2, description);
            ps.setInt(3, incidentID);
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public static int updateIncident(String description, int incidentID) {
        
        int result = -1;
        
        try {
            String stm = "UPDATE incidents_view "
                    + "SET description = ? "
                    + "WHERE incidentID = ?";
            PreparedStatement ps = con.prepareStatement(stm);
            ps.setString(1, description);
            ps.setInt(2, incidentID);
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
