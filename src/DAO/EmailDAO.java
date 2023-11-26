/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import Entity.Email;
import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class EmailDAO extends DuAn1DAO<Email, Integer> {
    final String INSERT_SQL = "INSERT INTO tblEmailKhachHang(MaKhachHang, MaPhieuGiamGia,TieuDeEmail,NoiDungEmail)\n" +
"VALUES (?,?,?,?)";
    final String SELECT_ALL_SQL = "SELECT * FROM tblEmailKhachHang";
    final String SELECT_EMAIL_TICHDIEM =  "SELECT tblHoaDon.MaKhachHang,tblKhachHang.TenKhachHang,tblKhachHang.EmailKH,SUM(tblHoaDon.ThanhToan) AS TongTienMua,COUNT(tblHoaDon.MaKhachHang) AS TichDiem\n" +
"    FROM tblKhachHang JOIN tblHoaDon ON tblKhachHang.MaKhachHang = tblHoaDon.MaKhachHang WHERE tblHoaDon.TrangThaiHoaDon = N'Đã thanh toán' \n" +
"	GROUP BY tblHoaDon.MaKhachHang, tblKhachHang.TenKhachHang,tblKhachHang.EmailKH";

    @Override
    public void insert(Email entity) {
       JDBCHelper.executeUpdate(INSERT_SQL,  entity.getMaKhachHang(),
               entity.getMaPhieuGiamGia(), entity.getTieuDeEmail(), entity.getNoiDungEmail());
    }

    @Override
    public void update(Email entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Email> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }
    
    public List<Email> selectEmailTichDiem(){
    return selectByEmailTichDiem(SELECT_EMAIL_TICHDIEM);
    }

    @Override
    public Email selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<Email> selectBySql(String sql, Object... args) {
        List<Email> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Email entityEM = new Email();

                entityEM.setMaEmail(rs.getInt("MaEmail"));
                entityEM.setMaKhachHang(rs.getInt("MaKhachHang"));
                entityEM.setMaPhieuGiamGia(rs.getInt("MaPhieuGiamGia"));
                entityEM.setTieuDeEmail(rs.getString("TieuDeEmail"));
                entityEM.setNoiDungEmail(rs.getString("NoiDungEmail"));
                list.add(entityEM);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<Email> selectByEmailTichDiem(String sql, Object... args){
    List<Email> list1 = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Email entityEM = new Email();
                entityEM.setMaKhachHang(rs.getInt("MaKhachHang"));
                entityEM.setTenKhachHang(rs.getString("TenKhachHang"));
                entityEM.setEmail(rs.getString("EmailKH"));
                entityEM.setTongMua(rs.getFloat("TongTienMua"));
                entityEM.setTichDiem(rs.getInt("TichDiem"));
                list1.add(entityEM);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }   
        return list1;
    }
   
}
