/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author coc
 */
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MuonTra {
    private String SoPH;
    private String MaDG;
    private String MaNV;
    private String NgayM;
    private String NgayHT;

    public MuonTra() {
    }

    public String getSoPH() {
        return SoPH;
    }

    public void setSoPH(String SoPH) {
        this.SoPH = SoPH;
    }

    public String getMaDG() {
        return MaDG;
    }

    public void setMaDG(String MaDG) {
        this.MaDG = MaDG;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getNgayM() {
        return NgayM;
    }

    public void setNgayM(String NgayM) {
        this.NgayM = NgayM;
    }

    public String getNgayHT() {
        return NgayHT;
    }

    public void setNgayHT(String NgayHT) {
        this.NgayHT = NgayHT;
    }

    public MuonTra(String SoPH, String MaDG, String MaNV, String NgayM, String NgayHT) {
        this.SoPH = SoPH;
        this.MaDG = MaDG;
        this.MaNV = MaNV;
        this.NgayM = NgayM;
        this.NgayHT = NgayHT;
    }

    public void add() throws SQLException {
        if(this.SoPH == null){
            this.SoPH = createMa();
        }
        String addQuery = "INSERT INTO MuonTra (SoPH, MaDG, MaNV, NgayM, NgayHT) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conn.conn();
             PreparedStatement statement = connection.prepareStatement(addQuery)) {

            statement.setString(1, SoPH);
            statement.setString(2, MaDG);
            statement.setString(3, MaNV);
            statement.setString(4, NgayM);
            statement.setString(5, NgayHT);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully.");
            }
            Conn.ColseConn(connection);
        } catch (SQLException e) {
            throw new SQLException("Error inserting data: " + e.getMessage());
        }
    }

    public void fix() throws SQLException {
        String fixQuery = "UPDATE MuonTra SET MaDG = ?, MaNV = ?, NgayM = ?, NgayHT = ? WHERE SoPH = ?";

        try (Connection connection = Conn.conn();
             PreparedStatement statement = connection.prepareStatement(fixQuery)) {

            statement.setString(1, MaDG);
            statement.setString(2, MaNV);
            statement.setString(3, NgayM);
            statement.setString(4, NgayHT);
            statement.setString(5, SoPH);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully.");
            }
            Conn.ColseConn(connection);
        } catch (SQLException e) {
            throw new SQLException("Error updating data: " + e.getMessage());
        }
    }

    public void delete() throws SQLException {
        String deleteQuery = "DELETE FROM MuonTra WHERE SoPH = ?";

        try (Connection connection = Conn.conn();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, SoPH);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data deleted successfully.");
            }
            Conn.ColseConn(connection);
        } catch (SQLException e) {
            throw new SQLException("Error deleting data: " + e.getMessage());
        }
    }
    
    private String createMa(){
        String ma = "mt";
        String query = "select top(1) SoPH from MuonTra order by SoPH desc";
        
            ResultSet rs;
        try {
            rs = Conn.getData(query);
            if(rs.next()){
                Integer i = Integer.valueOf(rs.getString(1).substring(2));
               Random rm = new Random();
                i +=rm.nextInt(Integer.MAX_VALUE);
                return ma + i.toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuonTra.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return ma + "1";
    }
}