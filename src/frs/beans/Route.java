package frs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-10
 *
 * Route Entity.
 */
@Entity
public class Route {

    @Id
    @GeneratedValue
    private long id;

    private String fromCode;
    private String toCode;
    private String airlineCode;
    private String flightNo;
    private double price;

    public Route() {
    }

    public Route(String fromCode, String toCode, String airlineCode, String flightNo, double price) {
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.airlineCode = airlineCode;
        this.flightNo = flightNo;
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

    // Getter for airlineCode.
    public String getAirlineCode() {
        return airlineCode;
    }

    // Setter for airlineCode.
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    // Getter for flightNo.
    public String getFlightNo() {
        return flightNo;
    }

    // Setter for flightNo.
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
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
