package ru.bellintegrator.practice.dao.user;

import ru.bellintegrator.practice.model.User;

import java.util.List;

/**
 * слой Dao для сотрудников
 */
public interface UserDao {
    /**
     * Формирует лист с типом данных User по заданым парамтрам
     *
     * @param user
     */
    public List<User> getList(User user);

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