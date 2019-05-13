package ru.bellintegrator.practice.view.office;

import ru.bellintegrator.practice.model.Office;

public class OfficeFilterView {
    private Integer id;
    private String name;
    private Boolean isActive;

    public OfficeFilterView() {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
