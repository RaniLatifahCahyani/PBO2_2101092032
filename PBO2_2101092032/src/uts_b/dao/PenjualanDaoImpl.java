/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uts_b.model.Penjualan;

/**
 *
 * @author lenovo
 */
public class PenjualanDaoImpl implements PenjualanDao {

    @Override
    public void insert(Connection con, Penjualan penjualan) throws Exception {
        String sql = "insert into penjualan values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penjualan.getKodepesanan());
        ps.setString(2, penjualan.getTanggal());
        ps.setString(3, penjualan.getNamapemesan());
        ps.setString(4, penjualan.getTotalbayar());
        ps.setString(5, penjualan.getOngkir());
        ps.setString(6, penjualan.getDiskon());
        ps.executeUpdate();
        
    }

    @Override
    public void update(Connection con, Penjualan penjualan) throws Exception {
            String sql = 
                "update penjualan set tanggal=?, namapemesan=?, totalbayar=?, ongkir, diskon where kodepesanan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penjualan.getTanggal());
        ps.setString(2, penjualan.getNamapemesan());
        ps.setString(3, penjualan.getTotalbayar());
        ps.setString(4, penjualan.getOngkir());
        ps.setString(5, penjualan.getDiskon());
        ps.setString(6, penjualan.getKodepesanan());
        ps.executeUpdate();
    }

    @Override
    public void delete(Connection con, Penjualan penjualan) throws Exception {
          String sql = "delete from penjualan "
                + "where kodepesanan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penjualan.getKodepesanan());
        ps.executeUpdate();
    }

    @Override
    public Penjualan getPenjualan(Connection con, String Kode) throws Exception {
        String sql = 
                "select * from penjualan where kodepesanan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        String kode = null;
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Penjualan penjualan = null;
        if (rs.next()){
            penjualan = new Penjualan();
            penjualan.setKodepesanan(rs.getString(1));
            penjualan.setTanggal(rs.getString(2));
            penjualan.setNamapemesan(rs.getString(3));
            penjualan.setTotalbayar(rs.getString(4));
            penjualan.setOngkir(rs.getString(5));
            penjualan.setDiskon(rs.getString(6));
        }
        return penjualan;
    }

    @Override
    public List<Penjualan> getAllPenjualan(Connection con) throws Exception {
        String sql = null;
       PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Penjualan> list = new ArrayList<>();
        Penjualan penjualan = null;
        while (rs.next()){
            penjualan = new Penjualan();
            penjualan.setKodepesanan(rs.getString(1));
            penjualan.setTanggal(rs.getString(2));
            penjualan.setNamapemesan(rs.getString(3));
            penjualan.setTotalbayar(rs.getString(4));
            penjualan.setOngkir(rs.getString(5));
            penjualan.setDiskon(rs.getString(6));
            list.add(penjualan);
        }
        return list;
    }
   
}
