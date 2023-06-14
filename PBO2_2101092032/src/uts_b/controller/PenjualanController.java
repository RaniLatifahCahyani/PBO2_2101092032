/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_b.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rani.dao.PenjualanDaoImpl;
import rani.dao.Koneksi;
import uts_b.dao.PenjualanDao;
import uts_b.model.Penjualan;
import uts_b.view.FormPenjualan;


public class PenjualanController {
    
    private FormPenjualan formPenjualan;
    private Penjualan penjualan;
    private PenjualanDao penjualanDao;
    private Connection con;
    private Koneksi koneksi;


 public PenjualanController(FormPenjualan formPenjualan){
try{
    this.formPenjualan = formPenjualan;
    penjualanDao = new PenjualanDaoImpl();
    con = new Koneksi().getKoneksi();
            } catch (ClassNotFoundException ex) {
     Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, 
     null, ex);
        } catch (SQLException ex) {
    Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, 
    null, ex);
        }
    }

public void clearForm(){
        formPenjualan.getTxtkodepesanan().setText("");
        formPenjualan.getTxttanggal().setText("");
        formPenjualan.getTxtnamapemesan().setText("");
        formPenjualan.getTxttotalbayar().setText("");
        formPenjualan.getTxtongkir().setText("");
        formPenjualan.getTxtdiskon().setText("");
    }
}

public void insert(){
        try {
            penjualan = new Penjualan();
            penjualan.setKodepesanan(formPesanan.getTxtkodepesanan().getText());
            penjualan.setNamapemesan(
                    formPenjual.getTxtnamapemesan().getText());
            penjualan.setTanggal(
                    formPenjual.getTxttanggal().getText());
            penjualan.setJeniskelamin (
                    formAnggota.getCbojeniskelamin().getSelectedItem().toString());
            penjualanDao.insert(con,anggota);
            JOptionPane.showMessageDialog(formAnggota, "Entri Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }



        
