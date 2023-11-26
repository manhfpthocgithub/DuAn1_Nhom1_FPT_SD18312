/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class TichDiem {
    private int maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String diaChi;
    private Float tongTienMua;
    private int tichDiem;

    public TichDiem() {
    }

    public TichDiem(int maKhachHang, String tenKhachHang, String soDienThoai, String diaChi, Float tongTienMua, int tichDiem) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.tongTienMua = tongTienMua;
        this.tichDiem = tichDiem;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Float getTongTienMua() {
        return tongTienMua;
    }

    public void setTongTienMua(Float tongTienMua) {
        this.tongTienMua = tongTienMua;
    }

    public int getTichDiem() {
        return tichDiem;
    }

    public void setTichDiem(int tichDiem) {
        this.tichDiem = tichDiem;
    }
    
}
