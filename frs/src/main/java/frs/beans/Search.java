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
 * Search EJB: corresponds with EJB-1 in specification.
 */
@Stateful
public class Search {

    @PersistenceContext(unitName = "airline-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airlineEntityManager;

    @PersistenceContext(unitName = "airport-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airportEntityManager;

    @PersistenceContext(unitName = "route-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager routeEntityManager;

    // Search for all possible routes.
    public List<Route> getRoutes(String orig, String dest) throws Exception {
        Query origAirportQuery = airportEntityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + orig + "'");
        List<Airport> origAirports = origAirportQuery.getResultList();

        Query destAirportQuery = airportEntityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + dest + "'");
        List<Airport> destAirports = destAirportQuery.getResultList();

        List<Route> routes = new ArrayList<Route>();
        for (Airport origAirport : origAirports)
            for (Airport destAirport : destAirports) {
                Query routeQuery = routeEntityManager.createQuery("SELECT r FROM Route AS r WHERE r.fromCode = '" + origAirport.getAirportCode() + "' AND r.toCode = '" + destAirport.getAirportCode() + "'");
                routes.addAll(routeQuery.getResultList());
            }

        return routes;
    }

    // Get all inbound routes.
    public List<Route> getInboundRoutes(String airportCode) throws Exception {
        Query routeQuery = routeEntityManager.createQuery("SELECT r FROM Route AS r WHERE r.toCode = '" + airportCode + "'");
        List<Route> routes = routeQuery.getResultList();
        return routes;
    }

    // Get all outbound routes.
    public List<Route> getOutboundRoutes(String airportCode) throws Exception {
        Query routeQuery = routeEntityManager.createQuery("SELECT r FROM Route AS r WHERE r.fromCode = '" + airportCode + "'");
        List<Route> routes = routeQuery.getResultList();
        return routes;
    }

}
