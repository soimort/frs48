package frs.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class BankDAO {

    @PersistenceContext(unitName = "bank-unit")
    private EntityManager entityManager;

    public void save(Bank bank) {
        entityManager.persist(bank);
    }

    public void update(Bank bank) {
        entityManager.merge(bank);
    }

    public List<Bank> getAll() {
        return entityManager.createNamedQuery("Bank.getAll", Bank.class)
            .getResultList();
    }

    public Bank getBankByIdNo(String idNo) throws Exception {
        Query query = entityManager.createQuery("SELECT b FROM Bank AS b WHERE b.idNo = '" + idNo + "'");
        List<Bank> banks = query.getResultList();
        return banks.get(0);
    }

}
