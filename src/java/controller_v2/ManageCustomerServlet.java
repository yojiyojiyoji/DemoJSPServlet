/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_v2;

import business.Customer;
import data.LibCustomerIO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yoji_salut
 */
public class ManageCustomerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        String action = (String)request.getParameter("action");
        
        if(action == null || action.equals("")) {

            //request.setAttribute("action", action);
            
            String url = "/customer_manager/customer_manager.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("search")) {
            
            ArrayList<Customer> customers;
            String lastName = request.getParameter("lastName");
            customers = LibCustomerIO.get_customers_by_lastName(lastName);
            
            request.setAttribute("customers", customers);
            request.setAttribute("pageType", "search");

            String url = "/customer_manager/customer_manager.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
        }
        else if(action.equals("updateCustomer")) {
            
            String customerID = request.getParameter("customerID");
            Customer customer = LibCustomerIO.get_customer_by_id(Integer.parseInt(customerID));
            //System.out.println(customer.getFirstName());
            request.setAttribute("customer", customer);
            request.setAttribute("pageType", "updateCustomer"); // why can't we use action?

            String url = "/customer_manager/customer_update.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
        }
        else if(action.equals("updateCustomerExe")) {
            
            //validation
            Customer c = new Customer();
            c.setCustomerID(Integer.parseInt(request.getParameter("customerID")));
            c.setFirstName(request.getParameter("firstName"));
            c.setLastName(request.getParameter("lastName"));
            c.setAddress(request.getParameter("address"));
            c.setCity(request.getParameter("city"));
            c.setState(request.getParameter("state"));
            c.setPostalCode(request.getParameter("postalCode"));
            c.setCountryCode(request.getParameter("countryCode"));
            c.setPhone(request.getParameter("phone"));
            c.setEmail(request.getParameter("email"));
            c.setPassword(request.getParameter("password"));
            
            //execute update
            int result = LibCustomerIO.update_customer(c);
            //int result = -1;
            if(result == 1) {
                
                request.setAttribute("pageType", "updateCustomerExe"); // why can't we use action?

                String url = "/customer_manager/customer_update.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
            else {
                String errorMessage = "Update failed. Please contect with customer support.";
                request.setAttribute("errorMessage", errorMessage);
                String url = "/errors/error.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
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
