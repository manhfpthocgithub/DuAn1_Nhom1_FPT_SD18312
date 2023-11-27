package Entity;

public class HoaDonChiTiet {

    private int MaHDCT;
    private int MaHD;
    private String MaSPCT;
    private int SoLuongHDCT;
    private float DonGiaHDCT;
    private float ThanhTien;
    private boolean TrangThaiHDCT;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int MaHDCT, int MaHD, String MaSPCT, int SoLuongHDCT, float DonGiaHDCT, float ThanhTien, boolean TrangThaiHDCT) {
        this.MaHDCT = MaHDCT;
        this.MaHD = MaHD;
        this.MaSPCT = MaSPCT;
        this.SoLuongHDCT = SoLuongHDCT;
        this.DonGiaHDCT = DonGiaHDCT;
        this.ThanhTien = ThanhTien;
        this.TrangThaiHDCT = TrangThaiHDCT;
    }

    public HoaDonChiTiet(int MaHD, String MaSPCT, int SoLuongHDCT, float DonGiaHDCT, float ThanhTien, boolean TrangThaiHDCT) {
        this.MaHD = MaHD;
        this.MaSPCT = MaSPCT;
        this.SoLuongHDCT = SoLuongHDCT;
        this.DonGiaHDCT = DonGiaHDCT;
        this.ThanhTien = ThanhTien;
        this.TrangThaiHDCT = TrangThaiHDCT;
    }

    
    public int getMaHDCT() {
        return MaHDCT;
    }

    public void setMaHDCT(int MaHDCT) {
        this.MaHDCT = MaHDCT;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(String MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public int getSoLuongHDCT() {
        return SoLuongHDCT;
    }

    public void setSoLuongHDCT(int SoLuongHDCT) {
        this.SoLuongHDCT = SoLuongHDCT;
    }

    public float getDonGiaHDCT() {
        return DonGiaHDCT;
    }

    public void setDonGiaHDCT(float DonGiaHDCT) {
        this.DonGiaHDCT = DonGiaHDCT;
    }

    public boolean isTrangThaiHDCT() {
        return TrangThaiHDCT;
    }

    public void setTrangThaiHDCT(boolean TrangThaiHDCT) {
        this.TrangThaiHDCT = TrangThaiHDCT;
    }

    

   

}
