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
public class Registration implements Serializable {
    
    private int customerID;
    private String productCode;
    private Date registrationDate;

    public Registration() {
        this.customerID = 0;
        this.productCode = "";
        this.registrationDate = new Date(0);
    }

    public Registration(int customerID, String productCode, Date registrationDate) {
        this.customerID = customerID;
        this.productCode = productCode;
        this.registrationDate = registrationDate;
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
}
