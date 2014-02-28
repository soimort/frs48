package frs.beans;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 1.0
 * @since  2014-01-14
 *
 * Traveller Entity.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Traveller.getAll", query = "SELECT t FROM Traveller t")})
public class Traveller {

    @Id
    @GeneratedValue
    private long id;

    private String idNo;
    private String travellerName;
    private String email;
    private Date travelDate;
    private String fromCode;
    private String toCode;
    private double price;

    public Traveller() {
    }

    public Traveller(String idNo,
                     String travellerName,
                     String email,
                     Date travelDate,
                     String fromCode,
                     String toCode,
                     double price) {
        this.idNo = idNo;
        this.travellerName = travellerName;
        this.email = email;
        this.travelDate = travelDate;
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.price = price;
    }

    // Getter for id.
    public long getId() {
        return id;
    }

    // Setter for id.
    public void setId(long id) {
        this.id = id;
    }

    // Getter for idNo.
    public String getIdNo() {
        return idNo;
    }

    // Setter for idNo.
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    // Getter for travellerName.
    public String getTravellerName() {
        return travellerName;
    }

    // Setter for travellerName.
    public void setTravellerName(String travellerName) {
        this.travellerName = travellerName;
    }

    // Getter for email.
    public String getEmail() {
        return email;
    }

    // Setter for email.
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for travelDate.
    public Date getTravelDate() {
        return travelDate;
    }

    // Setter for travelDate.
    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    // Getter for fromCode.
    public String getFromCode() {
        return fromCode;
    }

    // Setter for fromCode.
    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    // Getter for toCode.
    public String getToCode() {
        return toCode;
    }

    // Setter for toCode.
    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    // Getter for price.
    public double getPrice() {
        return price;
    }

    // Setter for price.
    public void setPrice(double price) {
        this.price = price;
    }

}
