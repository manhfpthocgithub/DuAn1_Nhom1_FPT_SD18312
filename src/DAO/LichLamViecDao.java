/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.LichLamViec;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Utils.JDBCHelper;

/**
 *
 * @author Pico123
 */
public class LichLamViecDao extends DuAn1DAO<LichLamViec, Integer>{
    final String INSERT_SQL = "insert into tblLichLamViec (MaNhanVien, NgayBatDauLam, NgayKetThucLam, CaLam) values (?, ?, ?, ?)";
    final String UPDATE_SQL = "update tblLichLamViec set MaNhanVien = ?, NgayBatDauLam = ?, NgayKetThucLam = ?, CaLam = ? where MaLichLamViec = ?";
    final String DELETE_SQL = "delete tblLichLamViec where MaLichLamViec = ?";
    final String SELECT_ALL_SQL = "select * from tblLichLamViec ";
    final String SELECT_BY_ID_SQL = "select * from tblLichLamViec where MaLichLamViec = ?";
    @Override
    public void insert(LichLamViec entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaNv(), entity.getNgayBD(), entity.getNgayKT(), entity.getCalam());
    }

    @Override
    public void update(LichLamViec entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getMaNv(), entity.getNgayBD(), entity.getNgayKT(), entity.getCalam(), entity.getID());
    }

    @Override
    public List<LichLamViec> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public LichLamViec selectById(Integer key) {
        List<LichLamViec> list = selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<LichLamViec> selectBySql(String sql, Object... args) {
             List<LichLamViec> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                LichLamViec entity = new LichLamViec();
                entity.setID(rs.getInt("MaLichLamViec"));
                entity.setMaNv(rs.getString("MaNhanVien"));
                entity.setNgayBD(rs.getDate("NgayBatDauLam"));
                entity.setNgayKT(rs.getDate("NgayKetThucLam"));
                entity.setCalam(rs.getString("CaLam"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
         
    public List<LichLamViec> selectLLV(String MaNhanVien){
        String sql = "select * from tblLichLamViec where MaNhanVien = ?";
        return this.selectBySql(sql, MaNhanVien);
    }
    
    public void delete(Integer id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);

    }
}
