/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class XImage {

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/Hinh/fpt.png");
        return new ImageIcon(url).getImage();
    }

    public static void save(File src) {
        File dst = new File("C:\\PRO1041-SD18312-Group1\\logos\\", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
//            src.getAbsolutePath()
            Path from = Paths.get(src.getCanonicalPath());
            Path to = Paths.get(dst.getCanonicalPath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("C:\\PRO1041-SD18312-Group1\\logos\\", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    
    public static void saveTH(File src) {
        File dst = new File("C:\\PRO1041-SD18312-Group1\\logosTH\\", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
//            src.getAbsolutePath()
            Path from = Paths.get(src.getCanonicalPath());
            Path to = Paths.get(dst.getCanonicalPath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static ImageIcon readTH(String fileName) {
        File path = new File("C:\\PRO1041-SD18312-Group1\\logosTH\\", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
