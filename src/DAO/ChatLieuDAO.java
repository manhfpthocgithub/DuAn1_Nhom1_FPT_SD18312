/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ChatLieu;
import Entity.LoaiAo;
import Entity.MauSac;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChatLieuDAO extends DuAn1DAO1<ChatLieu, Integer> {

    String INSERT_SQL = "INSERT INTO tblChatLieu (TenChatLieu,TrangThai) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE tblChatLieu SET TenChatLieu=? , TrangThai = ? WHERE MaChatLieu=?";
    String SELECT_ALL_SQL = "SELECT * FROM tblChatLieu ";
    String SELECT_ALL_SQL_NO_TT = "SELECT * FROM tblChatLieu ";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblChatLieu WHERE MaChatLieu =?";

    @Override
    public int insert(ChatLieu entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getTenChatLieu(), entity.isTrangThai());
    }

    @Override
    public int update(ChatLieu entity) {
        return JDBCHelper.executeUpdate(UPDATE_SQL, entity.getTenChatLieu(), entity.isTrangThai(), entity.getMaChatLieu());
    }

    @Override
    public ChatLieu selectById(Integer id) {
        List<ChatLieu> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChatLieu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChatLieu> selectBySql(String sql, Object... args) {
        List<ChatLieu> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setMaChatLieu(rs.getInt(1));
                cl.setTenChatLieu(rs.getString(2));
                cl.setTrangThai(rs.getBoolean(3));
                list.add(cl);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public boolean KiemTraTenChatLieu(String tenChatLieu) {
        List<LoaiAo> list = new ArrayList<>();
        String sql = "SELECT * FROM tblChatLieu WHERE TenChatLieu =?";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, tenChatLieu);
            while (rs.next()) {
                return false;
            }
            rs.getStatement().getConnection().close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
// select tất cả không trạng thái
    public List<ChatLieu> selectAllNoTT() {
        return this.selectBySql(SELECT_ALL_SQL_NO_TT);
    }
// kiểm tra tên chất liệu đang ngừng hoạt động
    public boolean KiemTraTenChatLieuNHD(String tenChatLieu) {
        List<ChatLieu> list = new ArrayList<>();
        String sql = "SELECT * FROM tblChatLieu WHERE TenChatLieu =? AND TrangThai=0";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, tenChatLieu);
            while (rs.next()) {
                return true;
            }
            rs.getStatement().getConnection().close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    // để update trạng thái cho các chất liệu trùng tên với tên thêm nhưng đang ngừng hoạt động
    
    public int updateTTNHD(String tenChatLieu){
        String sql = "UPDATE tblChatLieu SET TrangThai = 1 WHERE TenChatLieu=?";
        return JDBCHelper.executeUpdate(sql, tenChatLieu);
    }
}
