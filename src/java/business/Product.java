/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Timestamp;

/**
 *
 * @author yoji_salut
 */
public class Product implements Serializable {
    
    String  productCode;
    String  name;
    double  version;
    Date    releaseDate;
    //Timestamp releaseDate; 

    public Product() {
        this.productCode = "";
        this.name = "";
        this.version = 0.0;
        this.releaseDate = new Date(0);
    }

    public Product(String productCode, String name, double version, Date releaseDate) {
        this.productCode = productCode;
        this.name = name;
        this.version = version;
        this.releaseDate = releaseDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
