/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JComboBox;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import model.*;

/**
 *
 * @author coc
 */
public class AllTableCtrl {

    public void hienComboBox(JComboBox cmb) throws SQLException {
        String query = "select table_name from information_schema.tables";
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ResultSet rs = Conn.getData(query);
        while (rs.next()) {
            model.addElement(rs.getString(1));
        }
        cmb.setModel(model);
    }
}
