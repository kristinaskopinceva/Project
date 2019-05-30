package ru.bellintegrator.practice.dao.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {
    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getById(Integer id) {
        return em.find(Country.class, id);
    }
    @Override
    public Country getByCode(Integer citizenshipCode) {
       /* String query = "SELECT h.id FROM "+ Country.class.getSimpleName()+" h WHERE h.code = :code";
        TypedQuery<Country> typedQuery = em.createQuery(query,Country.class);
        typedQuery.setParameter("code",code);
       return typedQuery.getSingleResult();
       */
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        criteriaQuery.where(builder.equal(root.get("code"),citizenshipCode)).select(root);
        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();

}}