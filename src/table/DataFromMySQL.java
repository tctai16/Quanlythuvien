/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ketnoi.KetNoiSQL;

/**
 *
 * @author COMPUTER
 */
public class DataFromMySQL {

    public static void getAndShowData(JTable jTable, ArrayList<String> ColumnTitles, String selectQuery) {
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        dtm.setRowCount(0);
        try (
                Connection con = KetNoiSQL.layKetNoi();
                PreparedStatement ps = con.prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vector rowData = new Vector();
                for (String title : ColumnTitles) {
                    rowData.add(rs.getString(title));
                }
                dtm.addRow(rowData);
            }
            
         
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DataFromMySQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static boolean exist(String selectQuery) {
        try (
                Connection con = KetNoiSQL.layKetNoi();
                PreparedStatement ps = con.prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DataFromMySQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int aggregate(String aggregateQuery) {
        try (
                Connection con = KetNoiSQL.layKetNoi();
                PreparedStatement ps = con.prepareStatement(aggregateQuery);
                ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DataFromMySQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return 0;
    }
}
