package org.example.kr.dao;

import org.example.kr.dao.impl.*;

public class DAOFactory {
    private final UserDAO userDAO;
    private final EquipmentDAO equipmentDAO;
    private final UserEquipDAO userEquipDAO;
    private final TypesDAO typesDAO;

    public DAOFactory() {
        DAOManager mngr = new DAOManager();
        userDAO = new UserDAOImpl(mngr);
        equipmentDAO = new EquipmentDAOImpl(mngr);
        userEquipDAO = new UserEquipDAOImpl(mngr);
        typesDAO = new TypesDAOImpl(mngr);
    }

    public UserDAO getUserDAO() { return userDAO; }
    public EquipmentDAO getEquipmentDAO() { return equipmentDAO; }
    public UserEquipDAO getUserEquipDAO() { return userEquipDAO; }
    public TypesDAO getTypesDAO() { return typesDAO; }
}
