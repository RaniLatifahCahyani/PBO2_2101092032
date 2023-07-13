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
import javax.swing.table.DefaultTableModel;
import rani.dao.AnggotaDao;
import rani.dao.AnggotaDaoImpl;
import rani.dao.BukuDao;
import rani.dao.BukuDaoImpl;
import rani.dao.Koneksi;
import rani.dao.PeminjamanDao;
import rani.dao.PeminjamanDaoImpl;
import rani.dao.PengembalianDao;
import rani.dao.PengembalianDaoImpl;
import rani.model.Anggota;
import rani.model.Peminjaman;
import rani.model.Pengembalian;
import rani.view.FormPengembalian;


/**
 *
 * @author lenovo
 */
public class PengembalianController {
    FormPengembalian formPengembalian;
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    PeminjamanDao peminjamanDao;
    PengembalianDao pengembalianDao;
    Pengembalian pengembalian;
    Connection con;

    
    public PengembalianController(FormPengembalian formPengembalian){
        try {
            this.formPengembalian = formPengembalian;
            anggotaDao = new AnggotaDaoImpl();
            bukuDao = new BukuDaoImpl();
            peminjamanDao = new PeminjamanDaoImpl();
            pengembalianDao = new PengembalianDaoImpl();
            Koneksi k = new Koneksi();
            con = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
    
     public void clearForm(){
         formPengembalian.getTxtkodeanggota().setText("");
        formPengembalian.getTxtkodebuku().setText("");
        formPengembalian.getTxttglpinjam().setText("");
        formPengembalian.getTxttglkembali().setText("");
        formPengembalian.getTxttgldikembalikan().setText("");
        formPengembalian.getTxtterlambat().setText("");
        formPengembalian.getTxtdenda().setText("");
        
    }

    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPengembalian.getTblpengembalian().getModel();
            tabel.setRowCount(0);
            List<Pengembalian> list = pengembalianDao.getAllPengembalian(con);
            for(Pengembalian p : list){
                Anggota anggota = anggotaDao.getAnggota(con, p.getKodeanggota());
                Peminjaman pinjam = peminjamanDao.getPeminjaman(con, p.getKodeanggota(), p.getKodebuku(), p.getTglpinjam());
                Object[] row = {
                    p.getKodeanggota(),
                    anggota.getNamaAnggota(),
                    p.getKodebuku(),
                    pinjam.getTglpinjam(),
                    pinjam.getTglkembali(),
                    p.getTgldikembalikan(),
                    p.getTerlambat(),
                    p.getDenda()
                };
                tabel.addRow(row); 
            }
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
         public void getPengembalian(){
         try {
             String kodeanggota;
             //kodeanggota = formPengembalian.getTblPengembalian().getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 0).toString();
             kodeanggota = formPengembalian.getTblpengembalian().getValueAt(formPengembalian.getTblpengembalian().getSelectedRow(), 0).toString();
             String kodebuku = formPengembalian.getTblpengembalian().getValueAt(formPengembalian.getTblpengembalian().getSelectedRow(), 2).toString();
             String tglpinjam = formPengembalian.getTblpengembalian().getValueAt(formPengembalian.getTblpengembalian().getSelectedRow(), 3).toString();
             //String kodeanggota = formPengembalian.getTblPengembalian().getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(),0).toString();
             pengembalian = new Pengembalian();
             Peminjaman peminjaman = peminjamanDao.getPeminjaman(con, kodeanggota, kodebuku, tglpinjam);
             int terlambat = pengembalianDao.selisihTanggal(con, pengembalian.getTgldikembalikan(),peminjaman.getTglkembali());
             pengembalian.setTerlambat(terlambat);
             double denda = pengembalian.getDenda();
             formPengembalian.getTxtkodeanggota().setText(kodeanggota);
             formPengembalian.getTxtkodebuku().setText(kodebuku);
             formPengembalian.getTxttglpinjam().setText(tglpinjam);
             formPengembalian.getTxttglkembali().setText(peminjaman.getTglkembali());
             formPengembalian.getTxttgldikembalikan().setText(pengembalian.getTgldikembalikan());
             formPengembalian.getTxtterlambat().setText(terlambat+"");
             formPengembalian.getTxtdenda().setText(denda+"");
         } catch (Exception ex) {
             Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }
    
    }

    
    
       

