package ru.bellintegrator.practice.service;

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
import ru.bellintegrator.practice.controller.advice.response_wrapper.ResponseDataTeg;
import ru.bellintegrator.practice.view.organization.OrganizationView;

/**
 * Итеграционный тест для контроллера Organization, используется TestRestTemplate
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrgControllerIntegrationTest {
    final String patternURL = "http://localhost:8080/api/organization";
    private HttpHeaders header;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetListWhenSuccess() {
        String url = patternURL + "/list";
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Магнит");
        organizationView.setInn("2310031475");
        organizationView.setActive(true);
        header = new HttpHeaders(); // создаем новой хедер
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(organizationView, header); // собираем HTTP запрос
        ResponseEntity<ResponseDataTeg<OrganizationView>> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<ResponseDataTeg<OrganizationView>>() {
                });
        ResponseDataTeg<OrganizationView> data = responseEntity.getBody();//формируем ответ
        OrganizationView responseBody = data.getData();
        Assert.assertNotNull(responseBody);
        int id = responseBody.getId();
        Assert.assertEquals(1, id);
    }

    @Test
    public void testGetListWhenError() {
        String url = patternURL + "/list";
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Магнит");
        organizationView.setActive(false);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity,
                String.class);
        String result = responseEntity.getBody();
        String expected = "{\"error\":\\Не все обязательные параменты укзааны, список организаций не сформирован!\\}";
        Assert.assertEquals(expected, result);
    }
    @Test
    public void testGetOrgByIdWhenSuccess() {
        String url = patternURL + "/1";
        ResponseEntity<ResponseDataTeg<OrganizationView>> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseDataTeg<OrganizationView>>() {
                        });
        ResponseDataTeg<OrganizationView> data = responseEntity.getBody();
        OrganizationView responseBody = data.getData();
        Assert.assertNotNull(responseBody);
        Assert.assertEquals("ЗАО \"Тандер\"", responseBody.getFullName());
    }
    @Test
    public void testGetOrgByIdWhenError() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(patternURL + "/158", HttpMethod.GET, null,
                        new ParameterizedTypeReference<String>() {
                        });
        String responseBody = responseEntity.getBody();
        String expected = "{\"error\":\"Организация с id 158 не найдена\"}";
        Assert.assertEquals(expected, responseBody);

    }
    @Test
    public void testUpdateWhenSuccess() {

    }
}