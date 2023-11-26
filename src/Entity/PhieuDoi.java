/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

public class PhieuDoi {
    private int MaPhieuDoi;
    private int MaKhachHang;
    private int MaHoaDon;
    private String NgayDoiHang;
    private float TienThanhToan;
    private String LyDoDoiHang;

    public PhieuDoi() {
    }

    public PhieuDoi(int MaPhieuDoi, int MaKhachHang, int MaHoaDon, String NgayDoiHang, float TienThanhToan, String LyDoDoiHang) {
        this.MaPhieuDoi = MaPhieuDoi;
        this.MaKhachHang = MaKhachHang;
        this.MaHoaDon = MaHoaDon;
        this.NgayDoiHang = NgayDoiHang;
        this.TienThanhToan = TienThanhToan;
        this.LyDoDoiHang = LyDoDoiHang;
    }

    public int getMaPhieuDoi() {
        return MaPhieuDoi;
    }

    public void setMaPhieuDoi(int MaPhieuDoi) {
        this.MaPhieuDoi = MaPhieuDoi;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getNgayDoiHang() {
        return NgayDoiHang;
    }

    public void setNgayDoiHang(String NgayDoiHang) {
        this.NgayDoiHang = NgayDoiHang;
    }

    public float getTienThanhToan() {
        return TienThanhToan;
    }

    public void setTienThanhToan(float TienThanhToan) {
        this.TienThanhToan = TienThanhToan;
    }

    public String getLyDoDoiHang() {
        return LyDoDoiHang;
    }

    public void setLyDoDoiHang(String LyDoDoiHang) {
        this.LyDoDoiHang = LyDoDoiHang;
    }

    @Override
    public String toString() {
        return "PhieuDoi{" + "MaPhieuDoi=" + MaPhieuDoi + ", MaKhachHang=" + MaKhachHang + ", MaHoaDon=" + MaHoaDon + ", NgayDoiHang=" + NgayDoiHang + ", TienThanhToan=" + TienThanhToan + ", LyDoDoiHang=" + LyDoDoiHang + '}';
    }

    
    
    
}

//    MaPhieuDoiHang INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
//    MaKhachHang INT NOT NULL, --FK
//    MaHoaDon INT NOT NULL, --FK
//    NgayDoiHang DATE NOT NULL,
//    TienDaThanhToan FLOAT NOT NULL,
//    LyDoDoiHang NVARCHAR(MAX) NULL,

