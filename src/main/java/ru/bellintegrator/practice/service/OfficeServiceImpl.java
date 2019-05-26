package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.exception.ServiceException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.office.OfficeView;

import javax.transaction.Transactional;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFacade mapperFacade, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeView> getList(OfficeView officeView) {
        Office office = new Office(organizationDao.getById(officeView.getOrgId()),
                officeView.getName(), officeView.getPhone(),
                officeView.getActive());
        List<Office> officeDaoList = officeDao.getList(office);
        if (!officeDaoList.isEmpty()) {
            return mapperFacade.mapAsList(officeDaoList, OfficeView.class);
        } else {
            throw new ServiceException("Список офисов по указанным параметрам не сформирован!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView getById(Integer id) {
        Office office = officeDao.getById(id);
        if (office != null) {
            return mapperFacade.map(office, OfficeView.class);
        } else {
            throw new ServiceException("Офис с id: " + id + " не найден в БД!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView officeView) {
        if (officeView.getId() != null && officeView.getName() != null && officeView.getAddress() != null &&
                (officeDao.getById(officeView.getId()) != null)) {
            officeDao.update(mapperFacade.map(officeView, Office.class));
        } else {
            throw new ServiceException("Указанный id не найден или не заполнены обязательные поля," +
                    " обновление не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OfficeView officeView) {
        if ((officeView.getOrgId() != null && officeView.getName() != null && officeView.getAddress() != null
                && officeView.getPhone() != null && officeView.getActive() != null)) {
            officeDao.add(mapperFacade.map(officeView, Office.class));
        } else {
            throw new ServiceException("Обязательные параметры указаны не полностью, запись не будет создана в БД!");
        }
    }
}