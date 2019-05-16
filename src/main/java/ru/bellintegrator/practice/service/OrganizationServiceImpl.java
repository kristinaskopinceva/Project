package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.exception.MyException;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.organization.OrganizationView;

import javax.transaction.Transactional;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OrganizationView> getList(OrganizationView organizationView) {
        Organization organization = new Organization(organizationView.getName(), organizationView.getInn(),
                organizationView.getActive());
        List<Organization> list = organizationDao.getList(organization);
        if (!list.isEmpty()) {
            return mapperFacade.mapAsList(list, OrganizationView.class);
        } else {
            throw new MyException("Список организаций по указанным параметрам не сформирован!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationView getById(Integer id) {
        Organization organization = organizationDao.getById(id);
        if (organizationDao.getById(id) != null) {
            return mapperFacade.map(organization, OrganizationView.class);
        } else {
            throw new MyException("Организация с id: " + id + "не найдена в БД!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView organizationView) {
        if (organizationDao.getById(organizationView.getId()) != null) {
            organizationDao.update(mapperFacade.map(organizationView, Organization.class));
        } else {
            throw new MyException("Указанный id: " + organizationView.getId() + " не найден в БД, обновление не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationView organizationView) {
        if (organizationView != null) {
            organizationDao.add(mapperFacade.map(organizationView, Organization.class));
        } else {
            throw new MyException("Нет инфорамации о новой организации, запись не создана!");
        }
    }
}