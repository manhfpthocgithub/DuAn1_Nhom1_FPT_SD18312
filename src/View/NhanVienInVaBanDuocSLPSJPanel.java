/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.LichLamViecDao;
import DAO.NhanVienDao;
import Entity.LichLamViec;
import Entity.NhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import Utils.JDBCHelper;
import Utils.MsgBox;


/**
 *
 * @author Pico123
 */
public class NhanVienInVaBanDuocSLPSJPanel extends javax.swing.JPanel {

    NhanVienDao dao = new NhanVienDao();
    int row = 0;
    List<NhanVien> list = new ArrayList<>();
    /**
     * Creates new form NhanVienInVaBanDuocSLPSJPanel
     */
    public NhanVienInVaBanDuocSLPSJPanel() {
        initComponents();
        fillComboboxGioiTinhKH();
        addlisst();
        fillTable();
    }

    //HIỂN THỊ DANH SÁCH BÊN BẢNG tblNhanVien
    void fillTable(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblNhanVien.getModel();
        tblModel.setRowCount(0);
        try {
//            String keyword = txtTimKiem.getText();
            //List<NhanVien> ds = dao.selectByKeyword(keyword); 
            List<NhanVien> ds = dao.selectAll4();
            for (NhanVien nv : ds) {
                Object[] row = new Object[10];
                row[0] = nv.getMaNV();
                row[1] = nv.getTenNV();
                row[2] = nv.isGioitinh() ? "Nam" : "Nữ";
                row[3] = sd.format(nv.getNgaysinh());
                row[4] = nv.getSoCCCD();
                row[5] = nv.getSoDT();
                row[6] = nv.getEmail();
                row[7] = nv.getDiachi();
                row[8] = nv.isTrangthai()? "Đang làm" : "Đã nghỉ";
                row[9] = nv.getGhichu();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    //TÌM KIẾM BÊN tblNhanVien
    void fillTableTimKiem(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblNhanVien.getModel();
        tblModel.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<NhanVien> ds = dao.selectByKeyword4(keyword); 
            for (NhanVien nv : ds) {
                Object[] row = new Object[10];
                row[0] = nv.getMaNV();
                row[1] = nv.getTenNV();
                row[2] = nv.isGioitinh() ? "Nam" : "Nữ";
                row[3] = sd.format(nv.getNgaysinh());
                row[4] = nv.getSoCCCD();
                row[5] = nv.getSoDT();
                row[6] = nv.getEmail();
                row[7] = nv.getDiachi();
                row[8] = nv.isTrangthai()? "Đang làm" : "Đã nghỉ";
                row[9] = nv.getGhichu();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    //TÌM KIẾM BẰNG CBO BÊN DANH SÁCH NHÂN VIÊN
    void fillComboboxGioiTinhKH() {
        List<String> gioiTinhKH = dao.selectAllGioiTinhKH();
        Set<String> uniqueGioiTinhKH = new HashSet<>(gioiTinhKH);
        for (String item1 : uniqueGioiTinhKH) {
            cboTimgioitinh.addItem(item1);
        }
    }
    
    //TÌM KIẾM BẰNG CBO BÊN DANH SÁCH NHÂN VIÊN
    void addlisst(){
    cboTimgioitinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String gioiTinhKH = (String) cboTimgioitinh.getSelectedItem();
                Integer thuTuGioiTinh = null;

                if (gioiTinhKH.equals("Nam")) {
                    thuTuGioiTinh = 1;
                }
                if (gioiTinhKH.equals("Nữ")) {
                    thuTuGioiTinh = 0;
                }
                if (gioiTinhKH.equals("All")) {
                    fillTable();
                }

                filterByGioiTinh(thuTuGioiTinh);
            }
        });
    }
    
    //TÌM KIẾM BẰNG CBO BÊN DANH SÁCH NHÂN VIÊN
    public List<NhanVien> filterByGioiTinh(Integer gioiTinhKH) {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tableModel = (DefaultTableModel) tblNhanVien.getModel();
        tableModel.setRowCount(0);
        List<NhanVien> lists1 = dao.filterByGioiTinhKH2(gioiTinhKH);
        for (NhanVien nv : lists1) {
             Object[] row = new Object[10];
                row[0] = nv.getMaNV();
                row[1] = nv.getTenNV();
                row[2] = nv.isGioitinh() ? "Nam" : "Nữ";
                row[3] = sd.format(nv.getNgaysinh());
                row[4] = nv.getSoCCCD();
                row[5] = nv.getSoDT();
                row[6] = nv.getEmail();
                row[7] = nv.getDiachi();
                row[8] = nv.isTrangthai()? "Đang làm" : "Đã nghỉ";
                row[9] = nv.getGhichu();
            tableModel.addRow(row);
        }

        return lists1;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        cboTimgioitinh = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNVinHD = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSLSPcuaNV = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Số CCCD", "Số ĐT", "Email", "Địa chỉ", "Trạng thái", "Ghi chú"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNhanVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        cboTimgioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        jLabel18.setText("Tìm kiếm tên nhân viên");
        jLabel18.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(cboTimgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 411, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cboTimgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(329, 329, 329))
        );

        jTabbedPane1.addTab("Danh sách nhân viên", jPanel4);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhân viên in những hóa đơn"));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblNVinHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Mã hóa đơn", "Ngày tạo hóa đơn", "Trạng thái hóa đơn"
            }
        ));
        jScrollPane2.setViewportView(tblNVinHD);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng sản phẩn nhân viên bán được"));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblSLSPcuaNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã nhân viên", "Số lượng sản phẩm bán được"
            }
        ));
        jScrollPane3.setViewportView(tblSLSPcuaNV);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:

        //         DefaultTableModel dtm = (DefaultTableModel) this.tblNhanVien.getModel();
        //        int index = this.tblNhanVien.getSelectedRow();
        //        if(index == -1){
            //            return;
            //        }
        //        String ma = dtm.getValueAt(index, 0).toString();
        //        txtMaNV.setText(ma);
        //        String ten = dtm.getValueAt(index, 1).toString();
        //        txtTenNV.setText(ten);
        //        String gioitinh = dtm.getValueAt(index, 2).toString();
        //        if(gioitinh.equalsIgnoreCase("Nam")){
            //            rdoNam.setSelected(true);
            //        }else{
            //            rdoNu.setSelected(true);
            //        }
        //        String ngaysinh = dtm.getValueAt(index, 3).toString();
        //        txtNgaySinh.setToolTipText(ngaysinh);
        //        String socccd = dtm.getValueAt(index, 4).toString();
        //        txtSoCccd.setText(socccd);
        //        String sodt = dtm.getValueAt(index, 5).toString();
        //        txtSodt.setText(sodt);
        //        String email = dtm.getValueAt(index, 6).toString();
        //        txtEmail.setText(email);
        //        String diachi = dtm.getValueAt(index, 7).toString();
        //        txtDiachi.setText(diachi);
        //        String trangthai = dtm.getValueAt(index, 8).toString();
        //        if(trangthai.equalsIgnoreCase("Hoạt động")){
            //            rdoHD.setSelected(true);
            //        }else{
            //            rdoNHD.setSelected(true);
            //        }
        //        String ghichu = dtm.getValueAt(index, 9).toString();
        //        txtGhichu.setText(ghichu);
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblNVinHD.getModel();
        tblModel.setRowCount(0);
        int row = tblNhanVien.getSelectedRow();
        list = dao.selectNVinHD(tblNhanVien.getValueAt(row, 0).toString());
        try {
            //            String keyword = txtTimNV.getText();
            //            List<NhanVien> ds = dao.selectByKeyword2(keyword);

            for (NhanVien nv : list) {
                Object[] row1 = new Object[4];
                row1[0] = nv.getMaNV();
                row1[1] = nv.getMahd();
                row1[2] = sd.format(nv.getNgaytaoHD());
                row1[3] = nv.getTrangthaihd();
                tblModel.addRow(row1);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }

        tblModel = (DefaultTableModel) this.tblSLSPcuaNV.getModel();
        tblModel.setRowCount(0);
        list = dao.selectSLSPbancuaNV(tblNhanVien.getValueAt(row, 0).toString());
        try {
            //            String keyword = txtTimNV.getText();
            //            List<NhanVien> ds = dao.selectByKeyword2(keyword);

            for (NhanVien nv : list) {
                Object[] row1 = new Object[4];
                row1[0] = nv.getMaNV();
                row1[1] = nv.getSoluong();
                tblModel.addRow(row1);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void tblNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMousePressed
        // TODO add your handling code here:
        //        if (evt.getClickCount() == 1) {
            //            this.row = tblNhanVien.rowAtPoint(evt.getPoint());
            //            edit();
            //        }
    }//GEN-LAST:event_tblNhanVienMousePressed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
        fillTableTimKiem();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboTimgioitinh;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblNVinHD;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblSLSPcuaNV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
