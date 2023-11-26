/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class LoaiAo {

    private int maLoaiAo;
    private String tenLoaiAo;
    private boolean trangThai;

    public LoaiAo() {
    }

    public LoaiAo(String tenLoaiAo, boolean trangThai) {
        this.tenLoaiAo = tenLoaiAo;
        this.trangThai = trangThai;
    }

    
    public LoaiAo(int maLoaiAo, String tenLoaiAo, boolean trangThai) {
        this.maLoaiAo = maLoaiAo;
        this.tenLoaiAo = tenLoaiAo;
        this.trangThai = trangThai;
    }

    public int getMaLoaiAo() {
        return maLoaiAo;
    }

    public void setMaLoaiAo(int maLoaiAo) {
        this.maLoaiAo = maLoaiAo;
    }

    public String getTenLoaiAo() {
        return tenLoaiAo;
    }

    public void setTenLoaiAo(String tenLoaiAo) {
        this.tenLoaiAo = tenLoaiAo;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   
    @Override
    public String toString() {
        return this.tenLoaiAo;
    }

}
