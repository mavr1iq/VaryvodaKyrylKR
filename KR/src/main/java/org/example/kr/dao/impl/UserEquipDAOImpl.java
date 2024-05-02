package org.example.kr.dao.impl;

import org.example.kr.dao.*;
import org.example.kr.model.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserEquipDAOImpl implements UserEquipDAO {
    DAOManager mngr;
    UserDAO userDAO;
    EquipmentDAO equipmentDAO;
    Connection con;
    Statement stmt;
    public UserEquipDAOImpl(DAOManager mngr) {
        this.mngr = mngr;
        this.stmt = mngr.getStatement();
        this.con = mngr.getConnection();
        userDAO = new UserDAOImpl(mngr);
        equipmentDAO = new EquipmentDAOImpl(mngr);
    }
    @Override
    public List<Integer> getWishList(int id) {
        String sql = "select * from loginequip where login_id = ?";
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String equipId = rs.getString("equip_id");
                list.add(Integer.parseInt(equipId));
            }
            return list;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean addToWishList(int equipID, int loginID) {
        String sql = "INSERT INTO loginequip(login_id, equip_id) VALUES(?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, loginID);
            pstmt.setInt(2, equipID);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean removeFromWishList(int equipId, int userId) {
        String sql = "DELETE FROM loginequip WHERE login_id = ? AND equip_id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, equipId);
            return pstmt.executeUpdate() > 0;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
