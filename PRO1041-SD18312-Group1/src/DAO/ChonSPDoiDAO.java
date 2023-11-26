package DAO;

import Entity.ChonSPDoi;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;
import java.sql.*;

public class ChonSPDoiDAO extends DuAn1DAO<ChonSPDoi, String>{
    String SELECT_ALL_SQL = "SELECT tblChiTietSanPham.MaSPCT, tblAoKhoacMuaDong.MaAoKhoac, tblAoKhoacMuaDong.TenAoKhoac, tblAoKhoacMuaDong.TenPhongCach,tblSize.TenSize,tblChiTietSanPham.GiaAK ,tblChiTietSanPham.SoLuongAK\n" +
"FROM tblAoKhoacMuaDong\n" +
"INNER JOIN tblChiTietSanPham ON tblAoKhoacMuaDong.MaAoKhoac = tblChiTietSanPham.MaAoKhoac\n" +
"INNER JOIN tblSize  ON tblChiTietSanPham.MaSize = tblSize.MaSize;";
    
    String SELECT_BY_ID = "SELECT tblChiTietSanPham.MaSPCT, tblAoKhoacMuaDong.MaAoKhoac, tblAoKhoacMuaDong.TenAoKhoac, tblAoKhoacMuaDong.TenPhongCach,tblSize.TenSize,tblChiTietSanPham.GiaAK ,tblChiTietSanPham.SoLuongAK\n" +
"FROM tblAoKhoacMuaDong\n" +
"INNER JOIN tblChiTietSanPham ON tblAoKhoacMuaDong.MaAoKhoac = tblChiTietSanPham.MaAoKhoac\n" +
"INNER JOIN tblSize  ON tblChiTietSanPham.MaSize = tblSize.MaSize"
            + "WHERE tblChiTietSanPham.MaSPCT=?";
    @Override
    public int insert(ChonSPDoi entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(ChonSPDoi entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChonSPDoi selectById(String id) {
        List<ChonSPDoi> list = selectBySql(SELECT_BY_ID, id);
        if(list.isEmpty()){
            return null ;
        }
        return list.get(0);
    }
    

    @Override
    public List<ChonSPDoi> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChonSPDoi> selectBySql(String sql, Object... args) {
        List<ChonSPDoi> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                ChonSPDoi chsp = new ChonSPDoi();
                chsp.setMaSPCT(rs.getString(1));
                chsp.setMaAoKhoac(rs.getString(2));
                chsp.setTenAoKhoac(rs.getString(3));
                chsp.setTenPhongCach(rs.getString(4));
                chsp.setTenSize(rs.getString(5));
                chsp.setGiaAoKhoac(rs.getFloat(6));
                chsp.setSoLuong(rs.getInt(7));
                
                list.add(chsp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
}
