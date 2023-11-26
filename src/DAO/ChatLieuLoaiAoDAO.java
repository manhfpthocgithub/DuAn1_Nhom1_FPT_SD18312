/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.AoKhoacMuaDong;
import Entity.ChatLieu_LoaiAo;
import Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class ChatLieuLoaiAoDAO extends DuAn1DAO1<ChatLieu_LoaiAo, Integer> {

    String INSERT_SQL = "INSERT INTO tblCL_LA\n"
            + "             ( MaLoaiAo, MaChatLieu, TrangThai)\n"
            + "VALUES (?,?,?)";
    String UPDATE_SQL = "UPDATE tblCL_LA\n"
            + "SET MaLoaiAo =?, MaChatLieu =?, TrangThai =? WHERE MaChatLieuLoaiAo=?";
    String SELECT_ALL_SQL = "SELECT * FROM tblCL_LA";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblCL_LA WHERE MaChatLieuLoaiAo =?";

    @Override
    public int insert(ChatLieu_LoaiAo entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaLoaiAo(), entity.getMaChatLieu(), entity.isTrangThai());
    }

    @Override
    public int update(ChatLieu_LoaiAo entity) {
        return JDBCHelper.executeUpdate(UPDATE_SQL, entity.getMaLoaiAo(), entity.getMaChatLieu(), entity.isTrangThai(), entity.getMaChatLieuLoaiAo());
    }

    @Override
    public ChatLieu_LoaiAo selectById(Integer id) {
        List<ChatLieu_LoaiAo> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChatLieu_LoaiAo> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChatLieu_LoaiAo> selectBySql(String sql, Object... args) {
        List<ChatLieu_LoaiAo> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
               ChatLieu_LoaiAo clla = new ChatLieu_LoaiAo();
               clla.setMaChatLieuLoaiAo(rs.getInt(1));
               clla.setMaLoaiAo(rs.getInt(2));
               clla.setMaChatLieu(rs.getInt(3));
               clla.setTrangThai(rs.getBoolean(4));
               list.add(clla);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<ChatLieu_LoaiAo> getChatLieuCuaLoaiAo(int maLa){
        
        String sql = "SELECT * FROM tblCL_LA WHERE MaLoaiAO=?";
        List<ChatLieu_LoaiAo> list = this.selectBySql(sql, maLa);
        if(list.isEmpty()){
            return null;
        }
        return list ;
    }
    
    public ChatLieu_LoaiAo getChatLieuLoaiAo(int maCl , int maLa){
        String sql = "SELECT * FROM tblCL_LA WHERE  MaChatLieu=? AND MaLoaiAO=? AND TrangThai = 1";
        List<ChatLieu_LoaiAo> list = this.selectBySql(sql, maCl,maLa);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0) ;
    }
 
    public List<ChatLieu_LoaiAo> selectAllHD() {
        String SELECT_ALL_SQL_HD = "SELECT * FROM tblCL_LA WHERE TrangThai=1";
        return this.selectBySql(SELECT_ALL_SQL);
    }
}
