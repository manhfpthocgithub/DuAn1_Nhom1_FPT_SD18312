/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ThuongHieu;
import Utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class ThuongHieuDAO extends DuAn1DAO<ThuongHieu, Integer> {

    String INSERT_SQL = "INSERT INTO tblThuongHieu (TenThuongHieu,AnhThuongHieu,NgayTao,GhiChuTH,TrangThai) VALUES (?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE tblThuongHieu SET TenThuongHieu=?,AnhThuongHieu=?,NgayTao=?,GhiChuTH=? , TrangThai = ? WHERE MaThuongHieu=?";
    String SELECT_ALL_SQL = "SELECT * FROM tblThuongHieu";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblThuongHieu WHERE MaThuongHieu =?";

    @Override
    public int insert(ThuongHieu entity) {
        return JdbcHelper.executeUpdate(INSERT_SQL, entity.getTenThuongHieu(), entity.getAnhThuongHieu(), entity.getNgayTao(), entity.getGhiChuTH(), entity.isTrangThai());
    }

    @Override
    public int update(ThuongHieu entity) {
        return JdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenThuongHieu(), entity.getAnhThuongHieu(), entity.getNgayTao(), entity.getGhiChuTH(), entity.isTrangThai(), entity.getMaThuongHieu());
    }

    @Override
    public ThuongHieu selectById(Integer id) {
        List<ThuongHieu> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ThuongHieu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ThuongHieu> selectBySql(String sql, Object... args) {
        List<ThuongHieu> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setMaThuongHieu(rs.getInt(1));
                th.setTenThuongHieu(rs.getString(2));
                th.setAnhThuongHieu(rs.getString(3));
                th.setNgayTao(rs.getString(4));
                th.setGhiChuTH(rs.getString(5));
                th.setTrangThai(rs.getBoolean(6));
                list.add(th);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean KiemTraThuongHieu(String TenMau) {
        List<ThuongHieu> list = new ArrayList<>();
        String sql = "SELECT * FROM tblThuongHieu WHERE TenThuongHieu=?";
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

    public List<ThuongHieu> selectAllHD() {
        String SELECT_ALL_SQL_HD = "SELECT * FROM tblThuongHieu WHERE TrangThai = 1";

        return this.selectBySql(SELECT_ALL_SQL_HD);
    }
}
