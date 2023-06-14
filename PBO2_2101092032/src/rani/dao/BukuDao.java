/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rani.dao;

import java.sql.Connection;
import java.util.List;
import rani.model.Buku;

/**
 *
 * @author lenovo
 */
public interface BukuDao {
    public void insert(Connection con, Buku buku) throws Exception;
    public void update(Connection con, Buku buku) throws Exception;
    public void delete(Connection con, Buku buku) throws Exception;
    public Buku getBuku(Connection con, String Kode) throws Exception;
    public List<Buku> getAllBuku(Connection con)throws Exception;
}
