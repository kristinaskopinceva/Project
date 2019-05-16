package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.exception.MyException;
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

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, MapperFacade mapperFacade, CountryDao countryDao
    ) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<UserView> getList(UserView userView) {
        User user = new User(officeDao.getById(userView.getId()), userView.getFirstName(), userView.getSecondName(),
                userView.getMiddleName(), userView.getPosition(), countryDao.getById(userView.getCountry()));
        List<User> list = userDao.getList(user);
        if (!list.isEmpty()) {
            return mapperFacade.mapAsList(list, UserView.class);
        } else {
            throw new MyException("Список сотрудников по указанным параметрам не сформирован!");
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
            throw new MyException("Сотрудник с id: " + id + " не найден в БД!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserView userView) {
        if (userDao.getById(userView.getId()) != null) {
            userDao.update(mapperFacade.map(userView, User.class));
        } else {
            throw new MyException("Указанный id: " + userView.getId() + " не найден, обновление не будет произведено!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(UserView userView) {
        if (userView != null) {
            userDao.add(mapperFacade.map(userView, User.class));
        } else {
            throw new MyException("Нет инфорамации о новом сотруднике, запись не создана!");
        }
    }

}