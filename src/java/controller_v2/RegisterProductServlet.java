/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_v2;

import business.Customer;
import business.Product;
import data.DBConnection;
import data.LibCustomerIO;
import data.LibProductIO;
import data.LibRegistrationIO;
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
public class RegisterProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = (String)request.getParameter("action");
 
        if(action == null || action.equals("")) {

            //request.setAttribute("action", action);
            
            String url="/product_register/customer_login.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
        }
        else if(action.equals("registerProduct")) {
            
            String email = (String)request.getParameter("email");
            
            if(email.equals("")) {
                
                String message = "Please enter an email address to get customer record.";
                request.setAttribute("message", message);
                String url="/product_register/customer_login.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
            else {
            
                DBConnection.getConnection();
                Customer customer = LibCustomerIO.get_customer_by_email(email);

                //customerID value for default constructor is -1
                if(customer.getCustomerID() != -1) {
                    ArrayList<Product> products;
                    products = LibProductIO.getAllProducts();

                    //close?
                    request.setAttribute("pageType", "registerProduct");
                    request.setAttribute("customer", customer);
                    request.setAttribute("products", products);

                    String url = "/product_register/register_product.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request,response);
                }
                else {
                    String message = "No customer record was retrieved with email address: " + email;
                    request.setAttribute("message", message);
                    String url="/product_register/customer_login.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
                
            }
            
        }
        else if(action.equals("registerProductExe")) {
            
            String customerID = (String)request.getParameter("customerID");
            String productCode = (String)request.getParameter("productCode");
            
            LibRegistrationIO.getConnection();
            int result = LibRegistrationIO.register_product(Integer.valueOf(customerID), productCode);
            LibRegistrationIO.closeConnection();
            
            if(result == 1) {
                request.setAttribute("pageType", "registerProductExe");
                request.setAttribute("productCode", productCode);

                String url = "/product_register/register_product.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request,response);
            }
            else {
                String errorMessage = "Registration failed. Please call customer support center.";
                request.setAttribute("errorMessage", errorMessage);
                
                String url = "/errors/error.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request,response);
                
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
