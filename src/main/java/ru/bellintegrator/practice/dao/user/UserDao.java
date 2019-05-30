package ru.bellintegrator.practice.dao.user;

import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

/**
 * слой Dao для сотрудников
 */
public interface UserDao {
    /**
     * Формирует лист с типом данных User по заданым парамтрам
     *
     * @param userView
     */
    public List<User> getList(UserView userView);

    /**
     * Возвращает информацию о сотруднике по заданному id
     *
     * @param id
     */
    public User getById(Integer id);

    /**
     * Обновить данные о сотруднике в БД
     *
     * @param user
     */
    public void update(User user);

    /**
     * Добавить сотрудника в БД
     *
     * @param user
     */
    public void add(User user);
}