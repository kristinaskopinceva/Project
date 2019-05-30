package ru.bellintegrator.practice.dao.document;

import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.DocType;

/**
 * слой Dao для юзер док инфо
 */
public interface DocDao {
    /**
     * Произвести обновление инфомации по док. сторудника
     *
     * @param doc
     */
    public void update(Doc doc);
    /**
     * добавить документ сотрудника
     *
     * @param doc
     */
    public void add(Doc doc);
    /**
     * получить док через доктайп
     *
     * @param docType
     */
    public Doc getByDocType(DocType docType);



}