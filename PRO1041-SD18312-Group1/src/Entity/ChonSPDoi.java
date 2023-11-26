package Entity;

public class ChonSPDoi {
    private String MaSPCT;
    private String MaAoKhoac;
    private String TenAoKhoac;
    private String TenPhongCach;
    private String TenSize;
    private float GiaAoKhoac;
    private int SoLuong;

    public ChonSPDoi() {
    }

    public ChonSPDoi(String MaSPCT, String MaAoKhoac, String TenAoKhoac, String TenPhongCach, String TenSize, float GiaAoKhoac, int SoLuong) {
        this.MaSPCT = MaSPCT;
        this.MaAoKhoac = MaAoKhoac;
        this.TenAoKhoac = TenAoKhoac;
        this.TenPhongCach = TenPhongCach;
        this.TenSize = TenSize;
        this.GiaAoKhoac = GiaAoKhoac;
        this.SoLuong = SoLuong;
    }

    public String getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(String MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public String getMaAoKhoac() {
        return MaAoKhoac;
    }

    public void setMaAoKhoac(String MaAoKhoac) {
        this.MaAoKhoac = MaAoKhoac;
    }

    public String getTenAoKhoac() {
        return TenAoKhoac;
    }

    public void setTenAoKhoac(String TenAoKhoac) {
        this.TenAoKhoac = TenAoKhoac;
    }

    public String getTenPhongCach() {
        return TenPhongCach;
    }

    public void setTenPhongCach(String TenPhongCach) {
        this.TenPhongCach = TenPhongCach;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }

    public float getGiaAoKhoac() {
        return GiaAoKhoac;
    }

    public void setGiaAoKhoac(float GiaAoKhoac) {
        this.GiaAoKhoac = GiaAoKhoac;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    @Override
    public String toString() {
        return "ChonSPDoi{" + "MaSPCT=" + MaSPCT + ", MaAoKhoac=" + MaAoKhoac + ", TenAoKhoac=" + TenAoKhoac + ", TenPhongCach=" + TenPhongCach + ", TenSize=" + TenSize + ", GiaAoKhoac=" + GiaAoKhoac + ", SoLuong=" + SoLuong + '}';
    }

    
}
