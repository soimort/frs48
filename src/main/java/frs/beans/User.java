package frs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 1.0
 * @since  2014-01-15
 *
 * User Entity.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")})
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String idNo;

    private String name;
    private String email;
    private String country;

    public User() {
    }

    public User(String userName, String password, String name, String email, String country) {
        this.userName = userName;
        this.password = password;
        this.name = name;
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

    // Getter for password.
    public String getPassword() {
        return password;
    }

    // Setter for password.
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for idNo.
    public String getIdNo() {
        return idNo;
    }

    // Setter for idNo.
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    // Getter for name.
    public String getName() {
        return name;
    }

    // Setter for name.
    public void setName(String name) {
        this.name = name;
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
