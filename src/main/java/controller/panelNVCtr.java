/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import model.NhanVien;

/**
 *
 * @author coc
 */
public class panelNVCtr {
    public static void show(JPasswordField fp,JButton bt,boolean ck){
        fp.setText("");
        fp.setVisible(ck);
        bt.setVisible(ck);
    }
    public static void CaiDat(JComboBox cmb,JPasswordField fp,JButton bt){
        int itemSelected = cmb.getSelectedIndex();
        if(itemSelected == 1){
            show(fp,bt,true);
            fp.setEchoChar((char) 0);
            
        }
        else if(itemSelected == 2){
            show(fp,bt,true);
            fp.setEchoChar('\u2022');
        }
        else {
            show(fp,bt,false);
        }
    }
    public static NhanVien save(JComboBox cmb,NhanVien nv, String str) throws SQLException{
        int itemSelected = cmb.getSelectedIndex();
        if(itemSelected == 1){
            nv.setTenNV(str);
            
            
        }
        else if(itemSelected == 2){
            nv.setPass(str);
        }
        
        nv.fix();
        return nv;
    }
}
