package Entity;


public class HoaDon {
    private int MaHD;
    private String MaNV;
    private int MaKH;
    private float TongTien;
    private float ThanhToan;
    private String NgayTaoHoaDon;
    private boolean TrangThaiHD;
    private String GhiChuHD;

    public HoaDon() {
    }

    public HoaDon(int MaHD, String MaNV, int MaKH, float TongTien, float ThanhToan, String NgayTaoHoaDon, boolean TrangThaiHD, String GhiChuHD) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.TongTien = TongTien;
        this.ThanhToan = ThanhToan;
        this.NgayTaoHoaDon = NgayTaoHoaDon;
        this.TrangThaiHD = TrangThaiHD;
        this.GhiChuHD = GhiChuHD;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public float getThanhToan() {
        return ThanhToan;
    }

    public void setThanhToan(float ThanhToan) {
        this.ThanhToan = ThanhToan;
    }

    public String getNgayTaoHoaDon() {
        return NgayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(String NgayTaoHoaDon) {
        this.NgayTaoHoaDon = NgayTaoHoaDon;
    }

    public boolean isTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(boolean TrangThaiHD) {
        this.TrangThaiHD = TrangThaiHD;
    }

    public String getGhiChuHD() {
        return GhiChuHD;
    }

    public void setGhiChuHD(String GhiChuHD) {
        this.GhiChuHD = GhiChuHD;
    }

    public String TrangThai(){ 
        if(TrangThaiHD==true){ 
            return "Đang Hoạt Động";
        }
        return "Ngừng Hoạt Động";
    }
    @Override
    public String toString() {
        //return "HoaDon{" + "MaHD=" + MaHD + ", MaNV=" + MaNV + ", MaKH=" + MaKH + ", TongTien=" + TongTien + ", ThanhToan=" + ThanhToan + ", NgayTaoHoaDon=" + NgayTaoHoaDon + ", TrangThaiHD=" + TrangThaiHD + ", GhiChuHD=" + GhiChuHD + '}';
        return TrangThai();
    }
     
}
