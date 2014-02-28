package frs.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "user-unit")
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public List<User> getAll() {
        return entityManager.createNamedQuery("User.getAll", User.class)
            .getResultList();
    }

    public User getUserByUserName(String userName) throws Exception {
        Query query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.userName = '" + userName + "'");
        List<User> users = query.getResultList();
        return users.get(0);
    }

    public User getUserByIdNo(String idNo) throws Exception {
        Query query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.idNo = '" + idNo + "'");
        List<User> users = query.getResultList();
        return users.get(0);
    }

}
