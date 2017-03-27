/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

/**
 *
 * @author yoji_salut
 */
public class TechAvailability implements Serializable {
    
    private int techID;
    private String techFirstName;
    private String techLastName;
    private int numOpenIncidents;

    public TechAvailability() {
        this.techID = 0;
        this.techFirstName = "";
        this.techLastName = "";
        this.numOpenIncidents = 0;
    }

    public TechAvailability(int techID, String techFirstName, String techLastName, int numOpenIncidents) {
        this.techID = techID;
        this.techFirstName = techFirstName;
        this.techLastName = techLastName;
        this.numOpenIncidents = numOpenIncidents;
    }

    public int getTechID() {
        return techID;
    }

    public void setTechID(int techID) {
        this.techID = techID;
    }

    public String getTechFirstName() {
        return techFirstName;
    }

    public void setTechFirstName(String techFirstName) {
        this.techFirstName = techFirstName;
    }

    public String getTechLastName() {
        return techLastName;
    }

    public void setTechLastName(String techLastName) {
        this.techLastName = techLastName;
    }

    public int getNumOpenIncidents() {
        return numOpenIncidents;
    }

    public void setNumOpenIncidents(int numOpenIncidents) {
        this.numOpenIncidents = numOpenIncidents;
    }
}
