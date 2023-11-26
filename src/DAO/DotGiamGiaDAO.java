
package DAO;

import Entity.AKGG;
import Entity.DotGiamGia;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.JDBCHelper;


public class DotGiamGiaDAO extends DuAn1DAO<DotGiamGia,String> {
    

    final String INSERT_SQL = "INSERT INTO tblDotGiamGia(MaDotGiamGia,MaNhanVien,TenDotGiamGia,GiaTriDGG,NgayBatDau,NgayKetThuc,MaLoaiAo,TrangThaiDGG,GhiChuDGG) VALUES(?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE tblDotGiamGia SET MaNhanVien =?, TenDotGiamGia =?, GiaTriDGG =?, NgayBatDau=?,NgayKetThuc=?,MaLoaiAo=?,TrangThaiDGG=?,GhiChuDGG=? WHERE MaDotGiamGia =?";
    final String UPDATE2_SQL = "UPDATE tblDotGiamGia SET TrangThaiDGG = 1 WHERE MaDotGiamGia = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM tblDotGiamGia WHERE TrangThaiDGG = 0";
    final String SELECT_BY_ID_SQL = "SELECT * FROM tblDotGiamGia WHERE TenDotGiamGia like ? ";
    final String SELECT_LAK = "SELECT MaLoaiAo,TenLoaiAo FROM tblLoaiAo";

    @Override
    public void insert(DotGiamGia entity) {
         JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaDotGiamGia(),entity.getMaNhanVien(),entity.getTenDotGiamGia(),entity.getGiaTriDGG(),entity.getNgayBatDau(),entity.getNgayKetThuc(),entity.getSanPhamDGG(),entity.isTrangThai(),entity.getGhiChuDGG());
    }

    @Override
    public void update(DotGiamGia entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL,entity.getMaNhanVien(),entity.getTenDotGiamGia(),entity.getGiaTriDGG(),entity.getNgayBatDau(),entity.getNgayKetThuc(),entity.getSanPhamDGG(),entity.isTrangThai(),entity.getGhiChuDGG(),entity.getMaDotGiamGia());
    }

    @Override
    public List<DotGiamGia> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DotGiamGia selectById(String key) {
        List<DotGiamGia> list = selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
                         
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DotGiamGia> selectBySql(String sql, Object... args) {
        List<DotGiamGia> listGG = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                DotGiamGia entity = new DotGiamGia();
                entity.setMaDotGiamGia(rs.getString("MaDotGiamGia"));
                entity.setMaNhanVien(rs.getString("MaNhanVien"));
                entity.setTenDotGiamGia(rs.getString("TenDotGiamGia"));
                entity.setGiaTriDGG(rs.getInt("GiaTriDGG"));
                entity.setNgayBatDau(rs.getString("NgayBatDau"));
                entity.setNgayKetThuc(rs.getString("NgayKetThuc"));
                entity.setSanPhamDGG(rs.getString("MaLoaiAo"));
                entity.setTrangThai(rs.getBoolean("TrangThaiDGG"));
                entity.setGhiChuDGG(rs.getString("GhiChuDGG"));
                 listGG.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return listGG;
    }

    
    public void update21(String  entity ) {
        JDBCHelper.executeUpdate(UPDATE2_SQL,entity);
    }

   
    public List<DotGiamGia> selectByKeyWord(String keyword){ 
        String sql = "SELECT * FROM tblDotGiamGia WHERE TenDotGiamGia like ? AND TrangThaiDGG = 0";
        return this.selectBySql(sql, "%"+keyword+"%");
    }



    
    public void setNgay(){
            String sql = "UPDATE tblDotGiamGia SET TrangThaiDGG = 1 WHERE NgayKetThuc < CONVERT(DATE, GETDATE(), 103)";
        JDBCHelper.executeUpdate(sql);
    }
    

    
    
    
    
    

   
}
