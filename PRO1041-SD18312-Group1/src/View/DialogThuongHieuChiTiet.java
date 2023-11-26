/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.ThuongHieuChiTietDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import Entity.ThuongHieu;
import Entity.ThuongHieuChiTiet;
import Entity.XuatXu;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DialogThuongHieuChiTiet extends javax.swing.JDialog {

    ThuongHieuChiTietDAO thctdao = new ThuongHieuChiTietDAO();
    ThuongHieuDAO thdao = new ThuongHieuDAO();
    XuatXuDAO xuDAO = new XuatXuDAO();
    int row = -1;

    /**
     * Creates new form FormJDialog
     */
    public DialogThuongHieuChiTiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initTable();
        fillTable();
        initComboBoxThuongHieu();
        initComboBoxXuatXu();
        initComboboxTrangThai();
    }

    private void initComboboxTrangThai() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboTrangThai.getModel();
        boxModel.removeAllElements();
        String[] cboList = new String[]{
            "Đang hoạt động", "Ngừng hoạt động"
        };
        for (String cbList : cboList) {
            boxModel.addElement(cbList);
        }
        cboTrangThai.setModel(boxModel);
    }

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tblThuongHieuChiTiet.getModel();
        String[] columns = new String[]{
            "Mã thương hiệu chi tiết", "Tên thương hiệu", "Tên xuất xứ", "Email",
            "Website", "Số điện thoại", "Địa điểm bảo hành", "Ghi chú", "Trạng thái"
        };
        tableModel.setColumnIdentifiers(columns);
    }

    private void fillTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tblThuongHieuChiTiet.getModel();
        tableModel.setRowCount(0);
        List<ThuongHieuChiTiet> list = thctdao.selectAll();
        for (ThuongHieuChiTiet thct : list) {
            tableModel.addRow(new Object[]{
                thct.getMaThuongHieu(), thdao.selectById(thct.getMaThuongHieu()), xuDAO.selectById(thct.getMaXuatXu()),
                thct.getEmailChiTiet(), thct.getWebsiteChiTiet(), thct.getSoDienThoai(), thct.getDiaDiemBHTHCT(), thct.getGhiChuTHCT(), thct.isTrangThai() ? "Đang hoạt động" : "Ngừng hoạt động"
            });
        }
    }

    private void initComboBoxThuongHieu() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboThuongHieu.getModel();
        boxModel.removeAllElements();
        List<ThuongHieu> list = thdao.selectAll();
        for (ThuongHieu th : list) {
            boxModel.addElement(th);
        }
        cboThuongHieu.setModel(boxModel);
    }

    private void initComboBoxXuatXu() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboXuatXu.getModel();
        boxModel.removeAllElements();
        List<XuatXu> list = xuDAO.selectAll();
        for (XuatXu xx : list) {
            boxModel.addElement(xx);
        }
        cboXuatXu.setModel(boxModel);
    }

    private ThuongHieuChiTiet getModel() {
        ThuongHieu th = (ThuongHieu) cboThuongHieu.getSelectedItem();
        XuatXu xx = (XuatXu) cboXuatXu.getSelectedItem();
        return new ThuongHieuChiTiet(0, xx.getMaXuatXu(), txtEmail.getText().trim(), txtWebsite.getText().trim(), txtSoDienThoai.getText(), txtGhiChu.getText().trim(), txtGhiChu.getText().trim(), cboTrangThai.getSelectedItem() == "Đang hoạt động" ? true : false);
    }

    private boolean validateForm() {
        String email = txtEmail.getText().trim();
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Email không được để trống ");
            return false;
        }

        String website = txtWebsite.getText().trim();
        if (website.equals("")) {
            JOptionPane.showMessageDialog(this, "Website không được để trống .");
            return false;
        }

        String sdt = txtSoDienThoai.getText().trim();
        if (sdt.equals("")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống .");
            return false;
        }

        String ddbh = txtDiaDiemBaoHanh.getText().trim();
        if (ddbh.equals("")) {
            JOptionPane.showMessageDialog(this, "Địa điểm bảo hành không được để trống .");
            return false;
        }

        String ghiChu = txtGhiChu.getText().trim();
        if (ghiChu.equals("")) {
            JOptionPane.showMessageDialog(this, "Ghi chú không được để trống .");
            return false;
        }

        return true;
    }

    private void clear() {
        txtMaTHCT.setText("");
        cboThuongHieu.setSelectedIndex(0);
        cboXuatXu.setSelectedIndex(0);
        txtEmail.setText("");
        txtWebsite.setText("");
        txtSoDienThoai.setText("");
        txtDiaDiemBaoHanh.setText("");
        txtGhiChu.setText("");
        cboTrangThai.setSelectedIndex(0);
        row = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuongHieuChiTiet = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        cboTrangThai = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        txtWebsite = new javax.swing.JTextField();
        txtMaTHCT = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        cboXuatXu = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        btnThemThuongHieu = new javax.swing.JButton();
        btnThemXuatXu = new javax.swing.JButton();
        txtDiaDiemBaoHanh = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thương hiệu chi tiết"));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tblThuongHieuChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblThuongHieuChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieuChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuongHieuChiTiet);

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMaTHCT.setEnabled(false);

        cboXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThemThuongHieu.setText("+");
        btnThemThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuongHieuActionPerformed(evt);
            }
        });

        btnThemXuatXu.setText("+");
        btnThemXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXuatXuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtWebsite)
                    .addComponent(txtEmail)
                    .addComponent(txtMaTHCT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboXuatXu, 0, 360, Short.MAX_VALUE)
                            .addComponent(cboThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDiaDiemBaoHanh))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMaTHCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemThuongHieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemXuatXu))
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDiaDiemBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new java.awt.GridLayout(9, 1));

        jLabel3.setText("Mã THCT :");
        jPanel3.add(jLabel3);

        jLabel6.setText("Tên thương hiệu :");
        jPanel3.add(jLabel6);

        jLabel10.setText("Tên xuất xứ :");
        jPanel3.add(jLabel10);

        jLabel4.setText("Email :");
        jPanel3.add(jLabel4);

        jLabel5.setText("Website :");
        jPanel3.add(jLabel5);

        jLabel7.setText("Số điện thoại :");
        jPanel3.add(jLabel7);

        jLabel9.setText("Địa điểm BHTHCT :");
        jPanel3.add(jLabel9);

        jLabel8.setText("Ghi chú :");
        jPanel3.add(jLabel8);

        jLabel2.setText("Trạng thái :");
        jPanel3.add(jLabel2);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            ThuongHieu thuongHieu = (ThuongHieu) cboThuongHieu.getSelectedItem();
            XuatXu xuatXu = (XuatXu) cboXuatXu.getSelectedItem();
            if (thctdao.getThuongHieuChiTiet(thuongHieu.getMaThuongHieu(), xuatXu.getMaXuatXu()) == null) {
                if (thctdao.insert(getModel()) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm mới thương hiệu chi tiết thành công .");
                    fillTable();
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm mới thương hiệu chi tiết không thành công .");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thương hiệu chi tiết đã tồn tại trong bảng .");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(row < -1){
            if (validateForm()) {
            ThuongHieuChiTiet thct = getModel();
            thct.setMaThuongHieu(Integer.parseInt(txtMaTHCT.getText().trim()));
            if (thctdao.update(thct) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thương hiệu chi tiết thành công .");
                fillTable();
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thương hiệu chi tiết không thành công .");
            }
        }
        }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (row > -1) {
            ThuongHieuChiTiet thct = getModel();
            thct.setMaThuongHieu(Integer.parseInt(txtMaTHCT.getText().trim()));
            thct.setTrangThai(false);
            if (thctdao.update(thct) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thương hiệu chi tiết thành công .");
                fillTable();
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thương hiệu chi tiết không thành công .");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trên bảng được chọn .");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblThuongHieuChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieuChiTietMouseClicked
        // TODO add your handling code here:
        row = tblThuongHieuChiTiet.getSelectedRow();
        txtMaTHCT.setText(tblThuongHieuChiTiet.getValueAt(row, 0).toString());
        cboThuongHieu.setSelectedItem(tblThuongHieuChiTiet.getValueAt(row, 1).toString());
        Object selectedTh = tblThuongHieuChiTiet.getValueAt(row, 1);
        DefaultComboBoxModel<ThuongHieu> cbBoxModel = (DefaultComboBoxModel) cboThuongHieu.getModel();
        for (int i = 0; i < cbBoxModel.getSize(); i++) {
            ThuongHieu thuongHieu = cbBoxModel.getElementAt(i);
            if (thuongHieu.toString().equals(selectedTh.toString())) {
                cboThuongHieu.setSelectedItem(thuongHieu);
                break;
            }
        }

        Object selectedXx = tblThuongHieuChiTiet.getValueAt(row, 2);
        DefaultComboBoxModel<XuatXu> cbBoxModel1 = (DefaultComboBoxModel) cboXuatXu.getModel();
        for (int i = 0; i < cbBoxModel1.getSize(); i++) {
            XuatXu xuatXu = cbBoxModel1.getElementAt(i);
            if (xuatXu.toString().equals(selectedXx.toString())) {
                cboXuatXu.setSelectedItem(xuatXu);
                break;
            }
        }
        txtEmail.setText(tblThuongHieuChiTiet.getValueAt(row, 3).toString());
        txtWebsite.setText(tblThuongHieuChiTiet.getValueAt(row, 4).toString());
        txtSoDienThoai.setText(tblThuongHieuChiTiet.getValueAt(row, 5).toString());
        txtDiaDiemBaoHanh.setText(tblThuongHieuChiTiet.getValueAt(row, 6).toString());
        txtGhiChu.setText(tblThuongHieuChiTiet.getValueAt(row, 7).toString());
        if (tblThuongHieuChiTiet.getValueAt(row, 8).toString() == "Đang hoạt động") {
            cboTrangThai.setSelectedIndex(0);
        } else {
            cboTrangThai.setSelectedIndex(1);
        }

    }//GEN-LAST:event_tblThuongHieuChiTietMouseClicked

    private void btnThemThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuongHieuActionPerformed
        // TODO add your handling code here:
        DialogThuongHieu dth = new DialogThuongHieu(null, true);
        dth.setVisible(true);
        initComboBoxThuongHieu();
    }//GEN-LAST:event_btnThemThuongHieuActionPerformed

    private void btnThemXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXuatXuActionPerformed
        // TODO add your handling code here:
        DialogXuatXu dxx = new DialogXuatXu(null, true);
        dxx.setVisible(true);
        initComboBoxXuatXu();
    }//GEN-LAST:event_btnThemXuatXuActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(DialogThuongHieuChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogThuongHieuChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogThuongHieuChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogThuongHieuChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogThuongHieuChiTiet dialog = new DialogThuongHieuChiTiet(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemThuongHieu;
    private javax.swing.JButton btnThemXuatXu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblThuongHieuChiTiet;
    private javax.swing.JTextField txtDiaDiemBaoHanh;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaTHCT;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtWebsite;
    // End of variables declaration//GEN-END:variables
}
