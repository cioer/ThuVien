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
import java.sql.SQLException;

public class ChiTietMuonTra {
    private String SoPH;
    private String MaS;
    private String NgayT;
    private float TienPhat;

    // Constructors, getters and setters

    public ChiTietMuonTra() {
    }

    public ChiTietMuonTra(String SoPH, String MaS, String NgayT, float TienPhat) {
        this.SoPH = SoPH;
        this.MaS = MaS;
        this.NgayT = NgayT;
        this.TienPhat = TienPhat;
    }

    public String getSoPH() {
        return SoPH;
    }

    public void setSoPH(String SoPH) {
        this.SoPH = SoPH;
    }

    public String getMaS() {
        return MaS;
    }

    public void setMaS(String MaS) {
        this.MaS = MaS;
    }

    public String getNgayT() {
        return NgayT;
    }

    public void setNgayT(String NgayT) {
        this.NgayT = NgayT;
    }

    public float getTienPhat() {
        return TienPhat;
    }

    public void setTienPhat(float TienPhat) {
        this.TienPhat = TienPhat;
    }

    
    public void add() throws SQLException {
        String query = "INSERT INTO ChiTietMuonTra (SoPH, MaS, NgayT, TienPhat) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = Conn.conn();
            
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, SoPH);
            statement.setString(2, MaS);
            statement.setString(3, NgayT);
            statement.setFloat(4, TienPhat);
            statement.executeUpdate();
            connection.commit();
            connection.close();
            System.out.println("Thêm dữ liệu thành công!");
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM ChiTietMuonTra WHERE SoPH = ? AND MaS = ?";
        try {
            Connection connection = Conn.conn();
            
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, SoPH);
            statement.setString(2, MaS);
            statement.executeUpdate();
            connection.commit();
            connection.close();
            System.out.println("Xóa dữ liệu thành công!");
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
    }

    public void fix() throws SQLException {
        String query = "UPDATE ChiTietMuonTra SET NgayT = ?, TienPhat = ? WHERE SoPH = ? AND MaS = ?";
        try {
            Connection connection = Conn.conn();
            
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, NgayT);
            statement.setFloat(2, TienPhat);
            statement.setString(3, SoPH);
            statement.setString(4, MaS);
            statement.executeUpdate();
            connection.commit();
            connection.close();
            System.out.println("Cập nhật dữ liệu thành công!");
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
    }

    // Other methods
}