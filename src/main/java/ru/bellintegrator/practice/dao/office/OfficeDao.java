package ru.bellintegrator.practice.dao.office;

import org.springframework.stereotype.Component;
import ru.bellintegrator.practice.model.Office;

import java.util.List;

@Component
public interface OfficeDao {
    public List<Office> getList(Office office);

    public Office getById(Integer id);

    public void update(Office office);

    public void add(Office office);

}