/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import model.*;

/**
 *
 * @author Admin
 */
public class SachCtrl {
    public static int sosachHienCo(String mads){
        try {
            List<String> dsMaSHienCo = DauSach.MaSachHienCo(mads);
            return dsMaSHienCo.size();
            
        } catch (SQLException ex) {
            Logger.getLogger(SachCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }

    public void SachtoTable(JTable tb, String maDS, String tenS, String tacGia, String nhaXB, String namXB, float donGiaduoi, float donGiaTren, String sortOrder) {

        Object[] obj = new Object[]{"Mã đầu sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Đơn giá"};
        DefaultTableModel model = new DefaultTableModel(obj, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Đặt tất cả các ô không thể chỉnh sửa
            }
        };
        try {
            List<DauSach> dsDS = DauSach.searchDS(maDS, tenS, tacGia, nhaXB, namXB, donGiaduoi, donGiaTren, sortOrder);
            for (DauSach ds : dsDS) {
                Object[] item = new Object[6];
                item[0] = ds.getMaDS();
                item[1] = ds.getTenS();
                item[2] = ds.getTacGia();
                item[3] = ds.getNhaXB();
                item[4] = ds.getNamXB();
                item[5] = ds.getDonGia();

                model.addRow(item);
            }
            tb.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(SachCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
