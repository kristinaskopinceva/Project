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
import java.util.HashSet;
import java.util.Set;

@Entity (name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(name = "full_name",nullable = false,length = 100)
    private String fullName;
    @Column(nullable = false,length = 9)
    private String inn;
    @Column(nullable = false,length = 12)
    private String kpp;
    @Column(nullable = false,length = 150)
    private String address;
    @Column(nullable = false,length = 20)
    private String phone;
    @Column(name = "is_active",nullable = false)
    private Boolean isActive;
    public Organization() {}
    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;

    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @OneToMany( mappedBy = "organization", cascade = {CascadeType.ALL})
    private Set<Office> office;

    public Set<Office> getOffice() {
        return office;
    }
    public void setOffice(Set<Office> office) {
        this.office = office;
    }
}