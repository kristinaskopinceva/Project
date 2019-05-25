package ru.bellintegrator.practice.dao.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Country;

import javax.persistence.EntityManager;

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
}