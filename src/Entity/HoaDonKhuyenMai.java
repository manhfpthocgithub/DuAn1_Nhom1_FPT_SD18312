
package Entity;

import java.util.Date;

/**
 *
 * @author BANG
 */
public class HoaDonKhuyenMai {
   private int MaHDKhuyenMai ;
   private int MaPGG;
   private int MaHoaDon;
   private float SoTienConLai;
   private boolean TrangThaiHDKM;
   private String ghichuHDMK;
    public HoaDonKhuyenMai() {
    }

    public HoaDonKhuyenMai(int MaHDKhuyenMai, int MaPGG, int MaHoaDon, float SoTienConLai, boolean TrangThaiHDKM, String ghichuHDMK) {
        this.MaHDKhuyenMai = MaHDKhuyenMai;
        this.MaPGG = MaPGG;
        this.MaHoaDon = MaHoaDon;
        this.SoTienConLai = SoTienConLai;
        this.TrangThaiHDKM = TrangThaiHDKM;
        this.ghichuHDMK = ghichuHDMK;
    }

    public String getGhichuHDMK() {
        return ghichuHDMK;
    }

    public void setGhichuHDMK(String ghichuHDMK) {
        this.ghichuHDMK = ghichuHDMK;
    }

    public int getMaHDKhuyenMai() {
        return MaHDKhuyenMai;
    }

    public void setMaHDKhuyenMai(int MaHDKhuyenMai) {
        this.MaHDKhuyenMai = MaHDKhuyenMai;
    }

    public int getMaPGG() {
        return MaPGG;
    }

    public void setMaPGG(int MaPGG) {
        this.MaPGG = MaPGG;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public float getSoTienConLai() {
        return SoTienConLai;
    }

    public void setSoTienConLai(float SoTienConLai) {
        this.SoTienConLai = SoTienConLai;
    }

    public boolean isTrangThaiHDKM() {
        return TrangThaiHDKM;
    }

    public void setTrangThaiHDKM(boolean TrangThaiHDKM) {
        this.TrangThaiHDKM = TrangThaiHDKM;
    }
       
}
