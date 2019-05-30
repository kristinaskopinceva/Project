package ru.bellintegrator.practice.dao.office;

import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * слой Dao для офиса
 */
public interface OfficeDao {
    /**
     * Формирует лист с типом данных Office по заданым парамтрам
     *
     * @param office
     */
    public List<Office> getList(Office office);

    /**
     * Возвращает информацию об офисе по заданному id
     *
     * @param id
     */
    public Office getById(Integer id);

    /**
     * Обновить данные об офисе в БД
     *
     * @param office
     */
    public void update(Office office);

    /**
     * Добавить новый офис в БД
     *
     * @param
     */
    public void add(Office office);
}