/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author haila
 */
public class AKGG {
    private String maLAK;
    private String tenLAK;

    public AKGG() {
    }

    public AKGG(String maLAK, String tenLAK) {
        this.maLAK = maLAK;
        this.tenLAK = tenLAK;
    }

    public String getMaLAK() {
        return maLAK;
    }

    public void setMaLAK(String maLAK) {
        this.maLAK = maLAK;
    }

    public String getTenLAK() {
        return tenLAK;
    }

    public void setTenLAK(String tenLAK) {
        this.tenLAK = tenLAK;
    }

    @Override
    public String toString() {
        return "AKGG{" + "maLAK=" + maLAK + ", tenLAK=" + tenLAK + '}';
    }
    
    
}
