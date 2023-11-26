/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

public class PhieuDoi {
    private int MaPD;
    private int MaKH;
    private int MaHD;
    private String NgayDoiHang;
    private float TienThanhToan;
    private String LyDoDoiHang;

    public PhieuDoi() {
    }

    public PhieuDoi(int MaPD, int MaKH, int MaHD, String NgayDoiHang, float TienThanhToan, String LyDoDoiHang) {
        this.MaPD = MaPD;
        this.MaKH = MaKH;
        this.MaHD = MaHD;
        this.NgayDoiHang = NgayDoiHang;
        this.TienThanhToan = TienThanhToan;
        this.LyDoDoiHang = LyDoDoiHang;
    }

    public int getMaPD() {
        return MaPD;
    }

    public void setMaPD(int MaPD) {
        this.MaPD = MaPD;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
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
        return "PhieuDoi{" + "MaPD=" + MaPD + ", MaKH=" + MaKH + ", MaHD=" + MaHD + ", NgayDoiHang=" + NgayDoiHang + ", TienThanhToan=" + TienThanhToan + ", LyDoDoiHang=" + LyDoDoiHang + '}';
    }
    
    
}

//    MaPhieuDoiHang INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
//    MaKhachHang INT NOT NULL, --FK
//    MaHoaDon INT NOT NULL, --FK
//    NgayDoiHang DATE NOT NULL,
//    TienDaThanhToan FLOAT NOT NULL,
//    LyDoDoiHang NVARCHAR(MAX) NULL,

