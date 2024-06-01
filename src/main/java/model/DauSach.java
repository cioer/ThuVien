/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coc
 */
public class DauSach {

    private String maDS;
    private String tenS;
    private String tacGia;
    private String nhaXB;
    private String namXB;

    private float donGia;

    // Constructor
    public DauSach(String maDS) throws SQLException {
        GetDataFromSQL(maDS);
    }

    public DauSach(String maDS, String tenS, String tacGia, String nhaXB, String namXB, float donGia) {
        this.maDS = maDS;
        this.tenS = tenS;
        this.tacGia = tacGia;
        this.nhaXB = nhaXB;
        this.namXB = namXB;

        this.donGia = donGia;
    }

    // Getters and Setters
    public String getMaDS() {
        return maDS;
    }

    public void setMaDS(String maDS) {
        this.maDS = maDS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public String getNamXB() {
        return namXB;
    }

    public void setNamXB(String namXB) {
        this.namXB = namXB;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public static List<String> getListMaDS() throws SQLException {
        List<String> dsMaDS = new ArrayList<>();
        String query = "select maDS from dausach";
        Connection cn = Conn.conn();
        Statement stm = cn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            String msds = rs.getString(1);
            dsMaDS.add(msds);
        }
        return dsMaDS;
    }

    private void GetDataFromSQL(String maDS) throws SQLException {
        String query = "select * from DauSach where MaDS = ?";
        Connection cn = Conn.conn();
        PreparedStatement pstm = cn.prepareStatement(query);
        pstm.setString(1, maDS);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            this.maDS = rs.getString("MaDS");
            this.donGia = rs.getFloat("dongia");
            this.namXB = rs.getString("namXB");
            this.nhaXB = rs.getString("nhaxb");
            this.tacGia = rs.getString("tacgia");
            this.tenS = rs.getString("tens");
        }
        Conn.ColseConn(cn);
    }

    public int add() throws SQLException {
        String query = "INSERT INTO DauSach (MaDS, TenS, TacGia, NhaXB, NamXB, DonGia) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = Conn.conn();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, maDS);
        statement.setString(2, tenS);
        statement.setString(3, tacGia);
        statement.setString(4, nhaXB);
        statement.setString(5, namXB);
        statement.setFloat(6, donGia);

        int ck = statement.executeUpdate();
        connection.commit();
        Conn.ColseConn(connection);
        return ck;

    }

    public int delete() throws SQLException {
        String query = "DELETE FROM DauSach WHERE MaDS = ?";
        Connection connection = Conn.conn();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, this.maDS);
        int i = statement.executeUpdate();
        Conn.ColseConn(connection);
        if(i == 0){
            throw new SQLException("Record with MaDS " + maDS + " does not exist.");
        }
        return i;
    }

    public int fix() throws SQLException {
        String query = "UPDATE DauSach SET TenS = ?, TacGia = ?, NhaXB = ?, NamXB = ?, DonGia = ? WHERE MaDS = ?";
        Connection connection = Conn.conn();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, tenS);
        statement.setString(2, tacGia);
        statement.setString(3, nhaXB);
        statement.setString(4, namXB);
        statement.setFloat(5, donGia);
        statement.setString(6, maDS);
        int affectedRows = statement.executeUpdate();
        connection.commit();
        Conn.ColseConn(connection);
        if(affectedRows == 0){
            throw new SQLException("Record with MaDS " + maDS + " does not exist.");
        }
        return affectedRows;
    }

}
