/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ketnoi;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class KetNoiSQL {

    /**
     * @param args the command line arguments
     */
    public static Connection layKetNoi() {
        Connection ketNoi = null;
        String uRL = "jdbc:mysql://localhost:3306/QLTV";
        String userName = "root";
        String password = "hale0806";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ketNoi = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Ket noi CSDL thanh cong!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Khong the ket noi voi CSDL!");
        }
        return ketNoi;
    }
//    public static void main(String[] args) {
//        // TODO code application logic here
//        KetNoiSQL kn = new KetNoiSQL();
//        kn.layKetNoi();
//    }
}
