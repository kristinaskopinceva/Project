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
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.service.UserServiceImpl;
import ru.bellintegrator.practice.view.user.UserFilterView;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
        public List<UserView> getList(@RequestBody UserFilterView view)  {
            return userService.getByFilter(view);
        }
        @ApiOperation(value = "Получить сотрудников по id", httpMethod = "GET")
        @GetMapping(value = "/{id:[\\d]+}\"}")
        public UserView getById(@PathVariable("id") Integer id)  {
            return userService.getById(id);
        }
        @ApiOperation(value = "Создать и сохранить измнения", httpMethod = "POST")
        @PostMapping("/save")
        public UserView save(@RequestBody UserView view)  {
            return userService.save(view);
        }
        @ApiOperation(value = "Обновить список организаций", httpMethod = "POST")
        @PostMapping("/update")
        public UserView update(@RequestBody UserView view)  {
            return userService.update(view);
        }
    }
