/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_v2;

import business.Product;
import data.LibProductIO;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yoji_salut
 */
public class ManageProductServlet extends HttpServlet {
    
    ArrayList<Product> products = new ArrayList<Product>();
    
    @Override
    public void init() {
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        
        if(action == null || action.equals("")) {
            //DBH.init();
            products = LibProductIO.getAllProducts();
            request.setAttribute("products", products);

            String url = "/product_manager/product_manager.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("addProduct")) {
            
            //response.sendRedirect("./product_manager/add_product.jsp");
            String url="/product_manager/add_product.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else if(action.equals("addProductExe")) {
            
            Product p = new Product();
            Date date;
            String dateStr ="";

            ArrayList<String> messages = new ArrayList<String>();
            boolean isValid = true;

            //validation!
            if(request.getParameter("productCode").equals("")) {
                messages.add("Please enter the product code.");
                isValid = false;
            }else {
                p.setProductCode(request.getParameter("productCode"));
            }
            
            if(request.getParameter("name").equals("")) {
                messages.add("Please enter the product name.");
                isValid = false;
            }else {
                p.setName(request.getParameter("name"));
            }
            
            if(request.getParameter("version").equals("")) {
                messages.add("Please enter the product version.");
                isValid = false;
            }else {
                try {
                    p.setVersion(Double.parseDouble(request.getParameter("version")));
                }
                catch(NumberFormatException e) {
                    messages.add("Product version must be a number.");
                    isValid = false;
                }
            }
            
            if(request.getParameter("releaseDate").equals("")) {
                messages.add("Please enter the release Date.");
                isValid = false;
                
            }else {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    date = sdf.parse(request.getParameter("releaseDate"));
                    p.setReleaseDate(date);
                    //p.setReleaseDate(sdf.parse(request.getParameter("releaseDate")));
                    dateStr = sdf.format(date);
                }
                catch(ParseException e) {
                    messages.add("Format of the date must be 'yyyy-mm-dd'.");
                    isValid = false;
                    dateStr = "";
                }
            }
            
            if(isValid) {
                int result = LibProductIO.addProduct(p); // use result later
                response.sendRedirect("ManageProduct");
            }
            else {
                request.setAttribute("product", p);
                request.setAttribute("messages", messages);
                request.setAttribute("dateStr", dateStr);
                
                String url="/product_manager/add_product.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
            
        }
        else if(action.equals("deleteProduct")) {
            
            String productCode = request.getParameter("productCode");
            int result = LibProductIO.deleteProduct(productCode);
            
            products = LibProductIO.getAllProducts();
            request.setAttribute("products", products);
            
            String url = "/product_manager/product_manager.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
