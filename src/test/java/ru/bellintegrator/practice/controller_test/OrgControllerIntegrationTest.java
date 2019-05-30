package ru.bellintegrator.practice.controller_test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.view.organization.OrganizationView;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Итеграционный тест для контроллера Organization, для тестирования используется TestRestTemplate класс
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrgControllerIntegrationTest {
    private static final String PATTERN_URL = "/api/organization";
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
        header.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(organizationView, header); // собираем HTTP запрос
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(PATTERN_URL + "/list", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue(responseEntity.getBody().contains("Краснодар, ул. Солнечная, 15/5"));
    }

    @Test
    public void testGetListWhenError() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Магнит");
        organizationView.setActive(false);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(organizationView, header);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(PATTERN_URL + "/list", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void testGetOrgByIdWhenSuccess() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/1", HttpMethod.GET, null,
                        String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue(responseEntity.getBody().contains("997350001"));
    }

    @Test
    public void testGetOrgByIdWhenError() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/158", HttpMethod.GET, null,
                        String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
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
                testRestTemplate.postForEntity(PATTERN_URL + "/update", httpEntity, String.class);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertNotNull(responseEntity);
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
                testRestTemplate.postForEntity(PATTERN_URL + "/update", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
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
                = testRestTemplate.postForEntity(PATTERN_URL + "/add", httpEntity, String.class);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertNotNull(responseEntity);
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
                = testRestTemplate.postForEntity(PATTERN_URL + "/add", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));

    }
}
