package ru.bellintegrator.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.controller.advice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для сотрудников
 */
@Api(value = "UserController", description = "Управление информацией о работниках")
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Получить список сотрудников по фильтру", httpMethod = "POST")
    @PostMapping("/list")
    public List<UserView> getList(@RequestBody UserView view) {
        List<UserView> userViews = userService.getList(view);
        if (userViews.isEmpty()) {
            throw new IllegalStateException("Сотрудники не найдены!");
        } else {
            return userViews;
        }
    }

    @ApiOperation(value = "Получить сотрудников по id", httpMethod = "GET")
    @GetMapping(value = "/{id:[\\d]+}\"}")
    public UserView getById(@PathVariable("id") Integer id) {
        UserView userView = userService.getById(id);
        if (userView == null) {
            throw new CustomNotFoundException("Организация с id " + id + " не найдена");
        } else {
            return userView;
        }
    }

    @ApiOperation(value = "Создать и сохранить измнения", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody UserView view) {
        userService.add(view);
    }

    @ApiOperation(value = "Обновить список организаций", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody UserView view) {
        userService.update(view);
    }
}
