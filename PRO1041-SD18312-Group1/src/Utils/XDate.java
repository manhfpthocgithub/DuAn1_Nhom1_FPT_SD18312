/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        //String s = "19-09-2023" ;
        // Chuyển đổi từ chuỗi lấy từ form sang kiểu dữ liệu date
        // Date date = Xdate.toDate(s,"dd-MM-yyyy");
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String toString(Date date, String pattern) {
        // Date now = new Date();
        //String s = XDate.toString(now , "dd-MM-yyyy");
        try {
            formater.applyPattern(pattern);
            return formater.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static Date addDays(Date date, long days) {
        //Date now = new Date();
        // Date after = XDate.addDays(now ,10);
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

}
