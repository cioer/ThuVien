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

public class DocGia {
    private String maDG;
    private String tenDG;
    private String soDT;
    private String lop;
    private String khoa;

    // Constructors, getters, and setters
    
    public DocGia() {
    }

    public DocGia(String maDG, String tenDG, String soDT, String lop, String khoa) {
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.soDT = soDT;
        this.lop = lop;
        this.khoa = khoa;
    }

    public String getMaDG() {
        return maDG;
    }

    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    //
    public void add() throws SQLException {
        String query = "INSERT INTO DocGia (MaDG, tenDG, SoDT, Lop, Khoa) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conn.conn();
                PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, maDG);
            statement.setString(2, tenDG);
            statement.setString(3, soDT);
            statement.setString(4, lop);
            statement.setString(5, khoa);

            statement.executeUpdate();
            Conn.ColseConn(connection);
        }
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM DocGia WHERE MaDG = ?";

        try (Connection connection = Conn.conn();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, maDG);

            statement.executeUpdate();
            Conn.ColseConn(connection);
        }
    }

    public void fix() throws SQLException {
        String query = "UPDATE DocGia SET tenDG = ?, SoDT = ?, Lop = ?, Khoa = ? WHERE MaDG = ?";

        try (Connection connection = Conn.conn();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, tenDG);
            statement.setString(2, soDT);
            statement.setString(3, lop);
            statement.setString(4, khoa);
            statement.setString(5, maDG);

            statement.executeUpdate();
            Conn.ColseConn(connection);
        }
    }
}
