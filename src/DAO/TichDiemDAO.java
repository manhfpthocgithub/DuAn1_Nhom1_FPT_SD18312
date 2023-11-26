/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.LichSuKhachHang;
import Entity.TichDiem;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TichDiemDAO extends DuAn1DAO<TichDiem, Integer> {

    final String SELECT_ALL_SQL = "SELECT tblHoaDon.MaKhachHang,tblKhachHang.TenKhachHang,tblKhachHang.SoDienThoaiKH,tblKhachHang.DiaChiKH,SUM(tblHoaDon.ThanhToan) AS TongTienMua,COUNT(tblHoaDon.MaKhachHang) AS TichDiem\n"
            + "    FROM tblKhachHang JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang WHERE tblHoaDon.TrangThaiHoaDon = N'Đã thanh toán' AND tblKhachHang.MaKhachHang = ? \n"
            + "	GROUP BY tblHoaDon.MaKhachHang, tblKhachHang.TenKhachHang,tblKhachHang.SoDienThoaiKH,tblKhachHang.DiaChiKH";

    @Override
    public void insert(TichDiem entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(TichDiem entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TichDiem> selectAll() {
         return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TichDiem selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<TichDiem> selectBySql(String sql, Object... args) {
     List<TichDiem> list = new ArrayList<>();
        try {
            ResultSet rs =JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                TichDiem entity = new TichDiem();

                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
               entity.setTenKhachHang(rs.getString("TenKhachHang"));
               entity.setSoDienThoai(rs.getString("SoDienThoaiKH"));
               entity.setDiaChi(rs.getString("DiaChiKH"));
               entity.setTongTienMua(rs.getFloat("TongTienMua"));
               entity.setTichDiem(rs.getInt("TichDiem"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
 
    
    public List<TichDiem> selectTichDiem(int MaKhachHang){
    return this.selectBySql(SELECT_ALL_SQL, MaKhachHang);
    
    }
}
