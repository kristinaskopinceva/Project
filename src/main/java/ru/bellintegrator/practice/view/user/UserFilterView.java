package ru.bellintegrator.practice.view.user;

import ru.bellintegrator.practice.model.User;

/**
 * класс сотрудники представление фильтр in
 * десериализация из json в java
 */
public class UserFilterView {
    private Integer id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private Integer citizenshipCode;


    public UserFilterView() {
    }

    public UserFilterView(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        secondName = user.getSecondName();
        middleName = user.getMiddleName();
        position = user.getPosition();
        citizenshipCode = user.getCountry().getCode();

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
