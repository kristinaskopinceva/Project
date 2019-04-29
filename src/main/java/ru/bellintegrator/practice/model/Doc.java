package ru.bellintegrator.practice.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity (name = "docs")

public class Doc {

    @Id
    private Integer id;
    @Version
    private Integer version;
    private Integer code;
    @Column(nullable = false,length = 150)
    private String name;

    public Doc(){ }

    public Doc(Integer code, String name){
        this.code = code;
        this. name = name;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }



}