/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class SanPhamHoaDon {

    private String maCTSP;
    private String tenAo;
    private int soLuongTon;
    private String mauSac;
    private String size;
    private String loaiAo;
    private double gia;
    private double giaDaGiam;

    public SanPhamHoaDon() {
    }

    public SanPhamHoaDon(String maCTSP, String tenAo, int soLuongTon, String mauSac, String size, String loaiAo, double gia, double giaDaGiam) {
        this.maCTSP = maCTSP;
        this.tenAo = tenAo;
        this.soLuongTon = soLuongTon;
        this.mauSac = mauSac;
        this.size = size;
        this.loaiAo = loaiAo;
        this.gia = gia;
        this.giaDaGiam = giaDaGiam;
    }

    public String getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(String maCTSP) {
        this.maCTSP = maCTSP;
    }

    public String getTenAo() {
        return tenAo;
    }

    public void setTenAo(String tenAo) {
        this.tenAo = tenAo;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLoaiAo() {
        return loaiAo;
    }

    public void setLoaiAo(String loaiAo) {
        this.loaiAo = loaiAo;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getGiaDaGiam() {
        return giaDaGiam;
    }

    public void setGiaDaGiam(double giaDaGiam) {
        this.giaDaGiam = giaDaGiam;
    }

}
