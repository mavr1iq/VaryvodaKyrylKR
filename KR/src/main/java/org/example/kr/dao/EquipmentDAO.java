package org.example.kr.dao;

import org.example.kr.model.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDAO {
    Object get(int id);
    Object get(String name);
    List<Equipment> getAll();
    Boolean set(String name, String description, String type, Float price);
    Boolean updateEquipment(String keyName, String name, String description, String type, Float price);
    Boolean updateEquipment(String keyName, String name, String type, Float price);
    Boolean updateEquipment(String keyName, String type);
    Boolean updateEquipment(String keyName, Float price);
    Boolean delete(String name);
}