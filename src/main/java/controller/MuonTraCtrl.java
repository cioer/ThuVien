/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChiTietMuonTra;
import model.DauSach;
import model.MuonTra;
import model.Sach;

/**
 *
 * @author coc
 */
public class MuonTraCtrl {

    public static void MuonSach(String maNV, String maDG, String MaDS, String soLuongSach, String soNgayMuon) {
        
        MuonTraCtrl mtCtr = new MuonTraCtrl();
        mtCtr.setMaDG(maDG);
        mtCtr.setMaDS(MaDS);
        mtCtr.setMaNV(maNV);
        mtCtr.setSoLuongSach(Integer.valueOf(soLuongSach));
        mtCtr.setSoNgayMuon(Integer.valueOf(soNgayMuon));

        
        try {
            mtCtr.muonSach();
        } catch (SQLException ex) {
            Logger.getLogger(MuonTraCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }



private String MaDG, MaNV,MaDS;
    private Integer soLuongSach, soNgayMuon;

    public String getMaDS() {
        return MaDS;
    }

    public void setMaDS(String MaDS) {
        this.MaDS = MaDS;
    }
    
    

    public void setSoLuongSach(Integer soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public void setSoNgayMuon(Integer soNgayMuon) {
        this.soNgayMuon = soNgayMuon;
    }

    public MuonTraCtrl() {

    }

    public void setMaDG(String MaDG) {
        this.MaDG = MaDG;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaDG() {
        return MaDG;
    }

    public String getMaNV() {
        return MaNV;
    }

    

    public void muonSach() throws SQLException {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        
        
        MuonTra mtr = new MuonTra();
        mtr.setMaDG(MaDG);
        mtr.setMaNV(MaNV);
        mtr.setNgayM(formattedDate);
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, this.soNgayMuon);
        Date newDate = calendar.getTime();
        formattedDate = dateFormat.format(newDate);
        mtr.setNgayHT(formattedDate);
        
        mtr.add();//xong phieu muon
        String soph = mtr.getSoPH();
        //add chi tiet phieu muon
        System.out.println("form muon tra methon muon sach "+MaDS);
        List<String> dsMaS = DauSach.MaSachHienCo(MaDS);
        
        for(int i = 0; i < this.soLuongSach;i++){
            System.out.println("controller.MuonTraCtrl.muonSach() "+dsMaS.get(i));
            ChiTietMuonTra ctMT = new ChiTietMuonTra();
            ctMT.setSoPH(soph);
            ctMT.setMaS(dsMaS.get(i));
            ctMT.add();
            Sach.DoiTinhTrang(dsMaS.get(i));
        }
    }
}
