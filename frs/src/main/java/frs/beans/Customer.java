package frs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-11
 *
 * Customer Entity.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private String userName;
    private String email;
    private String country;

    public Customer() {
    }

    public Customer(String userName, String email, String country) {
        this.userName = userName;
        this.email = email;
        this.country = country;
    }

    // Getter for id.
    public long getId() {
        return id;
    }

    // Setter for id.
    public void setId(long id) {
        this.id = id;
    }

    // Getter for userName.
    public String getUserName() {
        return userName;
    }

    // Setter for userName.
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter for email.
    public String getEmail() {
        return email;
    }

    // Setter for email.
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for country.
    public String getCountry() {
        return country;
    }

    // Setter for country.
    public void setCountry(String country) {
        this.country = country;
    }

}
