package ru.bellintegrator.practice.controller;
import com.sun.xml.internal.ws.handler.HandlerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.Organization.FilterView;
import ru.bellintegrator.practice.view.Organization.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


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
    public List<OrganizationView> getOrgByFilter (@RequestBody OrganizationFilterView view)  {
        return organizationService.getByFilter(view.getInn(),view.getName(),view.getIsActive());}


    @ApiOperation(value = "Получить список орг по id", httpMethod = "GET")
    @RequestMapping(value = "/{id:[\\d]+}\"}")
    public OrganizationView getOrgById(@PathVariable ("id") Integer id){
        return organizationService.getById(id);}

    @ApiOperation(value = "Обновить список организаций", httpMethod = "POST")
    @RequestMapping(value = "/update")
    public OrganizationView update(@RequestBody OrganizationView view){
        return  organizationService.update(view);}

    @ApiOperation(value = "Создать и сохранить измнения", httpMethod = "POST")
    @RequestMapping(value = "/save")
    public OrganizationView save(@RequestBody OrganizationView view){
        return  organizationService.save(view);}
}