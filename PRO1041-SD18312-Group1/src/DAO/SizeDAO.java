/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Size;
import Utils.JdbcHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class SizeDAO extends DuAn1DAO<Size, Integer> {

    String INSERT_SQL = "INSERT INTO tblSize (TenSize,TrangThai) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE tblSize SET TenSiZe=? , TrangThai = ? WHERE MaSize =?";
    String SELECT_ALL_SQL = "SELECT * FROM tblSize ";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblSize WHERE MaSize =?";

    @Override
    public int insert(Size entity) {
        return JdbcHelper.executeUpdate(INSERT_SQL, entity.getTenSize(), entity.isTrangThai());
    }

    @Override
    public int update(Size entity) {
        return JdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenSize(), entity.isTrangThai(), entity.getMaSize());
    }

    @Override
    public Size selectById(Integer id) {
        List<Size> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Size> selectAll() {
        List<Size> list = this.selectBySql(SELECT_ALL_SQL);
        if(list.isEmpty()){
            return null ;
        }
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<Size> selectBySql(String sql, Object... args) {
        List<Size> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Size sz = new Size();
                sz.setMaSize(rs.getInt(1));
                sz.setTenSize(rs.getString(2));
                sz.setTrangThai(rs.getBoolean(3));
                list.add(sz);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean KiemTraTenSize(String tenSize) {
        List<Size> list = new ArrayList<>();
        String sql = "SELECT * FROM tblSize WHERE TenSize=?";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, tenSize);
            while (rs.next()) {
                return false;
            }
            rs.getStatement().getConnection().close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    
    public List<Size> selectAllHD() {
        String SELECT_ALL_SQL_HD = "SELECT * FROM tblSize WHERE TrangThai = 1 ";
        List<Size> list = this.selectBySql(SELECT_ALL_SQL_HD);
        if(list.isEmpty()){
            return null ;
        }
        return this.selectBySql(SELECT_ALL_SQL);
    }

}
