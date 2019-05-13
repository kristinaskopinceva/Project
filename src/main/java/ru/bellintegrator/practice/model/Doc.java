package ru.bellintegrator.practice.model;
import javax.persistence.*;
import java.util.Date;

@Entity (name = "docs")

public class Doc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    @Column(name = "doc_code",nullable = false)
    private Integer docCode;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "doc_number",nullable = false,length = 30)
    private String docNumber;
    @Column(name = "doc_date")
    private Date docDate;


    public Doc(){ }

    public Doc(Integer docCode,User userId, String docNumber,Date docDate){
        this.docCode = docCode;
        this. user = userId;
        this.docNumber = docNumber;
        this.docDate = docDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}