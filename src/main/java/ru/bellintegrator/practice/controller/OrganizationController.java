package ru.bellintegrator.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.controller.advice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.exception.ServiceException;
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
        if (organizationView == null) {
            throw new ServiceException("Пустое тело запроса!");
        } else {
            List<OrganizationView> organizationViews = organizationService.getList(organizationView);
            if (organizationViews.isEmpty()) {
                throw new IllegalStateException("Организации не найдены!");
            } else {
                return organizationViews;
            }
        }
    }

    @ApiOperation(value = "Получить огр по id", httpMethod = "GET")
    @RequestMapping(value = "/{id:[\\d]+}\"}")
    public OrganizationView getOrgById(@PathVariable("id") Integer id) {
        OrganizationView organizationView = organizationService.getById(id);
        if (organizationView == null) {
            throw new CustomNotFoundException("Организация с id" + id + " не найдена");
        } else {
            return organizationView;
        }
    }

    @ApiOperation(value = "Обновить орг", httpMethod = "POST")
    @RequestMapping(value = "/update")
    public void update(@RequestBody OrganizationView view) {
        organizationService.update(view);
    }

    @ApiOperation(value = "Добавить орг", httpMethod = "POST")
    @RequestMapping(value = "/save")
    public void save(@RequestBody OrganizationView view) {
        organizationService.add(view);
    }
}