/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.ThuongHieuDAO;
import Entity.MauSac;
import Entity.ThuongHieu;
import Utils.XImage;
import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DialogThuongHieu extends javax.swing.JDialog {

    ThuongHieuDAO thdao = new ThuongHieuDAO();
    int row = - 1;

    /**
     * Creates new form FormThuongHieu
     */
    public DialogThuongHieu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initComboBox();
        initTable();
        fillTable();
    }

    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tblThuongHieu.getModel();
        String[] columns = new String[]{
            "Mã Thương hiệu", "Tên thương hiệu", "Tên ảnh", "Ngày tạo", "Ghi chú", "Trạng thái"
        };
        tableModel.setColumnIdentifiers(columns);
    }

    private void fillTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tblThuongHieu.getModel();
        tableModel.setRowCount(0);
        List<ThuongHieu> list = thdao.selectAll();
        for (ThuongHieu th : list) {
            tableModel.addRow(new Object[]{
                th.getMaThuongHieu(), th.getTenThuongHieu(), th.getAnhThuongHieu(), th.getNgayTao(), th.getGhiChuTH(), th.isTrangThai() ? "Đang hoạt động" : "Ngừng hoạt động"
            });
        }
        tblThuongHieu.setModel(tableModel);
    }

    private void initComboBox() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboTrangThai.getModel();
        boxModel.removeAllElements();
        String[] cboItem = new String[]{
            "Đang hoạt động", "Ngừng hoạt động"
        };
        cboTrangThai.setModel(new DefaultComboBoxModel<>(cboItem));
    }

    private ThuongHieu getModel() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayNhap = sdf.format(dcNgayNhap.getDate());
        return new ThuongHieu(0, txtTenThuongHieu.getText().trim(), lblAnhThuongHieu.getToolTipText(), ngayNhap, txtGhiChu.getText().trim(), cboTrangThai.getSelectedItem() == "Đang hoạt động" ? true : false);
    }
// đường dẫn
    private void chonAnh() {
        JFileChooser jfc = new JFileChooser("A:\\Du an 1\\DuAn1_Nhom1\\src\\Images");
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            String tenFileChon = file.getName();

            XImage.saveTH(file); // Lưu hình vào thư mục logos
            // Đọc hình từ thư mục logos
            ImageIcon icon = XImage.read(file.getName());
            int height = lblAnhThuongHieu.getHeight();
            int width = lblAnhThuongHieu.getWidth();
            Image i;
            try {
                i = ImageIO.read(file).getScaledInstance(width, height, 0);
                icon.setImage(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            lblAnhThuongHieu.setIcon(icon);
            System.out.println(tenFileChon);
            lblAnhThuongHieu.setToolTipText(tenFileChon);
        }
    }

    private boolean validateForm() {
        String tenThuongHieu = txtTenThuongHieu.getText().trim();
        if (tenThuongHieu.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên thương hiệu không được để trống .");
            return false;
        }

        try {
            Date date = dcNgayNhap.getDate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày không đúng định dạng vui lòng chọn lại .");
            return false;
        }

        String tenAnh = lblAnhThuongHieu.getToolTipText().trim();
        if (tenAnh.equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ảnh thương hiệu .");
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
        txtMaThuongHieu.setText("");
        txtTenThuongHieu.setText("");
        dcNgayNhap.setDate(null);
        txtGhiChu.setText("");
        lblAnhThuongHieu.setToolTipText("");
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
        tblThuongHieu = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtGhiChu = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        txtMaThuongHieu = new javax.swing.JTextField();
        txtTenThuongHieu = new javax.swing.JTextField();
        dcNgayNhap = new com.toedter.calendar.JDateChooser();
        lblAnhThuongHieu = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thương hiệu"));

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

        tblThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuongHieu);

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMaThuongHieu.setEnabled(false);

        dcNgayNhap.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaThuongHieu)
                    .addComponent(txtGhiChu)
                    .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenThuongHieu)
                    .addComponent(dcNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dcNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblAnhThuongHieu.setToolTipText("");
        lblAnhThuongHieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblAnhThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhThuongHieuMouseClicked(evt);
            }
        });

        jPanel3.setLayout(new java.awt.GridLayout(5, 1, 0, 20));

        jLabel1.setText("Mã thương hiệu :");
        jPanel3.add(jLabel1);

        jLabel4.setText("Tên thương hiệu :");
        jPanel3.add(jLabel4);

        jLabel3.setText("Ngày tạo :");
        jPanel3.add(jLabel3);

        jLabel2.setText("Ghi chú :");
        jPanel3.add(jLabel2);

        jLabel5.setText("Trạng thái :");
        jPanel3.add(jLabel5);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblAnhThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnhThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            if (thdao.KiemTraThuongHieu(txtTenThuongHieu.getText())) {
                if (thdao.insert(getModel()) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thương hiệu thành công .");
                    fillTable();
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thương hiệu không thành công .");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tên thương hiệu đã tồn tại trong bảng .");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (row > -1) {
            if (validateForm()) {
                ThuongHieu thuongHieu = getModel();
                thuongHieu.setMaThuongHieu(Integer.parseInt(txtMaThuongHieu.getText().trim()));
                if (thdao.update(thuongHieu) > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thương hiệu thành công .");
                    fillTable();
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thương hiệu không thành công . ");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào được chọn .");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        if (row > -1) {
            ThuongHieu thuongHieu = getModel();
            thuongHieu.setMaThuongHieu(Integer.parseInt(txtMaThuongHieu.getText().trim()));
            thuongHieu.setTrangThai(false);
            if (thdao.update(thuongHieu) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thương hiệu thành công .");
                fillTable();
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thương hiệu không thành công . ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào được chọn .");
        }
    }//GEN-LAST:event_btnXoaActionPerformed
// Đường dẫn
    private void tblThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieuMouseClicked
        // TODO add your handling code here:
        row = tblThuongHieu.getSelectedRow();
        txtMaThuongHieu.setText(tblThuongHieu.getValueAt(row, 0).toString());
        txtTenThuongHieu.setText(tblThuongHieu.getValueAt(row, 1).toString());
        lblAnhThuongHieu.setToolTipText(tblThuongHieu.getValueAt(row, 2).toString());
        ImageIcon icon = XImage.read(tblThuongHieu.getValueAt(row, 2).toString());
        int height = lblAnhThuongHieu.getHeight();
        int width = lblAnhThuongHieu.getWidth();
        File file = new File("logosTH\\" + tblThuongHieu.getValueAt(row, 2).toString());
        Image i;
        try {
            i = ImageIO.read(file).getScaledInstance(width, height, 0);
            icon.setImage(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblAnhThuongHieu.setIcon(icon);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(tblThuongHieu.getValueAt(row, 3).toString());
            dcNgayNhap.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(DialogThuongHieu.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtGhiChu.setText(tblThuongHieu.getValueAt(row, 4).toString());
        if (tblThuongHieu.getValueAt(row, 5).toString().equals("Đang hoạt động")) {
            cboTrangThai.setSelectedIndex(0);
        } else {
            cboTrangThai.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblThuongHieuMouseClicked

    private void lblAnhThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhThuongHieuMouseClicked
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_lblAnhThuongHieuMouseClicked

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
            java.util.logging.Logger.getLogger(DialogThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogThuongHieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogThuongHieu dialog = new DialogThuongHieu(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser dcNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhThuongHieu;
    private javax.swing.JTable tblThuongHieu;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaThuongHieu;
    private javax.swing.JTextField txtTenThuongHieu;
    // End of variables declaration//GEN-END:variables
}
