package DAO;

import Entity.PhieuGiaoHang;
import Entity.PhieuGiaoHang_ChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

public class PhieuGiaoHang_ChiTietDAO {

    final String select = "SELECT\n"
            + "    tblPhieuGiaoHang.MaHoaDon,\n"
            + "    tblAoKhoacMuaDong.TenAoKhoac,\n"
            + "    SUM(tblHoaDonChiTiet.SoLuongHDCT) AS TongSoLuong,\n"
            + "    SUM(tblHoaDonChiTiet.DonGiaHDCT) AS TongDonGia,\n"
            + "    tblDonViVanChuyen.TenDonVi,\n"
            + "    tblDonViVanChuyen.PhuongThucVanChuyen\n"
            + "FROM\n"
            + "    tblPhieuGiaoHang\n"
            + "JOIN\n"
            + "    tblHoaDon ON tblPhieuGiaoHang.MaHoaDon = tblHoaDon.MaHoaDon\n"
            + "JOIN\n"
            + "    tblHoaDonChiTiet ON tblHoaDon.MaHoaDon = tblHoaDonChiTiet.MaHoaDon\n"
            + "JOIN\n"
            + "    tblAoKhoacMuaDong ON tblHoaDonChiTiet.MaAoKhoac = tblAoKhoacMuaDong.MaAoKhoac\n"
            + "JOIN\n"
            + "    tblDonViVanChuyen ON tblPhieuGiaoHang.MaDonViVanChuyen = tblDonViVanChuyen.MaDonViVanChuyen\n"
            + "  where tblPhieuGiaoHang.MaHoaDon = ?\n"
            + "GROUP BY\n"
            + "    tblPhieuGiaoHang.MaHoaDon, tblAoKhoacMuaDong.TenAoKhoac, tblDonViVanChuyen.TenDonVi, tblDonViVanChuyen.PhuongThucVanChuyen";

    protected List<PhieuGiaoHang_ChiTiet> selectAllChiTietPhieuGiaoHang(String sql, Object... args) {
        List<PhieuGiaoHang_ChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                PhieuGiaoHang_ChiTiet chiTiet = new PhieuGiaoHang_ChiTiet();

                chiTiet.setMaHoaDon(rs.getInt("MaHoaDon"));
                chiTiet.setTenAoKhoac(rs.getString("TenAoKhoac"));
                chiTiet.setSoLuongHDCT(rs.getInt("TongSoLuong")); // Sửa ở đây
                chiTiet.setDonGia(rs.getFloat("TongDonGia")); // Sửa ở đây
                chiTiet.setDVVC(rs.getString("TenDonVi"));
                chiTiet.setHinhThucThanhToan(rs.getBoolean("PhuongThucVanChuyen"));

                list.add(chiTiet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public PhieuGiaoHang_ChiTiet selectByMaHoaDon(Integer key) {
        List<PhieuGiaoHang_ChiTiet> list = selectAllChiTietPhieuGiaoHang(select, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
