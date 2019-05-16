package ru.bellintegrator.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

@Entity(name = "doc")

public class Doc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_code_type")
    private DocType docType;
    @Column(name = "number", nullable = false, length = 30)
    private String number;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;


    public Doc() {
    }

    public Doc(Integer docCode, User userId, String docNumber, Date docDate) {
        this.id = docCode;
        this.user = userId;
        this.number = docNumber;
        this.date = docDate;
    }

    public Integer getId() {
        return id;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDocNumber() {
        return number;
    }

    public void setDocNumber(String docNumber) {
        this.number = docNumber;
    }

    public Date getDocDate() {
        return date;
    }

    public void setDocDate(Date docDate) {
        this.date = docDate;
    }
}