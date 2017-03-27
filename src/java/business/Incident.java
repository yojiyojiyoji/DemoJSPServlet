/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yuno
 */
public class Incident implements Serializable {
    
    private int incidentID; //int NOT NULL AUTO_INCREMENT,
    private int customerID;
    private String productCode;
    private int techID;
    private Date dateOpened;
    private Date dateClosed;
    private String title;
    private String description;

    public Incident() {
        this.incidentID = 0;
        this.customerID = 0;
        this.productCode = "";
        this.techID = 0;
        this.dateOpened = new Date(0);
        this.dateClosed = new Date(0);
        this.title = "";
        this.description = "";
    }

    public Incident(int incidentID, int customerID, String productCode, int techID, Date dateOpened, Date dateClosed, String title, String description) {
        this.incidentID = incidentID;
        this.customerID = customerID;
        this.productCode = productCode;
        this.techID = techID;
        this.dateOpened = dateOpened;
        this.dateClosed = dateClosed;
        this.title = title;
        this.description = description;
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
}
