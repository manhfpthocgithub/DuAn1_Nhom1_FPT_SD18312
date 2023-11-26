    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.AKGG;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Utils.JDBCHelper;

/**
 *
 * @author haila
 */
public class LoaiAKDAO extends DuAn1DAO<AKGG, String>{
    
    final String sql = "SELECT MaLoaiAo,TenLoaiAo FROM tblLoaiAo";
    
    @Override
    public void insert(AKGG entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(AKGG entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public List<AKGG> selectAll() {
    return selectBySql(sql);
    }

    @Override
    public AKGG selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<AKGG> selectBySql(String sql, Object... args) {
     List<AKGG> listLAK = new ArrayList<>();
        try {
                ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while(rs.next()) {
               AKGG entity = new AKGG();
               entity.setMaLAK(rs.getString("MaLoaiAo"));
               entity.setTenLAK(rs.getString("TenLoaiAo"));
               listLAK.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return listLAK;    
    }
    
    
    
}
