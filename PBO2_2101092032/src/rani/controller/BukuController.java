/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rani.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rani.dao.BukuDao;
import rani.dao.BukuDaoImpl;
import rani.dao.Koneksi;
import rani.model.Buku;
import rani.view.FormBuku;

/**
 *
 * @author lenovo
 */
public class BukuController {
 
    private FormBuku formBuku;
    private Buku buku;
    private BukuDao bukuDao;
    private Connection con;
    private Koneksi koneksi;
    
    public BukuController(FormBuku formBuku){
        try {
            this.formBuku = formBuku;
            bukuDao = new BukuDaoImpl();
            koneksi = new Koneksi();
            con = koneksi.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public void clearForm(){
        formBuku.getTxtkodebuku().setText("");
        formBuku.getTxtjudul().setText("");
        formBuku.getTxtpengarang().setText("");
        formBuku.getTxtpenerbit().setText("");
    }
    
    public void insert(){
        try {
            buku = new Buku();
            buku.setKodebuku(formBuku.getTxtkodebuku().getText());
            buku.setJudul(formBuku.getTxtjudul().getText());
            buku.setPengarang(formBuku.getTxtpengarang().getText());
            buku.setPenerbit(formBuku.getTxtpenerbit().getText());
            bukuDao.insert(con, buku);
            JOptionPane.showMessageDialog(formBuku, "Entri OK");
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            buku = new Buku();
            buku.setKodebuku(formBuku.getTxtkodebuku().getText());
            buku.setJudul(formBuku.getTxtjudul().getText());
            buku.setPengarang(formBuku.getTxtpengarang().getText());
            buku.setPenerbit(formBuku.getTxtpenerbit().getText());
            bukuDao.update(con, buku);   
            JOptionPane.showMessageDialog(formBuku, "Update OK");
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            bukuDao.delete(con, buku);
            JOptionPane.showMessageDialog(formBuku, "Delete OK");
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formBuku.getTxtkodebuku().getText();
            buku = bukuDao.getBuku(con, kode);
            if(buku != null){
                formBuku.getTxtjudul().setText(buku.getJudul());
                formBuku.getTxtpengarang().setText(buku.getPengarang());
                formBuku.getTxtpenerbit().setText(buku.getPenerbit());
            }else{
                JOptionPane.showMessageDialog(formBuku, "Data Tidak Ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formBuku.getTblbuku().getModel();
            tabel.setRowCount(0);
            List<Buku> list = bukuDao.getAllBuku(con);
            for(Buku buku1 : list){
                //array yang datanya langsung diisi
                Object[] row = {
                    buku1.getKodebuku(),
                    buku1.getJudul(),
                    buku1.getPengarang(),
                    buku1.getPenerbit()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    



