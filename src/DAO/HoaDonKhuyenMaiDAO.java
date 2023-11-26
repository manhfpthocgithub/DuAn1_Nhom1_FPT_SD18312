package DAO;

import Entity.HoaDonKhuyenMai;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Utils.JDBCHelper;
public class HoaDonKhuyenMaiDAO extends DuAn1DAO<HoaDonKhuyenMai, Integer>{
    final String SELECT_ALL_SQL = "SELECT * FROM tblHoaDonKhuyenMai";
    final String SELECT_BY_ID_SQL = "SELECT * FROM tblHoaDonKhuyenMai WHERE MaHoaDonKhuyenMai = ?";
    
    
    @Override
    public void insert(HoaDonKhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HoaDonKhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonKhuyenMai> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);       
        }

    @Override
    public HoaDonKhuyenMai selectById(Integer key) {
        List<HoaDonKhuyenMai> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
        }

    @Override
    protected List<HoaDonKhuyenMai> selectBySql(String sql, Object... args) {
        List<HoaDonKhuyenMai> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while(rs.next()){
                HoaDonKhuyenMai hdkm = new HoaDonKhuyenMai();
                hdkm.setMaHDKhuyenMai(rs.getInt(1));
                hdkm.setMaPGG(rs.getInt(2));
                hdkm.setMaHoaDon(rs.getInt(3));               
                hdkm.setSoTienConLai(rs.getFloat(4));
                hdkm.setTrangThaiHDKM(rs.getBoolean(5));
                list.add(hdkm);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }  
}
