package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import model.*;

/**
 *
 * @author Admin
 */
public class SuaSachCtrl {

    public static DefaultTableModel sachToTable(String MaDS) {
        List<Sach> dsS;
        Object[] obj = new Object[]{"Mã sách", "Mã đầu sách", "vị trí", "Tình trạng"};
        DefaultTableModel model = new DefaultTableModel(obj, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Đặt tất cả các ô không thể chỉnh sửa
            }
        };
        try {
            dsS = Sach.getListSach(MaDS);
            for (Sach s : dsS) {
            Object[] item = new Object[4];
            item[0] = s.getMaS();

            item[1] = s.getMaDS();
            item[2] = s.getViTri();
            item[3] = s.getTinhTrang();
            model.addRow(item);
        }
        } catch (SQLException ex) {
            Logger.getLogger(SuaSachCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
       return model;
    }
}
