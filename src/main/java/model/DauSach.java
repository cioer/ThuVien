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
    private Integer soLuotMuon;

    public Integer getSoLuotMuon() {
        return soLuotMuon;
    }

    public void setSoLuotMuon(Integer soLuotMuon) {
        this.soLuotMuon = soLuotMuon;
    }

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

    private DauSach() {
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

    
    public static List<DauSach> searchDS(String maDS, String tenS, String tacGia, String nhaXB, String namXB, float donGiaduoi, float donGiaTren, String sortOrder) throws SQLException {//asc tang dan desc giam dan
        List<DauSach> dsDS = new ArrayList<>();

        String query1 = "SELECT MaDS FROM [DauSach] "
                + "WHERE ([MaDS] LIKE '%' + ? + '%' OR ? IS NULL) "
                + "  AND ([TenS] LIKE '%' + ? + '%' OR ? IS NULL) "
                + "  AND ([TacGia] LIKE '%' + ? + '%' OR ? IS NULL)"
                + "  AND ([NhaXB] LIKE '%' + ? + '%' OR ? IS NULL)"
                + "  AND ([NamXB] LIKE '%' + ? + '%' OR ? IS NULL)"
                + "  AND ([DonGia] >= ? and [DonGia] <= ?) ";
        String query2 = query1 + "ORDER BY [DonGia] " + sortOrder + ";";
        try {
            Connection conn = Conn.conn();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(query2);
            pstmt.setString(1, maDS);
            pstmt.setString(2, maDS);
            pstmt.setString(3, tenS);
            pstmt.setString(4, tenS);
            pstmt.setString(5, tacGia);
            pstmt.setString(6, tacGia);
            pstmt.setString(7, nhaXB);
            pstmt.setString(8, nhaXB);
            pstmt.setString(9, namXB);
            pstmt.setString(10, namXB);

            pstmt.setFloat(11, donGiaduoi);
            pstmt.setFloat(12, donGiaTren);

            ResultSet rs = pstmt.executeQuery();
            conn.commit();
            while (rs.next()) {
                DauSach ds = new DauSach(rs.getString(1));

                dsDS.add(ds);

            }

            Conn.ColseConn(conn);
        } catch (SQLException e) {
            throw new SQLException("Loi cau lenh !");
        }
        return dsDS;
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
        if (!rs.next()) {
            throw new SQLException("ma dau sach khong ton tai");
        } else {
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
        if (i == 0) {
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
        if (affectedRows == 0) {
            throw new SQLException("Record with MaDS " + maDS + " does not exist.");
        }
        return affectedRows;
    }

    public static List<DauSach> top5() throws SQLException {
        List<DauSach> dsDS = new ArrayList<>();
        String query = "select top(5) ds.MaDs, count(*) as soluotmuon from DauSach ds "
                + "inner join Sach s on s.MaDS = ds.MaDS "
                + "inner join ChiTietMuonTra ctmt on ctmt.MaS = s.MaS "
                + "group by ds.MaDS "
                + "order by count(*) desc";
        try {
            ResultSet rs = Conn.getData(query);
            while (rs.next()) {
                DauSach ds = new DauSach(rs.getString(1));
                ds.setSoLuotMuon(rs.getInt(2));
                dsDS.add(ds);
            }
        } catch (SQLException ex) {
            throw new SQLException("Loi tim top 5 dau sach " + ex.getMessage());
        }
        return dsDS;
    }
    
    public static List<String> MaSachHienCo(String MaDS) throws SQLException {
        int i = 0;
        List<String> dsMaS = new ArrayList<>();
        String query = "select s.mas from DauSach ds "
                + "inner join Sach s on s.MaDS = ds.MaDS "
                + "where MaDS = '"+MaDS+"' "
                + "and TinhTrang like N'có sẵn'";
                
        try {
            ResultSet rs = Conn.getData(query);
            while(rs.next()){
                dsMaS.add(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            throw new SQLException("Loi tim so sach cua 1 dau sach " + ex.getMessage());
        }
        return dsMaS;
    }
    @Override
    public String toString() {
        return "DauSach{" + "maDS=" + maDS + ", tenS=" + tenS + ", tacGia=" + tacGia + ", nhaXB=" + nhaXB + ", namXB=" + namXB + ", donGia=" + donGia + ", soLuotMuon=" + soLuotMuon + '}';
    }

}
