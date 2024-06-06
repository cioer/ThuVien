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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocGia {

    private String maDG;
    private String tenDG;
    private String soDT;
    private String lop;
    private String khoa;
    private Integer soLanMuon;

    public Integer getSoLanMuon() {
        return soLanMuon;
    }

    public void setSoLanMuon(Integer soLanMuon) {
        this.soLanMuon = soLanMuon;
    }

    // Constructors, getters, and setters
    public DocGia(String maDG) throws SQLException {
        truyvansql(maDG);
    }

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
        if(maDG == null){
            maDG = crateMaDG();
        }
        String query = "INSERT INTO DocGia (MaDG, tenDG, SoDT, Lop, Khoa) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conn.conn(); PreparedStatement statement = connection.prepareStatement(query)) {

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

        try (Connection connection = Conn.conn(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, maDG);

            statement.executeUpdate();
            Conn.ColseConn(connection);
        }
    }

    public void fix() throws SQLException {
        String query = "UPDATE DocGia SET tenDG = ?, SoDT = ?, Lop = ?, Khoa = ? WHERE MaDG = ?";

        try (Connection connection = Conn.conn(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, tenDG);
            statement.setString(2, soDT);
            statement.setString(3, lop);
            statement.setString(4, khoa);
            statement.setString(5, maDG);

            statement.executeUpdate();
            Conn.ColseConn(connection);
        }
    }

    public static List<DocGia> top5DG() throws SQLException {
        List<DocGia> dsDG = new ArrayList<>();
        String query = "select top(5) dg.MaDG , count(*) as soLanMuon from DocGia dg "
                + "inner join MuonTra mt on dg.MaDG = mt.MaDG "
                + "group by dg.MaDG "
                + "order by count(*) desc";
        try {
            ResultSet rs = Conn.getData(query);
            while (rs.next()) {
                DocGia dg = new DocGia(rs.getString(1));

                dg.setSoLanMuon(rs.getInt(2));
                dsDG.add(dg);
            }
        } catch (SQLException ex) {
            throw new SQLException("Loi tim top 5 doc gia " + ex.getMessage());
        }
        return dsDG;
    }

    private void truyvansql(String maDG) throws SQLException {
        String query = "select * from DocGia where MaDG = ?";
        Connection cn = Conn.conn();
        PreparedStatement pstm = cn.prepareStatement(query);
        pstm.setString(1, maDG);
        ResultSet rs = pstm.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Ma doc gia khong ton tai");
        } else {
            this.maDG = rs.getString(1);
            this.tenDG = rs.getString(2);
            this.soDT = rs.getString(3);
            this.lop = rs.getString(4);
            this.khoa = rs.getString(5);

        }
    }

    public List<String> getListSoDT(String sdt) {
        List<String> dsSoDT = new ArrayList<>();
        String query = "select SoDT from DocGia where SoDT LIKE '%' + ? + '%' ";
        try {
            Connection cn = Conn.conn();
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setString(1, sdt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                dsSoDT.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsSoDT;
    }
    public String getMaDG(String sdt) throws SQLException{
        String query = "select maDG from DocGia where sodt = ?";
        try {
            Connection cn = Conn.conn();
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setString(1, sdt);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }else{
                throw new SQLException("khong co so dt nay!");
            }
        } catch (SQLException ex) {
            throw new SQLException("tim so dt bi loi!");
        }
    }
    
    private String crateMaDG(){
        String madg = "dg1";
        String query = "select top(1) madg from DocGia order by madg desc";
        try {
            ResultSet rs = Conn.getData(query);
            if(rs.next()){
                Integer i = Integer.valueOf(rs.getString(1).substring(2));
                i +=1;
                return "dg" + i.toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return madg;
    }
}
