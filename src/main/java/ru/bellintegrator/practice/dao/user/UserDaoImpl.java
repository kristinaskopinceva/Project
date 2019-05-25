package ru.bellintegrator.practice.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.DaoException;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
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
    public List<User> getList(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder(); // строить объеты запросов
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class); // парамтеры типа возвра данных
        Root<User> userRoot = criteriaQuery.from(User.class); //корн каталог для обхода деревва и отпра запрос в em
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(userRoot.get("office").get("id"), user.getOffice().getId()));
        predicates.add(builder.equal(userRoot.get("firstName"), user.getFirstName()));
        predicates.add(builder.equal(userRoot.get("secondName"), user.getSecondName()));
        predicates.add(builder.equal(userRoot.get("middleName"), user.getMiddleName()));
        predicates.add(builder.equal(userRoot.get("position"), user.getPosition()));
        predicates.add(builder.equal(userRoot.get("doc").get("docType").get("code"), user.getDoc()));
        predicates.add(builder.equal(userRoot.get("country").get("code"), user.getCountry().getCode()));
        criteriaQuery.select(userRoot).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(criteriaQuery).getResultList();
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
        if (user != null) {
            em.merge(user);
        } else {
            throw new DaoException("Пустая ссылка в объекте user, обновление информации не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(User user) {
        if (user != null) {
            em.persist(user);
        } else {
            throw new DaoException("Пустая ссылка в объекте user, запись не будет создана в БД!");
        }
    }
}







