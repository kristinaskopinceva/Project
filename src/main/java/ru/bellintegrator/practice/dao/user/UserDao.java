package ru.bellintegrator.practice.dao.user;

import org.springframework.stereotype.Component;
import ru.bellintegrator.practice.model.User;

import java.util.List;

@Component
public interface UserDao {
    List<User> getList(User user);

    User getById(Integer id);

    User update(User user);

    User add(User user);

}