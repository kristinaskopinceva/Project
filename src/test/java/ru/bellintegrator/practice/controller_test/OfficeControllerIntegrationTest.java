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
import ru.bellintegrator.practice.view.office.OfficeView;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Итеграционный тест для контроллера Office, для тестирования используется TestRestTemplate класс
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfficeControllerIntegrationTest {
    private static final String PATTERN_URL = "/api/office";
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
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(PATTERN_URL + "/list", httpEntity,
                String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue(responseEntity.getBody().contains("Краснодар"));
    }

    @Test
    public void testGetListWhenError() {
        OfficeView officeView = new OfficeView();
        officeView.setName("Офис Магнит ГК");
        officeView.setPhone("+7(861)-456-00-00");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OfficeView> httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(PATTERN_URL + "/list", httpEntity,
                String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void testGetOrgByIdWhenSuccess() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/3", HttpMethod.GET, null,
                        String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue(responseEntity.getBody().contains("Офис Магнит Ростов"));
    }

    @Test
    public void testGetOrgByIdWhenError() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/158", HttpMethod.GET, null, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @Test
    public void testUpdateWhenSuccess() {
        OfficeView officeView = new OfficeView();
        officeView.setId(1);
        officeView.setAddress("г.Краснодар, ул.Красная, 65/9");
        officeView.setActive(true);
        officeView.setName("Ростелеком офис");
        officeView.setOrgId(2);
        officeView.setPhone("89615400005");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(PATTERN_URL + "/update", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
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
                testRestTemplate.postForEntity(PATTERN_URL + "/update", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @Test
    public void testAddWhenSuccess() {
        OfficeView officeView = new OfficeView();
        officeView.setName("Ростелеком_офис");
        officeView.setAddress("г.Краснодар");
        officeView.setPhone("89061112364");
        officeView.setActive(true);
        officeView.setOrgId(1);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.postForEntity(PATTERN_URL + "/add", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        System.out.println(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));
    }

    @Test
    public void testAddWhenError() {
        OfficeView officeView = new OfficeView();
        officeView.setAddress("г.Краснодар, ул. Уральская 221");
        officeView.setPhone("8961564000");
        officeView.setActive(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(officeView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.postForEntity(PATTERN_URL + "/add", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}