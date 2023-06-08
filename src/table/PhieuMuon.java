/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ketnoi.KetNoiSQL;

/**
 *
 * @author COMPUTER
 */
public class PhieuMuon {

    public static void insert(String maDocGia, String maSach, String ngayMuon, String hanTra) {
        try (
                Connection con = KetNoiSQL.layKetNoi();
                PreparedStatement rs = con.prepareStatement("INSERT INTO PhieuMuon VALUES(?, ?, ?, ?)")) {
            rs.setString(1, maDocGia);
            rs.setString(2, maSach);
            rs.setString(3, ngayMuon);
            rs.setString(4, hanTra);
            rs.executeUpdate();
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void delete(String maDocGia, String maSach) {
        try (
                Connection con = KetNoiSQL.layKetNoi();
                PreparedStatement rs = con.prepareStatement("DELETE FROM PhieuMuon WHERE MANGUOIDUNG = ? AND MASACH = ?")) {
            rs.setString(1, maDocGia);
            rs.setString(2, maSach);
            rs.executeUpdate();
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
