package ru.bellintegrator.practice.dao.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.MyException;
import ru.bellintegrator.practice.model.Office;

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
public class OfficeDaoImpl implements OfficeDao {
    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> getList(Office office) {
        CriteriaBuilder builder = em.getCriteriaBuilder(); // строить объеты запросов
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class); // парамтеры типа возвра данных
        Root<Office> officeRoot = criteriaQuery.from(Office.class); //корн каталог для обхода деревва и отпра запрос в em
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(officeRoot.get("organization").get("id"), office.getOrganization().getId()));
        predicates.add(builder.equal(officeRoot.get("name"), office.getName()));
        predicates.add(builder.equal(officeRoot.get("phone"), office.getPhone()));
        predicates.add(builder.equal(officeRoot.get("isActive"), office.getActive()));
        criteriaQuery.select(officeRoot).where(predicates.toArray(new Predicate[]{}));
        List<Office> officeList = em.createQuery(criteriaQuery).getResultList();
        return officeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getById(Integer id) {
        return em.find(Office.class, id);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office update(Office office) {
        if (office.getId() != null && office.getName() != null && office.getAddress() != null) {
            return em.merge(office);
        } else {
            throw new MyException("Не все параметры указаны, обновление информации не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office add(Office office) {
        if (office.getOrganization().getId() != null && office.getName() != null && office.getAddress() != null) {
            em.persist(office);
            return office;
        } else {
            throw new MyException("Обязательные параметры указаны не полностью, запись не будет создана в БД!");
        }
    }


}

