/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class ChatLieu_LoaiAo {
    private int maChatLieuLoaiAo ;
    private int maLoaiAo ;
    private int maChatLieu ;
    private boolean trangThai;

    public ChatLieu_LoaiAo() {
    }

    public ChatLieu_LoaiAo(int maChatLieuLoaiAo, int maLoaiAo, int maChatLieu, boolean trangThai) {
        this.maChatLieuLoaiAo = maChatLieuLoaiAo;
        this.maLoaiAo = maLoaiAo;
        this.maChatLieu = maChatLieu;
        this.trangThai = trangThai;
    }

    public int getMaChatLieuLoaiAo() {
        return maChatLieuLoaiAo;
    }

    public void setMaChatLieuLoaiAo(int maChatLieuLoaiAo) {
        this.maChatLieuLoaiAo = maChatLieuLoaiAo;
    }


    public int getMaLoaiAo() {
        return maLoaiAo;
    }

    public void setMaLoaiAo(int maLoaiAo) {
        this.maLoaiAo = maLoaiAo;
    }

    public int getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(int maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
