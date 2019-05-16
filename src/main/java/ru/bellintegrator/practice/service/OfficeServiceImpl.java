package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.exception.MyException;
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
            throw new MyException("Список офисов по указанным параметрам не сформирован!");
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
            throw new MyException("Офис с id: " + id + " не найден в БД!");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView officeView) {
        if (officeDao.getById(officeView.getId())!= null) {
            officeDao.update(mapperFacade.map(officeView, Office.class));
        } else {
            throw new MyException("Указанный id: "+officeView.getId()+" не найден, обновление не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OfficeView officeView) {
        organizationDao.getById(officeView.getOrgId());
        Office office = new Office(organizationDao.getById(officeView.getOrgId()),
                officeView.getName(), officeView.getAddress(), officeView.getPhone(),
                officeView.getActive());
        if (office != null) {
            officeDao.add(office);
        } else {
            throw new MyException("Нет инфорамации о новом офисе, запись не создана!");
        }


    }
}