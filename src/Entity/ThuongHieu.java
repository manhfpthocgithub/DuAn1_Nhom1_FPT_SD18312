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
public class ThuongHieu {

    private int maThuongHieu;
    private String tenThuongHieu;
    private String anhThuongHieu;
    private String ngayTao;
    private String ghiChuTH;
    private boolean trangThai;

    public ThuongHieu() {
    }

    
    
    public ThuongHieu(int maThuongHieu, String tenThuongHieu, String anhThuongHieu, String ngayTao, String ghiChuTH, boolean trangThai) {
        this.maThuongHieu = maThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
        this.anhThuongHieu = anhThuongHieu;
        this.ngayTao = ngayTao;
        this.ghiChuTH = ghiChuTH;
        this.trangThai = trangThai;
    }

    public int getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getAnhThuongHieu() {
        return anhThuongHieu;
    }

    public void setAnhThuongHieu(String anhThuongHieu) {
        this.anhThuongHieu = anhThuongHieu;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }


    public String getGhiChuTH() {
        return ghiChuTH;
    }

    public void setGhiChuTH(String ghiChuTH) {
        this.ghiChuTH = ghiChuTH;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    
    @Override
    public String toString() {
        return this.tenThuongHieu;
    }
    
    

}
