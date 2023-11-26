/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.PhieuDoi;
import DAO.PhieuDoi_HoaDonChiTietDAO;
import DAO.HoaDonDAO;
import DAO.PhieuDoiDAO;
import utils.MsgBox;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class HoaDonChiTietJDiaLog extends javax.swing.JDialog {

    PhieuDoiDAO pdd = new PhieuDoiDAO();
    HoaDonDAO hdd = new HoaDonDAO();
    PhieuDoi_HoaDonChiTietDAO hdctdao = new PhieuDoi_HoaDonChiTietDAO();
    DefaultTableModel tableModel = new DefaultTableModel();
    //DefaultComboBoxModel<Object> dcbm = new DefaultComboBoxModel<>();
    int row = 0;

    public HoaDonChiTietJDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        //fillTableHoaDon();
        fillTablePhieuDoi();
        fillCBOTrangThai();
    }

    public void fillCBOTrangThai() {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) cboTrangThai.getModel();
        dcbm.removeAllElements();
//            List<HoaDon> list1 = hdd.selectAll1();
//            for(HoaDon hd : list1){ 
//                
//            dcbm.addElement(hd);
//        }
        String[] listCBO = new String[]{
            "Đang Hoạt Động", "Ngừng Hoạt Động"
        };
        for (String tt : listCBO) {
            dcbm.addElement(tt);
        }
        cboTrangThai.setModel(dcbm);

    }

    public void fillTableHoaDon() {
        tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel.setRowCount(0);
        try {
            boolean tt = true;
//            HoaDon hd1 = (HoaDon) cboTrangThai.getSelectedItem();
            if (cboTrangThai.getSelectedItem().toString().equals("Đang Hoạt Động")) {
                 System.out.println(String.valueOf(cboTrangThai.getSelectedItem()));
                tt = true;
            } else {
                 System.out.println(String.valueOf(cboTrangThai.getSelectedItem()));
                tt = false;
            }

            List<HoaDon> list = hdd.filterTT(tt);
            System.out.println(tt);
            for (HoaDon pd : list) {
                Object[] rowData = new Object[]{
                    pd.getMaHD(),
                    pd.getMaNV(),
                    pd.getMaKH(),
                    pd.getTongTien(),
                    pd.getThanhToan(),
                    pd.getNgayTaoHoaDon(),
                    pd.isTrangThaiHD() ? "Đang Hoạt Động" : "Ngừng Hoạt Động",
                    pd.getGhiChuHD()
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            System.out.println(e);
            //MsgBox.alert(this, "Lỗi truy vấn dữ liệu !");
        }
        //tableModel.fireTableChanged();
    }

    public void fillHoaDonChiTiet() {
        tableModel = (DefaultTableModel) tblHDCT.getModel();
        tableModel.setRowCount(0);

        int mahdct = Integer.parseInt(txtmahd.getText());

        try {
            List<HoaDonChiTiet> list = hdctdao.selectByMaHD(mahdct);
            for (HoaDonChiTiet hdct : list) {
                Object[] rowData = {
                    hdct.getMaHDCT(),
                    hdct.getMaHD(),
                    hdct.getMaAoKhoac(),
                    hdct.getSoLuongHDCT(),
                    hdct.getDonGiaHDCT(),
                    hdct.isTrangThaiHDCT() ? "Đang Hoạt Động" : "Không Hoạt Động"

                };
                tableModel.addRow(rowData);
            }

        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn !");
        }
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

    void fillAllHoaDon() {
        tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel.setRowCount(0);
        try {
            List<HoaDon> list = hdd.selectAll();
            for (HoaDon pd : list) {
                Object[] rowData = new Object[]{
                    pd.getMaHD(),
                    pd.getMaNV(),
                    pd.getMaKH(),
                    pd.getTongTien(),
                    pd.getThanhToan(),
                    pd.getNgayTaoHoaDon(),
                    pd.isTrangThaiHD() ? "Đang Hoạt Động" : "Ngừng Hoạt Động",
                    pd.getGhiChuHD()
                };
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

    private void allowNumericOnly(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (isNumeric(string)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (isNumeric(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isNumeric(String text) {
                for (int i = 0; i < text.length(); i++) {
                    if (!Character.isDigit(text.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabDoiHang = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnDoiHang = new javax.swing.JButton();
        txtTienDaTT = new javax.swing.JTextField();
        jdcNgayDoiHang = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtLyDoDoiHang = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiemHD = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTimKiemHD = new javax.swing.JButton();
        btnTatCa = new javax.swing.JButton();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtmahd = new javax.swing.JTextField();
        txtmanv = new javax.swing.JTextField();
        txtmakh = new javax.swing.JTextField();
        txttongtien = new javax.swing.JTextField();
        txtthanhtoan = new javax.swing.JTextField();
        rdoHoatDong = new javax.swing.JRadioButton();
        rdoNgungHoatDong = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnTrangThai = new javax.swing.JButton();
        jdcNgayTaoHD = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabeltien = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPhamDoi = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnxoa = new javax.swing.JButton();
        btnHoanThanh = new javax.swing.JButton();
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
        jdcNgayTaoPhieu = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HOÀN TRẢ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel5.setText("Tiền Đã Thanh Toán");

        jLabel6.setText("Ngày Đổi Hàng");

        btnDoiHang.setText("Chọn Sản Phẩm");
        btnDoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiHangActionPerformed(evt);
            }
        });

        jdcNgayDoiHang.setDateFormatString("dd-MM-yyyy");

        jLabel7.setText("Lý Do Đổi Hàng");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienDaTT, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                            .addComponent(txtLyDoDoiHang))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jdcNgayDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTienDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLyDoDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jdcNgayDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btnDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Tìm Kiếm");

        txtTimKiemHD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemHDCaretUpdate(evt);
            }
        });
        txtTimKiemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemHDActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Mã NV", "Mã KH", "Tổng Tiền ", "Thanh Toán", "Ngày Tạo HĐ", "Trạng Thái HĐ", "Ghi Chú"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        btnTimKiemHD.setText("Tìm Kiếm");
        btnTimKiemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemHDActionPerformed(evt);
            }
        });

        btnTatCa.setText("Tất Cả Hóa Đơn");
        btnTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTatCa)
                .addGap(352, 352, 352))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(btnTimKiemHD)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel9.setText("Mã HĐ");

        jLabel10.setText("Mã NV");

        jLabel11.setText("Mã KH");

        jLabel12.setText("Tổng Tiền");

        jLabel13.setText("Thanh Toán");

        jLabel14.setText("Ngày Tạo HĐ");

        jLabel15.setText("Trạng Thái HĐ");

        jLabel16.setText("Ghi Chú");

        rdoHoatDong.setText("Đang Hoạt Động");

        rdoNgungHoatDong.setText("Ngừng Hoạt Động");
        rdoNgungHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNgungHoatDongActionPerformed(evt);
            }
        });

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        btnTrangThai.setText("Cập Nhật Trạng Thái");
        btnTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangThaiActionPerformed(evt);
            }
        });

        jdcNgayTaoHD.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmahd)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rdoHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtthanhtoan)
                                        .addComponent(txttongtien)
                                        .addComponent(txtmakh)
                                        .addComponent(txtmanv)
                                        .addComponent(jdcNgayTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                                    .addComponent(rdoNgungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jdcNgayTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rdoHoatDong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoNgungHoatDong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HÓA DƠN CHI TIẾT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐCT", "Mã HĐ", "Mã Áo Khoác", "Số Lượng", "Đơn Giá", "Trạng Thái"
            }
        ));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
        );

        TabDoiHang.addTab("Tạo Phiếu Đổi", jPanel1);

        jLabeltien.setText("0.0 đ");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM ĐỔI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPhamDoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Áo Khoác", "Tên Áo Khoác", "Giá Áo Khoác", "Mã Chất Liệu", "Tên Phong Cách", "Số lượng"
            }
        ));
        tblSanPhamDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamDoiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblSanPhamDoiMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPhamDoi);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Áo Khoác", "Tên Áo Khoác", "Giá sản phẩm", "Mã Chất Liệu", "Tên Phong Cách", "Số lượng"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tổng tiền trả");

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnHoanThanh.setText("Hoàn thành");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabeltien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnxoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHoanThanh))))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabeltien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnxoa)
                            .addComponent(btnHoanThanh))))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        TabDoiHang.addTab("Chọn Sản Phẩm Đổi", jPanel9);

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
                .addContainerGap(74, Short.MAX_VALUE))
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

        jdcNgayTaoPhieu.setDateFormatString("dd-MM-yyyy");

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
                    .addComponent(jdcNgayTaoPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jdcNgayTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(162, Short.MAX_VALUE))
        );

        TabDoiHang.addTab("Quản Lý Phiếu Đổi", jPanel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("ĐỔI HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabDoiHang)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(497, 497, 497)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 700, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiHangActionPerformed
        // TODO add your handling code here:
        TabDoiHang.setSelectedIndex(1);
        //        Date date = jdcNgayDoiHang.getDate();
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //        String ngayDoiHang = sdf.format(date);
        //
        //        if(txtLyDoDoiHang.getText().trim().isEmpty()){
        //            MsgBox.alert(this, "Hãy nhập lý do đổi hàng !");
        //            return;
        //        }
        //        else if(jdcNgayDoiHang.equals("")){
        //            MsgBox.alert(this, "Hãy nhập ngày đổi hàng !");
        //            return;
        //        }else{
        //            for (int row : tblHoaDon.getSelectedRows()) {
        //            String trangthai = (String) tblHoaDon.getValueAt(row, 6);
        //
        //            PhieuDoi pd = new PhieuDoi();
        //            if (trangthai == "Đang Hoạt Động") {
        //                pd.setMaHD((int) tblHoaDon.getValueAt(row, 0));
        //                pd.setMaKH((int) tblHoaDon.getValueAt(row, 2));
        //                pd.setNgayDoiHang(ngayDoiHang);
        //                pd.setTienThanhToan((float) tblHoaDon.getValueAt(row, 4));
        //                pd.setLyDoDoiHang(txtLyDoDoiHang.getText());
        //                pdd.Insert(pd);
        //            } else {
        //                MsgBox.alert(this, "Đơn hàng đã ngừng hoạt động !");
        //
        //            }
        //
        //        }
        //
        //        pdd.UpdateTT(Integer.parseInt(txtmahd.getText()));
        //        fillTableHoaDon();
        //        fillTablePhieuDoi();
        //
        //        TabDoiHang.setSelectedIndex(2);
        //        }
    }//GEN-LAST:event_btnDoiHangActionPerformed

    private void txtTimKiemHDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemHDCaretUpdate
        // TODO add your handling code here:
        fillTableHoaDon();
    }//GEN-LAST:event_txtTimKiemHDCaretUpdate

    private void txtTimKiemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (row < 0) {
            return;
        }
        txtTienDaTT.setText(tblHoaDon.getValueAt(row, 4).toString());

        txtmahd.setText(tblHoaDon.getValueAt(row, 0).toString());
        txtmanv.setText(tblHoaDon.getValueAt(row, 1).toString());
        txtmakh.setText(tblHoaDon.getValueAt(row, 2).toString());
        txttongtien.setText(tblHoaDon.getValueAt(row, 3).toString());
        txtthanhtoan.setText(tblHoaDon.getValueAt(row, 4).toString());
        try {
            jdcNgayTaoHD.setDate(sdf.parse(tblHoaDon.getValueAt(row, 5).toString()));
        } catch (ParseException ex) {
            //Logger.getLogger(PhieuDoiJDiaLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        String trangthai = tblHoaDon.getValueAt(row, 6).toString();
        if (trangthai == "Đang Hoạt Động") {
            rdoHoatDong.setSelected(true);
        } else {
            rdoNgungHoatDong.setSelected(true);
        }
        txtGhiChu.setText(tblHoaDon.getValueAt(row, 7).toString());
        fillHoaDonChiTiet();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMousePressed
        // TODO add your handling code here:
        //        if (evt.getClickCount() == 2) {
        //            this.row = tblHoaDon.rowAtPoint(evt.getPoint());
        //            new HoaDonChiTietJDiaLog(null, true).setVisible(true);
        //        }
    }//GEN-LAST:event_tblHoaDonMousePressed

    private void btnTimKiemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemHDActionPerformed
        // TODO add your handling code here:
        int Search = Integer.parseInt(txtTimKiemHD.getText());
        tableModel = (DefaultTableModel) tblHoaDon.getModel();
        // Xóa dữ liệu hiện có trong bảng
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        boolean found = false;

        // Tìm kiếm hóa đơn theo mã và hiển thị kết quả
        List<HoaDon> listhd = hdd.selectAll();
        for (HoaDon hd : listhd) {
            if (hd.getMaHD() == Search) {
                tableModel.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getMaNV(),
                    hd.getMaKH(),
                    hd.getTongTien(),
                    hd.getThanhToan(),
                    hd.getNgayTaoHoaDon(),
                    hd.isTrangThaiHD() ? "Đang Hoạt Động" : "Không Hoạt Động",
                    hd.getGhiChuHD(),});
                found = true;
            }

        }

        if (!found) {
            MsgBox.alert(this, "Không tìm thấy hóa đơn có mã là: " + Search);
            txtTimKiemHD.setText("");
        }
    }//GEN-LAST:event_btnTimKiemHDActionPerformed

    private void btnTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaActionPerformed
        // TODO add your handling code here:
        try {
            MsgBox.alert(this, "Hiển thị tất cả hóa đơn !");
            fillAllHoaDon();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTatCaActionPerformed

    private void rdoNgungHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNgungHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNgungHoatDongActionPerformed

    private void btnTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangThaiActionPerformed
        // TODO add your handling code here:

        int mahd = Integer.parseInt(txtmahd.getText());
        HoaDon hd = new HoaDon();
        int tt;
        if (rdoHoatDong.isSelected()) {
            tt = 1;
        } else {
            tt = 0;
        }
        try {
            hdd.UpdateTT(tt, mahd);
            MsgBox.alert(this, "Cập nhật trạng thái thành công !");
            fillTableHoaDon();
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật trạng thái thất bại !");
        }
    }//GEN-LAST:event_btnTrangThaiActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHDCTMouseClicked

    private void tblSanPhamDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDoiMouseClicked

    }//GEN-LAST:event_tblSanPhamDoiMouseClicked

    private void tblSanPhamDoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDoiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamDoiMouseEntered

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        //        if (evt.getClickCount() == 1) {
        //            soluongdoi = JOptionPane.showInputDialog(this, "Nhập số lượng");
        //            tableDoi();
        //        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        //        DefaultTableModel dtm = (DefaultTableModel)tblSanPhamDoi.getModel();
        //        int row = this.tblSanPhamDoi.getSelectedRow();
        //        if(row != -1){
        //            dtm.removeRow(row);
        //
        //        }
        //        jLabeltien.setText(formatCurrency(tienTru));
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        //                if(tienTru < 0){
        //                        JOptionPane.showMessageDialog(this, "Nhập quá số tiền được đổi");
        //                        return;
        //                    }else if(tienTru > 0){
        //                        JOptionPane.showMessageDialog(this, "Số tiền thừa là"+tienTru);
        //                    }
        //
        //                JOptionPane.showMessageDialog(this, "Thêm sản phẩm đổi thành công");
        //                this.dispose();
        Date date = jdcNgayDoiHang.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayDoiHang = sdf.format(date);

        if (txtLyDoDoiHang.getText().trim().isEmpty()) {
            MsgBox.alert(this, "Hãy nhập lý do đổi hàng !");
            TabDoiHang.setSelectedIndex(0);
            return;
        } else if (jdcNgayDoiHang.equals("")) {
            MsgBox.alert(this, "Hãy nhập ngày đổi hàng !");
            TabDoiHang.setSelectedIndex(0);
            return;
        } else {
            for (int row : tblHoaDon.getSelectedRows()) {
                String trangthai = (String) tblHoaDon.getValueAt(row, 6);

                PhieuDoi pd = new PhieuDoi();
                if (trangthai == "Đang Hoạt Động") {
                    pd.setMaHD((int) tblHoaDon.getValueAt(row, 0));
                    pd.setMaKH((int) tblHoaDon.getValueAt(row, 2));
                    pd.setNgayDoiHang(ngayDoiHang);
                    pd.setTienThanhToan((float) tblHoaDon.getValueAt(row, 4));
                    pd.setLyDoDoiHang(txtLyDoDoiHang.getText());
                    pdd.insert(pd);
                } else {
                    MsgBox.alert(this, "Đơn hàng đã ngừng hoạt động !");

                }

            }

            pdd.UpdateTT(Integer.parseInt(txtmahd.getText()));
            fillTableHoaDon();
            fillTablePhieuDoi();

            TabDoiHang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_btnHoanThanhActionPerformed

    private void tblXemPhieuDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXemPhieuDoiMouseClicked
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int row = tblXemPhieuDoi.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtmapd.setText(tblXemPhieuDoi.getValueAt(row, 0).toString());
        txtmahdpd.setText(tblXemPhieuDoi.getValueAt(row, 1).toString());
        txtmakhpd.setText(tblXemPhieuDoi.getValueAt(row, 2).toString());
        try {
            jdcNgayTaoPhieu.setDate(sdf.parse(tblXemPhieuDoi.getValueAt(row, 3).toString()));
        } catch (ParseException ex) {
            //Logger.getLogger(PhieuDoiJDiaLog.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        fillTableHoaDon();
    }//GEN-LAST:event_cboTrangThaiActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDonChiTietJDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietJDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietJDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietJDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoaDonChiTietJDiaLog dialog = new HoaDonChiTietJDiaLog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTabbedPane TabDoiHang;
    private javax.swing.JButton btnDoiHang;
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnHuyPD;
    private javax.swing.JButton btnInPD;
    private javax.swing.JButton btnTatCa;
    private javax.swing.JButton btnTimKiemHD;
    private javax.swing.JButton btnTimKiemPD;
    private javax.swing.JButton btnTrangThai;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JButton btnfill;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabeltien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private com.toedter.calendar.JDateChooser jdcNgayDoiHang;
    private com.toedter.calendar.JDateChooser jdcNgayTaoHD;
    private com.toedter.calendar.JDateChooser jdcNgayTaoPhieu;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNgungHoatDong;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamDoi;
    private javax.swing.JTable tblXemPhieuDoi;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtLyDoDoiHang;
    private javax.swing.JTextField txtTienDaTT;
    private javax.swing.JTextField txtTimKiemHD;
    private javax.swing.JTextField txtTimPhieuDoi;
    private javax.swing.JTextField txtlido;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtmahdpd;
    private javax.swing.JTextField txtmakh;
    private javax.swing.JTextField txtmakhpd;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtmapd;
    private javax.swing.JTextField txtthanhtoan;
    private javax.swing.JTextField txttiendatt;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
