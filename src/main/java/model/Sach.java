/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author coc
 */
public class Sach {

    private String maS;
    private String maDS;
    private String viTri;
    private String tinhTrang;

    public Sach() {
    }

    public void setMaS(String maS) {
        this.maS = maS;
    }

    public void setMaDS(String maDS) {
        this.maDS = maDS;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getMaDS() {
        return maDS;
    }

    public String getMaS() {
        return maS;
    }

    public String getViTri() {
        return viTri;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public Sach(String maS, String maDS, String viTri, String tinhTrang) {
        this.maDS = maDS;
        this.maS = maS;
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
    }

    public void add() throws SQLException {
        if(this.maS == null){
            this.maS = crateMaDG();
        }
        String query = "INSERT INTO Sach (MaS, MaDS, ViTri, TinhTrang) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = Conn.conn();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maS);
            statement.setString(2, maDS);
            statement.setString(3, viTri);
            statement.setString(4, "có sẵn");

            statement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                Conn.ColseConn(connection);
            }
        }
    }

    public void fix() throws SQLException {
        String query = "UPDATE Sach SET MaDS = ?, ViTri = ?, TinhTrang = ? WHERE MaS = ?";
        Connection connection = null;
        try {
            connection = Conn.conn();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maDS);
            statement.setString(2, viTri);
            statement.setString(3, tinhTrang);
            statement.setString(4, maS);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Record with MaS " + maS + " does not exist.");
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                Conn.ColseConn(connection);
            }
        }
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM Sach WHERE MaS = ?";
        Connection connection = null;
        try {
            connection = Conn.conn();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maS);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Record with MaS " + maS + " does not exist.");
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                Conn.ColseConn(connection);
            }
        }
    }

    public static List<Sach> getListSach(String maDS) throws SQLException {
        String query = "SELECT * FROM Sach WHERE MaDS = ?";
        List<Sach> sachList = new ArrayList<>();

        try (Connection connection = Conn.conn(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, maDS);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new SQLException("No books found with MaDS: " + maDS);
                } else {
                    do {
                        Sach sach = new Sach();
                        sach.setMaS(resultSet.getString("MaS"));
                        sach.setMaDS(resultSet.getString("MaDS"));
                        sach.setViTri(resultSet.getString("ViTri"));
                        sach.setTinhTrang(resultSet.getString("TinhTrang"));

                        sachList.add(sach);
                    } while (resultSet.next());
                }
            }
            Conn.ColseConn(connection);
        } catch (SQLException e) {
            throw e;
        }

        return sachList;
    }

    public static void DoiTinhTrang(String mas) throws SQLException {
        String ranh = "có sẵn";
        String Koranh = "đã mượn";
        String update = ranh;
        String query = "select TinhTrang from Sach where mas = '" + mas + "'";
        ResultSet rs = Conn.getData(query);
        if (rs.next()) {
            if (rs.getString(1).equals(ranh)) {
                update = Koranh;
            } else {
                update = ranh;
            }
        }
        update = "update Sach set TinhTrang = N'" + update + "' where mas = '" + mas + "'";
        Conn.update(update);
    }
    private String crateMaDG(){
        String madg = "s1";
        String query = "select top(1) mas from Sach order by mas desc";
        try {
            ResultSet rs = Conn.getData(query);
            if(rs.next()){
                Integer i = Integer.valueOf(rs.getString(1).substring(1));
                Random rm = new Random();
                i +=rm.nextInt(Integer.MAX_VALUE);
                return "s" + i.toString();
            }
        } catch (SQLException ex) {
            System.err.println("loi tao ma sach!");
        }
        return madg;
    }
}
