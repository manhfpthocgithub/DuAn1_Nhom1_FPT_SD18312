/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class HoaDon {
    private int maHoaDon;
    private String maNhanVien;
    private int maKhachHang;
    private int maPhieuGiamGia;
    private float tongTien;
    private float thanhToan;
    private Date ngayTaoHoaDon;
    private String trangThaiHoaDon;
    private String ghiChuHD;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, String maNhanVien, int maKhachHang, Date ngayTaoHoaDon, String trangThaiHoaDon) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.trangThaiHoaDon = trangThaiHoaDon;
    }
    

    public HoaDon(int maHoaDon, String maNhanVien, int maKhachHang, int maPhieuGiamGia, float tongTien, float thanhToan, Date ngayTaoHoaDon, String trangThaiHoaDon, String ghiChuHD) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.maPhieuGiamGia = maPhieuGiamGia;
        this.tongTien = tongTien;
        this.thanhToan = thanhToan;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.trangThaiHoaDon = trangThaiHoaDon;
        this.ghiChuHD = ghiChuHD;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaPhieuGiamGia() {
        return maPhieuGiamGia;
    }

    public void setMaPhieuGiamGia(int maPhieuGiamGia) {
        this.maPhieuGiamGia = maPhieuGiamGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public float getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(float thanhToan) {
        this.thanhToan = thanhToan;
    }

    public Date getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(Date ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public String getTrangThaiHoaDon() {
        return trangThaiHoaDon;
    }

    public void setTrangThaiHoaDon(String trangThaiHoaDon) {
        this.trangThaiHoaDon = trangThaiHoaDon;
    }

    public String getGhiChuHD() {
        return ghiChuHD;
    }

    public void setGhiChuHD(String ghiChuHD) {
        this.ghiChuHD = ghiChuHD;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang + ", maPhieuGiamGia=" + maPhieuGiamGia + ", tongTien=" + tongTien + ", thanhToan=" + thanhToan + ", ngayTaoHoaDon=" + ngayTaoHoaDon + ", trangThaiHoaDon=" + trangThaiHoaDon + ", ghiChuHD=" + ghiChuHD + '}';
    }

    
}
