package DAO;

import Entity.HoaDonChiTiet;
import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HoaDonChiTietDAO extends DuAn1DAO1<HoaDonChiTiet, Integer> {

    final String INSERT_SQL = "INSERT INTO tblHoaDonChiTiet\n"
            + "             (MaHoaDon, MaSPCT, SoLuongHDCT, DonGiaHDCT, ThanhTienHDCT, TrangThaiHDCT)\n"
            + "VALUES (?,?,?,?,?,1)";
    final String UPDATE_SQL = "UPDATE tblHoaDonChiTiet\n"
            + "SET MaHoaDon =?, MaSPCT =?, SoLuongHDCT =?, DonGiaHDCT =?, ThanhTienHDCT =? WHERE MaHoaDonChiTiet =?";
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
                entity.setMaHDCT(rs.getInt(1));
                entity.setMaHD(rs.getInt(2));
                entity.setMaSPCT(rs.getString(3));
                entity.setSoLuongHDCT(rs.getInt(4));
                entity.setDonGiaHDCT(rs.getFloat(5));
                entity.setThanhTien(rs.getFloat(6));
                entity.setTrangThaiHDCT(rs.getBoolean(7));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public int insert(HoaDonChiTiet entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaHD(), entity.getMaSPCT(), entity.getSoLuongHDCT(), entity.getDonGiaHDCT(), entity.getThanhTien());
    }

    @Override
    public int update(HoaDonChiTiet entity) {
        return JDBCHelper.executeUpdate(UPDATE_SQL, entity.getMaHD(), entity.getMaSPCT(), entity.getSoLuongHDCT(), entity.getDonGiaHDCT(), entity.getThanhTien(), entity.getMaHDCT());
    }

    public int updateHDCTSP(int soLuong, float thanhTien, int maHDCT) {
        String sql = "UPDATE tblHoaDonChiTiet SET SoLuongHDCT =? , ThanhTienHDCT=? WHERE MaHoaDonChiTiet = ?";
        return JDBCHelper.executeUpdate(sql, soLuong, thanhTien, maHDCT);
    }

    public void xoaHDCT(int maHDCT){
        String sql = "DELETE FROM tblHoaDonChiTiet WHERE MaHoaDonChiTiet=?";
         JDBCHelper.executeUpdate(sql, maHDCT);
    }
}
