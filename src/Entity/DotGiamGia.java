/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author haila
 */
public class DotGiamGia {
    private String MaDotGiamGia;
    private String MaNhanVien;
    private String TenDotGiamGia;
    private int GiaTriDGG;
    private String NgayBatDau;
    private String NgayKetThuc;
    private String SanPhamDGG;
    private boolean TrangThai;
    private String GhiChuDGG;

    public DotGiamGia() {
    }

    public DotGiamGia(String MaDotGiamGia, String MaNhanVien, String TenDotGiamGia, int GiaTriDGG, String NgayBatDau, String NgayKetThuc,String SanPhamDGG, boolean TrangThai, String GhiChuDGG) {
        this.MaDotGiamGia = MaDotGiamGia;
        this.MaNhanVien = MaNhanVien;
        this.TenDotGiamGia = TenDotGiamGia;
        this.GiaTriDGG = GiaTriDGG;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.SanPhamDGG = SanPhamDGG;
        this.TrangThai = TrangThai;
        this.GhiChuDGG = GhiChuDGG;
    }

    public String getMaDotGiamGia() {
        return MaDotGiamGia;
    }

    public void setMaDotGiamGia(String MaDotGiamGia) {
        this.MaDotGiamGia = MaDotGiamGia;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenDotGiamGia() {
        return TenDotGiamGia;
    }

    public void setTenDotGiamGia(String TenDotGiamGia) {
        this.TenDotGiamGia = TenDotGiamGia;
    }

    public int getGiaTriDGG() {
        return GiaTriDGG;
    }

    public void setGiaTriDGG(int GiaTriDGG) {
        this.GiaTriDGG = GiaTriDGG;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChuDGG() {
        return GhiChuDGG;
    }

    public void setGhiChuDGG(String GhiChuDGG) {
        this.GhiChuDGG = GhiChuDGG;
    }
    public String getSanPhamDGG(){
        return SanPhamDGG;
    }
    public void setSanPhamDGG(String SanPhamDGG){
        this.SanPhamDGG = SanPhamDGG;
    }
    @Override
    public String toString() {
        return "DotGiamGia{" + "MaDotGiamGia=" + MaDotGiamGia + ", MaNhanVien=" + MaNhanVien + ", TenDotGiamGia=" + TenDotGiamGia + ", GiaTriDGG=" + GiaTriDGG + ", NgayBatDau=" + NgayBatDau + ", NgayKetThuc=" + NgayKetThuc + ", TrangThai=" + TrangThai + ", GhiChuDGG=" + GhiChuDGG + '}';
    }
    
    
}
