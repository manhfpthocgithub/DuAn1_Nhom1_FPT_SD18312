/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class AoKhoacMuaDong {

    private String maAoKhoac;
    private String tenAoKhoac;
    private String ngayNhap;
    private String ngaySua;
    private int maThuongHieuChiTiet;
    private int maChatLieuLoaiAo;
    private String tenPhongCach;
    private String diaChiNhapHang;
    private String ghiChu;
    private String trangThai;

    public AoKhoacMuaDong() {
    }

    public AoKhoacMuaDong(String maAoKhoac, String tenAoKhoac, String ngayNhap, String ngaySua, int maThuongHieuChiTiet, int maChatLieuLoaiAo, String tenPhongCach, String diaChiNhapHang, String ghiChu, String trangThai) {
        this.maAoKhoac = maAoKhoac;
        this.tenAoKhoac = tenAoKhoac;
        this.ngayNhap = ngayNhap;
        this.ngaySua = ngaySua;
        this.maThuongHieuChiTiet = maThuongHieuChiTiet;
        this.maChatLieuLoaiAo = maChatLieuLoaiAo;
        this.tenPhongCach = tenPhongCach;
        this.diaChiNhapHang = diaChiNhapHang;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public String getMaAoKhoac() {
        return maAoKhoac;
    }

    public void setMaAoKhoac(String maAoKhoac) {
        this.maAoKhoac = maAoKhoac;
    }

    public String getTenAoKhoac() {
        return tenAoKhoac;
    }

    public void setTenAoKhoac(String tenAoKhoac) {
        this.tenAoKhoac = tenAoKhoac;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getMaThuongHieuChiTiet() {
        return maThuongHieuChiTiet;
    }

    public void setMaThuongHieuChiTiet(int maThuongHieuChiTiet) {
        this.maThuongHieuChiTiet = maThuongHieuChiTiet;
    }

    public int getMaChatLieuLoaiAo() {
        return maChatLieuLoaiAo;
    }

    public void setMaChatLieuLoaiAo(int maChatLieuLoaiAo) {
        this.maChatLieuLoaiAo = maChatLieuLoaiAo;
    }

    public String getTenPhongCach() {
        return tenPhongCach;
    }

    public void setTenPhongCach(String tenPhongCach) {
        this.tenPhongCach = tenPhongCach;
    }

    public String getDiaChiNhapHang() {
        return diaChiNhapHang;
    }

    public void setDiaChiNhapHang(String diaChiNhapHang) {
        this.diaChiNhapHang = diaChiNhapHang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return this.tenAoKhoac;
    }
    
    

}
