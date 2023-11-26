/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.ChonSPDoiDAO;
import DAO.HoaDonChiTietDAO;
import DAO.HoaDonDAO;
import DAO.PhieuDoiDAO;
import Entity.PhieuDoi;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;

/**
 *
 * @author Admin
 */
public class QuanLyPhieuDoiJPanel extends javax.swing.JPanel {

    ChonSPDoiDAO cspddao = new ChonSPDoiDAO();
    PhieuDoiDAO pdd = new PhieuDoiDAO();
    HoaDonDAO hdd = new HoaDonDAO();
    HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
    DefaultTableModel tableModel = new DefaultTableModel();
    DefaultComboBoxModel<Object> dcbm = new DefaultComboBoxModel<>();
    //int row = 0;
    int soluongtru;
    String soluongdoi;
    double tienTru;
    double totalSum;
    public QuanLyPhieuDoiJPanel() {
        initComponents();
        fillTablePhieuDoi();
    }

    public void fillTablePhieuDoi() {
        tableModel = (DefaultTableModel) tblXemPhieuDoi.getModel();
        tableModel.setRowCount(0);
        try {
//            List<PhieuDoi> list = pdd.selectAll();
            String keyword = txtTimPhieuDoi.getText();
            List<PhieuDoi> list = pdd.selectByKeyword(keyword);
            for (PhieuDoi pd : list) {

                Object[] rowData = new Object[]{
                    pd.getMaPD(),
                    pd.getMaKH(),
                    pd.getMaHD(),
                    pd.getNgayDoiHang(),
                    pd.getTienThanhToan(),
                    pd.getLyDoDoiHang(),};
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu !");
        }
    }
    
    void Delete() {
        String intValue = JOptionPane.showInputDialog(this, "Nhập một giá trị:");
        try {
            int mapb = Integer.parseInt(intValue);
            pdd.Delete(mapb);
            this.fillTablePhieuDoi();
            MsgBox.alert(this, "Hủy thành công !");
        } catch (Exception e) {
            MsgBox.alert(this, "Hủy thất bại !");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblXemPhieuDoi = new javax.swing.JTable();
        btnHuyPD = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTimPhieuDoi = new javax.swing.JTextField();
        btnTimKiemPD = new javax.swing.JButton();
        btnfill = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtmapd = new javax.swing.JTextField();
        txtmahdpd = new javax.swing.JTextField();
        txtmakhpd = new javax.swing.JTextField();
        txttiendatt = new javax.swing.JTextField();
        txtlido = new javax.swing.JTextField();
        btnXuatFile = new javax.swing.JButton();
        btnInPD = new javax.swing.JButton();
        txtNgayDoiHang = new javax.swing.JTextField();

        tblXemPhieuDoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Đổi", "Mã Hóa Đơn", "Mã Khách Hàng", "Ngày Đổi Hàng", "Tiền Đã Thanh Toán", "Lý Do"
            }
        ));
        tblXemPhieuDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblXemPhieuDoiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblXemPhieuDoi);

        btnHuyPD.setText("HỦY PHIẾU ĐỔI");
        btnHuyPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyPDActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm Phiếu Đổi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel8.setText("Mã Phiếu Trả");

        txtTimPhieuDoi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimPhieuDoiCaretUpdate(evt);
            }
        });

        btnTimKiemPD.setText("Tìm Kiếm");
        btnTimKiemPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemPDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimPhieuDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnTimKiemPD, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTimPhieuDoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemPD))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnfill.setText("HIỂN THỊ TẤT CẢ");
        btnfill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfillActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Phiếu Đổi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel17.setText("Mã Phiếu Đổi");

        jLabel18.setText("Mã Hóa Đơn");

        jLabel19.setText("Mã Khách Hàng");

        jLabel20.setText("Ngày Đổi Hàng");

        jLabel21.setText("Tiền Đã TT");

        jLabel22.setText("Lý Do");

        btnXuatFile.setText("XUẤT FILE");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnInPD.setText("IN PHIẾU ĐỔI");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtlido, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(txttiendatt)
                    .addComponent(txtmakhpd)
                    .addComponent(txtmahdpd)
                    .addComponent(txtmapd)
                    .addComponent(txtNgayDoiHang))
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnInPD)
                .addGap(26, 26, 26)
                .addComponent(btnXuatFile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtmapd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtmahdpd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtmakhpd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNgayDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txttiendatt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtlido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInPD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnfill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuyPD)
                        .addGap(38, 38, 38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuyPD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnfill, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1173, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblXemPhieuDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXemPhieuDoiMouseClicked
        // TODO add your handling code here:
        int row = tblXemPhieuDoi.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtmapd.setText(tblXemPhieuDoi.getValueAt(row, 0).toString());
        txtmahdpd.setText(tblXemPhieuDoi.getValueAt(row, 1).toString());
        txtmakhpd.setText(tblXemPhieuDoi.getValueAt(row, 2).toString());
        txtNgayDoiHang.setText(tblXemPhieuDoi.getValueAt(row, 3).toString());
        txttiendatt.setText(tblXemPhieuDoi.getValueAt(row, 4).toString());
        txtlido.setText(tblXemPhieuDoi.getValueAt(row, 5).toString());
    }//GEN-LAST:event_tblXemPhieuDoiMouseClicked

    private void btnHuyPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyPDActionPerformed
        // TODO add your handling code here:
        Delete();
    }//GEN-LAST:event_btnHuyPDActionPerformed

    private void txtTimPhieuDoiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimPhieuDoiCaretUpdate
        // TODO add your handling code here:
        fillTablePhieuDoi();
    }//GEN-LAST:event_txtTimPhieuDoiCaretUpdate

    private void btnTimKiemPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemPDActionPerformed
        // TODO add your handling code here:
        //allowNumericOnly(txtTimPhieuDoi);
        int maPDToSearch = Integer.parseInt(txtTimPhieuDoi.getText());
        tableModel = (DefaultTableModel) tblXemPhieuDoi.getModel();
        // Xóa dữ liệu hiện có trong bảng
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        boolean found = false;
        // Tìm kiếm hóa đơn theo mã và hiển thị kết quả
        List<PhieuDoi> listpd = pdd.selectAll();

        for (PhieuDoi pd : listpd) {
            if (pd.getMaPD() == maPDToSearch) {
                tableModel.addRow(new Object[]{
                    pd.getMaPD(),
                    pd.getMaKH(),
                    pd.getMaHD(),
                    pd.getNgayDoiHang(),
                    pd.getTienThanhToan(),
                    pd.getLyDoDoiHang()
                });
                found = true;
                //fillTableHoaDon();
            }
        }
        if (!found) {
            MsgBox.alert(this, "Không tìm thấy phiếu đổi có mã là: " + maPDToSearch);
            txtTimPhieuDoi.setText("");
        }
    }//GEN-LAST:event_btnTimKiemPDActionPerformed

    private void btnfillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfillActionPerformed
        // TODO add your handling code here:
        fillTablePhieuDoi();
    }//GEN-LAST:event_btnfillActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        //        String DefaultCurrentDirectoryPath = "D:\\FPT Polytechnic\\FALL 2023 (KI 4)\\BLOCK 2\\DU AN 1\\PhieuTra";
        //        // TODO add your handling code here:
        //        File excelFile;
        //        FileInputStream ExcelFIS = null;
        //        BufferedInputStream ExcelBIS = null;
        //
        //        JFileChooser ejfc = new JFileChooser(DefaultCurrentDirectoryPath);
        //        int excelChooser = ejfc.showOpenDialog(null);
        //
        //        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            //            excelFile = ejfc.getSelectedFile();
            //            //ExcelFIS = new FileInputStream(excelFile);
            //            ExcelBIS = new BufferedInputStream(ExcelFIS);
            //            //XSSFWorkbook excelJlabelImport = new XSSFWorkbook();
            //            //XSSFSheet
            //        }
    }//GEN-LAST:event_btnXuatFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyPD;
    private javax.swing.JButton btnInPD;
    private javax.swing.JButton btnTimKiemPD;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JButton btnfill;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblXemPhieuDoi;
    private javax.swing.JTextField txtNgayDoiHang;
    private javax.swing.JTextField txtTimPhieuDoi;
    private javax.swing.JTextField txtlido;
    private javax.swing.JTextField txtmahdpd;
    private javax.swing.JTextField txtmakhpd;
    private javax.swing.JTextField txtmapd;
    private javax.swing.JTextField txttiendatt;
    // End of variables declaration//GEN-END:variables
}
