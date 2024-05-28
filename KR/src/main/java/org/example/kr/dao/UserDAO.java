package org.example.kr.dao;

import org.example.kr.model.User;

public interface UserDAO {
    User get(String key);
    Boolean set(String name, String pass);
}
