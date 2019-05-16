package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.organization.OrganizationView;

import java.util.List;

/**
 * Сервис для огранизаций
 */
@Validated
public interface OrganizationService {
    /**
     * Получить список огр по фильру из БД
     *
     * @param organizationView
     */
    List<OrganizationView> getList(OrganizationView organizationView);

    /**
     * Получить огр по Идентифика́тору из БД
     *
     * @param id
     */
    OrganizationView getById(Integer id);

    /**
     * Обновить информацию об огр в БД
     *
     * @param organizationView
     */
    void update(OrganizationView organizationView);

    /**
     * Добавить новый офис в БД
     *
     * @param organizationView
     */
    void add(OrganizationView organizationView);

}