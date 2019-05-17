package ru.bellintegrator.practice.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.office.OfficeView;
import ru.bellintegrator.practice.view.user.UserView;

/**
 * Фабрика для создания MapperFactory.
 * При необходимости можно добавить кастомные мапперы
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        mapperFactory.classMap(User.class, UserView.class)
                .field("office.id", "officeId")
                .field("country.code", "citizenshipCode")
                .field("country.name", "citizenshipName")
                .field("doc.name", "docName")
                .field("doc.number", "docNumber")
                .field("doc.date", "docDate")
                .byDefault()
                .register();
        mapperFactory.classMap(Office.class, OfficeView.class)
                .field("organization.id", "orgId")
                .byDefault()
                .register();
        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {

        return true;

    }
}