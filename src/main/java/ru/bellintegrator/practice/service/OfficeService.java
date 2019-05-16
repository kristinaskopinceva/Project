package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.office.OfficeView;

import java.util.List;

/**
 * Сервис для офиса
 */
@Validated
public interface OfficeService {
    /**
     * Получить список офисов по фильру из БД
     *
     * @param officeView
     */
    List<OfficeView> getList(OfficeView officeView);

    /**
     * Получить офис по Идентифика́тору из БД
     *
     * @param id
     */
    OfficeView getById(Integer id);

    /**
     * Обновить информацию об офисе в БД
     *
     * @param officeView
     */
    void update(OfficeView officeView);

    /**
     * Добавить новый офис в БД
     *
     * @param officeView
     */
    void add(OfficeView officeView);


}
