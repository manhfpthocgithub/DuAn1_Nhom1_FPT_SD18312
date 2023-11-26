/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPham {
    private String maSPCT ;
    private String maAoKhoac ;
    private Double giaAK ;
    private int soLuongAK ;
    private int maSize ;
    private int maMauSac ;
    private String tenAnh ;
    private String moTa ;
    private boolean  trangThai ;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String maSPCT, String maAoKhoac, Double giaAK, int soLuongAK, int maSize, int maMauSac, String tenAnh, String moTa, boolean  trangThai) {
        this.maSPCT = maSPCT;
        this.maAoKhoac = maAoKhoac;
        this.giaAK = giaAK;
        this.soLuongAK = soLuongAK;
        this.maSize = maSize;
        this.maMauSac = maMauSac;
        this.tenAnh = tenAnh;
        this.moTa = moTa;
        this.trangThai = trangThai ;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getMaAoKhoac() {
        return maAoKhoac;
    }

    public void setMaAoKhoac(String maAoKhoac) {
        this.maAoKhoac = maAoKhoac;
    }

    public Double getGiaAK() {
        return giaAK;
    }

    public void setGiaAK(Double giaAK) {
        this.giaAK = giaAK;
    }

    public int getSoLuongAK() {
        return soLuongAK;
    }

    public void setSoLuongAK(int soLuongAK) {
        this.soLuongAK = soLuongAK;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(int maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
}
