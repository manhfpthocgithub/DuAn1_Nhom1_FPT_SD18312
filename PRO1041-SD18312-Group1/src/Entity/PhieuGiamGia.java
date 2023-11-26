/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;


public class PhieuGiamGia {
    private int MaPhieuGiamGia;
    private String TenPGG;
    private int GiaTriPGG;
    private double TongTienHang;
    private boolean TrangThaiPGG;
    private String NgayTao;
    private String NgayHetHan;
    private String GhiChu;
    private String MaNV; 

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(int MaPhieuGiamGia, String TenPGG, int GiaTriPGG, double TongTienHang, boolean TrangThaiPGG, String NgayTao, String NgayHetHan, String GhiChu, String MaNV) {
        this.MaPhieuGiamGia = MaPhieuGiamGia;
        this.TenPGG = TenPGG;
        this.GiaTriPGG = GiaTriPGG;       
        this.TongTienHang = TongTienHang;
        this.TrangThaiPGG = TrangThaiPGG;
        this.NgayTao = NgayTao;
        this.NgayHetHan = NgayHetHan;
        this.GhiChu = GhiChu;
        this.MaNV = MaNV;
    }

    public int getMaPhieuGiamGia() {
        return MaPhieuGiamGia;
    }

    public void setMaPhieuGiamGia(int MaPhieuGiamGia) {
        this.MaPhieuGiamGia = MaPhieuGiamGia;
    }

    public String getTenPGG() {
        return TenPGG;
    }

    public void setTenPGG(String TenPGG) {
        this.TenPGG = TenPGG;
    }

    public int getGiaTriPGG() {
        return GiaTriPGG;
    }

    public void setGiaTriPGG(int GiaTriPGG) {
        this.GiaTriPGG = GiaTriPGG;
    }

    public double getTongTienHang() {
        return TongTienHang;
    }

    public void setTongTienHang(double TongTienHang) {
        this.TongTienHang = TongTienHang;
    }

    public boolean isTrangThaiPGG() {
        return TrangThaiPGG;
    }

    public void setTrangThaiPGG(boolean TrangThaiPGG) {
        this.TrangThaiPGG = TrangThaiPGG;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(String NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String GiaTriPGG(){
        if(GiaTriPGG >1000){
            return GiaTriPGG + ".đ"; 
        }else if(GiaTriPGG <100){
            return GiaTriPGG +"%";
        }else{
            return GiaTriPGG + ".đ";
        }   
    }

    @Override
    public String toString() {
        return "PhieuGiamGia{" + "MaPhieuGiamGia=" + MaPhieuGiamGia + ", TenPGG=" + TenPGG + ", GiaTriPGG=" + GiaTriPGG + ", TongTienHang=" + TongTienHang + ", TrangThaiPGG=" + TrangThaiPGG + ", NgayTao=" + NgayTao + ", NgayHetHan=" + NgayHetHan + ", GhiChu=" + GhiChu + ", MaNV=" + MaNV + '}';
    }
}


