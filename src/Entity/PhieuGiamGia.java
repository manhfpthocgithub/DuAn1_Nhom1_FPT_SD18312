/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

public class PhieuGiamGia {

    private int MaPhieuGiamGia;
    private String TenPGG;
    private int GiaTriPGG;
    private double TongTienHang;
    private boolean TrangThaiPGG;
    private Date NgayTao;
    private Date NgayHetHan;
    private String GhiChu;
    private String MaNV;

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(int MaPhieuGiamGia, String TenPGG, int GiaTriPGG, double TongTienHang, boolean TrangThaiPGG, Date NgayTao, Date NgayHetHan, String GhiChu, String MaNV) {
        this.MaPhieuGiamGia = MaPhieuGiamGia;
        this.TenPGG = TenPGG;
        this.GiaTriPGG = GiaTriPGG;
        this.TongTienHang = TongTienHang;
        this.TrangThaiPGG = TrangThaiPGG;
        this.NgayTao = NgayTao;
        this.NgayHetHan = NgayHetHan;
        this.GhiChu = GhiChu;
        this.MaNV = MaNV;
    }

    public int getMaPhieuGiamGia() {
        return MaPhieuGiamGia;
    }

    public void setMaPhieuGiamGia(int MaPhieuGiamGia) {
        this.MaPhieuGiamGia = MaPhieuGiamGia;
    }

    public String getTenPGG() {
        return TenPGG;
    }

    public void setTenPGG(String TenPGG) {
        this.TenPGG = TenPGG;
    }

    public int getGiaTriPGG() {
        return GiaTriPGG;
    }

    public void setGiaTriPGG(int GiaTriPGG) {
        this.GiaTriPGG = GiaTriPGG;
    }

    public double getTongTienHang() {
        return TongTienHang;
    }

    public void setTongTienHang(double TongTienHang) {
        this.TongTienHang = TongTienHang;
    }

    public boolean isTrangThaiPGG() {
        return TrangThaiPGG;
    }

    public void setTrangThaiPGG(boolean TrangThaiPGG) {
        this.TrangThaiPGG = TrangThaiPGG;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(Date NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    @Override
    public String toString() {
        return ""+GiaTriPGG;
    }
}
