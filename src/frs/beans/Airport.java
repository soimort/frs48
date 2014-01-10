package frs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Airport {

    @Id
    @GeneratedValue
    private long id;

    private String airportCode;
    private String airportName;
    private String city;
    private double latitude;
    private double longitude;
    private double altitude;

    public Airport() {
    }

    public Airport(String airportCode, String airportName, String city, double latitude, double longitude, double altitude) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    // Getter for id
    public long getId() {
        return id;
    }

    // Setter for id
    public void setId(long id) {
        this.id = id;
    }

    // Getter for airportCode
    public String getAirportCode() {
        return airportCode;
    }

    // Setter for airportCode
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter for airportName
    public String getAirportName() {
        return airportName;
    }

    // Setter for airportName
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    // Getter for city
    public String getCity() {
        return city;
    }

    // Setter for city
    public void setCity(String city) {
        this.city = city;
    }

    // Getter for latitude
    public double getLatitude() {
        return latitude;
    }

    // Setter for latitude
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Getter for longitude
    public double getLongitude() {
        return longitude;
    }

    // Setter for longitude
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Getter for altitude
    public double getAltitude() {
        return altitude;
    }

    // Setter for altitude
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
