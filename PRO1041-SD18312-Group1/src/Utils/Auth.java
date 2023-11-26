/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Entity.NguoiDung;

/**
 *
 * @author ADMIN
 */
public class Auth {
    // đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static NguoiDung user = null;

    // đối tượng này xóa thông tin người sử dụng khi có yêu cầu đăng xuất
    public static void clear() {
        Auth.user = null;
    }
    
    //Kiểm tra 1 thông tin nào đó đã đăng nhập hay chưa

    public static boolean isLogin() {
        return Auth.user != null;
    }
// phân loại vai trò 
    public static boolean isManager() {
        return Auth.isLogin() && user.isVaiTro();
    }
}
