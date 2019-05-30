package ru.bellintegrator.practice.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getList(UserView userView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("office").get("id"), userView.getOfficeId()));
        predicates.add(builder.like(root.get("firstName"), userView.getFirstName()));
        predicates.add(builder.like(root.get("secondName"), userView.getSecondName()));
        predicates.add(builder.like(root.get("middleName"), userView.getMiddleName()));
        predicates.add(builder.like(root.get("position"), userView.getPosition()));
        predicates.add(builder.equal(root.get("doc").get("docType").get("code"),
                userView.getDocCode()));
        predicates.add(builder.equal(root.get("country").get("code"), userView.getCitizenshipCode()));
        criteriaQuery
                .where(predicates.toArray(new Predicate[]{}))
                .select(root);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Integer id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        em.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(User user) {
        em.persist(user);
    }
}







