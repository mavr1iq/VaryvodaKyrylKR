package org.example.kr.dao;

import org.example.kr.dao.impl.EquipmentDAOImpl;
import org.example.kr.dao.impl.UserDAOImpl;
import org.example.kr.dao.impl.UserEquipDAOImpl;

public class DAOFactory {
    private final UserDAO userDAO;
    private final EquipmentDAO equipmentDAO;
    private final UserEquipDAO userEquipDAO;
    private final DAOManager mngr;

    public DAOFactory() {
        mngr = new DAOManager();
        userDAO = new UserDAOImpl(mngr);
        equipmentDAO = new EquipmentDAOImpl(mngr);
        userEquipDAO = new UserEquipDAOImpl(mngr);
    }

    public UserDAO getUserDAO() { return userDAO; }
    public EquipmentDAO getEquipmentDAO() { return equipmentDAO; }
    public UserEquipDAO getUserEquipDAO() { return userEquipDAO; }
}
