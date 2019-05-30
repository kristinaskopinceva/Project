package ru.bellintegrator.practice.dao.country;

import ru.bellintegrator.practice.model.Country;

public interface CountryDao {
    Country getById(Integer id);

    public Country getByCode(Integer code);
}
