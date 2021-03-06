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
import ru.bellintegrator.practice.service.OfficeService;
import ru.bellintegrator.practice.view.office.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для офисов
 */
@Api(value = "OfficeController", description = "Управление информацией о офисах")
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Получить список офисов по фильтру", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeView> getList(@RequestBody OfficeView officeView) {
        return officeService.getList(officeView);
    }

    @ApiOperation(value = "Получить оффисы по id", httpMethod = "GET")
    @GetMapping(value = "/{id:[\\d]+}")
    public OfficeView getById(@PathVariable("id") Integer id) {
        return officeService.getById(id);
    }

    @ApiOperation(value = "Обновить список оффисов", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody OfficeView view) {
        officeService.update(view);
    }

    @ApiOperation(value = "Создать и сохранить измнения", httpMethod = "POST")
    @PostMapping("/add")
    public void add(@RequestBody OfficeView view) {
        officeService.add(view);
    }


}

