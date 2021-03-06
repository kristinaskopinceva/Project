package ru.bellintegrator.practice.view.user;

import java.util.Date;

/**
 * класс сотрудники представление
 * данные из этого view будут сериализованы в json
 */
public class UserView {
    private Integer id;
    private Integer officeId;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private String phone;
    private Integer docCode;
    private String docName;
    private String docNumber;
    private Date docDate;
    private String citizenshipName;
    private Integer citizenshipCode;
    private Boolean isIdentified;

    public UserView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer office) {
        this.officeId = office;
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

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
    @Override
    public String toString() {
        return "{id: " + id + "; FirstName: " + firstName+ "; SecondName: " + secondName + "; MiddleName: " + middleName +
                "; position: " + position + "; phone " + phone + "; docName " + docName + "; docNumber :" + docNumber + "; docDate :" + docDate + "; citizenshipCode "
                + citizenshipCode + "; citizenshipName: " + citizenshipName + "; isIdentified " + isIdentified + "}";
    }


}


