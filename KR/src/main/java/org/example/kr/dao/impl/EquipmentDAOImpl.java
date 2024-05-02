package org.example.kr.dao.impl;

import org.example.kr.dao.DAOManager;
import org.example.kr.dao.EquipmentDAO;
import org.example.kr.model.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {
    DAOManager mngr;
    Connection con;
    Statement stmt;
    public EquipmentDAOImpl(DAOManager mngr) {
        this.mngr = mngr;
        this.stmt = mngr.getStatement();
        this.con = mngr.getConnection();
    }

    @Override
    public Object get(String key){
        String sql = "select * from \"equipData\" where \"name\" = ? ";

        try {
            PreparedStatement pstmt = this.con.prepareStatement(sql);
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            if (rs == null) {
                return null;
            }
            if (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String type = rs.getString("type");
                float price = rs.getFloat("price");

                return new Equipment(id, key, description, type, price);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Object get(int id) {
        String sql = "select * from \"equipData\" where \"id\" = ? ";

        try {
            PreparedStatement pstmt = this.con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs == null) {
                return null;
            }
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String type = rs.getString("type");
                float price = rs.getFloat("price");

                return new Equipment(id, name, description, type, price);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Equipment> getAll() {
        String sql = "select * from \"equipData\" order by id";
        List<Equipment> list = new ArrayList<Equipment>();

        try {
            PreparedStatement pstmt = this.con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String type = rs.getString("type");
                float price = rs.getFloat("price");
                list.add(new Equipment(id, name, description, type, price));
            }
            return list;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean set(String name, String description, String type, Float price) {
        String sql = "insert into \"equipData\"(\"name\", \"description\", \"type\", \"price\") values (?,?,?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, type);
            pstmt.setFloat(4, price);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean updateEquipment(String keyName, String name, String description, String type, Float price) {
        String sql = "UPDATE \"equipData\" SET name=?, description=?, price=?, type=? WHERE name=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setFloat(3, price);
            pstmt.setString(4, type);
            pstmt.setString(5, keyName);
            return pstmt.executeUpdate() > 0;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean updateEquipment(String keyName, String name, String type, Float price) {
        String sql = "UPDATE \"equipData\" SET name=?, price=?, type=? WHERE name=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setFloat(2, price);
            pstmt.setString(3, type);
            pstmt.setString(4, keyName);
            return pstmt.executeUpdate() > 0;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean updateEquipment(String keyName, String type) {
        String sql = "UPDATE \"equipData\" SET type=? WHERE name=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, type);
            pstmt.setString(2, keyName);
            return pstmt.executeUpdate() > 0;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean updateEquipment(String keyName, Float price) {
        String sql = "UPDATE \"equipData\" SET price=? WHERE name=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setFloat(1, price);
            pstmt.setString(2, keyName);
            return pstmt.executeUpdate() > 0;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(String name) {
        String sql = "delete from \"equipData\" where name=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            return pstmt.executeUpdate() > 0;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
