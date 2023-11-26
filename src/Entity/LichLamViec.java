/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Pico123
 */
public class LichLamViec {
    private int ID;
    private String maNv;
    private Date ngayBD;
    private Date ngayKT;
    private String calam;

    public LichLamViec() {
    }

    public LichLamViec(int ID, String maNv, Date ngayBD, Date ngayKT, String calam) {
        this.ID = ID;
        this.maNv = maNv;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.calam = calam;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public String getCalam() {
        return calam;
    }

    public void setCalam(String calam) {
        this.calam = calam;
    }

    @Override
    public String toString() {
        return "LichLamViec{" + "ID=" + ID + ", maNv=" + maNv + ", ngayBD=" + ngayBD + ", ngayKT=" + ngayKT + ", calam=" + calam + '}';
    }
    
    
}
