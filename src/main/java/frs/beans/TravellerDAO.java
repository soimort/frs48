package frs.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TravellerDAO {

    @PersistenceContext(unitName = "traveller-unit")
    private EntityManager entityManager;

    public void save(Traveller traveller) {
        entityManager.persist(traveller);
    }

    public void update(Traveller traveller) {
        entityManager.merge(traveller);
    }

    public List<Traveller> getAll() {
        return entityManager.createNamedQuery("Traveller.getAll", Traveller.class)
            .getResultList();
    }

    public Traveller getTravellerByIdNo(String idNo) throws Exception {
        Query query = entityManager.createQuery("SELECT b FROM Traveller AS t WHERE t.idNo = '" + idNo + "'");
        List<Traveller> travellers = query.getResultList();
        return travellers.get(0);
    }

}
