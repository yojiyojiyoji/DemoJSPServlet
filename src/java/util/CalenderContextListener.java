/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author yoji_salut
 */
public class CalenderContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent e) {
        
        ServletContext sc = e.getServletContext();
        
        Calendar calendar = Calendar.getInstance();

        int currentYear = calendar.get(Calendar.YEAR);
        sc.setAttribute("currentYear", currentYear);

        Date currentDate = calendar.getTime();
        sc.setAttribute("currentDate", currentDate);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
