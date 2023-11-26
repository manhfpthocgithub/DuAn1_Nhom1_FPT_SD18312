/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author admin
 */
public abstract class DuAn1DAO<Entitytype,KeyType> {
    abstract public void insert(Entitytype entity);
    abstract public void update(Entitytype entity);
    abstract public Entitytype selectById(KeyType id);
    abstract public java.util.List<Entitytype> selectAll();
    abstract protected java.util.List<Entitytype> selectBySql(String sql , Object ...args); 
}
