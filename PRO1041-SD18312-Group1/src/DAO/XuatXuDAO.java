/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ThuongHieuChiTiet;
import Entity.XuatXu;
import Utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class XuatXuDAO extends DuAn1DAO<XuatXu, Integer> {

    String INSERT_SQL = "INSERT INTO tblXuatXu (XuatXu,TrangThai) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE tblXuatXu SET XuatXu=? , TrangThai = ? WHERE MaXuatXu=?";
    String SELECT_ALL_SQL = "SELECT * FROM tblXuatXu ";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblXuatXu WHERE MaXuatXu =?";

    @Override
    public int insert(XuatXu entity) {
        return JdbcHelper.executeUpdate(INSERT_SQL, entity.getXuatXu(), entity.isTrangThai());
    }

    @Override
    public int update(XuatXu entity) {
        return JdbcHelper.executeUpdate(UPDATE_SQL, entity.getXuatXu(), entity.isTrangThai(), entity.getMaXuatXu());
    }

    @Override
    public XuatXu selectById(Integer id) {
        List<XuatXu> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<XuatXu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<XuatXu> selectBySql(String sql, Object... args) {
        List<XuatXu> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                XuatXu xx = new XuatXu();
                xx.setMaXuatXu(rs.getInt(1));
                xx.setXuatXu(rs.getString(2));
                xx.setTrangThai(rs.getBoolean(3));
                list.add(xx);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean KiemTraTenXuatXu(String tenXuatXu) {
        List<XuatXu> list = new ArrayList<>();
        String sql = "SELECT * FROM tblMauSacSP WHERE TenMauSac=?";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, tenXuatXu);
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

  

}
