package frs.beans;

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
 * Airlines EJB.
 */
@Stateful
public class Airlines {

    @PersistenceContext(unitName = "airline-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public void addAirline(Airline airline) throws Exception {
        entityManager.persist(airline);
    }

    public void deleteAirline(Airline airline) throws Exception {
        entityManager.remove(airline);
    }

    public List<Airline> getAirlines() throws Exception {
        Query query = entityManager.createQuery("SELECT a from Airline as a");
        return query.getResultList();
    }
}
