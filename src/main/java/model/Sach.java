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
        String query = "INSERT INTO Sach (MaS, MaDS, ViTri, TinhTrang) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = Conn.conn();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maS);
            statement.setString(2, maDS);
            statement.setString(3, viTri);
            statement.setString(4, tinhTrang);

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
}
