package DAO;

import Entity.PhieuDoi;
import utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuDoiDAO extends DuAn1DAO1<PhieuDoi, Integer> {

    final String INSERT_SQL = "INSERT INTO tblPhieuDoiHang (MaKhachHang, MaHoaDon, NgayDoiHang, TienDaThanhToan, LyDoDoiHang) VALUES(?, ?, ?, ?,?)";
    final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?,HocPhi = ?,ThoiLuong = ?,Hinh = ?,MoTa = ? WHERE MaCD = ?";
    final String DELETE_SQL = "DELETE FROM tblPhieuDoiHang WHERE MaPhieuDoiHang = ?";
    final String SELECTALL_SQL = "SELECT * FROM tblPhieuDoiHang";
    final String SELECTBYID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD = ?";
    final String UPDATE_TT = " UPDATE tblHoaDon SET TrangThaiHoaDon = 0 WHERE MaHoaDon = ?";

//    @Override
//    public void Insert(PhieuDoi entity) {
//        JdbcHelper.Update(INSERT_SQL, entity.getMaKH(), entity.getMaHD(), entity.getNgayDoiHang(), entity.getTienThanhToan(), entity.getLyDoDoiHang());
//    }
//
//    @Override
//    public void Update(PhieuDoi entity) {
//        //JdbcHelper.Update(UPDATE_SQL,entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(),entity.getMaCD());
//    }
//
//    public void UpdateTT(Integer id) {
//        JdbcHelper.Update(UPDATE_TT, id);
//    }
//
//    @Override
//    public void Delete(Integer id) {
//        JdbcHelper.Update(DELETE_SQL, id);
//    }
//
//    @Override
//    public List<PhieuDoi> selectAll() {
//        return selectBySQL(SELECTALL_SQL);
//    }
//
//    @Override
//    public PhieuDoi selectByID(Integer id) {
//        List<PhieuDoi> list = selectBySQL(SELECTBYID_SQL, id);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }
//
//    @Override
//    public List<PhieuDoi> selectBySQL(String sql, Object... args) {
//        List<PhieuDoi> listCD = new ArrayList<>();
//        try {
//            ResultSet rs = JdbcHelper.Query(sql, args);
//            while (rs.next()) {
//                PhieuDoi entity = new PhieuDoi();
//                entity.setMaPD(rs.getInt("MaPhieuDoiHang"));
//                entity.setMaKH(rs.getInt("MaKhachHang"));
//                entity.setMaHD(rs.getInt("MaHoaDon"));
//                entity.setNgayDoiHang(rs.getString("NgayDoiHang"));
//                entity.setTienThanhToan(rs.getFloat("TienDaThanhToan"));
//                entity.setLyDoDoiHang(rs.getString("LyDoDoiHang"));
//                listCD.add(entity);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//        return listCD;
//    }
    @Override
    public void insert(PhieuDoi entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaKH(), entity.getMaHD(), entity.getNgayDoiHang(), entity.getTienThanhToan(), entity.getLyDoDoiHang());
    }

    @Override
    public void update(PhieuDoi entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public void UpdateTT(Integer id) {
        JDBCHelper.executeUpdate(UPDATE_TT, id);
    }
    @Override
    public PhieuDoi selectById(Integer key) {
        List<PhieuDoi> list = selectBySql(SELECTBYID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<PhieuDoi> selectBySql(String sql, Object... args) {
        List<PhieuDoi> listCD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                PhieuDoi entity = new PhieuDoi();
                entity.setMaPD(rs.getInt("MaPhieuDoiHang"));
                entity.setMaKH(rs.getInt("MaKhachHang"));
                entity.setMaHD(rs.getInt("MaHoaDon"));
                entity.setNgayDoiHang(rs.getString("NgayDoiHang"));
                entity.setTienThanhToan(rs.getFloat("TienDaThanhToan"));
                entity.setLyDoDoiHang(rs.getString("LyDoDoiHang"));
                listCD.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return listCD;
    }

    public List<PhieuDoi> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM tblPhieuDoiHang WHERE MaPhieuDoiHang Like ? ";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    @Override
    public List<PhieuDoi> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }
    
    public void Delete(Integer id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);
    }
}
