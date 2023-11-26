/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.SanPhamHoaDon;
import Utils.DBConnect;
import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class SanPhamHoaDonDAO extends DuAn1DAO1<SanPhamHoaDon, String> {

    @Override
    public int insert(SanPhamHoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(SanPhamHoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPhamHoaDon selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamHoaDon> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<SanPhamHoaDon> selectBySql(String sql, Object... args) {
        List<SanPhamHoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                SanPhamHoaDon sphd = new SanPhamHoaDon();
                sphd.setMaCTSP(rs.getString(1));
                sphd.setTenAo(rs.getString(2));
                sphd.setSoLuongTon(rs.getInt(3));
                sphd.setMauSac(rs.getString(4));
                sphd.setSize(rs.getString(5));
                sphd.setLoaiAo(rs.getString(6));
                sphd.setGia(rs.getDouble(7));
                sphd.setGiaDaGiam(rs.getDouble(8));
                list.add(sphd);
            }
            rs.close();
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<SanPhamHoaDon> getAll() {
        String sql = "select CTSP.MaSPCT,AKMD.TenAoKhoac , CTSP.SoLuongAK,MS.TenMauSac, SZ.tenSize,LA.TenLoaiAo,CTSP.GiaAK,CTSP.GiaAK - case \n"
                + "	   when DGG.TrangThaiDGG = 0 then CTSP.GiaAK / 100 * DGG.GiaTriDGG\n"
                + "	   ELSE 0 \n"
                + "	   END AS 'Gia giam'\n"
                + "	   from tblAoKhoacMuaDong AKMD JOIN tblChiTietSanPham CTSP ON AKMD.MaAoKhoac = CTSP.MaAoKhoac JOIN tblMauSacSP MS ON CTSP.MaMauSac = MS.MaMauSac JOIN tblSize SZ ON CTSP.MaSize = SZ.MaSize\n"
                + "	   JOIN tblCL_LA CLLA ON CLLA.MaChatLieuLoaiAo = AKMD.MaChatLieuLoaiAo JOIN tblLoaiAo LA ON CLLA.MaLoaiAo = LA.MaLoaiAo \n"
                + "	   JOIN tblDotGiamGia DGG ON CLLA.MaLoaiAo = DGG.MaLoaiAo";
        List<SanPhamHoaDon> list = selectBySql(sql);
        return list;
    }

    public List<SanPhamHoaDon> timKiemTheoTen(String timKiem) {
        String sql = "select CTSP.MaSPCT,AKMD.TenAoKhoac , CTSP.SoLuongAK,MS.TenMauSac, SZ.tenSize,LA.TenLoaiAo,CTSP.GiaAK,CTSP.GiaAK - case \n"
                + "	   when DGG.TrangThaiDGG = 0 then CTSP.GiaAK / 100 * DGG.GiaTriDGG\n"
                + "	   ELSE 0 \n"
                + "	   END AS 'Gia giam'\n"
                + "	   from tblAoKhoacMuaDong AKMD JOIN tblChiTietSanPham CTSP ON AKMD.MaAoKhoac = CTSP.MaAoKhoac JOIN tblMauSacSP MS ON CTSP.MaMauSac = MS.MaMauSac JOIN tblSize SZ ON CTSP.MaSize = SZ.MaSize\n"
                + "	   JOIN tblCL_LA CLLA ON CLLA.MaChatLieuLoaiAo = AKMD.MaChatLieuLoaiAo JOIN tblLoaiAo LA ON CLLA.MaLoaiAo = LA.MaLoaiAo\n"
                + "	   JOIN tblDotGiamGia DGG ON CLLA.MaLoaiAo = DGG.MaLoaiAo\n"
                + "	   WHERE AKMD.TenAoKhoac LIKE ?";
        List<SanPhamHoaDon> list = this.selectBySql(sql, "%" + timKiem + "%");
        return list;
    }

    public List<SanPhamHoaDon> locSanPham(String ms, String sz, String la) {
        String sql = "select CTSP.MaSPCT,AKMD.TenAoKhoac , CTSP.SoLuongAK,MS.TenMauSac, SZ.tenSize,LA.TenLoaiAo,CTSP.GiaAK,CTSP.GiaAK - case \n"
                + "	   when DGG.TrangThaiDGG = 0 then CTSP.GiaAK / 100 * DGG.GiaTriDGG\n"
                + "	   ELSE 0 \n"
                + "	   END AS 'Gia giam'\n"
                + "	   from tblAoKhoacMuaDong AKMD JOIN tblChiTietSanPham CTSP ON AKMD.MaAoKhoac = CTSP.MaAoKhoac JOIN tblMauSacSP MS ON CTSP.MaMauSac = MS.MaMauSac JOIN tblSize SZ ON CTSP.MaSize = SZ.MaSize\n"
                + "	   JOIN tblCL_LA CLLA ON CLLA.MaChatLieuLoaiAo = AKMD.MaChatLieuLoaiAo JOIN tblLoaiAo LA ON CLLA.MaLoaiAo = LA.MaLoaiAo\n"
                + "	   JOIN tblDotGiamGia DGG ON CLLA.MaLoaiAo = DGG.MaLoaiAo\n"
                + "	   WHERE MS.TenMauSac LIKE ? AND SZ.tenSize LIKE ? AND LA.TenLoaiAo LIKE ?";
        List<SanPhamHoaDon> list = this.selectBySql(sql, "%" + ms + "%", sz + "%", "%" + la + "%");
        return list;
    }

}
