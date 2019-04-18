package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity(name = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version = 0;
    @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "org_id")
        private Organization organization;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 150)
    private String address;
    @Column(nullable = false,length = 20)
    private String phone;
    @Column(name = "is_active",nullable = false)
    Boolean isActive;
    public Office(){}

    public Office(Organization org, String name,String address, String phone, Boolean isActive) {
        organization=org;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization org ) {
        organization = org;
    }
}