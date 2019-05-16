package ru.bellintegrator.practice.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.MyException;
import ru.bellintegrator.practice.model.Organization;

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
        CriteriaBuilder builder = em.getCriteriaBuilder(); // строить объеты запросов
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class); // парамтеры типа возвра данных
        Root<Organization> organizationRoot = criteria.from(Organization.class); //корн каталог для обхода деревва и отпра запрос в em
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(organizationRoot.get("name"), org.getName()));
        predicates.add(builder.equal(organizationRoot.get("inn"), org.getInn()));
        predicates.add(builder.equal(organizationRoot.get("isActive"), org.getActive()));
        criteria.select(organizationRoot).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(criteria).getResultList();

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
        //проверка на обяз.поля in при update, проверка на id в бд есть в слое сервис
        if (org.getName() != null && org.getFullName() != null && org.getInn() != null && org.getKpp() != null &&
                org.getAddress() != null) {
            return em.merge(org);
        } else {
            throw new MyException("Не все параметры указаны, обновление информации не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization add(Organization org) {
        if (org.getName() != null && org.getFullName() != null && org.getInn() != null &&
                org.getKpp() != null && org.getAddress() != null) {
            em.persist(org);
            return org;
        }
        throw new MyException("Обязательные параметры указаны не полностью, запись не будет создана в БД!");
    }

}