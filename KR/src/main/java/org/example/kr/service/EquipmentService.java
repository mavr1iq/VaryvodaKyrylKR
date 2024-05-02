package org.example.kr.service;

import org.example.kr.dao.DAOFactory;
import org.example.kr.model.Equipment;

import java.sql.SQLException;
import java.util.List;

public class EquipmentService {
    DAOFactory daoFactory;
    public EquipmentService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Equipment getByName(String name) throws SQLException {
        return (Equipment) daoFactory.getEquipmentDAO().get(name);
    }

    public Equipment getById(int id) throws SQLException {
        return (Equipment) daoFactory.getEquipmentDAO().get(id);
    }

    public List<Equipment> getAll() throws SQLException {
        return daoFactory.getEquipmentDAO().getAll();
    }

    public boolean addEquipment(String name, String description, String type, float price) throws SQLException {
        return daoFactory.getEquipmentDAO().set(name, description, type, price);
    }

    public boolean updateEquipment(String keyName, String name, String description, String type, Float price) throws SQLException {
        return daoFactory.getEquipmentDAO().updateEquipment(keyName, name, description, type, price);
    }

    public boolean updateEquipment(String keyName, String name, String type, Float price) throws SQLException {
        return daoFactory.getEquipmentDAO().updateEquipment(keyName, name, type, price);
    }
    public boolean updateEquipment(String keyName, Float price) throws SQLException {
        return daoFactory.getEquipmentDAO().updateEquipment(keyName, price);
    }
    public boolean updateEquipment(String keyName, String type) throws SQLException {
        return daoFactory.getEquipmentDAO().updateEquipment(keyName, type);
    }
    public boolean deleteEquipment(String keyName) throws SQLException {
        return daoFactory.getEquipmentDAO().delete(keyName);
    }
}
