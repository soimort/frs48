package frs.beans;

import java.util.ArrayList;
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
 * Route DAO.
 */
@Stateless
public class RouteDAO {

    @PersistenceContext(unitName = "route-unit")
    private EntityManager entityManager;

    public void save(Route route) {
        entityManager.persist(route);
    }

    public void update(Route route) {
        entityManager.merge(route);
    }

    public List<Route> getAll() {
        return entityManager.createNamedQuery("Route.getAll", Route.class)
            .getResultList();
    }

    // Get all routes by airports.
    public List<Route> getAllByAirports(List<Airport> origAirports, List<Airport> destAirports) {
        List<Route> routes = new ArrayList<Route>();
        for (Airport origAirport : origAirports) {
            for (Airport destAirport : destAirports) {
                Query query = entityManager.createQuery("SELECT r FROM Route AS r WHERE r.fromCode = '" + origAirport.getAirportCode() + "' AND r.toCode = '" + destAirport.getAirportCode() + "'");
                routes.addAll(query.getResultList());
            }
        }
        return routes;
    }

    // Get all inbound routes.
    public List<Route> getInboundRoutes(String airportCode) throws Exception {
        Query query = entityManager.createQuery("SELECT r FROM Route AS r WHERE r.toCode = '" + airportCode + "'");
        List<Route> routes = query.getResultList();
        return routes;
    }

    // Get all outbound routes.
    public List<Route> getOutboundRoutes(String airportCode) throws Exception {
        Query query = entityManager.createQuery("SELECT r FROM Route AS r WHERE r.fromCode = '" + airportCode + "'");
        List<Route> routes = query.getResultList();
        return routes;
    }
}
