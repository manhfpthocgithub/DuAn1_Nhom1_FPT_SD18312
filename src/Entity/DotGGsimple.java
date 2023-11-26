/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author haila
 */
public class DotGGsimple {
    private String maDGG;
    private String tenDGG;
    private int GiatriDGG;

    public DotGGsimple() {
    }

    public DotGGsimple(String maDGG, String tenDGG, int GiatriDGG) {
        this.maDGG = maDGG;
        this.tenDGG = tenDGG;
        this.GiatriDGG = GiatriDGG;
    }

    public String getMaDGG() {
        return maDGG;
    }

    public void setMaDGG(String maDGG) {
        this.maDGG = maDGG;
    }

    public String getTenDGG() {
        return tenDGG;
    }

    public void setTenDGG(String tenDGG) {
        this.tenDGG = tenDGG;
    }

    public int getGiatriDGG() {
        return GiatriDGG;
    }

    public void setGiatriDGG(int GiatriDGG) {
        this.GiatriDGG = GiatriDGG;
    }

    @Override
    public String toString() {
        return "DotGGsimple{" + "maDGG=" + maDGG + ", tenDGG=" + tenDGG + ", GiatriDGG=" + GiatriDGG + '}';
    }
    
    
}
