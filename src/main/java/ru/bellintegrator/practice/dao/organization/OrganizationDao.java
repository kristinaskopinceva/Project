package ru.bellintegrator.practice.dao.organization;

import ru.bellintegrator.practice.model.Organization;

import java.util.List;

/**
 * слой Dao для организаций
 */
public interface OrganizationDao {
    /**
     * Формирует лист с типом данных Organization по заданым парамтрам
     *
     * @param organization
     */
    public List<Organization> getList(Organization organization);

    /**
     * Возвращает информацию об организации по заданному id
     *
     * @param id
     */
    public Organization getById(Integer id);

    /**
     * Обновить данные об организации в БД
     *
     * @param organization
     */
    public Organization update(Organization organization);

    /**
     * Добавить организацию в БД
     *
     * @param organization
     */
    public Organization add(Organization organization);
}