/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yoji_salut
 */
public class IncidentView implements Serializable {
    
    private int incidentID; //int NOT NULL AUTO_INCREMENT,
    private int customerID;
    private String productCode;
    private int techID;
    private Date dateOpened;
    private Date dateClosed;
    private String title;
    private String description;
    
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    
    private String productName;
    
    private String technicianFirstName;
    private String technicianLastName;
    private String technicianEmail;

    public IncidentView() {
        this.incidentID = 0;
        this.customerID = 0;
        this.productCode = "";
        this.techID = 0;
        this.dateOpened = new Date(0);
        this.dateClosed = new Date(0);
        this.title = "";
        this.description = "";
        this.customerEmail = "";
        this.customerFirstName = "";
        this.customerLastName = "";
        this.productName = "";
        this.technicianFirstName = "";
        this.technicianLastName = "";
        this.technicianEmail = "";
    }

    public IncidentView(int incidentID, int customerID, String productCode, 
            int techID, Date dateOpened, Date dateClosed, String title, 
            String description, String customerEmail, String customerFirstName, 
            String customerLastName, String productName, 
            String technicianFirstName, String technicianLastName, 
            String technicianEmail) {
        
        this.incidentID = incidentID;
        this.customerID = customerID;
        this.productCode = productCode;
        this.techID = techID;
        this.dateOpened = dateOpened;
        this.dateClosed = dateClosed;
        this.title = title;
        this.description = description;
        this.customerEmail = customerEmail;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.productName = productName;
        this.technicianFirstName = technicianFirstName;
        this.technicianLastName = technicianLastName;
        this.technicianEmail = technicianEmail;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getTechID() {
        return techID;
    }

    public void setTechID(int techID) {
        this.techID = techID;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTechnicianFirstName() {
        return technicianFirstName;
    }

    public void setTechnicianFirstName(String technicianFirstName) {
        this.technicianFirstName = technicianFirstName;
    }

    public String getTechnicianLastName() {
        return technicianLastName;
    }

    public void setTechnicianLastName(String technicianLastName) {
        this.technicianLastName = technicianLastName;
    }

    public String getTechnicianEmail() {
        return technicianEmail;
    }

    public void setTechnicianEmail(String technicianEmail) {
        this.technicianEmail = technicianEmail;
    }
}
