package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.dao.doc_type.DocTypeDao;
import ru.bellintegrator.practice.dao.document.DocDao;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.exception.ServiceException;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final MapperFacade mapperFacade;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final DocTypeDao docTypeDao;
    private final DocDao docDao;


    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, MapperFacade mapperFacade, CountryDao countryDao, DocTypeDao docTypeDao, DocDao docDao
    ) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
        this.docDao = docDao;


    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<UserView> getList(UserView userView) {
        if (userView.getOfficeId() == null) {
            throw new ServiceException("Не все обязательные параменты укзааны, список организаци не сформирован!");
        } else {
            User user = new User();
            List<User> employees = userDao.getList(userView);
            return mapperFacade.mapAsList(employees, UserView.class);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserView getById(Integer id) {
        User user = userDao.getById(id);
        if (user != null) {
            return mapperFacade.map(user, UserView.class);
        } else {
            throw new ServiceException("Сотрудник с id: " + id + " не найден в БД!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserView userView) {
        if (userView.getId() != null && userView.getFirstName() != null && userView.getPosition() != null &&
                (userDao.getById(userView.getId())) != null) {
            User user = mapperFacade.map(userView, User.class);
            user.setOffice(officeDao.getById(userView.getOfficeId()));
            Country country = countryDao.getById(userView.getCitizenshipCode());
            user.setCountry(country);
            userDao.update(user);
        } else {
            throw new ServiceException("Указанный id не найден или не заполнены обязательные поля, обновление не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(UserView userView) {
        if (userView.getOfficeId() != null && userView.getFirstName() != null && userView.getPosition() != null && officeDao.getById(userView.getOfficeId()).getId() != null) {
            User user = mapperFacade.map(userView, User.class);
            user.setOffice(officeDao.getById(userView.getOfficeId()));
            userDao.add(user);
        } else {
            throw new ServiceException("Обязательные параметры указаны не полностью или не верно, запись не будет создана в БД!");
        }
    }
}