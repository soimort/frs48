package frs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-10
 *
 * Airline Entity.
 */
@Entity
public class Airline {

    @Id
    @GeneratedValue
    private long id;

    private String airlineCode;
    private String airlineName;

    public Airline() {
    }

    public Airline(String airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    // Getter for id.
    public long getId() {
        return id;
    }

    // Setter for id.
    public void setId(long id) {
        this.id = id;
    }

    // Getter for airlineCode.
    public String getAirlineCode() {
        return airlineCode;
    }

    // Setter for airlineCode.
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    // Getter for airlineName.
    public String getAirlineName() {
        return airlineName;
    }

    // Setter for airlineName.
    public void setAirlineName(String airlinename) {
        this.airlineName = airlineName;
    }
}
