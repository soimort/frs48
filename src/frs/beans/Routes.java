package frs.beans;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-10
 *
 * Routes EJB.
 */
@Stateful
public class Routes {

    @PersistenceContext(unitName = "route-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public void addRoute(Route route) throws Exception {
        entityManager.persist(route);
    }

    public void deleteRoute(Route route) throws Exception {
        entityManager.remove(route);
    }

    public List<Route> getRoutes() throws Exception {
        Query query = entityManager.createQuery("SELECT r from Route as r");
        return query.getResultList();
    }
}
