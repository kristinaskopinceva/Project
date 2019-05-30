package ru.bellintegrator.practice.dao.doc_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao {
    private final EntityManager em;

    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getById(Integer id) {
        DocType docType = em.find(DocType.class, id);
        return docType;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getByName(String name){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = builder.createQuery(DocType.class);
        Root<DocType> root = criteriaQuery.from(DocType.class);
        criteriaQuery.where(builder.equal(root.get("name"),name))
                .select(root);
        TypedQuery<DocType> query = em.createQuery(criteriaQuery);
        System.out.println(query.getSingleResult());
        return query.getSingleResult();
    }
    public DocType getByCode(Integer code){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = builder.createQuery(DocType.class);
        Root<DocType> root = criteriaQuery.from(DocType.class);
        criteriaQuery.where(builder.equal(root.get("code"),code))
                .select(root);
        TypedQuery<DocType> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}