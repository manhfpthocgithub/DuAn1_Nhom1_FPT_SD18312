/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class MauSac {

    private int maMau;
    private String tenMauSac;
    private boolean trangThai;

    public MauSac() {
    }

    public MauSac(String tenMauSac, boolean trangThai) {
        this.tenMauSac = tenMauSac;
        this.trangThai = trangThai;
    }

    
    public MauSac(int maMau, String tenMauSac, boolean trangThai) {
        this.maMau = maMau;
        this.tenMauSac = tenMauSac;
        this.trangThai = trangThai;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return this.tenMauSac;
    }

    
}
