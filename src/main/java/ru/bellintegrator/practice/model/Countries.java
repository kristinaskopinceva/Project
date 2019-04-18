package ru.bellintegrator.practice.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "countries")

public class Countries{
    @Id
    private Integer code;
    @Version
    private Integer version;
    @Column(nullable = false,length = 50)
    private String name;

    public Countries(){}

    public Countries(Integer code, String name){
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




