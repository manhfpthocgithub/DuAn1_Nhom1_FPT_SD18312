/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.LoaiAo;
import Entity.MauSac;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LoaiAoDAO extends DuAn1DAO1<LoaiAo, Integer> {

    String INSERT_SQL = "INSERT INTO tblLoaiAo (TenLoaiAo,TrangThai) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE tblLoaiAo SET TenLoaiAo=? , TrangThai = ? WHERE MaLoaiAo=?";
    String SELECT_ALL_SQL = "SELECT * FROM tblLoaiAo ";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblLoaiAo WHERE MaLoaiAO =?";

    @Override
    public int insert(LoaiAo entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getTenLoaiAo(), entity.isTrangThai());
    }

    @Override
    public int update(LoaiAo entity) {
        return JDBCHelper.executeUpdate(UPDATE_SQL, entity.getTenLoaiAo(), entity.isTrangThai(), entity.getMaLoaiAo());
    }

    @Override
    public LoaiAo selectById(Integer id) {
        List<LoaiAo> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<LoaiAo> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<LoaiAo> selectBySql(String sql, Object... args) {
        List<LoaiAo> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
               LoaiAo la = new LoaiAo();
                la.setMaLoaiAo(rs.getInt(1));
                la.setTenLoaiAo(rs.getString(2));
               la.setTrangThai(rs.getBoolean(3));
                list.add(la);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public boolean KiemTraTenLoaiAo(String tenDanhMuc) {
        List<LoaiAo> list = new ArrayList<>();
        String sql = "SELECT * FROM tblLoaiAo WHERE TenDanhMuc =?";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, tenDanhMuc);
            while (rs.next()) {
                return false;
            }
            rs.getStatement().getConnection().close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public List<LoaiAo> selectAllHD() {
        String SELECT_ALL_SQL_HD = "SELECT * FROM tblLoaiAo WHERE TrangThai =1 ";
        return this.selectBySql(SELECT_ALL_SQL_HD);
    }
    
    // kiểm tra tên chất liệu đang ngừng hoạt động
    public boolean KiemTraTenLoaiAoNHD(String tenLoaiAo) {
        List<LoaiAo> list = new ArrayList<>();
        String sql = "SELECT * FROM tblLoaiAo WHERE TenLoaiAo =? AND TrangThai=0";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, tenLoaiAo);
            while (rs.next()) {
                return true;
            }
            rs.getStatement().getConnection().close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    // để update trạng thái cho các chất liệu trùng tên với tên thêm nhưng đang ngừng hoạt động
    
    public int updateTTNHD(String tenChatLieu){
        String sql = "UPDATE tblLoaiAo SET TrangThai = 1 WHERE TenLoaiAo=?";
        return JDBCHelper.executeUpdate(sql, tenChatLieu);
    }
}
