/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author BANG
 */
public class LichSuPGG {
    private int maLichSu;
    private int maphieugiamgia;
    private int giatripgg;
    private double tongtienhang;
    private String NgaySuDung;
    private boolean TrangThaiLS;
    private int maHoaDon;
    private String ghichu;
    

    public LichSuPGG() {
    }

    public LichSuPGG(int maLichSu, int maphieugiamgia, int giatripgg, double tongtienhang, String NgaySuDung, boolean TrangThaiLS, int maHoaDon, String ghichu) {
        this.maLichSu = maLichSu;
        this.maphieugiamgia = maphieugiamgia;
        this.giatripgg = giatripgg;
        this.tongtienhang = tongtienhang;
        this.NgaySuDung = NgaySuDung;
        this.TrangThaiLS = TrangThaiLS;
        this.maHoaDon = maHoaDon;
        this.ghichu = ghichu;
    }

    public int getMaLichSu() {
        return maLichSu;
    }

    public void setMaLichSu(int maLichSu) {
        this.maLichSu = maLichSu;
    }

    public int getMaphieugiamgia() {
        return maphieugiamgia;
    }

    public void setMaphieugiamgia(int maphieugiamgia) {
        this.maphieugiamgia = maphieugiamgia;
    }

    public int getGiatripgg() {
        return giatripgg;
    }

    public void setGiatripgg(int giatripgg) {
        this.giatripgg = giatripgg;
    }

    public double getTongtienhang() {
        return tongtienhang;
    }

    public void setTongtienhang(double tongtienhang) {
        this.tongtienhang = tongtienhang;
    }

    public String getNgaySuDung() {
        return NgaySuDung;
    }

    public void setNgaySuDung(String NgaySuDung) {
        this.NgaySuDung = NgaySuDung;
    }

    public boolean isTrangThaiLS() {
        return TrangThaiLS;
    }

    public void setTrangThaiLS(boolean TrangThaiLS) {
        this.TrangThaiLS = TrangThaiLS;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
    

    @Override
    public String toString() {
        return "LichSuPGGDAO{" + "maLichSu=" + maLichSu + ", maphieugiamgia=" + maphieugiamgia + ", NgaySuDung=" + NgaySuDung + ", TrangThaiLS=" + TrangThaiLS + ", maHoaDon=" + maHoaDon + ", ghichu=" + ghichu + '}';
    }
}
