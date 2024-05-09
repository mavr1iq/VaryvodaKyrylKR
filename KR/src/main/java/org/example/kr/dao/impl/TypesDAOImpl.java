package org.example.kr.dao.impl;

import org.example.kr.dao.DAOManager;
import org.example.kr.dao.TypesDAO;
import org.example.kr.model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypesDAOImpl implements TypesDAO {
    DAOManager mngr;
    Connection con;
    Statement stmt;
    public TypesDAOImpl(DAOManager mngr) {
        this.mngr = mngr;
        this.stmt = mngr.getStatement();
        this.con = mngr.getConnection();
    }

    @Override
    public List<Type> getAll() {
        String sql = "select * from types where type_id IS NULL ORDER BY id";
        List<Type> list = new ArrayList<Type>();

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("type");
                int typeId = rs.getInt("type_id");
                Type t = new Type(id, name, typeId);
                list.add(t);
            }
            return list;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Type> getAllById(int typeId) {
        String sql = "select * from types where type_id = ? ORDER BY id";
        List<Type> list = new ArrayList<Type>();

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, typeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("type");
                Type t = new Type(id, name, typeId);
                list.add(t);
            }
            return list;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Type get(int id) {
        String sql = "select * from types where id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {
                return null;
            }
            if (rs.next()) {
                int typeId = rs.getInt("type_id");
                String name = rs.getString("type");
                return new Type(id, name, typeId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Boolean add(String name, int typeId) {
        String sql = "insert into types(type, type_id) values(?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, typeId);
            return pstmt.executeUpdate() > 0;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean add(String name) {
        String sql = "insert into types(type) values(?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            return pstmt.executeUpdate() > 0;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean update(String key, String name, int typeId) {
        String sql = "UPDATE types SET type=?, type_id=? WHERE type=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, typeId);
            pstmt.setString(3, key);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean update(String key, String name) {
        String sql = "UPDATE types SET type=? WHERE type=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, key);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean update(String key, int typeId) {
        String sql = "UPDATE types SET type_id=? WHERE type=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, typeId);
            pstmt.setString(2, key);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(int id) {
        String sql = "delete from types where id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public int getId(String name) {
        String sql = "select * from types where type = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs == null) {
                return -1;
            }
            if (rs.next()) {
                return rs.getInt("id");
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public String getName(int id) {
        String sql = "select * from types where id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs == null) {
                return null;
            }
            if (rs.next()) {
                return rs.getString("type");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
