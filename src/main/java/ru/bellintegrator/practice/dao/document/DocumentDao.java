package ru.bellintegrator.practice.dao.document;

import org.springframework.stereotype.Component;
import ru.bellintegrator.practice.model.Doc;

@Component
public interface DocumentDao {
    Doc getById(Integer id);
}