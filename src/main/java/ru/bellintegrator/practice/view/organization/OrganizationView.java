package ru.bellintegrator.practice.view.organization;

public class OrganizationView {
    private Integer id;
    private String name;
    private String fullName;
    private String inn;
    private String kpp;
    private String address;
    private String phone;
    private Boolean isActive;

    public OrganizationView() {
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

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";fullName:" + fullName + ";inn:" + inn + ";KPP:" + kpp +
                ";address" + address + ";phone:" + phone + ";isActive : " + isActive + "}";
    }
}