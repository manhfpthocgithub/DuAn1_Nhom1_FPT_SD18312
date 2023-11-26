package Entity;

public class HoaDonChiTiet {
    private int MaHDCT;
    private int MaHD;
    private String MaAoKhoac;
    private int SoLuongHDCT;
    private float DonGiaHDCT;
    private boolean TrangThaiHDCT;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int MaHDCT, int MaHD, String MaAoKhoac, int SoLuongHDCT, float DonGiaHDCT, boolean TrangThaiHDCT) {
        this.MaHDCT = MaHDCT;
        this.MaHD = MaHD;
        this.MaAoKhoac = MaAoKhoac;
        this.SoLuongHDCT = SoLuongHDCT;
        this.DonGiaHDCT = DonGiaHDCT;
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

    public String getMaAoKhoac() {
        return MaAoKhoac;
    }

    public void setMaAoKhoac(String MaAoKhoac) {
        this.MaAoKhoac = MaAoKhoac;
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

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "MaHDCT=" + MaHDCT + ", MaHD=" + MaHD + ", MaAoKhoac=" + MaAoKhoac + ", SoLuongHDCT=" + SoLuongHDCT + ", DonGiaHDCT=" + DonGiaHDCT + ", TrangThaiHDCT=" + TrangThaiHDCT + '}';
    }
    
    
    
}
