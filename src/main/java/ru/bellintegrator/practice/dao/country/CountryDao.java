package ru.bellintegrator.practice.dao.country;

import org.springframework.stereotype.Component;
import ru.bellintegrator.practice.model.Country;

@Component
public interface CountryDao {
    Country getById(Integer id);

}