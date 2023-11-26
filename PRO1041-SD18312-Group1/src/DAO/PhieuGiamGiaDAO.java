
package DAO;

import Entity.PhieuGiamGia;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;
import java.sql.*;

public class PhieuGiamGiaDAO extends DuAn1DAO1<PhieuGiamGia, Integer>{
    final String INSERT_SQL = "INSERT INTO tblPhieuGiamGia(TenPhieuGiamGia,GiaTriPGG,TongTienHangPGG,TrangThaiPGG,NgayTaoPGG,NgayHetHanPGG,GhiChuPGG,MaNhanVien) \n" 
    + "VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE tblPhieuGiamGia SET TenPhieuGiamGia = ?, GiaTriPGG = ?,TongTienHangPGG = ?, TrangThaiPGG = ?, \n" +
    "NgayTaoPGG = ?, NgayHetHanPGG = ?, GhiChuPGG = ?, MaNhanVien = ?\n" +
    "WHERE MaPhieuGiamGia = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM tblPhieuGiamGia";
    final String SELECT_TrangThai_SQL = "SELECT TrangThaiPGG FROM tblPhieuGiamGia";
    final String SELECT_NhanVien_SQL = "SELECT MaNhanVien FROM tblPhieuGiamGia";
    final String SELECT_BY_ID_SQL = "SELECT * FROM tblPhieuGiamGia WHERE MaPhieuGiamGia = ?"; 

    @Override
    public void insert(PhieuGiamGia entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getTenPGG(),entity.getGiaTriPGG(), entity.getTongTienHang(),
                entity.isTrangThaiPGG(),entity.getNgayTao(), entity.getNgayHetHan(), entity.getGhiChu(), entity.getMaNV());
        }

    @Override
    public void update(PhieuGiamGia entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getTenPGG(),entity.getGiaTriPGG(), entity.getTongTienHang(),entity.isTrangThaiPGG(),
                entity.getNgayTao(), entity.getNgayHetHan(), entity.getGhiChu(),entity.getMaNV(),entity.getMaPhieuGiamGia());
        }

    @Override
    public List<PhieuGiamGia> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
        }

    @Override
    public PhieuGiamGia selectById(Integer key) {
        List<PhieuGiamGia> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
        }

    @Override
    protected List<PhieuGiamGia> selectBySql(String sql, Object... args) {
        List<PhieuGiamGia> list = new ArrayList<>();
        try{
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while(rs.next()){
                PhieuGiamGia entity = new PhieuGiamGia();
                entity.setMaPhieuGiamGia(rs.getInt("MaPhieuGiamGia"));
                entity.setTenPGG(rs.getString("TenPhieuGiamGia"));
                entity.setGiaTriPGG(rs.getInt("GiaTriPGG"));
                entity.setTongTienHang(rs.getDouble("TongTienHangPGG"));
                entity.setTrangThaiPGG(rs.getBoolean("TrangThaiPGG"));
                entity.setNgayTao(rs.getString("NgayTaoPGG"));
                entity.setNgayHetHan(rs.getString("NgayHetHanPGG"));
                entity.setGhiChu(rs.getString("GhiChuPGG"));
                entity.setMaNV(rs.getString("MaNhanVien"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
        }
 
    public List<PhieuGiamGia> searchByID(Integer id){
        String sql = "Select * from tblPhieuGiamGia WHERE MaPhieuGiamGia = ?";
        return this.selectBySql(sql, id);
    }   
    
    public List<PhieuGiamGia> searchByName(String name){
        String sql = "Select * from tblPhieuGiamGia WHERE TenPhieuGiamGia = ?";
        return this.selectBySql(sql, name+"%");
    }   
    
    public List<PhieuGiamGia> deleteTrangThai(){
        String sql = "SELECT * FROM tblPhieuGiamGia WHERE TrangThaiPGG = 1";
        return this.selectBySql(sql);
    }
    
    public List<Integer> selectTrangThai(){
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
    
    public List<String> selectNhanVien(){
        List<String> list = new ArrayList();
        try {
            ResultSet rs = JDBCHelper.executeQuery(SELECT_NhanVien_SQL);
            while(rs.next()){
                String maNV = rs.getString("MaNhanVien");
                list.add(maNV);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<PhieuGiamGia> fillTrangThai(Integer PhieuGiamGia){
        String sql = "SELECT * FROM tblPhieuGiamGia WHERE TrangThaiPGG = ?";
        return selectBySql(sql, PhieuGiamGia);
    }
    
    public List<PhieuGiamGia> fillNhanVien(String MaNhanVien){
        String sql = "SELECT * FROM tblPhieuGiamGia WHERE MaNhanVien = ?";
        return selectBySql(sql, MaNhanVien);
    }
}
