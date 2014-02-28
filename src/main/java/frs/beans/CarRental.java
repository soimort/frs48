package frs.beans;

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
 * CarRental Entity.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CarRental.getAll", query = "SELECT c FROM CarRental c")})
public class CarRental {

    @Id
    @GeneratedValue
    private long id;

    private String licenseNo;
    private String driverName;
    private String modelYear;
    private double kilometers;
    private int seatsNo;
    private int days;
    private double payment;

    public CarRental() {
    }

    public CarRental(String licenseNo,
                     String driverName,
                     String modelYear,
                     double kilometers,
                     int seatsNo,
                     int days,
                     double payment) {
        this.licenseNo = licenseNo;
        this.driverName = driverName;
        this.modelYear = modelYear;
        this.kilometers = kilometers;
        this.seatsNo = seatsNo;
        this.days = days;
        this.payment = payment;
    }

    // Getter for id.
    public long getId() {
        return id;
    }

    // Setter for id.
    public void setId(long id) {
        this.id = id;
    }

    // Getter for licenseNo.
    public String getLicenseNo() {
        return licenseNo;
    }

    // Setter for licenseNo.
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    // Getter for driverName.
    public String getDriverName() {
        return driverName;
    }

    // Setter for driverName.
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    // Getter for modelYear.
    public String getModelYear() {
        return modelYear;
    }

    // Setter for modelYear.
    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    // Getter for kilometers.
    public double getKilometers() {
        return kilometers;
    }

    // Setter for kilometers.
    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    // Getter for seatsNo.
    public int getSeatsNo() {
        return seatsNo;
    }

    // Setter for seatsNo.
    public void setSeatsNo(int seatsNo) {
        this.seatsNo = seatsNo;
    }

    // Getter for days.
    public int getDays() {
        return days;
    }

    // Setter for days.
    public void setDays(int days) {
        this.days = days;
    }

    // Getter for payment.
    public double getPayment() {
        return payment;
    }

    // Setter for payment.
    public void setPayment(double payment) {
        this.payment = payment;
    }

}
