/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import data.LibIncidentIO;
import data.LibRegistrationViewIO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yoji_salut
 */
public class IncidentViewContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        LibRegistrationViewIO.getConnection();
        int result1 = LibRegistrationViewIO.createRegistrationView();
        LibRegistrationViewIO.closeConnection();
        
        LibIncidentIO.getConnection();
        int result2 = LibIncidentIO.creatIncidentView();
        LibIncidentIO.closeConnection();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        
        LibRegistrationViewIO.getConnection();
        int result1 = LibRegistrationViewIO.dropRegistrationView();
        LibRegistrationViewIO.closeConnection();
        
        LibIncidentIO.getConnection();
        int result2 = LibIncidentIO.dropIncidentView();
        LibIncidentIO.closeConnection();
    }
}
