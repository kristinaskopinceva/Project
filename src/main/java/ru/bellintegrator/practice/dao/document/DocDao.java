package ru.bellintegrator.practice.dao.document;

import ru.bellintegrator.practice.model.Doc;

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

}