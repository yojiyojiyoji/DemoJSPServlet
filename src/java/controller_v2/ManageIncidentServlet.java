/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_v2;

import business.Customer;
import business.IncidentView;
import business.RegistrationView;
import business.TechAvailability;
import business.Technician;
import data.LibCustomerIO;
import data.LibIncidentIO;
import data.LibRegistrationViewIO;
import data.LibTechnicianIO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yuno
 */
public class ManageIncidentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = (String)request.getParameter("action");
        HttpSession session = request.getSession();
        //Do we need if statement to make sure if the cookie exists?
        Cookie techEmailCookie = new Cookie("techEmailCookie", "");
        techEmailCookie.setMaxAge(60*60*24*365*2);
        techEmailCookie.setPath("/");
        
        if(action == null || action.equals("")) {
            //should be able to remove this part...
        }
        else if(action.equals("getCustomer")) {
            
            String url = "/incident_manager/get_customer.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("createIncident")) {
            
            String customerEmail = (String)request.getParameter("customerEmail");
            
            if(customerEmail.equals("")) {
                
                String message = "Please enter a valid email address to go to next step!";
                request.setAttribute("message", message);
                
                String url = "/incident_manager/get_customer.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
            else {
                
                ArrayList<RegistrationView> registrationViews;
                
                LibRegistrationViewIO.getConnection();
                registrationViews = LibRegistrationViewIO.getRegistrationsByCustomerEmail(customerEmail);
                LibRegistrationViewIO.closeConnection();
                
                if(registrationViews.size() != 0) {

                    request.setAttribute("registrationViews", registrationViews);
                    request.setAttribute("pageType", "createIncident");

                    String url = "/incident_manager/create_incident.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
                else
                {
                    String message = "We could not find the customer record with the email address: " + customerEmail;
                    request.setAttribute("message", message);

                    String url = "/incident_manager/get_customer.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
            }
        }
        else if(action.equals("createIncidentExe")) {
            
            int customerID = Integer.parseInt(request.getParameter("customerID"));
            String productCode = request.getParameter("productCode");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            ArrayList<String> errorMessages = new ArrayList<String>();
            boolean isValid = true;

            if(title.equals("")) {
                isValid = false;
                String errorMessage = "Please fill out the title.";
                errorMessages.add(errorMessage);
            }
            if(description.equals("")) {
                isValid = false;
                String errorMessage = "Please fill out the description.";
                errorMessages.add(errorMessage);
            }
            
            if(isValid)
            {
                Calendar cal = Calendar.getInstance();
                Date dateOpened = new Date(cal.getTimeInMillis());

                LibIncidentIO.getConnection();
                int result = LibIncidentIO.createNewIncident(customerID, productCode, dateOpened, title, description);
                LibIncidentIO.closeConnection();
                //int result = -1;

                if(result == 1) {

                    request.setAttribute("pageType", "createIncidentExe");
                    String url = "/incident_manager/create_incident.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
                else {

                    String errorMessage = "Failed to create the incident. Please call customer support.";

                    request.setAttribute("errorMessage", errorMessage);
                    String url = "/errors/error.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
            }
            else {
                
                ArrayList<RegistrationView> registrationViews;
                
                Customer customer = LibCustomerIO.get_customer_by_id(customerID);
                String customerEmail = customer.getEmail();
                
                LibRegistrationViewIO.getConnection();
                registrationViews = LibRegistrationViewIO.getRegistrationsByCustomerEmail(customerEmail);
                LibRegistrationViewIO.closeConnection();

                request.setAttribute("registrationViews", registrationViews);
                request.setAttribute("pageType", "createIncident");
                request.setAttribute("errorMessages", errorMessages);
                request.setAttribute("title", title);
                request.setAttribute("description", description);

                String url = "/incident_manager/create_incident.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);

            }
        }
        else if(action.equals("selectUnassignedIncident")) {
            
            ArrayList<IncidentView> incidentViewsForSelection;
            LibIncidentIO.getConnection();
            incidentViewsForSelection = LibIncidentIO.getAllUnassignedIncidentViews();
            LibIncidentIO.closeConnection();

            request.setAttribute("incidentViewsForSelection", incidentViewsForSelection);
            request.setAttribute("pageType", "selectUnassignedIncident");

            String url = "/incident_manager/select_incident.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

        }
        else if(action.equals("selectTechnician")) {
            
            //memorize the selected incident
            int incidentID = Integer.parseInt(request.getParameter("incidentID"));
            
            ArrayList<TechAvailability> techAvailabilities;
            LibIncidentIO.getConnection();
            techAvailabilities = LibIncidentIO.getTechAvailabilities();
            LibIncidentIO.closeConnection();
            
            request.setAttribute("techAvailabilities", techAvailabilities);
            request.setAttribute("incidentID", String.valueOf(incidentID)); // no need casting?
            request.setAttribute("pageType", "selectTechnician");

            String url = "/incident_manager/select_technician.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
        }
        else if(action.equals("assignConf")) {
            
            int incidentID = Integer.parseInt(request.getParameter("incidentID"));
            int techID = Integer.parseInt(request.getParameter("techID"));
            //System.out.println("" + incidentID);
            //System.out.println("" + techID);
            
            LibIncidentIO.getConnection();
            IncidentView incidentView = LibIncidentIO.getIncidentViewByIncidentID(incidentID);
            Technician technician = LibTechnicianIO.getTechnicianByTechID(techID);
            LibIncidentIO.closeConnection();
            //System.out.println(incidentView.getCustomerEmail());
            //System.out.println(technician.getEmail());
            
            request.setAttribute("incidentView", incidentView);
            request.setAttribute("technician", technician);
            request.setAttribute("pageType", "assignConf");
            
            String url = "/incident_manager/assign_conf.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("assignExe")) {
            
            int incidentID = Integer.parseInt(request.getParameter("incidentID"));
            int techID = Integer.parseInt(request.getParameter("techID"));
            
            LibIncidentIO.getConnection();
            int result = LibIncidentIO.assignTechToIncident(techID, incidentID);
            LibIncidentIO.closeConnection();
            //int result = -1;
            
            if(result == 1) {
                request.setAttribute("pageType", "assignExe");
                String url = "/incident_manager/assign_conf.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
            else {
                String errorMessage = "Failed to assign the incident. "
                        + "Please calll costomer support.";
                request.setAttribute("errorMessage", errorMessage);
                String url = "/errors/error.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
        else if(action.equals("technicianLogin")) {
            
            request.setAttribute("pageType","technicianLogin");

            String url = "/incident_manager/technician_login.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("selectAssignedIncident")) {
            
            String technicianEmail;
            
            if(request.getParameter("technicianEmail") != null 
                    && request.getParameter("technicianEmail").equals("")) {
                
                String message = "Please enter a technician's email address.";
                request.setAttribute("message", message);
                request.setAttribute("pageType","technicianLogin");
                //techEmailCookie.setValue("");
                //response.addCookie(techEmailCookie);
                
                String url = "/incident_manager/technician_login.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
                
            }
            else {
                
                if(request.getParameter("technicianEmail") == null) {

                    technicianEmail = (String)session.getAttribute("technicianEmail");
                }
                else {
                    technicianEmail = request.getParameter("technicianEmail");
                    synchronized(session) {
                        session.setAttribute("technicianEmail", technicianEmail);
                    }

                    //techEmailCookie = new Cookie("techEmailCookie", technicianEmail);
                    techEmailCookie.setValue(technicianEmail);
                    response.addCookie(techEmailCookie);
                }

                ArrayList<IncidentView> incidentViewsForSelection;
                LibIncidentIO.getConnection();
                incidentViewsForSelection = LibIncidentIO.getAssignedIncidentViewsByTechEmail(technicianEmail);
                LibIncidentIO.closeConnection();

                //request.setAttribute("loggedInEmail", technicianEmail);
                request.setAttribute("incidentViewsForSelection", incidentViewsForSelection);
                request.setAttribute("pageType", "selectAssignedIncident");

                String url = "/incident_manager/select_incident.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
        else if(action.equals("updateIncident")) {
            
            int incidentID = Integer.parseInt(request.getParameter("incidentID"));
            LibIncidentIO.getConnection();
            IncidentView incidentView = LibIncidentIO.getIncidentViewByIncidentID(incidentID);
            LibIncidentIO.closeConnection();
            
            request.setAttribute("incidentView", incidentView);
            request.setAttribute("pageType", "updateIncident");
            
            String url = "/incident_manager/update_incident.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("updateIncidentExe")) {
            
            int incidentID = Integer.parseInt(request.getParameter("incidentID"));
            String productCode = request.getParameter("productCode");
            String dateOpened = request.getParameter("dateOpened");
            String title  = request.getParameter("title");
            
            String description ="";
            Date dateClosed = new Date();
            Boolean isValid = true;
            ArrayList<String> errorMessages = new ArrayList<String>();
            int result = -1;
            
            //validate description
            if(((String)request.getParameter("description")).equals("")) {
                String errorMessage = "Please fill out the field of description";
                errorMessages.add(errorMessage);
                isValid = false;
            }
            else {
                description = request.getParameter("description");
            }
            
            //validate dateClosed
            if(request.getParameter("dateClosed") == null || 
                    ((String)request.getParameter("dateClosed")).equals("")) {
                
                if(isValid) {
                    LibIncidentIO.getConnection();
                    result = LibIncidentIO.updateIncident(description, incidentID);
                    LibIncidentIO.closeConnection();
                }
            }
            else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                
                try {
                    dateClosed = sdf.parse( (String)request.getParameter("dateClosed") );
                }
                catch(ParseException e) {
                    String errorMessage = "The format of date must be 'yyyy/mm/dd'";
                    errorMessages.add(errorMessage);
                    isValid = false;
                }
                if(isValid) {
                    LibIncidentIO.getConnection();
                    result = LibIncidentIO.updateIncident(dateClosed, description, incidentID);
                    LibIncidentIO.closeConnection();
                }
            }
            
            if(isValid == true) {
                if(result == 1) {
                    request.setAttribute("pageType", "updateIncidentExe");

                    String url = "/incident_manager/update_incident.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
                else {
                    String errorMessage = "Update failed. Please call customer support.";
                    request.setAttribute("errorMessage", errorMessage);
                    String url = "/errors/error.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
            }
            else 
            {
                Collections.reverse(errorMessages);
                request.setAttribute("errorMessages", errorMessages);

                LibIncidentIO.getConnection();
                IncidentView incidentView = LibIncidentIO.getIncidentViewByIncidentID(incidentID);
                LibIncidentIO.closeConnection();

                request.setAttribute("incidentView", incidentView);
                request.setAttribute("pageType", "updateIncident");

                String url = "/incident_manager/update_incident.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
        else if(action.equals("technicianLogout")) {
            session.invalidate();
            String url = "/messages/logged_out.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        
        else if(action.equals("displayUnassignedIncidents")) {
            ArrayList<IncidentView> incidentsForDisplay;
            
            LibIncidentIO.getConnection();
            incidentsForDisplay = LibIncidentIO.getAllUnassignedIncidentViews();
            LibIncidentIO.closeConnection();
            
            request.setAttribute("incidentsForDisplay", incidentsForDisplay);
            request.setAttribute("pageType", "unassigned");
            
            String url = "/incident_manager/display_incidents.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("displayAssignedIncidents")) {
            ArrayList<IncidentView> incidentsForDisplay;
            
            LibIncidentIO.getConnection();
            incidentsForDisplay = LibIncidentIO.getAllAssignedIncidentViews();
            LibIncidentIO.closeConnection();
            
            request.setAttribute("incidentsForDisplay", incidentsForDisplay);
            request.setAttribute("pageType", "assigned");
            
            String url = "/incident_manager/display_incidents.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
