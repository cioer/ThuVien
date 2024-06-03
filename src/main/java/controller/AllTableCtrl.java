/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JComboBox;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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

    public void hienTable(JTable tb, String tableName) throws SQLException {
        String query = "select * from " + tableName;

        ResultSet rs = Conn.getData(query);
        ResultSetMetaData rsmt = rs.getMetaData();
        int colCount = rsmt.getColumnCount();
        Object[] obj = new Object[colCount];
        for (int i = 0; i < colCount; i++) {
            obj[i] = rsmt.getColumnName(i + 1);
        }
        DefaultTableModel model = new DefaultTableModel(obj, 0);
        while (rs.next()) {
            Object[] item = new Object[colCount];
            for (int i = 0; i < colCount; i++) {
                item[i] = rs.getString(i + 1);
            }
            model.addRow(item);
        }
        
        tb.setModel(model);
    }
}
