/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ADMIN
 */
abstract public class DuAn1DAO1<Entitytype,KeyType> {
    abstract public int insert(Entitytype entity);
    abstract public int update(Entitytype entity);
    abstract public Entitytype selectById(KeyType id);
    abstract public java.util.List<Entitytype> selectAll();
    abstract protected java.util.List<Entitytype> selectBySql(String sql , Object ...args);
}
