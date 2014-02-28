package frs.beans;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.EntityTransaction;

@WebService(portName = "RentalServicePort", serviceName = "RentalServiceWebService", targetNamespace = "http://frs.org/wsdl")
@Stateless
public class RentalService {

    @PersistenceContext(unitName = "carrental-unit")
    private EntityManager entityManager;

    public double saveRental(String licenseNo, String driverName, String modelYear, double kilometers, int seatsNo, int days) {
        double z;
        if (kilometers >= 50000)
            z = 200;
        else if(kilometers < 10000)
            z = 0;
        else
            z = 100;
        double x = 200 * (2013 - Integer.parseInt(modelYear)) + z;
        double payment = (1500 - x) * days * 7 / seatsNo;

        CarRental carRental = new CarRental(licenseNo,
                                            driverName,
                                            modelYear,
                                            kilometers,
                                            seatsNo,
                                            days,
                                            payment);
        return payment;
    }

}
