package ru.bellintegrator.practice.dao.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.DaoException;
import ru.bellintegrator.practice.model.Doc;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
@Repository
public class DocDaoImpl implements DocDao {
    private final EntityManager em;

    @Autowired
    public DocDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Doc doc) {
        if (doc != null) {
            em.merge(doc);
        } else {
            throw new DaoException("Невозможно обновить информацию о докуменете сотрудника!");
        }}
        /**
         * {@inheritDoc}
         */
        @Override
        public void add(Doc doc){
            if (doc != null) {
                em.persist(doc);
            } else {
                throw new DaoException("Невозможно сщздать информацию о докуменете сотрудника!");
            }}
        }







