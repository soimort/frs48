package frs.beans;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-11
 *
 * CityInfo EJB: corresponds with EJB-3 in specification.
 */
@Stateful
public class CityInfo {

    @PersistenceContext(unitName = "airline-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airlineEntityManager;

    @PersistenceContext(unitName = "airport-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airportEntityManager;

    @PersistenceContext(unitName = "route-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager routeEntityManager;

    // Get altitudes.
    public List<Double> getAltitudes(String orig) throws Exception {
        Query origAirportQuery = airportEntityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + orig + "'");
        List<Airport> origAirports = origAirportQuery.getResultList();

        List<Double> altitudes = new ArrayList<Double>();
        for (Airport origAirport : origAirports) {
            double a = origAirport.getAltitude();
            altitudes.add(a);
        }

        return altitudes;
    }

    // Get latitudes.
    public List<Double> getLatitudes(String orig) throws Exception {
        Query origAirportQuery = airportEntityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + orig + "'");
        List<Airport> origAirports = origAirportQuery.getResultList();

        List<Double> latitudes = new ArrayList<Double>();
        for (Airport origAirport : origAirports) {
            double l = origAirport.getLatitude();
            latitudes.add(l);
        }

        return latitudes;
    }

    // Get longitudes.
    public List<Double> getLongitudes(String orig) throws Exception {
        Query origAirportQuery = airportEntityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + orig + "'");
        List<Airport> origAirports = origAirportQuery.getResultList();

        List<Double> longitudes = new ArrayList<Double>();
        for (Airport origAirport : origAirports) {
            double l = origAirport.getLongitude();
            longitudes.add(l);
        }

        return longitudes;
    }

}
