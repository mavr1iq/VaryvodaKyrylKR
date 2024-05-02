package org.example.kr.service;

import org.example.kr.dao.DAOFactory;
import org.example.kr.model.Equipment;
import org.example.kr.model.User;

import java.util.List;

public class UserService {
    DAOFactory daoFactory;

    public UserService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public User getByLogin(String login) {
        return daoFactory.getUserDAO().get(login);
    }

    public List<Integer> getWishList(int id) {
        return daoFactory.getUserEquipDAO().getWishList(id);
    }

    public boolean addToWishList(int equipID, int loginID) {
        return daoFactory.getUserEquipDAO().addToWishList(equipID, loginID);
    }

    public boolean removeFromWishList(int equipID, int loginID) {
        return daoFactory.getUserEquipDAO().removeFromWishList(equipID, loginID);
    }

    public boolean checkPass(User user, String pass) {
        return user.getPassword().equals(pass);
    }

    public boolean create(String login, String password) {
        return daoFactory.getUserDAO().set(login, password);
    }
}
