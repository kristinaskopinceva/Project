package ru.bellintegrator.practice.view.organization;

import ru.bellintegrator.practice.model.Organization;

/**
 * класс организации фильтр in
 * десериализация из json в java
 */
public class OrgFilterView {
    private String name;
    private String inn;
    private Boolean isActive;

    public OrgFilterView() {
    }

    public OrgFilterView(Organization organization) {
        name = organization.getName();
        inn = organization.getInn();
        this.isActive = organization.getActive();
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






