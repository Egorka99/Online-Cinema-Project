package com.epam.library.dao.interfaces;

import com.epam.library.model.Book;
import com.epam.library.model.Entity;

import java.util.List;

public interface IBaseAccessService<T extends Entity> {
    T getEntity(int id);
    void addNewEntity(T entity);
    void deleteEntity(int id);
    int getMaxId();
    List<T> getAllEntities();
}
