/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.EmailDAO;
import DAO.KhachHangDAO;
import DAO.LichSuKhachHangDAO;
import DAO.PhieuGiamGiaDAO;
import DAO.TichDiemDAO;
import Entity.Email;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.LichSuKhachHang;
import Entity.PhieuGiamGia;
import Entity.TichDiem;
import Utils.MsgBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.AuthenticationException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhachHangJdialog extends javax.swing.JDialog {

    KhachHangDAO daokh = new KhachHangDAO();
    LichSuKhachHangDAO daolskh = new LichSuKhachHangDAO();
    TichDiemDAO daotd = new TichDiemDAO();
    EmailDAO daoem = new EmailDAO();
    PhieuGiamGiaDAO daopgg = new PhieuGiamGiaDAO();
    Email em;
    KhachHang kh;
    LichSuKhachHang lskh;
    HoaDon hd;

    static int row = -1;

    public KhachHangJdialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        fillTable();

    }

    List<KhachHang> fillTable() {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) tblKhachHang.getModel();
        tblModel.setRowCount(0);
        List<KhachHang> ds = new ArrayList<>();
        try {
            ds = daokh.selectAll(); // đọc dữ liệu từ csdl
            for (KhachHang kh : ds) {
                Object[] row = new Object[10];
                row[0] = kh.getMaKhachHang();
                row[1] = kh.getTenKhachHang();
                row[2] = kh.getLoaiKhachHang();
                row[3] = kh.isGioiTinhKH() ? "Nam" : "Nữ";
//                row[4] = sd.format(kh.getNgaySinhKH());
                row[4] = kh.getNgaySinhKH();
                row[5] = kh.getSoDienThoaiKH();
                row[6] = kh.getEmailKH();
                row[7] = kh.getDiaChiKH();
                row[8] = kh.isTrangThaiKH() ? "Đang hoạt động" : "Ngừng hoạt động";
                row[9] = kh.getGhiChuKH();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.confirm(this, "Lỗi truy vấn dữ liệu");
        }
        return ds;

    }

    public KhachHang getModel() {
        KhachHang kh = new KhachHang();
        if (!txtMaKH.getText().trim().equals("")) {
            kh.setMaKhachHang(Integer.parseInt(txtMaKH.getText()));
        }
        kh.setTenKhachHang(txtTenKH.getText());
        kh.setLoaiKhachHang((String) cboLoaiKH.getSelectedItem());
        kh.setGioiTinhKH(rdoNamKH.isSelected());
        kh.setNgaySinhKH(dcNgaySinhKH.getDate());
        kh.setSoDienThoaiKH(txtSoDienThoaiKH.getText());
        kh.setEmailKH(txtEmailKH.getText());
        kh.setDiaChiKH(txtDiaChiKH.getText());
        kh.setTrangThaiKH(rdoDHD.isSelected());
        kh.setGhiChuKH(txtGhiChuKH.getText());
        return kh;
    }

    void setModel(KhachHang kh) {
        txtMaKH.setText(kh.getMaKhachHang() + "");
        txtTenKH.setText(kh.getTenKhachHang());
        cboLoaiKH.setSelectedItem(kh.getLoaiKhachHang());
        rdoNamKH.setSelected(kh.isGioiTinhKH());
        rdoNuKH.setSelected(!kh.isGioiTinhKH());

        dcNgaySinhKH.setDate(kh.getNgaySinhKH());
        txtSoDienThoaiKH.setText(kh.getSoDienThoaiKH());
        txtEmailKH.setText(kh.getEmailKH());
        txtDiaChiKH.setText(kh.getDiaChiKH());
        rdoDHD.setSelected(kh.isTrangThaiKH());
        rdoNHD.setSelected(!kh.isTrangThaiKH());
        txtGhiChuKH.setText(kh.getGhiChuKH());
//        txtNhanVien.setText(Auth.user.getMaNhanVien());
//        txtNgayDangKi.setText(nh.getNgayDangKi().toString());
    }

    void edit() {
        try {
            Integer maKH = (Integer) tblKhachHang.getValueAt(this.row, 0);
            KhachHang kh = daokh.selectById(maKH);
            if (kh != null) {
                setModel(kh);

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void clearForm() {
//        NguoiHoc nh = new NguoiHoc();
//        this.setModel(nh);
//        this.row = 0;
        txtMaKH.setText("");
        txtTenKH.setText("");
        cboLoaiKH.setSelectedIndex(0);
        rdoNamKH.setSelected(false);
        rdoNuKH.setSelected(false);
        dcNgaySinhKH.setDate(null);
        txtSoDienThoaiKH.setText("");
        txtEmailKH.setText("");
        txtDiaChiKH.setText("");
        rdoDHD.setSelected(false);
        rdoNHD.setSelected(false);
        txtGhiChuKH.setText("");

    }

    void updateKhachHang() {
        KhachHang kh = getModel();
        System.out.println(kh.getMaKhachHang());
        try {
            daokh.update(kh);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Chỉnh sửa thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Chỉnh sửa thất bại");
        }
    }

    void insertKhachHang() {
        KhachHang kh = getModel();
        System.out.println(kh.getMaKhachHang());
        try {
            daokh.insert(kh);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Chỉnh sửa thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Chỉnh sửa thất bại");
        }
    }

    void deleteKhachHang(Integer MaKhachHang) {
        KhachHang kh = getModel();
        if (MsgBox.confirm(this, "Bạn chắn chắn xóa người học này ?")) {
            try {
                daokh.updateTT(MaKhachHang);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public boolean checkValidate() {
//        if (txtTenKH.getText().isBlank()) {
//            MsgBox.alert(this, "Họ tên khách hàng không được để trống");
//            return false;
//        }
//        if (rdoNamKH.isSelected() == false && rdoNuKH.isSelected() == false) {
//            MsgBox.alert(this, "Hãy chọn giới tính của khách hàng");
//            return false;
//        }
////        if (dcNgaySinhKH.getText().isBlank()) {
////            MsgBox.alert(this, "Ngày sinh không được để trống");
////            return false;
////        }
//        if (txtSoDienThoaiKH.getText().isBlank()) {
//            MsgBox.alert(this, "Số điện thoại không được để trống");
//            return false;
//        }
//        if (txtEmailKH.getText().isBlank()) {
//            MsgBox.alert(this, "Email không được để trống");
//            return false;
//        }
//        if (txtDiaChiKH.getText().isBlank()) {
//            MsgBox.alert(this, "Địa chỉ không được để trống");
//            return false;
//        }
//        if (rdoDHD.isSelected() == false && rdoNHD.isSelected() == false) {
//            MsgBox.alert(this, "Hãy chọn trạng thái");
//            return false;
//        }
//        return true;
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChuKH = new javax.swing.JTextArea();
        rdoNamKH = new javax.swing.JRadioButton();
        rdoNuKH = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSoDienThoaiKH = new javax.swing.JTextField();
        txtEmailKH = new javax.swing.JTextField();
        txtDiaChiKH = new javax.swing.JTextField();
        cboLoaiKH = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        rdoDHD = new javax.swing.JRadioButton();
        rdoNHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        dcNgaySinhKH = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Loại Khách Hàng", "Giới Tính", "Ngày Sinh", "Số Điện Thoại", "Email", "Địa Chỉ", "Trạng Thái ", "Ghi Chú "
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh Sách Khách Hàng", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã Khách Hàng");

        jLabel2.setText("Tên Khách Hàng");

        jLabel3.setText("Giới Tính");

        jLabel4.setText("Ngày Sinh");

        jLabel5.setText("Số Điện Thoại");

        jLabel6.setText("Email");

        jLabel7.setText("Địa Chỉ ");

        jLabel8.setText("Ghi Chú");

        txtGhiChuKH.setColumns(20);
        txtGhiChuKH.setRows(5);
        jScrollPane1.setViewportView(txtGhiChuKH);

        rdoNamKH.setText("Nam");

        rdoNuKH.setText("Nữ");

        jLabel19.setText("Loại Khách Hàng");

        txtMaKH.setEnabled(false);

        txtTenKH.setText("Khách vãng lai");

        cboLoaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách thường mua", "Khách thân thiết", "Khách VIP" }));

        jLabel12.setText("Trạng Thái");

        rdoDHD.setSelected(true);
        rdoDHD.setText("Đang hoạt động");

        rdoNHD.setText("Ngừng hoạt động");

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        dcNgaySinhKH.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                        .addComponent(txtMaKH)
                                        .addComponent(dcNgaySinhKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEmailKH))
                                    .addGap(141, 141, 141)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rdoDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNHD)
                                .addGap(187, 187, 187))))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rdoNamKH, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNuKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoaiKH)
                            .addComponent(txtDiaChiKH)
                            .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNuKH)
                            .addComponent(rdoNamKH))))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(45, 45, 45)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmailKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(rdoDHD)
                        .addComponent(rdoNHD)
                        .addComponent(jLabel8))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm Khách Hàng Mới", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        if (evt.getClickCount() == 1) {
            this.row = tblKhachHang.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//        if (checkValidate()) {
        insertKhachHang();
//        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        row = tblKhachHang.getSelectedRow();
        this.dispose();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhachHangJdialog dialog = new KhachHangJdialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboLoaiKH;
    private com.toedter.calendar.JDateChooser dcNgaySinhKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoDHD;
    private javax.swing.JRadioButton rdoNHD;
    private javax.swing.JRadioButton rdoNamKH;
    private javax.swing.JRadioButton rdoNuKH;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtEmailKH;
    private javax.swing.JTextArea txtGhiChuKH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDienThoaiKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
