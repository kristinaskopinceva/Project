package ru.bellintegrator.practice.dao.organization;

import org.springframework.stereotype.Component;
import ru.bellintegrator.practice.model.Organization;

import java.util.List;

@Component
public interface OrganizationDao {
    public Organization getById(Integer id);

    public List<Organization> getList(Organization organization);

    public void update(Organization organization);

    public void add(Organization organization);
}