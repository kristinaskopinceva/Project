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
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Doc> docs;
    private Integer id;
    @Version
    private Integer version;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;
    @Column(name = "first_name", nullable = false,length = 50)
    private String firstName;
    @Column(name = "second_name",nullable = false,length = 50)
    private String secondName;
    @Column(name = "middle_name",length = 50)
    private String middleName;
    @Column(nullable = false,length = 50)
    private String position;
    @Column(nullable = false,length = 50)
    private String phone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code")
    private Country countries;
    @Column(name = "is_identified",nullable = false)
    private Boolean isIdentified;
    public User(){ }
    public User(Office officeId, String firstName, String secondName, String middleName, String position, String phone,
                Country countries1, Boolean isIdentified1) {
        office=officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        countries = countries1;
        isIdentified = isIdentified1;}

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

    public Country getCountries() {
        return countries;
    }

    public void setCountries(Country countries1) {
        countries = countries1;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}