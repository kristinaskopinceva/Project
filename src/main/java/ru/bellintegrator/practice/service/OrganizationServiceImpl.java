package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.exception.DaoException;
import ru.bellintegrator.practice.exception.ServiceException;
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
        if (organizationView.getName() == null || organizationView.getInn() == null || organizationView.getActive() == null) {
            throw new ServiceException("Не все обязательные параменты укзааны, список организаци не сформирован!");
        } else {
            Organization organization = new Organization(organizationView.getName(), organizationView.getInn(),
                    organizationView.getActive());
            List<Organization> list = organizationDao.getList(organization);
            if (!list.isEmpty()) {
                return mapperFacade.mapAsList(list, OrganizationView.class);
            } else {
                throw new ServiceException("Список организаций по указанным параметрам не сформирован!");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationView getById(Integer id) {
        Organization organization = organizationDao.getById(id);
        if (organization != null) {
            return mapperFacade.map(organization, OrganizationView.class);
        } else {
            throw new ServiceException("Организация с id: " + id + " не найдена в БД!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView organizationView) {
        if (organizationView.getName() != null && organizationView.getFullName() != null && organizationView.getInn() != null && organizationView.getKpp() != null &&
                organizationView.getAddress() != null && (organizationDao.getById(organizationView.getId()) != null)) {
            organizationDao.update(mapperFacade.map(organizationView, Organization.class));
        } else {
            throw new ServiceException("Указанный id: " + organizationView.getId() + " не найден или не заполнены обязательные поля, обновление не будет произведено!");
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationView organizationView) {
        if (organizationView.getName() != null && organizationView.getFullName() != null && organizationView.getInn() != null &&
                organizationView.getKpp() != null && organizationView.getAddress() != null) {
            organizationDao.add(mapperFacade.map(organizationView, Organization.class));
        } else {
            throw new DaoException("Обязательные параметры указаны не полностью, запись не будет создана в БД!");
        }
    }
}