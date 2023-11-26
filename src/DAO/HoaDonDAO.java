package DAO;

import Entity.HoaDon;
import Entity.PhieuDoi;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO extends DuAn1DAO1<HoaDon, Integer> {

    final String INSERT_SQL = "INSERT INTO tblHoaDon\n"
            + "             (MaNhanVien, MaKhachHang, NgayTaoHoaDon, TrangThaiHoaDon)\n"
            + "VALUES (?,?,?,N'Chưa thanh toán')";
    //final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?,HocPhi = ?,ThoiLuong = ?,Hinh = ?,MoTa = ? WHERE MaCD = ?";
    final String DELETE_SQL = "DELETE FROM tblHoaDon WHERE MaNH = ?";
    final String SELECTALL_SQL = "SELECT MaHoaDon, MaNhanVien, MaKhachHang,TongTien,ThanhToan,NgayTaoHoaDon, TrangThaiHoaDon, GhiChuHD FROM tblHoaDon WHERE TrangThaiHoaDon = 1";
    final String SELECTBYID_SQL = "SELECT * FROM tblHoaDon WHERE MaHoaDon = ?";
    final String SELECTTT_SQL = "SELECT MaHoaDon, MaNhanVien, MaKhachHang,TongTien,ThanhToan,NgayTaoHoaDon, TrangThaiHoaDon, GhiChuHD FROM tblHoaDon WHERE TrangThaiHoaDon = 1";
    final String UPDATETT_SQL = "UPDATE tblHoaDon SET TrangThaiHoaDon = ? WHERE MaHoaDon = ? ";

    public List<HoaDon> selectAll1() {
        String SQL = "SELECT * FROM tblHoaDon ";
        return selectBySql(SQL);
    }

    public List<HoaDon> SelectHDTT(boolean tt) {
        return selectBySql(SELECTTT_SQL, tt);
    }

    public List<HoaDon> filterByTT(Integer TT) {
        String sql = "SELECT * FROM tblHoaDon WHERE MaHoaDon = ?";
        return selectBySql(sql, TT);
    }

    public void UpdateTT(Integer tt, Integer entity) {
        JDBCHelper.executeUpdate(UPDATETT_SQL, tt, entity);
    }

    public List<HoaDon> selectByKeyword(String keyword) {
        //String sql = "SELECT MaHoaDon, MaNhanVien, MaKhachHang,TongTien,ThanhToan,NgayTaoHoaDon, TrangThaiHoaDon, GhiChuHD FROM tblHoaDon WHERE MaHoaDon Like ? AND TrangThaiHoaDon = 1";
        String sql = "SELECT * FROM tblHoaDon WHERE MaHoaDon Like ? AND TrangThaiHoaDon = N'Đã Thanh Toán'";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public List<HoaDon> filterTT(String TT) {
        String sql = "SELECT * FROM tblHoaDon WHERE TrangThaiHoaDon = ?";
        return selectBySql(sql, TT);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    @Override
    public HoaDon selectById(Integer key) {
        List<HoaDon> list = selectBySql(SELECTBYID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> listCD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
                entity.setMaNhanVien(rs.getString("MaNhanVien"));
                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
                entity.setMaPhieuGiamGia(rs.getInt("MaPhieuGiamGia"));
                entity.setTongTien(rs.getFloat("TongTien"));
                entity.setThanhToan(rs.getFloat("ThanhToan"));
                entity.setNgayTaoHoaDon(rs.getDate("NgayTaoHoaDon"));
                entity.setTrangThaiHoaDon(rs.getString("TrangThaiHoaDon"));
                entity.setGhiChuHD(rs.getString("GhiChuHD"));
                listCD.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return listCD;
    }

    @Override
    public int insert(HoaDon entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaNhanVien(), entity.getMaKhachHang(), entity.getNgayTaoHoaDon());
    }

    @Override
    public int update(HoaDon entity) {
        return JDBCHelper.executeUpdate(UPDATETT_SQL, entity.getTrangThaiHoaDon(), entity.getMaHoaDon());
    }

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT MaHoaDon, MaNhanVien, MaKhachHang, NgayTaoHoaDon, TrangThaiHoaDon\n"
                + "FROM tblHoaDon WHERE TrangThaiHoaDon=N'Chưa thanh toán'";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt(1));
                hd.setMaNhanVien(rs.getString(2));
                hd.setMaKhachHang(rs.getInt(3));
                hd.setNgayTaoHoaDon(rs.getDate(4));
                hd.setTrangThaiHoaDon(rs.getString(5));
                list.add(hd);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
        }
        return null;
    }

}
