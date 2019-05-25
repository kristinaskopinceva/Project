package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "second_name", nullable = false, length = 50)
    private String secondName;
    @Column(name = "middle_name", length = 50)
    private String middleName;
    @Column(nullable = false, length = 50)
    private String position;
    @Column(nullable = false, length = 50)
    private String phone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    private Country country;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Doc doc;
    @Column(name = "is_identified", nullable = false)
    private Boolean isIdentified;

    public User() {
    }
    public User(Doc doc){
        this.doc = doc;
    }

    public User(Integer id, Office officeId, String firstName, String lastName, String middleName,
                String position, String phone, Doc doc, Country countryId
            , Boolean isIdentified1) {
        office = officeId;
        this.firstName = firstName;
        this.secondName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.doc = doc;
        country = countryId;
        isIdentified = isIdentified1;
    }

    public User(Office officeId, String firstName, String lastName, String middleName, String position, Country countryId) {
        office = officeId;
        this.firstName = firstName;
        this.secondName = lastName;
        this.middleName = middleName;
        this.position = position;
        country = countryId;
    }

    public Integer getId() {
        return id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office1) {
        office = office1;
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

    public void setCountry(Country countries1) {
        country = countries1;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc docId) {
        doc = docId;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}