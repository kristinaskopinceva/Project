package ru.bellintegrator.practice.dao.doc_type;

import ru.bellintegrator.practice.model.DocType;
/**
 * DAO для списка доступных документов для юзеров
 */
public interface DocTypeDao {
    DocType getById(Integer id);
    DocType getByCode(Integer code);
    DocType getByName(String code);
}