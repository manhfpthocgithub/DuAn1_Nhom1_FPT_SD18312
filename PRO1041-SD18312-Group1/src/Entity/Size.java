/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class Size {

    private int maSize;
    private String tenSize;
    private boolean trangThai;

    public Size() {
    }

    public Size(String tenSize, boolean trangThai) {
        this.tenSize = tenSize;
        this.trangThai = trangThai;
    }

    public Size(int maSize, String tenSize, boolean trangThai) {
        this.maSize = maSize;
        this.tenSize = tenSize;
        this.trangThai = trangThai;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return this.tenSize ;
    }
    

}
