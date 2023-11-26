package Entity;

import java.util.Date;

public class PhieuGiaoHang {

    private int MaPhieuGiaoHang;
    private int MaHoaDon;
    private int MaDonViVanChuyen;
    private String TenNguoiNhan;
    private String SoDienThoaiNguoiNhan;
    private String DiaChiNhanHang;
    private boolean HinhThucThanhToan;
    private Date NgayGiaoHang;
    private Date NgayNhanHang;
    private String TrangThaiGiaoHang;
    private boolean TinhTrangPGH;
    private double TongGiaTriPGH;
    private String GhiChuPGH;


    public PhieuGiaoHang() {
    }

    public PhieuGiaoHang(int MaPhieuGiaoHang, int MaHoaDon, int MaDonViVanChuyen, String TenNguoiNhan, String SoDienThoaiNguoiNhan, String DiaChiNhanHang, boolean HinhThucThanhToan, Date NgayGiaoHang, Date NgayNhanHang, String TrangThaiGiaoHang, boolean TinhTrangPGH, double TongGiaTriPGH, String GhiChuPGH) {
        this.MaPhieuGiaoHang = MaPhieuGiaoHang;
        this.MaHoaDon = MaHoaDon;
        this.MaDonViVanChuyen = MaDonViVanChuyen;
        this.TenNguoiNhan = TenNguoiNhan;
        this.SoDienThoaiNguoiNhan = SoDienThoaiNguoiNhan;
        this.DiaChiNhanHang = DiaChiNhanHang;
        this.HinhThucThanhToan = HinhThucThanhToan;
        this.NgayGiaoHang = NgayGiaoHang;
        this.NgayNhanHang = NgayNhanHang;
        this.TrangThaiGiaoHang = TrangThaiGiaoHang;
        this.TinhTrangPGH = TinhTrangPGH;
        this.TongGiaTriPGH = TongGiaTriPGH;
        this.GhiChuPGH = GhiChuPGH;
    }

    public int getMaPhieuGiaoHang() {
        return MaPhieuGiaoHang;
    }

    public void setMaPhieuGiaoHang(int MaPhieuGiaoHang) {
        this.MaPhieuGiaoHang = MaPhieuGiaoHang;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaDonViVanChuyen() {
        return MaDonViVanChuyen;
    }

    public void setMaDonViVanChuyen(int MaDonViVanChuyen) {
        this.MaDonViVanChuyen = MaDonViVanChuyen;
    }

    public String getTenNguoiNhan() {
        return TenNguoiNhan;
    }

    public void setTenNguoiNhan(String TenNguoiNhan) {
        this.TenNguoiNhan = TenNguoiNhan;
    }

    public String getSoDienThoaiNguoiNhan() {
        return SoDienThoaiNguoiNhan;
    }

    public void setSoDienThoaiNguoiNhan(String SoDienThoaiNguoiNhan) {
        this.SoDienThoaiNguoiNhan = SoDienThoaiNguoiNhan;
    }

    public String getDiaChiNhanHang() {
        return DiaChiNhanHang;
    }

    public void setDiaChiNhanHang(String DiaChiNhanHang) {
        this.DiaChiNhanHang = DiaChiNhanHang;
    }

    public boolean isHinhThucThanhToan() {
        return HinhThucThanhToan;
    }

    public void setHinhThucThanhToan(boolean HinhThucThanhToan) {
        this.HinhThucThanhToan = HinhThucThanhToan;
    }

    public Date getNgayGiaoHang() {
        return NgayGiaoHang;
    }

    public void setNgayGiaoHang(Date NgayGiaoHang) {
        this.NgayGiaoHang = NgayGiaoHang;
    }

    public Date getNgayNhanHang() {
        return NgayNhanHang;
    }

    public void setNgayNhanHang(Date NgayNhanHang) {
        this.NgayNhanHang = NgayNhanHang;
    }

    public String getTrangThaiGiaoHang() {
        return TrangThaiGiaoHang;
    }

    public void setTrangThaiGiaoHang(String TrangThaiGiaoHang) {
        this.TrangThaiGiaoHang = TrangThaiGiaoHang;
    }

    public boolean getTinhTrangPGH() {
        return TinhTrangPGH;
    }

    public void setTinhTrangPGH(boolean TinhTrangPGH) {
        this.TinhTrangPGH = TinhTrangPGH;
    }

    public double getTongGiaTriPGH() {
        return TongGiaTriPGH;
    }

    public void setTongGiaTriPGH(double TongGiaTriPGH) {
        this.TongGiaTriPGH = TongGiaTriPGH;
    }

    public String getGhiChuPGH() {
        return GhiChuPGH;
    }

    public void setGhiChuPGH(String GhiChuPGH) {
        this.GhiChuPGH = GhiChuPGH;
    }

    @Override
    public String toString() {
        return "PhieuGiaoHang{" + "MaPhieuGiaoHang=" + MaPhieuGiaoHang + ", MaHoaDon=" + MaHoaDon + ", MaDonViVanChuyen=" + MaDonViVanChuyen + ", TenNguoiNhan=" + TenNguoiNhan + ", SoDienThoaiNguoiNhan=" + SoDienThoaiNguoiNhan + ", DiaChiNhanHang=" + DiaChiNhanHang + ", HinhThucThanhToan=" + HinhThucThanhToan + ", NgayGiaoHang=" + NgayGiaoHang + ", NgayNhanHang=" + NgayNhanHang + ", TrangThaiGiaoHang=" + TrangThaiGiaoHang + ", TinhTrangPGH=" + TinhTrangPGH + ", TongGiaTriPGH=" + TongGiaTriPGH + ", GhiChuPGH=" + GhiChuPGH + '}';
    }

  
    

}
