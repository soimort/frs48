package frs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-11
 *
 * Bank Entity.
 */
@Entity
public class Bank {

    @Id
    @GeneratedValue
    private long id;

    private String idNo;
    private String customerName;
    private double balance;

    public Bank() {
    }

    public Bank(String idNo, String customerName, double balance) {
        this.idNo = idNo;
        this.customerName = customerName;
        this.balance = balance;
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

    // Getter for customerName.
    public String getCustomerName() {
        return customerName;
    }

    // Setter for customerName.
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter for balance.
    public double getBalance() {
        return balance;
    }

    // Setter for balance.
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
