/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.*;

/**
 *
 * @author coc
 */
public class mainFormPanelWellcomCtrl {

    public static final int tbTopSach = 1;
    public static final int tbTopNV = 2;
    public static final int tbTopDG = 3;

    public static void loadTableTop(JTable tb, int tenTB) {
        DefaultTableModel model;
        Object[] obj;
        switch (tenTB) {
            case 1:
                obj = new Object[]{"Mã đầu sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Đơn giá", "Số lượng mượn"};
                model = new DefaultTableModel(obj,0);
                 {
                    try {
                        List<DauSach> dsDS = DauSach.top5();
                        for (DauSach ds : dsDS) {
                            Object[] item = new Object[7];
                            item[0] = ds.getMaDS();
                            item[1] = ds.getTenS();
                            item[2] = ds.getTacGia();
                            item[3] = ds.getNhaXB();
                            item[4] = ds.getNamXB();
                            item[5] = ds.getDonGia();
                            item[6] = ds.getSoLuotMuon();
                            model.addRow(item);
                        }
                        tb.setModel(model);

                    } catch (SQLException ex) {
                        Logger.getLogger(mainFormPanelWellcomCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

            case 2:
                obj = new Object[]{"Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Vai trò", "Số lần cho mượn"};
                model = new DefaultTableModel(obj,0);
                 {
                    try {
                        List<NhanVien> dsNV = NhanVien.top5NV();
                        for (NhanVien nv : dsNV) {
                            Object[] item = new Object[5];
                            item[0] = nv.getMaNV();
                            item[1] = nv.getTenNV();
                            item[2] = nv.getSoDT();
                            item[3] = nv.getVaiTro();
                            item[4] = nv.getSoLanChoMuon();
                            model.addRow(item);
                        }
                        tb.setModel(model);

                    } catch (SQLException ex) {
                        Logger.getLogger(mainFormPanelWellcomCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case 3:
                obj = new Object[]{"Mã đọc giả", "Tên đọc giả", "Số điện thoại", "Lớp", "Khoa", "Số lần mượn"};
                model = new DefaultTableModel(obj,0);
                try {
                    List<DocGia> dsDG = DocGia.top5DG();
                    for (DocGia dg : dsDG) {
                        Object[] item = new Object[6];
                        item[0] = dg.getMaDG();
                        item[1] = dg.getTenDG();
                        item[2] = dg.getSoDT();
                        item[3] = dg.getLop();
                        item[4] = dg.getKhoa();
                        item[5] = dg.getSoLanMuon();
                        model.addRow(item);
                    }
                    tb.setModel(model);
                } catch (SQLException ex) {
                    Logger.getLogger(mainFormPanelWellcomCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
        }

    }
}
