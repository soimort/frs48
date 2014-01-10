package frs.beans;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Stateful
public class Airports {

    @PersistenceContext(unitName = "airport-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public void addAirport(Airport airport) throws Exception {
        entityManager.persist(airport);
    }

    public void deleteAirport(Airport airport) throws Exception {
        entityManager.remove(airport);
    }

    public List<Airport> getAirports() throws Exception {
        Query query = entityManager.createQuery("SELECT a from Airport as a");
        return query.getResultList();
    }
}
