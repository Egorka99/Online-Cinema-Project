package com.epam.library.dao;

import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Entity;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class BaseAccessImpl<T extends Entity> implements IBaseAccessService<T> {

    protected LibraryDB DBconnection = LibraryDB.getInstance();
    protected static final Logger LOG = Logger.getLogger("DataAccessLogger");

    @Override
    public abstract T getEntity(int id);

    @Override
    public abstract void addNewEntity(T entity);

    @Override
    public abstract void deleteEntity(int id);

    @Override
    public abstract int getMaxId();

    @Override
    public abstract List<T> getAllEntities();
}
