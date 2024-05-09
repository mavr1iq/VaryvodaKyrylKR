package org.example.kr.dao;

import org.example.kr.model.Type;

import java.util.List;

public interface TypesDAO {
    List<Type> getAll();
    List<Type> getAllById(int id);
    Type get(int id);
    Boolean add(String name, int typeId);
    Boolean add(String name);
    Boolean update(String key, String name, int typeId);
    Boolean update(String key, String name);
    Boolean update(String key, int typeId);
    Boolean delete(int id);
    int getId(String name);
    String getName(int id);
}
