/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ChiTietSanPham;
import Utils.DBConnect;
import Utils.JdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPhamDAO extends DuAn1DAO<ChiTietSanPham, String> {

    String INSERT_SQL = "INSERT INTO tblChiTietSanPham\n"
            + "             (MaSPCT,MaAoKhoac, GiaAK, SoLuongAK, MaSize, MaMauSac, TenAnh, MoTa,TrangThai)\n"
            + "VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE tblChiTietSanPham\n"
            + "SET       MaAoKhoac =?, GiaAK =?, SoLuongAK =?, MaSize =?, MaMauSac =?, TenAnh =?, MoTa = ? , TrangThai=? WHERE MaSPCT =?";
    String UPDATE_SQL_TT = "UPDATE tblChiTietSanPham SET TrangThai=1 WHERE MaSPCT =?";
    String SELECT_ALL_SQL = "SELECT * FROM tblChiTietSanPham WHERE TrangThai = 1";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblChiTietSanPham WHERE MaSPCT =?";
    String SELECT_BY_ID_SQL_NHD = "SELECT * FROM tblChiTietSanPham WHERE MaSPCT =? AND TrangThai=0";

    @Override
    public int insert(ChiTietSanPham entity) {
        return JdbcHelper.executeUpdate(INSERT_SQL, entity.getMaSPCT(), entity.getMaAoKhoac(), entity.getGiaAK(), entity.getSoLuongAK(), entity.getMaSize(), entity.getMaMauSac(), entity.getTenAnh(), entity.getMoTa(), entity.isTrangThai());
    }

    @Override
    public int update(ChiTietSanPham entity) {
        return JdbcHelper.executeUpdate(UPDATE_SQL, entity.getMaAoKhoac(), entity.getGiaAK(), entity.getSoLuongAK(), entity.getMaSize(), entity.getMaMauSac(), entity.getTenAnh(), entity.getMoTa(), entity.isTrangThai(), entity.getMaSPCT());
    }

    @Override
    public ChiTietSanPham selectById(String id) {
        List<ChiTietSanPham> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChiTietSanPham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChiTietSanPham> selectBySql(String sql, Object... args) {
        List<ChiTietSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ChiTietSanPham chsp = new ChiTietSanPham();
                chsp.setMaSPCT(rs.getString(1));
                chsp.setMaAoKhoac(rs.getString(2));
                chsp.setGiaAK(rs.getDouble(3));
                chsp.setSoLuongAK(rs.getInt(4));
                chsp.setMaSize(rs.getInt(5));
                chsp.setMaMauSac(rs.getInt(6));
                chsp.setTenAnh(rs.getString(7));
                chsp.setMoTa(rs.getString(8));
                chsp.setTrangThai(rs.getBoolean(9));
                list.add(chsp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<ChiTietSanPham> selectByMaSP(String maSp) {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM tblChiTietSanPham WHERE MaAoKhoac=? AND TrangThai=1";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, maSp);
            while (rs.next()) {
                ChiTietSanPham chsp = new ChiTietSanPham();
                chsp.setMaSPCT(rs.getString(1));
                chsp.setMaAoKhoac(rs.getString(2));
                chsp.setGiaAK(rs.getDouble(3));
                chsp.setSoLuongAK(rs.getInt(4));
                chsp.setMaSize(rs.getInt(5));
                chsp.setMaMauSac(rs.getInt(6));
                chsp.setTenAnh(rs.getString(7));
                chsp.setMoTa(rs.getString(8));
                chsp.setTrangThai(rs.getBoolean(9));
                list.add(chsp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<ChiTietSanPham> getTheoThuocTinh(String tenMauSac, String tenSize) {
        String sql = "SELECT * FROM tblChiTietSanPham CTSP JOIN tblMauSacSP MS ON CTSP.MaMauSac = MS.MaMauSac JOIN tblSize Sz ON CTSP.MaSize = Sz.MaSize\n"
                + "WHERE MS.TenMauSac LIKE ? AND SZ.tenSize LIKE ?";
        List<ChiTietSanPham> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenMauSac + "%");
            ps.setString(2, "%" + tenSize + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chsp = new ChiTietSanPham();
                chsp.setMaSPCT(rs.getString(1));
                chsp.setMaAoKhoac(rs.getString(2));
                chsp.setGiaAK(rs.getDouble(3));
                chsp.setSoLuongAK(rs.getInt(4));
                chsp.setMaSize(rs.getInt(5));
                chsp.setMaMauSac(rs.getInt(6));
                chsp.setTenAnh(rs.getString(7));
                chsp.setMoTa(rs.getString(8));
                chsp.setTrangThai(rs.getBoolean(9));
                list.add(chsp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ChiTietSanPham selectById_NHD(String id) {
        List<ChiTietSanPham> list = this.selectBySql(SELECT_BY_ID_SQL_NHD, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public int updateTT(String maSPCT) {
        return JdbcHelper.executeUpdate(UPDATE_SQL_TT, maSPCT);
    }
}
