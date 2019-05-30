package ru.bellintegrator.practice.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;

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
public class OrganizationDaoImpl implements OrganizationDao {
    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getList(Organization org) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> root = criteriaQuery.from(Organization.class);
        criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("name"), org.getName()));
        predicates.add(builder.like(root.get("inn"), org.getInn()));
        predicates.add(builder.equal(root.get("isActive"), org.getActive()));
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getById(Integer id) {
        return em.find(Organization.class, id);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization update(Organization org) {
        return em.merge(org);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization add(Organization org) {
        return em.merge(org);

    }
}
