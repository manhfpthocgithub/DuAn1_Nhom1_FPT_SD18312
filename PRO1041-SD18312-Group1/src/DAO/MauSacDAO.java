/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.MauSac;
import Utils.JdbcHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class MauSacDAO extends DuAn1DAO<MauSac, Integer> {

    String INSERT_SQL = "INSERT INTO tblMauSacSP (TenMauSac,TrangThai) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE tblMauSacSP SET TenMauSac=? , TrangThai = ? WHERE MaMauSac=?";
    String SELECT_ALL_SQL = "SELECT * FROM tblMauSacSP";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblMauSacSP WHERE MaMauSac =?";

    @Override
    public int insert(MauSac entity) {
        return JdbcHelper.executeUpdate(INSERT_SQL, entity.getTenMauSac(), entity.isTrangThai());
    }

    @Override
    public int update(MauSac entity) {
        return JdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenMauSac(), entity.isTrangThai(), entity.getMaMau());

    }

    @Override
    public MauSac selectById(Integer id) {
        List<MauSac> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MauSac> selectAll() {
        List<MauSac> list = this.selectBySql(SELECT_ALL_SQL);
        if (list.isEmpty()) {
            return null;
        }
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<MauSac> selectBySql(String sql, Object... args) {
        List<MauSac> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setMaMau(rs.getInt(1));
                ms.setTenMauSac(rs.getString(2));
                ms.setTrangThai(rs.getBoolean(3));
                list.add(ms);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean KiemTraTenMau(String TenMau) {
        List<MauSac> list = new ArrayList<>();
        String sql = "SELECT * FROM tblMauSacSP WHERE TenMauSac=?";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, TenMau);
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

    public List<MauSac> selectAllHD() {
        String SELECT_ALL_SQL_HD = "SELECT * FROM tblMauSacSP WHERE TrangThai = 1";

        List<MauSac> list = this.selectBySql(SELECT_ALL_SQL_HD);
        if (list.isEmpty()) {
            return null;
        }
        return this.selectBySql(SELECT_ALL_SQL);
    }
}
