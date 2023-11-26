/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class ThuongHieuChiTiet {
        
    
    private int maThuongHieuChiTiet ;
    private int maThuongHieu ;
    private int maXuatXu ;
    private String emailChiTiet;
    private String websiteChiTiet;
    private String soDienThoai;
    private String diaDiemBHTHCT;
    private String ghiChuTHCT ;
    private boolean trangThai ;

    public ThuongHieuChiTiet() {
    }

    public ThuongHieuChiTiet(int maThuongHieu, int maXuatXu, String emailChiTiet, String websiteChiTiet, String soDienThoai, String diaDiemBHTHCT, String ghiChuTHCT, boolean trangThai) {
        this.maThuongHieu = maThuongHieu;
        this.maXuatXu = maXuatXu;
        this.emailChiTiet = emailChiTiet;
        this.websiteChiTiet = websiteChiTiet;
        this.soDienThoai = soDienThoai;
        this.diaDiemBHTHCT = diaDiemBHTHCT;
        this.ghiChuTHCT = ghiChuTHCT;
        this.trangThai = trangThai;
    }

    
    
    public ThuongHieuChiTiet(int maThuongHieuChiTiet, int maThuongHieu, int maXuatXu, String emailChiTiet, String websiteChiTiet, String soDienThoai, String diaDiemBHTHCT, String ghiChuTHCT, boolean trangThai) {
        this.maThuongHieuChiTiet = maThuongHieuChiTiet;
        this.maThuongHieu = maThuongHieu;
        this.maXuatXu = maXuatXu;
        this.emailChiTiet = emailChiTiet;
        this.websiteChiTiet = websiteChiTiet;
        this.soDienThoai = soDienThoai;
        this.diaDiemBHTHCT = diaDiemBHTHCT;
        this.ghiChuTHCT = ghiChuTHCT;
        this.trangThai = trangThai;
    }

    public int getMaThuongHieuChiTiet() {
        return maThuongHieuChiTiet;
    }

    public void setMaThuongHieuChiTiet(int maThuongHieuChiTiet) {
        this.maThuongHieuChiTiet = maThuongHieuChiTiet;
    }


    public int getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public int getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(int maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getEmailChiTiet() {
        return emailChiTiet;
    }

    public void setEmailChiTiet(String emailChiTiet) {
        this.emailChiTiet = emailChiTiet;
    }

    public String getWebsiteChiTiet() {
        return websiteChiTiet;
    }

    public void setWebsiteChiTiet(String websiteChiTiet) {
        this.websiteChiTiet = websiteChiTiet;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaDiemBHTHCT() {
        return diaDiemBHTHCT;
    }

    public void setDiaDiemBHTHCT(String diaDiemBHTHCT) {
        this.diaDiemBHTHCT = diaDiemBHTHCT;
    }

    public String getGhiChuTHCT() {
        return ghiChuTHCT;
    }

    public void setGhiChuTHCT(String ghiChuTHCT) {
        this.ghiChuTHCT = ghiChuTHCT;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    
    
    
         
}
