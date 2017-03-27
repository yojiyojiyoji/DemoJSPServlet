/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yoji_salut
 */
public class DBConnection {

    private static Connection connection;
    
    public static Connection getConnection() {
        try
        {
            if (connection==null){
                new Driver();
            }

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
        
        return connection;
    }
    
    public static void closeConnection() {
        
        try {
            if(connection != null) {
                connection.close();
            }
        }
        catch(SQLException e) {
            for(Throwable t: e) {
                t.printStackTrace();
            }
        }
    }
}
