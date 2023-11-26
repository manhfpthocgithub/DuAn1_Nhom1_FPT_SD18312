package DAO;

import Entity.PhieuGiamGia;
import java.util.ArrayList;
import java.util.List;
import Utils.JDBCHelper;
import java.sql.*;

public class PhieuGiamGiaDAO extends DuAn1DAO1<PhieuGiamGia, Integer> {

    final String INSERT_SQL = "INSERT INTO tblPhieuGiamGia(TenPhieuGiamGia,GiaTriPGG,TongTienHangPGG,TrangThaiPGG,NgayTaoPGG,NgayHetHanPGG,GhiChuPGG,MaNhanVien) \n"
            + "VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE tblPhieuGiamGia SET TenPhieuGiamGia = ?, GiaTriPGG = ?,TongTienHangPGG = ?, TrangThaiPGG = ?, \n"
            + "NgayTaoPGG = ?, NgayHetHanPGG = ?, GhiChuPGG = ?, MaNhanVien = ?\n"
            + "WHERE MaPhieuGiamGia = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM tblPhieuGiamGia WHERE TrangThaiPGG = 1";
    final String SELECT_TrangThai_SQL = "SELECT TrangThaiPGG FROM tblPhieuGiamGia";
    final String SELECT_NhanVien_SQL = "SELECT MaNhanVien FROM tblPhieuGiamGia";
    final String SELECT_BY_ID_SQL = "SELECT * FROM tblPhieuGiamGia WHERE MaPhieuGiamGia = ?";
    final String SELECT_XOA_TRANG_THAI = "UPDATE  tblPhieuGiamGia SET TrangThaiPGG = 0 WHERE MaPhieuGiamGia = ?";

    @Override
    public int insert(PhieuGiamGia entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getTenPGG(), entity.getGiaTriPGG(), entity.getTongTienHang(),
                entity.isTrangThaiPGG(), entity.getNgayTao(), entity.getNgayHetHan(), entity.getGhiChu(), entity.getMaNV());
    }

    @Override
    public int update(PhieuGiamGia entity) {
        return JDBCHelper.executeUpdate(UPDATE_SQL, entity.getTenPGG(), entity.getGiaTriPGG(), entity.getTongTienHang(), entity.isTrangThaiPGG(),
                entity.getNgayTao(), entity.getNgayHetHan(), entity.getGhiChu(), entity.getMaNV(), entity.getMaPhieuGiamGia());
    }

    @Override
    public List<PhieuGiamGia> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhieuGiamGia selectById(Integer key) {
        List<PhieuGiamGia> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<PhieuGiamGia> selectBySql(String sql, Object... args) {
        List<PhieuGiamGia> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                PhieuGiamGia entity = new PhieuGiamGia();
                entity.setMaPhieuGiamGia(rs.getInt("MaPhieuGiamGia"));
                entity.setTenPGG(rs.getString("TenPhieuGiamGia"));
                entity.setGiaTriPGG(rs.getInt("GiaTriPGG"));
                entity.setTongTienHang(rs.getDouble("TongTienHangPGG"));
                entity.setTrangThaiPGG(rs.getBoolean("TrangThaiPGG"));
                entity.setNgayTao(rs.getDate("NgayTaoPGG"));
                entity.setNgayHetHan(rs.getDate("NgayHetHanPGG"));
                entity.setGhiChu(rs.getString("GhiChuPGG"));
                entity.setMaNV(rs.getString("MaNhanVien"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PhieuGiamGia> searchByID(Integer id) {
        String sql = "Select * from tblPhieuGiamGia WHERE MaPhieuGiamGia = ?";
        return this.selectBySql(sql, id);
    }

    public List<PhieuGiamGia> searchByName(String name) {
        String sql = "Select * from tblPhieuGiamGia WHERE TenPhieuGiamGia LIKE ?";
        return this.selectBySql(sql, name + "%");
    }

    public void updateStatus() {
        String sql = "UPDATE tblPhieuGiamGia SET TrangThaiPGG = 0 WHERE NgayHetHanPGG < CONVERT(DATE, GETDATE(), 103)";
        JDBCHelper.executeUpdate(sql);
    }

    public List<Integer> selectTrangThai() {
        List<Integer> list = new ArrayList();
        try {
            ResultSet rs = JDBCHelper.executeQuery(SELECT_TrangThai_SQL);
            while (rs.next()) {
                int tt = rs.getInt("TrangThaiPGG");
                list.add(tt);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> selectNhanVien() {
        List<String> list = new ArrayList();
        try {
            ResultSet rs = JDBCHelper.executeQuery(SELECT_NhanVien_SQL);
            while (rs.next()) {
                String maNV = rs.getString("MaNhanVien");
                list.add(maNV);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<PhieuGiamGia> fillTrangThai(Integer PhieuGiamGia) {
        String sql = "SELECT * FROM tblPhieuGiamGia WHERE TrangThaiPGG = ?";
        return selectBySql(sql, PhieuGiamGia);
    }

    public List<PhieuGiamGia> fillNhanVien(String MaNhanVien) {
        String sql = "SELECT * FROM tblPhieuGiamGia WHERE MaNhanVien = ?";
        return selectBySql(sql, MaNhanVien);
    }

    public List<PhieuGiamGia> delete(Integer ma) {
        JDBCHelper.executeUpdate(SELECT_XOA_TRANG_THAI, ma);
        return this.selectBySql(SELECT_ALL_SQL);
    }

}
