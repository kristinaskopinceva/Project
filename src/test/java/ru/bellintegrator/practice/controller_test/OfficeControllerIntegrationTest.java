package ru.bellintegrator.practice.controller_test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.view.office.OfficeView;
import ru.bellintegrator.practice.view.organization.OrganizationView;

/**
 * Итеграционный тест для контроллера Office, для тестирования используется TestRestTemplate класс
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OfficeControllerIntegrationTest {
   static final String PATTERN_URL = "http://localhost:8080/api/office";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetListWhenSuccess() {
        OfficeView officeView = new OfficeView();
        officeView.setOrgId(1);
        officeView.setName("Офис Магнит ГК");
        officeView.setPhone("+7(861)-456-00-00");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OfficeView> httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<OfficeView> responseEntity = testRestTemplate.exchange(PATTERN_URL + "/list", HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<OfficeView>() {
                });
        OfficeView data = responseEntity.getBody();//формируем ответ
        Assert.assertNotNull(data);
        Assert.assertEquals(1, (int) data.getId());
    }

    @Test
    public void testGetListWhenError() {
        OfficeView officeView = new OfficeView();
        officeView.setOrgId(1);
        officeView.setName("Офис Магнит ГК");
        officeView.setPhone("+7(861)-456-00-00");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OfficeView> httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(PATTERN_URL + "/list", HttpMethod.POST, httpEntity,
                String.class);
        String expected = "{\"error\":Список офисов по указанным параметрам не сформирован!}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testGetOrgByIdWhenSuccess() {
        ResponseEntity<OfficeView> responseEntity =
                testRestTemplate.exchange( PATTERN_URL + "/1", HttpMethod.GET, null,
                        new ParameterizedTypeReference<OfficeView>() {
                        });
        Assert.assertEquals("Офис Магнит ГК", responseEntity.getBody().getName());
    }

    @Test
    public void testGetOrgByIdWhenError() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/158", HttpMethod.GET, null,
                        new ParameterizedTypeReference<String>() {
                        });
        String expected = "{\"error\":\"Офис с id: 158 не найден в БД!\"}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testUpdateWhenSuccess() {
        OfficeView officeView = new OfficeView();
        officeView.setId(1);
        officeView.setName("Ростелеком офис");
        officeView.setAddress("г.Краснодар, ул.Красная, 65/9");
        officeView.setPhone("89615400005");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/update", HttpMethod.POST, httpEntity, String.class);
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));
    }
    @Test
    public void testUpdateWhenError() {
        OfficeView officeView = new OfficeView();
        officeView.setId(1);
        officeView.setAddress("г.Краснодар, ул.Красная, 65/9");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/update", HttpMethod.POST, httpEntity, String.class);
        String expected = "{\"error\":Указанный id не найден или не заполнены обязательные поля, обновление не будет произведено!}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testAddWhenSuccess() {
        OfficeView officeView = new OfficeView();
        officeView.setOrgId(2);
        officeView.setName("СервисЮГ_Офис");
        officeView.setAddress("г.Краснодар, ул. Уральская 221");
        officeView.setPhone("8961564000");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.exchange(PATTERN_URL + "/add", HttpMethod.POST, httpEntity, String.class);
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));
    }
    @Test
    public void testAddWhenError() {
        OrganizationView organizationView = new OrganizationView();
        OfficeView officeView = new OfficeView();
        officeView.setOrgId(2);
        officeView.setAddress("г.Краснодар, ул. Уральская 221");
        officeView.setPhone("8961564000");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.exchange(PATTERN_URL + "/add", HttpMethod.POST, httpEntity, String.class);
        String expected = "{\"error\":Обязательные параметры указаны не полностью, запись не будет создана в БД!}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }
}