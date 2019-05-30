package ru.bellintegrator.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.organization.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для организаций
 */
@Api(value = "OrganizationController", description = "Управление информацией об организациях")
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Получить список орг по фильтру", httpMethod = "POST")
    @RequestMapping(value = "/list")
    public List<OrganizationView> getOrgByFilter(@RequestBody OrganizationView organizationView) {
        return organizationService.getList(organizationView);
    }

    @ApiOperation(value = "Получить огр по id", httpMethod = "GET")
    @GetMapping(value = "/{id:[\\d]+}")
    public OrganizationView getOrgById(@PathVariable("id") Integer id) {
        return organizationService.getById(id);
    }

    @ApiOperation(value = "Обновить орг", httpMethod = "POST")
    @RequestMapping(value = "/update")
    public void update(@RequestBody OrganizationView view) {
        organizationService.update(view);
    }

    @ApiOperation(value = "Добавить орг", httpMethod = "POST")
    @RequestMapping(value = "/add")
    public void add(@RequestBody OrganizationView view) {
        organizationService.add(view);
    }
}