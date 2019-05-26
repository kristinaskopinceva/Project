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
import ru.bellintegrator.practice.view.user.UserView;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Итеграционный тест для контроллера User, для тестирования используется TestRestTemplate класс
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerIntegrationTest {
    final String patternURL = "http://localhost:8080/api/user";
    private HttpHeaders header;
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
        userView.setCitizenshipCode(643);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserView> httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<UserView> responseEntity = testRestTemplate.exchange(patternURL + "/list", HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<UserView>() {
                });
        Assert.assertEquals("1Иванов", responseEntity.getBody() + responseEntity.getBody().getSecondName());
    }

    @Test
    public void testGetListWhenError() {
        UserView userView = new UserView();
        userView.setOfficeId(1);
        userView.setSecondName("Иванов");
        userView.setMiddleName("Иванович");
        userView.setPosition("Оператор");
        userView.setCitizenshipCode(643);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserView> httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(patternURL + "/list", HttpMethod.POST, httpEntity,
                String.class);
        String expected = "{\"error\":Список сотрудников по указанным параметрам не сформирован!}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testGetOrgByIdWhenSuccess() {
        ResponseEntity<UserView> responseEntity =
                testRestTemplate.exchange(patternURL + "/3", HttpMethod.GET, null,
                        new ParameterizedTypeReference<UserView>() {
                        });
        Assert.assertEquals("+7 (548) 958-79-99", responseEntity.getBody().getPhone());
    }

    @Test
    public void testGetOrgByIdWhenError() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(patternURL + "/158", HttpMethod.GET, null,
                        new ParameterizedTypeReference<String>() {
                        });
        String expected = "{\"error\":Сотрудник с id: 158 не найден в БД!}";
        Assert.assertEquals(expected, responseEntity.getBody());
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
        userView.setDocCode("21");
        userView.setDocName("паспорт");
        userView.setDocDate(new GregorianCalendar(1990, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307258963");
        userView.setCitizenshipCode(643);
        userView.setIdentified(true);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(patternURL + "/update", HttpMethod.POST, httpEntity, String.class);
        Assert.assertTrue("success", responseEntity.getBody().contains("success"));
    }

    @Test
    public void testUpdateWhenError() {
        UserView userView = new UserView();
        userView.setId(2);
        userView.setOfficeId(1);
        userView.setFirstName("Александр");
        userView.setSecondName("Пушкин");
        userView.setMiddleName("Сергеевич");
        userView.setPosition("Специалист ОЗ");
        userView.setPhone("896145897885");
        userView.setDocCode("21");
        userView.setDocName("паспорт");
        userView.setDocDate(new GregorianCalendar(1990, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307258963");
        userView.setCitizenshipCode(643);
        userView.setIdentified(true);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(patternURL + "/update", HttpMethod.POST, httpEntity, String.class);
        String expected = "{\"error\":Указанный id не найден или не заполнены обязательные поля, обновление не будет произведено!}";
        Assert.assertEquals(expected, responseEntity.getBody());
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
        userView.setDocCode("21");
        userView.setDocName("паспорт");
        userView.setDocDate(new GregorianCalendar(1985, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307356895");
        userView.setCitizenshipCode(380);
        userView.setIdentified(true);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.exchange(patternURL + "/add", HttpMethod.POST, httpEntity, String.class);
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
        userView.setDocCode("21");
        userView.setDocName("паспорт");
        userView.setDocDate(new GregorianCalendar(1985, Calendar.JANUARY, 25).getTime());
        userView.setDocNumber("0307356895");
        userView.setCitizenshipCode(380);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(userView, header);
        ResponseEntity<String> responseEntity
                = testRestTemplate.exchange(patternURL + "/add", HttpMethod.POST, httpEntity, String.class);
        String expected = "{\"error\":Обязательные параметры указаны не полностью, запись не будет создана в БД!}";
        Assert.assertEquals(expected, responseEntity.getBody());
    }
}