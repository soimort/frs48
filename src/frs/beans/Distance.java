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
 * Distance EJB: corresponds with EJB-2 in specification.
 */
@Stateful
public class Distance {

    @PersistenceContext(unitName = "airline-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airlineEntityManager;

    @PersistenceContext(unitName = "airport-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airportEntityManager;

    @PersistenceContext(unitName = "route-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager routeEntityManager;

    // Calculate distance between two cities.
    public List<Double> calcDistance(String orig, String dest) throws Exception {
        Query origAirportQuery = airportEntityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + orig + "'");
        List<Airport> origAirports = origAirportQuery.getResultList();

        Query destAirportQuery = airportEntityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + dest + "'");
        List<Airport> destAirports = destAirportQuery.getResultList();

        List<Double> distances = new ArrayList<Double>();
        double r = 6371.0; // mean earth radius
        for (Airport origAirport : origAirports)
            for (Airport destAirport : destAirports) {
                double phi1 = Math.toRadians(origAirport.getLatitude());
                double lambda1 = Math.toRadians(origAirport.getLongitude());
                double phi2 = Math.toRadians(destAirport.getLatitude());
                double lambda2 = Math.toRadians(destAirport.getLongitude());
                double d = Math.acos(Math.sin(phi1) * Math.sin(phi2) + Math.cos(phi1) * Math.cos(phi2) * Math.cos(Math.abs(lambda1 - lambda2))) * r;
                distances.add(d);
            }

        return distances;
    }

}
