package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

/**
 * Сервис для сотрудников орг
 */
@Validated
public interface UserService {
    /**
     * Получить список струдников по фильру из БД
     *
     * @param userView
     */
    List<UserView> getList(UserView userView);

    /**
     * Получить сотрудника по Идентифика́тору из БД
     *
     * @param id
     */
    UserView getById(Integer id);

    /**
     * Обновить информацию о струднике
     *
     * @param userView
     */
    void update(UserView userView);

    /**
     * Добавить нового сотрудника в БД
     *
     * @param userView
     */
    void add(UserView userView);

}