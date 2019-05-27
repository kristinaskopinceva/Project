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
import ru.bellintegrator.practice.view.organization.OrganizationView;

/**
 * Итеграционный тест для контроллера Organization, для тестирования используется TestRestTemplate класс
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrgControllerIntegrationTest {
    static final String PATTERN_URL = "http://localhost:8080/api/organization";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetListWhenSuccess() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Магнит");
        organizationView.setInn("2310031475");
        organizationView.setActive(true);
        HttpHeaders header = new HttpHeaders(); // создаем новой хедер
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(organizationView, header); // собираем HTTP запрос
        ResponseEntity<OrganizationView> responseEntity = testRestTemplate.exchange(PATTERN_URL + "/list", HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<OrganizationView>() {
                });
        Assert.assertEquals(1,(int )responseEntity.getBody().getId());
    }

    @Test
    public void testGetListWhenError() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Магнит");
        organizationView.setActive(false);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(PATTERN_URL + "/list", HttpMethod.POST, httpEntity,
                String.class);
        String expected = "{\"error\":\\Не все обязательные параменты укзааны, список организаций не сформирован!\\}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testGetOrgByIdWhenSuccess() {
        String url = PATTERN_URL + "/1";
        ResponseEntity<OrganizationView> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<OrganizationView>() {
                        });
        OrganizationView data = responseEntity.getBody();
        Assert.assertNotNull(data);
        Assert.assertEquals("ЗАО \"Тандер\"", data.getFullName());
    }

    @Test
    public void testGetOrgByIdWhenError() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/158", HttpMethod.GET, null,
                        new ParameterizedTypeReference<String>() {
                        });
        String expected = "{\"error\":\"Организация с id 158 не найдена в БД!\"}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testUpdateWhenSuccess() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(2);
        organizationView.setName("Ростелеком");
        organizationView.setFullName("ООО Ростелеком Интеграция");
        organizationView.setInn("564238975");
        organizationView.setKpp("997350001");
        organizationView.setAddress("г.Краснодар, ул.Красная, 65");
        organizationView.setPhone("89615467895");
        organizationView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/update", HttpMethod.POST, httpEntity, String.class);
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));
    }

    @Test
    public void testUpdateWhenError() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(18);
        organizationView.setName("Ростелеком");
        organizationView.setFullName("ООО Ростелеком ЮГ");
        organizationView.setInn("564238995");
        organizationView.setKpp("997350041");
        organizationView.setAddress("г.Краснодар, ул.Красная, 65/2");
        organizationView.setPhone("89615467885");
        organizationView.setActive(false);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/update", HttpMethod.POST, httpEntity, String.class);
        String expected = "{\"error\":Указанный id: 18 не найден или не заполнены обязательные поля, обновление не будет произведено!}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testAddWhenSuccess() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("СервисЮг");
        organizationView.setFullName("СервисЮГ_ОПТ");
        organizationView.setInn("154854896");
        organizationView.setKpp("4585115568");
        organizationView.setAddress("г.Краснодар, ул. Уральская 79");
        organizationView.setPhone("8961564859");
        organizationView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.exchange(PATTERN_URL + "/add", HttpMethod.POST, httpEntity, String.class);
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));

    }

    @Test
    public void testAddWhenError() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("СервисЮг");
        organizationView.setFullName("СервисЮГ_ОПТ");
        organizationView.setAddress("г.Краснодар, ул. Уральская 79");
        organizationView.setPhone("8961564859");
        organizationView.setActive(false);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.exchange(PATTERN_URL + "/add", HttpMethod.POST, httpEntity, String.class);
        String expected = "{\"error\":Обязательные параметры указаны не полностью, запись не будет создана в БД!}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }
}