package ru.bellintegrator.practice.view.user;


import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;

/**
 * класс сотрудники представление
 * данные из этого view будут сериализованы в json
 */

public class UserView {

    private Integer id;
    private Office office;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private String phone;
    private Country country;
    private Boolean isIdentified;

    public UserView() {
    }

    public UserView(User user) {
        id = user.getId();
        office = user.getOffice();
        firstName = user.getFirstName();
        secondName = user.getSecondName();
        middleName = user.getMiddleName();
        position = user.getPosition();
        phone = user.getPhone();
        country = user.getCountry();
        isIdentified = user.getIdentified();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    @Override
    public String toString() {
        return "{id: " + id + "; FirstName: " + firstName + "; SecondName: " + secondName + "; middleName: " + middleName +
                "; position: " + position + "; phone " + phone + "; citizenshipId: " + country
                + "; isIdentified " + isIdentified + "}";
    }


}


