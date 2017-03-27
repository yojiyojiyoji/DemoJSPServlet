/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_v2;

import business.Technician;
import data.LibTechnicianIO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yuno
 */
@WebServlet(name = "ManageTechnicianServlet", urlPatterns = {"/ManageTechnician"})
public class ManageTechnicianServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = (String)request.getParameter("action");
        
        if(action==null || action.equals("")) {
            
            ArrayList<Technician> technicians;
            technicians = LibTechnicianIO.getAllTechnicians();
            //System.out.println(technicians.get(0).getEmail());
            request.setAttribute("technicians", technicians);
            
            String url = "/technician_manager/technician_manager.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("addTechnician")) {
            
            Technician technician = new Technician();
            request.setAttribute("technician", technician);
            
            String url = "/technician_manager/add_technician.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("addTechnicianExe")) {
            
            boolean isValid = true;
            
            Technician technician = new Technician();
            
            //validation
            if(request.getParameter("firstName").equals("")) {
                isValid = false;
            }else {
                technician.setFirstName(request.getParameter("firstName"));
            }
            if(request.getParameter("lastName").equals("")) {
                isValid = false;
            }else {
                technician.setLastName(request.getParameter("lastName"));
            }
            if(request.getParameter("email").equals("")) {
                isValid = false;
            }else {
                technician.setEmail(request.getParameter("email"));
            }
            if(request.getParameter("phone").equals("")) {
                isValid = false;
            }else {
                technician.setPhone(request.getParameter("phone"));
            }
            if(request.getParameter("password").equals("")) {
                isValid = false;
            }else {
                technician.setPassword(request.getParameter("password"));
            }
            
            if(isValid) {
                int result = LibTechnicianIO.addTechnician(technician);

                if(result == 1) {

                    ArrayList<Technician> technicians;
                    technicians = LibTechnicianIO.getAllTechnicians();
                    request.setAttribute("technicians", technicians);

                    String url = "/technician_manager/technician_manager.jsp";
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
                else {
                    //error!
                }
            }
            else {
                //return the result of validation
                request.setAttribute("technician", technician);
                request.setAttribute("wasValid", "no");

                String url = "/technician_manager/add_technician.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
        else if(action.equals("deleteTechnician")) {
            
            String techID = (String)request.getParameter("techID");
            //validation
            int result = LibTechnicianIO.deleteTechnician(Integer.parseInt(techID));
            
            
            ArrayList<Technician> technicians;
            technicians = LibTechnicianIO.getAllTechnicians();
            request.setAttribute("technicians", technicians);
            
            String url = "/technician_manager/technician_manager.jsp";
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
