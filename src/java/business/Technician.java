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
public class Technician implements Serializable{
    
    private int techID; // int NOT NULL AUTO_INCREMENT,
    private String firstName;
    private String lastName;
    private String email; // UNIQUE,
    private String phone;
    private String password;

    public Technician() {
        this.techID = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.password = "";
    }

    public Technician(int techID, String firstName, String lastName, String email, String phone, String password) {
        this.techID = techID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getTechID() {
        return techID;
    }

    public void setTechID(int techID) {
        this.techID = techID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
