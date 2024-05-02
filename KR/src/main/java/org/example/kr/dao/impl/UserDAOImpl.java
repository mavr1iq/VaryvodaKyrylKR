package org.example.kr.dao.impl;

import org.example.kr.dao.DAOFactory;
import org.example.kr.dao.DAOManager;
import org.example.kr.dao.UserDAO;
import org.example.kr.model.Equipment;
import org.example.kr.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    DAOManager mngr;
    Connection con;
    Statement stmt;
    public UserDAOImpl(DAOManager mngr) {
        this.mngr = mngr;
        this.stmt = mngr.getStatement();
        this.con = mngr.getConnection();
    }

    @Override
    public User get(String key) {
        String sql = "select * from \"loginData\" where \"uName\" = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            if (rs == null) {
                return null;
            }else if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("uName");
                String password = rs.getString("uPass");
                Boolean admin = rs.getBoolean("admin");
                return new User(id, name, password, admin);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public Boolean set(String name, String pass) {
        String sql = "insert into \"loginData\" (\"uName\", \"uPass\") values (?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pass);
            int count = pstmt.executeUpdate();
            return count > 0;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
