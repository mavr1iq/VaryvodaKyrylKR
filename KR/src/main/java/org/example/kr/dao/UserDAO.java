package org.example.kr.dao;

import org.example.kr.model.Equipment;
import org.example.kr.model.User;

import java.util.List;

public interface UserDAO {
    User get(String key);
    Boolean set(String name, String pass);
}
