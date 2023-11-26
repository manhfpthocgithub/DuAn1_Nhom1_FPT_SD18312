/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDon;
import Entity.KhachHang;
import Entity.LichSuKhachHang;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class LichSuKhachHangDAO extends DuAn1DAO<LichSuKhachHang, Integer> {

    final String SELECT_ALL_SQL = "SELECT tblLichSuKhachHang.MaLichSuKhachHang,tblHoaDon.MaKhachHang,tblKhachHang.TenKhachHang,tblHoaDon.MaHoaDon,tblHoaDon.ThanhToan,tblHoaDon.TrangThaiHoaDon,\n"
            + "       tblHoaDon.NgayTaoHoaDon \n"
            + "FROM tblLichSuKhachHang JOIN tblKhachHang ON tblKhachHang.MaKhachHang = tblLichSuKhachHang.MaKhachHang \n"
            + "                        JOIN tblHoaDon ON tblHoaDon.MaHoaDon = tblLichSuKhachHang.MaHoaDon WHERE TrangThaiHoaDon = N'Đã thanh toán'";
   final String SELECT_LICH_SU_KH ="SELECT \n" +
"    tblKhachHang.MaKhachHang,\n" +
"    tblKhachHang.TenKhachHang,\n" +
"    tblHoaDon.MaHoaDon,    tblHoaDon.ThanhToan,\n" +
"    tblHoaDon.TrangThaiHoaDon,    tblHoaDon.NgayTaoHoaDon\n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán'";
    @Override
    public void insert(LichSuKhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(LichSuKhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<LichSuKhachHang> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }
    public List<LichSuKhachHang> selectLichSuKH(){
    return selectLichSuKH(SELECT_LICH_SU_KH);
    }
    
    public List<LichSuKhachHang> selectLichSuKH(String sql, Object... args){
    List<LichSuKhachHang> list1 = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
               LichSuKhachHang entityLS = new LichSuKhachHang();
                entityLS.setMaKhachHang(rs.getInt("MaKhachHang"));
                entityLS.setTenKhachHang(rs.getString("TenKhachHang"));
                entityLS.setMaHoaDon(rs.getInt("MaHoaDon"));
                entityLS.setThanhToan(rs.getInt("ThanhToan"));
                entityLS.setTrangThaiHoaDon(rs.getString("TrangThaiHoaDon"));
                entityLS.setNgayTaoHoaDon(rs.getDate("NgayTaoHoaDon"));
                list1.add(entityLS);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }   
        return list1;
    }
 
 
 
    @Override
    public LichSuKhachHang selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<LichSuKhachHang> selectBySql(String sql, Object... args) {
        List<LichSuKhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                LichSuKhachHang entityLS = new LichSuKhachHang();
                entityLS.setMaKhachHang(rs.getInt("MaKhachHang"));
                entityLS.setTenKhachHang(rs.getString("TenKhachHang"));
                entityLS.setMaHoaDon(rs.getInt("MaHoaDon"));
                entityLS.setThanhToan(rs.getInt("ThanhToan"));
                entityLS.setTrangThaiHoaDon(rs.getString("TrangThaiHoaDon"));
                entityLS.setNgayTaoHoaDon(rs.getDate("NgayTaoHoaDon"));
                list.add(entityLS);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<LichSuKhachHang> searchByName(String name) {
        String sql = "SELECT \n" +
"    tblKhachHang.MaKhachHang,\n" +
"    tblKhachHang.TenKhachHang,\n" +
"    tblHoaDon.MaHoaDon,    tblHoaDon.ThanhToan,\n" +
"    tblHoaDon.TrangThaiHoaDon,    tblHoaDon.NgayTaoHoaDon\n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán' AND tblKhachHang.TenKhachHang LIKE  ";
        return this.selectBySql(sql, "%" + name + "%");
    }

    final String maKhachHangLSKH = "SELECT \n" +
"    tblHoaDon.MaKhachHang\n" +
"   \n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán'";

    public List<String> selectMaKhachHangLSKH() {
        List<String> listMaKH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(maKhachHangLSKH);
            while (rs.next()) {
                String maKH = rs.getString("MaKhachHang");
                listMaKH.add(maKH);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listMaKH;
    }

    public List<LichSuKhachHang> filterByMaKH(Integer MaKH) {
        String sql = "SELECT \n" +
"    tblKhachHang.MaKhachHang,\n" +
"    tblKhachHang.TenKhachHang,\n" +
"    tblHoaDon.MaHoaDon,    tblHoaDon.ThanhToan,\n" +
"    tblHoaDon.TrangThaiHoaDon,    tblHoaDon.NgayTaoHoaDon\n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán' AND tblKhachHang.MaKhachHang = ?";
        return selectBySql(sql, MaKH);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    final String maHoaDonLSKH = "SELECT \n" +
"    tblHoaDon.MaHoaDon\n" +
"   \n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán'";

    public List<String> selectMaHoaDonLSKH() {
        List<String> listMaHD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(maHoaDonLSKH);
            while (rs.next()) {
                String maHD = rs.getString("MaHoaDon");
                listMaHD.add(maHD);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listMaHD;
    }

    public List<LichSuKhachHang> filterByMaHD(Integer MaHD) {
        String sql = "SELECT \n" +
"    tblKhachHang.MaKhachHang,\n" +
"    tblKhachHang.TenKhachHang,\n" +
"    tblHoaDon.MaHoaDon,    tblHoaDon.ThanhToan,\n" +
"    tblHoaDon.TrangThaiHoaDon,    tblHoaDon.NgayTaoHoaDon\n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán' AND tblHoaDon.MaHoaDon = ?";
        return selectBySql(sql, MaHD);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    final String ngayTaoHDLSKH = "SELECT \n" +
"    tblHoaDon.NgayTaoHoaDon\n" +
"   \n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán'";

    public List<String> selectNgayTaoHDLSKH() {
        List<String> listngayTaoHD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(ngayTaoHDLSKH);
            while (rs.next()) {
                String ngayTaoHD = rs.getString("NgayTaoHoaDon");
                listngayTaoHD.add(ngayTaoHD);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listngayTaoHD;
    }

    public List<LichSuKhachHang> filterByNgayTaoHD(String ngayTaoHD) {
        String sql = "SELECT \n" +
"    tblKhachHang.MaKhachHang,\n" +
"    tblKhachHang.TenKhachHang,\n" +
"    tblHoaDon.MaHoaDon,    tblHoaDon.ThanhToan,\n" +
"    tblHoaDon.TrangThaiHoaDon,    tblHoaDon.NgayTaoHoaDon\n" +
"FROM  tblKhachHang INNER JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang \n" +
"WHERE TrangThaiHoaDon = N'Đã thanh toán' AND tblHoaDon.NgayTaoHoaDon = ?";
        return selectBySql(sql, ngayTaoHD);
    }

}
