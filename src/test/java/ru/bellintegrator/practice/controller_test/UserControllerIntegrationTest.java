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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Итеграционный тест для контроллера User, для тестирования используется TestRestTemplate класс
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerIntegrationTest {
    static final String PATTERN_URL = "http://localhost:8080/api/user";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetListWhenSuccess() {
        UserView userView = new UserView();
        userView.setOfficeId(1);
        userView.setFirstName("Иван");
        userView.setSecondName("Иванов");
        userView.setMiddleName("Иванович");
        userView.setPosition("Оператор");
        userView.setDocCode(21);
        userView.setCitizenshipCode(643);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserView> httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(PATTERN_URL + "/list", httpEntity,
                String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue(responseEntity.getBody().contains("+7 (789) 966-07-98"));
    }

    @Test
    public void testGetListWhenError() {
        UserView userView = new UserView();
        //userView.setOfficeId(1);
        userView.setFirstName("Иван");
        userView.setSecondName("Иванов");
        userView.setMiddleName("Иванович");
        userView.setPosition("Оператор");
        userView.setDocCode(21);
        userView.setCitizenshipCode(643);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserView> httpEntity = new HttpEntity<>(userView, header);
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
        Assert.assertTrue(responseEntity.getBody().contains("Рук.сектора"));
    }

    @Test
    public void testGetOrgByIdWhenError() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/158", HttpMethod.GET, null,
                        new ParameterizedTypeReference<String>() {
                        });
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @Test
    public void testUpdateWhenSuccess() {
        UserView userView = new UserView();
        userView.setId(2);
        userView.setOfficeId(1);
        userView.setFirstName("Александр");
        userView.setSecondName("Пушкин");
        userView.setMiddleName("Сергеевич");
        userView.setPosition("Специалист ОЗ");
        userView.setPhone("896145897885");
        userView.setDocName("Паспорт гражданина Российской Федерации");
        userView.setDocDate(new GregorianCalendar(1990, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307258963");
        userView.setCitizenshipCode(1);
        userView.setIdentified(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(PATTERN_URL + "/update", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));
    }

    @Test
    public void testUpdateWhenError() {
        UserView userView = new UserView();
        userView.setOfficeId(1);
        userView.setFirstName("Александр");
        userView.setSecondName("Пушкин");
        userView.setMiddleName("Сергеевич");
        userView.setPosition("Специалист ОЗ");
        userView.setPhone("896145897885");
        userView.setDocName("Паспорт гражданина Российской Федерации");
        userView.setDocDate(new GregorianCalendar(1990, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307258963");
        userView.setCitizenshipCode(643);
        userView.setIdentified(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(PATTERN_URL + "/update", HttpMethod.POST, httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @Test
    public void testAddWhenSuccess() {
        UserView userView = new UserView();
        userView.setOfficeId(2);
        userView.setFirstName("Петр");
        userView.setSecondName("Петров");
        userView.setMiddleName("Петрович");
        userView.setPosition("Начальник отдела");
        userView.setPhone("89053215687");
        userView.setDocCode(21);
        userView.setDocName("паспорт");
        userView.setDocDate(new GregorianCalendar(1985, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307356895");
        userView.setCitizenshipCode(380);
        userView.setIdentified(true);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.postForEntity(PATTERN_URL + "/add", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));
    }

    @Test
    public void testAddWhenError() {
        UserView userView = new UserView();
        userView.setFirstName("Петр");
        userView.setSecondName("Петров");
        userView.setMiddleName("Петрович");
        userView.setPosition("Начальник отдела");
        userView.setPhone("89053215687");
        userView.setDocCode(21);
        userView.setDocName("паспорт");
        userView.setDocDate(new GregorianCalendar(1985, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307356895");
        userView.setCitizenshipCode(643);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.postForEntity(PATTERN_URL + "/add", httpEntity, String.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));

    }
}