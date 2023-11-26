/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Email {

    private int maEmail;
    private int maKhachHang;
    private int maPhieuGiamGia;
    private String tieuDeEmail;
    private String noiDungEmail;
    private String tenKhachHang;
    private String email;
    private float tongMua;
    private int tichDiem;

    public Email() {
    }

    public Email(int maEmail, int maKhachHang, int maPhieuGiamGia, String tieuDeEmail, String noiDungEmail, String tenKhachHang, String email, float tongMua, int tichDiem) {
        this.maEmail = maEmail;
        this.maKhachHang = maKhachHang;
        this.maPhieuGiamGia = maPhieuGiamGia;
        this.tieuDeEmail = tieuDeEmail;
        this.noiDungEmail = noiDungEmail;
        this.tenKhachHang = tenKhachHang;
        this.email = email;
        this.tongMua = tongMua;
        this.tichDiem = tichDiem;
    }

    public int getMaEmail() {
        return maEmail;
    }

    public void setMaEmail(int maEmail) {
        this.maEmail = maEmail;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaPhieuGiamGia() {
        return maPhieuGiamGia;
    }

    public void setMaPhieuGiamGia(int maPhieuGiamGia) {
        this.maPhieuGiamGia = maPhieuGiamGia;
    }

    public String getTieuDeEmail() {
        return tieuDeEmail;
    }

    public void setTieuDeEmail(String tieuDeEmail) {
        this.tieuDeEmail = tieuDeEmail;
    }

    public String getNoiDungEmail() {
        return noiDungEmail;
    }

    public void setNoiDungEmail(String noiDungEmail) {
        this.noiDungEmail = noiDungEmail;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTongMua() {
        return tongMua;
    }

    public void setTongMua(float tongMua) {
        this.tongMua = tongMua;
    }

    public int getTichDiem() {
        return tichDiem;
    }

    public void setTichDiem(int tichDiem) {
        this.tichDiem = tichDiem;
    }

   
   
   
}

