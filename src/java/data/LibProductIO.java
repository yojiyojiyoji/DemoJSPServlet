/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author yoji_salut
 */
public class LibProductIO {
    
    private static Connection con = DBConnection.getConnection();
    
    
    

    public static ArrayList<Product> getAllProducts() {

        ArrayList<Product> products = new ArrayList<Product>();

        try {
            Statement stm = con.createStatement();
            String query = "SELECT * FROM products";
            ResultSet rs  = stm.executeQuery(query);

            while(rs.next()) {

                String productCode = rs.getString("productCode");
                String name = rs.getString("name");
                double version = rs.getDouble("version");
                Timestamp releaseDateTs = rs.getTimestamp("releaseDate");
                Date releaseDate = new Date(releaseDateTs.getTime());

                Product p = new Product(productCode, name, version, releaseDate);
                products.add(p);
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
    
    public static int addProduct(Product product) {
        
        int result = -1;
        
        try {
            String pstm = "INSERT INTO products "
                    + "(productCode, name, version, releaseDate) "
                    + "VALUES(?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(pstm);
            ps.setString(1, product.getProductCode());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getVersion());
            ps.setTimestamp(4, new Timestamp(product.getReleaseDate().getTime()));
            
            result = ps.executeUpdate();
            
        }catch(SQLException e) {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }
        return result;
    }
    
    public static int deleteProduct(String productCode) {
        
        int result = -1;
        
        try {
            String pstm = "DELETE FROM products WHERE productCode = ?";
            PreparedStatement ps = con.prepareStatement(pstm);
            ps.setString(1, productCode);
            result = ps.executeUpdate();
        }
        catch(SQLException e) {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }
        
        return result;
    }
    
    //remove this part?
    public static void closeConnection() {
        
        if(con != null) {
            DBConnection.closeConnection();
        }
    }
}
