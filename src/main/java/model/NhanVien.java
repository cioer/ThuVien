/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVien {

    private String maNV;
    private String tenNV;
    private String soDT;
    private String pass;
    private String vaiTro;

    // Constructors, getters, and setters
    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String soDT, String pass, String vaiTro) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.soDT = soDT;
        this.pass = pass;
        this.vaiTro = vaiTro;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) throws SQLException {
        this.pass = hashPassword(pass);
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String CreateMaNV() throws SQLException{
        String query = "select top(1) MaNV from NhanVien order by MaNV desc";
        try {
            ResultSet rs = Conn.getData(query);
            String result = "";
            while ( rs. next()){
                result = rs.getString(1);
            }
            if (result.equals("")){
                return "nv1";
            }
            Integer i = Integer.parseInt(result.substring(2));
            i += 1;
            return "nv"+i.toString();
        } catch (SQLException ex) {
            throw new SQLException("Tạo mã NV không thành công "+ex.getMessage());
        }
        
    }
    
    public String SoLuotChoMuon() throws SQLException{
        String query = "select count(*) from MuonTra where MaNV = ?";
        Integer i = 0;
        try {
            PreparedStatement ptm = Conn.conn().prepareStatement(query);
            ptm.setString(1,maNV);
            ResultSet rs = ptm.executeQuery();
            while(rs.next()){
                i = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new SQLException("Loi Tim so luot cho muon: " + ex.getMessage());
        }
        return i.toString();
    }
    public void add() throws SQLException {
        String query = "INSERT INTO NhanVien (MaNV, TenNV, SoDT, pass, VaiTro) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conn.conn(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, maNV);
            statement.setString(2, tenNV);
            statement.setString(3, soDT);
            statement.setString(4, pass); // Mã hóa mật khẩu trước khi đưa vào cơ sở dữ liệu
            statement.setString(5, vaiTro);

            statement.executeUpdate();
            Conn.ColseConn(connection);
            System.out.println("Record added successfully.");
        }catch(SQLException ex){
            throw new SQLException("Đăng kí không thành công:" + ex.getMessage());
        }
    }

    public void fix() throws SQLException {
        String query = "UPDATE NhanVien SET TenNV = ?, SoDT = ?, pass = ?, VaiTro = ? WHERE MaNV = ?";

        try (Connection connection = Conn.conn(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, tenNV);
            statement.setString(2, soDT);
            statement.setString(3, pass); // Mã hóa mật khẩu trước khi cập nhật vào cơ sở dữ liệu
            statement.setString(4, vaiTro);
            statement.setString(5, maNV);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Record with MaNV " + maNV + " does not exist.");
            }
            Conn.ColseConn(connection);
            System.out.println("Record updated successfully.");
        }
    }

    public void login() throws SQLException {
        String query = "SELECT * FROM NhanVien WHERE SoDT = ? AND pass = ?";

        try (Connection connection = Conn.conn(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, soDT);
            statement.setString(2, pass);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    
                    this.setMaNV(resultSet.getString("MaNV"));
                    this.setTenNV(resultSet.getString("TenNV"));
                    this.setVaiTro(resultSet.getString("VaiTro"));

                }else{
                    Conn.ColseConn(connection);
                    throw new SQLException("Dang nhap khong thanh cong!");
                }
            }
            Conn.ColseConn(connection);
        }
    }

    private static String hashPassword(String password) throws SQLException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new SQLException("Error while hashing password: " + e.getMessage());
        }
    }

}
