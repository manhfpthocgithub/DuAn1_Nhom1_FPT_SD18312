package DAO;

import Entity.HoaDon;
import Entity.PhieuDoi;
import utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

public class HoaDonDAO extends DuAn1DAO1<HoaDon, Integer> {

    final String INSERT_SQL = "INSERT INTO ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) VALUES (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?,HocPhi = ?,ThoiLuong = ?,Hinh = ?,MoTa = ? WHERE MaCD = ?";
    final String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaNH = ?";
    final String SELECTALL_SQL = "SELECT * FROM tblHoaDon WHERE TrangThaiHoaDon = 1";
    final String SELECTBYID_SQL = "SELECT * FROM tblHoaDon WHERE MaHoaDon = ?";
    final String SELECTTT_SQL = "SELECT * FROM tblHoaDon WHERE TrangThaiHoaDon = 1";
    final String UPDATETT_SQL = " UPDATE tblHoaDon SET TrangThaiHoaDon = ? WHERE MaHoaDon = ? ;";

//    @Override
//    public void Insert(HoaDon entity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void Update(HoaDon entity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void Delete(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public List<HoaDon> selectAll() {
//        return selectBySQL(SELECTALL_SQL);
//    }
//
//    public List<HoaDon> selectAll1() {
//        String SQL = "SELECT * FROM tblHoaDon ";
//        return selectBySQL(SQL);
//    }
//
//    public List<HoaDon> selectAll2() {
//        String SQL = "SELECT * FROM tblHoaDon WHERE TrangThaiHoaDon = ?";
//        return selectBySQL(SQL);
//    }
//
//    @Override
//    public HoaDon selectByID(Integer id) {
//        List<HoaDon> list = selectBySQL(SELECTBYID_SQL, id);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }
//
//    @Override
//    public List<HoaDon> selectBySQL(String sql, Object... args) {
//        List<HoaDon> listCD = new ArrayList<>();
//        try {
//            ResultSet rs = JDBCHelper.executeQuery(sql, args);
//            while (rs.next()) {
//                HoaDon entity = new HoaDon();
//                entity.setMaHD(rs.getInt("MaHoaDon"));
//                entity.setMaNV(rs.getString("MaNhanVien"));
//                entity.setMaKH(rs.getInt("MaKhachHang"));
//                entity.setTongTien(rs.getFloat("TongTien"));
//                entity.setThanhToan(rs.getFloat("ThanhToan"));
//                entity.setNgayTaoHoaDon(rs.getString("NgayTaoHoaDon"));
//                entity.setTrangThaiHD(rs.getBoolean("TrangThaiHoaDon"));
//                entity.setGhiChuHD(rs.getString("GhiChuHD"));
//                listCD.add(entity);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//        return listCD;
//    }
    public List<HoaDon> SelectHDTT(boolean tt) {
        return selectBySql(SELECTTT_SQL, tt);
    }

    public List<HoaDon> filterByTT(Integer TT) {
        String sql = "SELECT * FROM tblHoaDon WHERE TrangThaiHoaDon = 1 AND MaHoaDon = ?";
        return selectBySql(sql, TT);
    }

    public void UpdateTT(Integer tt, Integer entity) {
        JDBCHelper.executeUpdate(UPDATETT_SQL, tt, entity);
    }

    public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM tblHoaDon WHERE MaHoaDon Like ? AND TrangThaiHoaDon = 1";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public List<HoaDon> filterTT(boolean TT) {
        String sql = "SELECT * FROM tblHoaDon WHERE TrangThaiHoaDon = ?";
        return selectBySql(sql, TT);
    }

    @Override
    public void insert(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon selectById(Integer key) {
        List<HoaDon> list = selectBySql(SELECTBYID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> listCD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getInt("MaHoaDon"));
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setMaKH(rs.getInt("MaKhachHang"));
                entity.setTongTien(rs.getFloat("TongTien"));
                entity.setThanhToan(rs.getFloat("ThanhToan"));
                entity.setNgayTaoHoaDon(rs.getString("NgayTaoHoaDon"));
                entity.setTrangThaiHD(rs.getBoolean("TrangThaiHoaDon"));
                entity.setGhiChuHD(rs.getString("GhiChuHD"));
                listCD.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return listCD;
    }
}
