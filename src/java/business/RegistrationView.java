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
public class RegistrationView implements Serializable {
    
    private int customerID;
    private String productCode;
    private Date registrationDate;
    
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    
    private String productName;

    public RegistrationView() {
        this.customerID = 0;
        this.productCode = "";
        this.registrationDate = new Date(0);
        this.customerFirstName = "";
        this.customerLastName = "";
        this.customerEmail = "";
        this.productName = "";
    }

    public RegistrationView(int customerID, String productCode, Date registrationDate, String customerFirstName, String customerLastName, String customerEmail, String productName) {
        this.customerID = customerID;
        this.productCode = productCode;
        this.registrationDate = registrationDate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.productName = productName;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
