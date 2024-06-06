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
import javax.swing.JTextField;
import model.DocGia;

/**
 *
 * @author coc
 */
public class DocGiaCtrl {
    
    public DocGiaCtrl() {
    }
    
    public void loadCmb(JTextField tf, JComboBox cmb) throws Exception {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        DocGia dg = new DocGia();
        List<String> dsSoDT = dg.getListSoDT(getStr(tf));
        model.addAll(dsSoDT);
        cmb.setModel(model);
    }
    
    public DocGia loadDocGia(JComboBox cmb) {
        String sodt = cmb.getSelectedItem().toString();
        DocGia dg = new DocGia();
        String maDG;
        try {
            maDG = dg.getMaDG(sodt);
            return new DocGia(maDG);
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String themDG(JTextField sdt, JTextField ten, JTextField lop, JTextField khoa) throws Exception {
        DocGia dg = new DocGia();
        String sodt = getStr(sdt);
        
        try {
            String madg = dg.getMaDG(sodt);
            if (madg != null) {
                return madg;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        dg.setTenDG(getStr(ten));
        dg.setSoDT(sodt);
        dg.setLop(getStr(lop));
        dg.setKhoa(getStr(khoa));
        
        try {
            dg.add();
            return dg.getMaDG(sodt);
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String getStr(JTextField tf) throws Exception {
        String str = tf.getText().trim();
        if (str.isEmpty()) {
            throw new Exception("Khong duoc de trong!");
        }
        return str;
    }
    
    public void rest(JTextField tfSoDTDocGia, JTextField tfTenDocGia, JTextField tfLopDocGia, JTextField tfKhoaDocGia) {
        setempty(tfSoDTDocGia);
        setempty(tfTenDocGia);
         setempty(tfLopDocGia);
          setempty(tfKhoaDocGia);
    }

    private void setempty(JTextField tf) {
        tf.setText("");
    }
}
