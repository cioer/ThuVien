/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.SQLException;
import model.NhanVien;
/**
 *
 * @author coc
 */
public class LoginAndSignUpCtrl {
    public NhanVien SignUp(String name,String soDT,String pass) throws SQLException{
        NhanVien nv = new NhanVien();
        nv.setMaNV(nv.CreateMaNV());
        nv.setTenNV(name);
        nv.setSoDT(soDT);
        nv.setPass(pass);
        nv.setVaiTro("Nhân Viên");
        nv.add();
        return nv;
    }
    
    public NhanVien Login(String soDT,String pass) throws SQLException{
        NhanVien nv = new NhanVien();
        nv.setSoDT(soDT);
        nv.setPass(pass);
        nv.login();
        return nv;
    }
    
    
}
