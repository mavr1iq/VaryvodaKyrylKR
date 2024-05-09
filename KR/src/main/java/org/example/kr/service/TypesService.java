package org.example.kr.service;

import org.example.kr.dao.DAOFactory;
import org.example.kr.model.Type;

import java.util.List;


public class TypesService {
    DAOFactory daoFactory;
    public TypesService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Type> getAll() {
        return daoFactory.getTypesDAO().getAll();
    }
    public List<Type> getAllById(int id) {
        return daoFactory.getTypesDAO().getAllById(id);
    }

    public Type get(int id) {
        return daoFactory.getTypesDAO().get(id);
    }

    public Boolean addCategory(String name, int typeId) {
        return daoFactory.getTypesDAO().add(name, typeId);
    }

    public Boolean addCategory(String name) {
        return daoFactory.getTypesDAO().add(name);
    }

    public Boolean updateCategory(String key, String name, int typeId) {
        return daoFactory.getTypesDAO().update(key, name, typeId);
    }

    public Boolean updateCategory(String key, String name) {
        return daoFactory.getTypesDAO().update(key, name);
    }

    public Boolean updateCategory(String key, int typeId) {
        return daoFactory.getTypesDAO().update(key, typeId);
    }

    public Boolean delete(int id) {
        return daoFactory.getTypesDAO().delete(id);
    }

    public int getId(String name) {
        return daoFactory.getTypesDAO().getId(name);
    }

    public String getName(int id) {
        return daoFactory.getTypesDAO().getName(id);
    }
}
