package ru.bellintegrator.practice.model;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version = 0;
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
      @JoinColumn(name = "doc_code")
        private Docs docs;
    @Column(name = "doc_number", length = 30)
       private String docsNumber;
    @Column(name = "doc_date")
    private Date docDate;
    @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "citizenship_code")
        private Countries countries;
    @Column(name = "is_identified",nullable = false)
    private Boolean isIdentified;
    public User(){ }
    public User(Office office1, String firstName, String secondName, String middleName, String position, String phone, Docs docs1,
                Countries countries1, Boolean isIdentified1) {
        office=office1;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        docs = docs1;
        countries = countries1;
        isIdentified = isIdentified1;}

        public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs1) {
        docs = docs1;
    }

    public String getDocsNumber() {
        return docsNumber;
    }

    public void setDocsNumber(String docsNumber) {
        this.docsNumber = docsNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries1) {
        countries = countries1;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}