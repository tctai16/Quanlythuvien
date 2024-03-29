/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ketnoi.KetNoiSQL;
import view.DangNhap;

/**
 *
 * @author Apple Bee
 */
public class NguoiDung {

    private String ma, matKhau, ten, gioiTinh, ngaySinh, diaChi, sdt, email, maVaiTro;

    public NguoiDung() {
    }

    public NguoiDung(String ma, String matKhau, String ten, String gioiTinh, String ngaySinh, String diaChi, String sdt, String email, String maVaiTro) {
        this.ma = ma;
        this.matKhau = matKhau;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.maVaiTro = maVaiTro;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(String maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public static void chinhSuaTTNguoiDung(String ma, String ten, String gioiTinh, String ngaySinh, String diaChi, String sdt, String email) {
        String sql = "update NGUOIDUNG set TENNGUOIDUNG = ?, GIOITINH = ?, NGAYSINH = ?, "
                + "DIACHI = ?, SDT = ?, EMAIL = ? "
                + "WHERE MANGUOIDUNG = ?";
        Connection con = KetNoiSQL.layKetNoi();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, gioiTinh);
            ps.setString(3, ngaySinh);
            ps.setString(4, diaChi);
            ps.setString(5, sdt);
            ps.setString(6, email);
            ps.setString(7, ma);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static NguoiDung layThongTinNguoiDung() {
        Connection con = KetNoiSQL.layKetNoi();
        NguoiDung n = null;
        String sql = "select MaNguoiDung,MatKhau,TenNguoiDung,GioiTinh,NgaySinh,DiaChi,SDT,Email,MaVaiTro from NGUOIDUNG where MANGUOIDUNG = '" + DangNhap.getMaNguoiDung() + "'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = new NguoiDung(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return n;
    }

    public static void aggregate(String aggregateQuery, JTable tb) {
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        model.setNumRows(0);
        try (
                Connection con = KetNoiSQL.layKetNoi();
                PreparedStatement ps = con.prepareStatement(aggregateQuery);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2)});
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(NguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
