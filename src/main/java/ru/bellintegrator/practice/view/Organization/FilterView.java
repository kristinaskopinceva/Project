package ru.bellintegrator.practice.view.Organization;

public class FilterView {
    public String name;
    private String inn;
    private Boolean isActive;

    public FilterView() {
    }
    public FilterView(String name, String inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}






