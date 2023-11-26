package DAO;

import Entity.HoaDonChiTiet;
import utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HoaDonChiTietDAO extends DuAn1DAO<HoaDonChiTiet, Integer> {
    final String INSERT_SQL = "INSERT INTO tblPhieuDoiHang (MaKhachHang, MaHoaDon, NgayDoiHang, TienDaThanhToan, LyDoDoiHang) VALUES(?, ?, ?, ?,?)";
    final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?,HocPhi = ?,ThoiLuong = ?,Hinh = ?,MoTa = ? WHERE MaCD = ?";
    final String DELETE_SQL = "DELETE FROM tblPhieuDoiHang WHERE MaPhieuDoiHang = ?";
    final String SELECTALL_SQL = "SELECT * FROM tblHoaDonChiTiet";
    final String SELECTBYID_SQL = "SELECT * FROM tblHoaDonChiTiet WHERE MaHoaDon = ?";
    
    @Override
    public List<HoaDonChiTiet> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    public List<HoaDonChiTiet> selectByMaHD(Integer ma) {
        String sql = "SELECT * FROM tblHoaDonChiTiet WHERE MaHoaDon = ? ";
        return this.selectBySql(sql, ma);
    }
    @Override
    public HoaDonChiTiet selectById(Integer key) {
        List<HoaDonChiTiet> list = selectBySql(SELECTBYID_SQL, key);
//        if(list.isEmpty()){
//            return null;
//        }
        return list.get(0);
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDonChiTiet entity = new HoaDonChiTiet();
                entity.setMaHDCT(rs.getInt("MaHoaDonChiTiet"));
                entity.setMaHD(rs.getInt("MaHoaDon"));
                entity.setMaAoKhoac(rs.getString("MaAoKhoac"));
                entity.setSoLuongHDCT(rs.getInt("SoLuongHDCT"));
                entity.setDonGiaHDCT(rs.getFloat("DonGiaHDCT"));
                entity.setTrangThaiHDCT(rs.getBoolean("TrangThaiHDCT"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
        return list;
    }

    @Override
    public int insert(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

