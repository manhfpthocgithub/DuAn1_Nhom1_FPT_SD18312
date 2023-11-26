/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class XuatXu {

    private int maXuatXu;
    private String xuatXu;
    private boolean trangThai;

    public XuatXu() {
    }

    public XuatXu(String xuatXu, boolean trangThai) {
        this.xuatXu = xuatXu;
        this.trangThai = trangThai;
    }

    
    public XuatXu(int maXuatXu, String xuatXu, boolean trangThai) {
        this.maXuatXu = maXuatXu;
        this.xuatXu = xuatXu;
        this.trangThai = trangThai;
    }

    public int getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(int maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return this.getXuatXu();
    }
    
    

}
