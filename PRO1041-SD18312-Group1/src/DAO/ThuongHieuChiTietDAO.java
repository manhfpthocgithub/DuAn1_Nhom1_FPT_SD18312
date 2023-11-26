/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ThuongHieuChiTiet;
import Utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class ThuongHieuChiTietDAO extends DuAn1DAO<ThuongHieuChiTiet, Integer> {

    String INSERT_SQL = "INSERT INTO tblThuongHieuChiTiet\n"
            + "             ( MaThuongHieu, MaXuatXu, EmailChiTiet, WebsiteChiTiet, SoDienThoai, DiaDiemBHTHCT, GhiChuTHCT, TrangThai)\n"
            + "VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE tblThuongHieuChiTiet\n"
            + "SET  MaThuongHieu =?, MaXuatXu =?, EmailChiTiet =?, WebsiteChiTiet =?, SoDienThoai =?, DiaDiemBHTHCT =?, GhiChuTHCT = ? WHERE MaThuongHieuChiTiet =?";
    String SELECT_ALL_SQL = "SELECT * FROM tblThuongHieuChiTiet WHERE TrangThai = 1";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblThuongHieuChiTiet WHERE MaThuongHieuChiTiet =?";

    @Override
    public int insert(ThuongHieuChiTiet entity) {
        return JdbcHelper.executeUpdate(INSERT_SQL, entity.getMaThuongHieu(), entity.getMaXuatXu(), entity.getEmailChiTiet(), entity.getWebsiteChiTiet(), entity.getSoDienThoai(), entity.getDiaDiemBHTHCT(), entity.getGhiChuTHCT(), entity.isTrangThai());
    }

    @Override
    public int update(ThuongHieuChiTiet entity) {
        return JdbcHelper.executeUpdate(UPDATE_SQL, entity.getMaThuongHieu(), entity.getMaXuatXu(), entity.getEmailChiTiet(), entity.getWebsiteChiTiet(), entity.getSoDienThoai(), entity.getDiaDiemBHTHCT(), entity.getGhiChuTHCT(), entity.isTrangThai(), entity.getMaThuongHieuChiTiet());
    }

    @Override
    public ThuongHieuChiTiet selectById(Integer id) {
        List<ThuongHieuChiTiet> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ThuongHieuChiTiet> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ThuongHieuChiTiet> selectBySql(String sql, Object... args) {
        List<ThuongHieuChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThuongHieuChiTiet thct = new ThuongHieuChiTiet();
                thct.setMaThuongHieuChiTiet(rs.getInt(1));
                thct.setMaThuongHieu(rs.getInt(2));
                thct.setMaXuatXu(rs.getInt(3));
                thct.setEmailChiTiet(rs.getString(4));
                thct.setWebsiteChiTiet(rs.getString(5));
                thct.setSoDienThoai(rs.getString(6));
                thct.setDiaDiemBHTHCT(rs.getString(7));
                thct.setGhiChuTHCT(rs.getString(8));
                thct.setTrangThai(rs.getBoolean(9));
                list.add(thct);

            }

            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<ThuongHieuChiTiet> getXuatXuCuaThuongHieu(int maTh) {
//        String sql = "SELECT * FROM tblThuongHieuChiTiet WHERE MaThuongHieu=?";
        String sql = "SELECT * FROM tblThuongHieuChiTiet THCT JOIN tblXuatXu XX ON THCT.MaXuatXu = XX.MaXuatXu WHERE THCT.MaThuongHieu=? AND XX.TrangThai=1";
        List<ThuongHieuChiTiet> list = this.selectBySql(sql, maTh);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public ThuongHieuChiTiet getThuongHieuChiTiet(int maTh, int maXx) {
        String sql = "SELECT * FROM tblThuongHieuChiTiet WHERE MaThuongHieu=? AND MaXuatXu=? AND TrangThai=1";
        List<ThuongHieuChiTiet> list = this.selectBySql(sql, maTh, maXx);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
