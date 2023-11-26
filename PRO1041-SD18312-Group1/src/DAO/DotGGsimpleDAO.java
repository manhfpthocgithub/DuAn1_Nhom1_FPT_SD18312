/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.AKGG;
import Entity.DotGGsimple;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author haila
 */
public class DotGGsimpleDAO extends DuAn1DAO<DotGGsimple, String>{
    final String SELECT_ALL = "SELECT MaDotGiamGia,TenDotGiamGia,GiaTriDGG FROM tblDotGiamGia";

   
    public void update2(DotGGsimple entity) {
       }

    @Override
    public List<DotGGsimple> selectAll() {
     return selectBySql(SELECT_ALL);
    }

    @Override
    public DotGGsimple selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<DotGGsimple> selectBySql(String sql, Object... args) {
        List<DotGGsimple> listDotGGS = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(SELECT_ALL, args);
            
            while(rs.next()){
                DotGGsimple entity = new DotGGsimple();
                entity.setMaDGG(rs.getString("MaDotGiamGia"));
                entity.setTenDGG(rs.getString("TenDotGiamGia"));
                entity.setGiatriDGG(rs.getInt("GiaTriDGG"));
                listDotGGS.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDotGGS;
    }

    @Override
    public int insert(DotGGsimple entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(DotGGsimple entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
