/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Pico123
 */
public class NhanVien {
    private String maNV;
    private String tenNV;
    private boolean gioitinh;
    private Date ngaysinh;
    private String soCCCD;
    private String soDT;
    private String email;
    private String diachi;
    private boolean trangthai;
    private String ghichu;
    private int soluong;

    public NhanVien() {
    }
    

//    public NhanVien(String maNV, String tenNV, boolean gioitinh, Date ngaysinh, String soCCCD, String soDT, String email, String diachi, boolean trangthai, String ghichu) {
//        this.maNV = maNV;
//        this.tenNV = tenNV;
//        this.gioitinh = gioitinh;
//        this.ngaysinh = ngaysinh;
//        this.soCCCD = soCCCD;
//        this.soDT = soDT;
//        this.email = email;
//        this.diachi = diachi;
//        this.trangthai = trangthai;
//        this.ghichu = ghichu;
//    }
//
//    public String getMaNV() {
//        return maNV;
//    }
//
//    public void setMaNV(String maNV) {
//        this.maNV = maNV;
//    }
//
//    public String getTenNV() {
//        return tenNV;
//    }
//
//    public void setTenNV(String tenNV) {
//        this.tenNV = tenNV;
//    }
//
//    public boolean isGioitinh() {
//        return gioitinh;
//    }
//
//    public void setGioitinh(boolean gioitinh) {
//        this.gioitinh = gioitinh;
//    }
//
//    public Date getNgaysinh() {
//        return ngaysinh;
//    }
//
//    public void setNgaysinh(Date ngaysinh) {
//        this.ngaysinh = ngaysinh;
//    }
//
//    public String getSoCCCD() {
//        return soCCCD;
//    }
//
//    public void setSoCCCD(String soCCCD) {
//        this.soCCCD = soCCCD;
//    }
//
//    public String getSoDT() {
//        return soDT;
//    }
//
//    public void setSoDT(String soDT) {
//        this.soDT = soDT;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getDiachi() {
//        return diachi;
//    }
//
//    public void setDiachi(String diachi) {
//        this.diachi = diachi;
//    }
//
//    public boolean isTrangthai() {
//        return trangthai;
//    }
//
//    public void setTrangthai(boolean trangthai) {
//        this.trangthai = trangthai;
//    }
//
//    public String getGhichu() {
//        return ghichu;
//    }
//
//    public void setGhichu(String ghichu) {
//        this.ghichu = ghichu;
//    }
//
//    @Override
//    public String toString() {
//        return "NhanVien{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + ", soCCCD=" + soCCCD + ", soDT=" + soDT + ", email=" + email + ", diachi=" + diachi + ", trangthai=" + trangthai + ", ghichu=" + ghichu + '}';
//    }

    public NhanVien(String maNV, String tenNV, boolean gioitinh, Date ngaysinh, String soCCCD, String soDT, String email, String diachi, boolean trangthai, String ghichu, int soluong) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.soCCCD = soCCCD;
        this.soDT = soDT;
        this.email = email;
        this.diachi = diachi;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
        this.soluong = soluong;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + ", soCCCD=" + soCCCD + ", soDT=" + soDT + ", email=" + email + ", diachi=" + diachi + ", trangthai=" + trangthai + ", ghichu=" + ghichu + ", soluong=" + soluong + '}';
    }

    

    
    
}
