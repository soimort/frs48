package frs.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 1.0
 * @since  2014-01-14
 *
 * Airport DAO.
 */
@Stateless
public class AirportDAO {

    @PersistenceContext(unitName = "airport-unit")
    private EntityManager entityManager;

    public void save(Airport airport) {
        entityManager.persist(airport);
    }

    public void update(Airport airport) {
        entityManager.merge(airport);
    }

    public List<Airport> getAll() {
        return entityManager.createNamedQuery("Airport.getAll", Airport.class)
            .getResultList();
    }

    // Get all airports by city name.
    public List<Airport> getAllByCity(String city) throws Exception {
        Query query = entityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + city + "'");
        List<Airport> airports = query.getResultList();
        return airports;
    }

    // Get the altitude of the first airport by city name.
    public Double getFirstAltitude(String city) throws Exception {
        Query query = entityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + city + "'");
        List<Airport> airports = query.getResultList();
        return airports.get(0).getAltitude();
    }

    // Get the latitude of the first airport by city name.
    public Double getFirstLatitude(String city) throws Exception {
        Query query = entityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + city + "'");
        List<Airport> airports = query.getResultList();
        return airports.get(0).getLatitude();
    }

    // Get the longitude of the first airport by city name.
    public Double getFirstLongitude(String city) throws Exception {
        Query query = entityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + city + "'");
        List<Airport> airports = query.getResultList();
        return airports.get(0).getLongitude();
    }

    // Calculate distance between two airports by city names.
    public Double calcDistance(String orig, String dest) throws Exception {
        Query origAirportQuery = entityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + orig + "'");
        List<Airport> origAirports = origAirportQuery.getResultList();

        Query destAirportQuery = entityManager.createQuery("SELECT a FROM Airport AS a WHERE a.city = '" + dest + "'");
        List<Airport> destAirports = destAirportQuery.getResultList();

        Airport origAirport = origAirports.get(0);
        Airport destAirport = destAirports.get(0);
        double r = 6371.0; // mean earth radius
        double phi1 = Math.toRadians(origAirport.getLatitude());
        double lambda1 = Math.toRadians(origAirport.getLongitude());
        double phi2 = Math.toRadians(destAirport.getLatitude());
        double lambda2 = Math.toRadians(destAirport.getLongitude());
        double d = Math.acos(Math.sin(phi1) * Math.sin(phi2) + Math.cos(phi1) * Math.cos(phi2) * Math.cos(Math.abs(lambda1 - lambda2))) * r;
        return d;
    }

}
