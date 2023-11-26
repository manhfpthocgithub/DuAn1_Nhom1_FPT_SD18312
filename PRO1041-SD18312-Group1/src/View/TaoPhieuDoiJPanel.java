/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.ChonSPDoiDAO;
import DAO.HoaDonChiTietDAO;
import DAO.HoaDonDAO;
import DAO.PhieuDoiDAO;
import Entity.ChiTietSanPham;
import Entity.ChonSPDoi;
import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.PhieuDoi;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;

/**
 *
 * @author Admin
 */
public class TaoPhieuDoiJPanel extends javax.swing.JPanel {

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
    public TaoPhieuDoiJPanel() {
        initComponents();
    }

    public double tongtien() {
        double tongTien = 0.0;
        for (ChonSPDoi sp : cspddao.selectAll()) {
            ChiTietSanPham ctsp = new ChiTietSanPham();
            int sluong = ctsp.getSoLuongAK();
            tongTien = sp.getGiaAoKhoac() * sluong;
        }
        jLabeltien.setText(formatCurrency(tongTien));
        return tongTien;
    }

    public double tongtienHD() {
        double tongTienHD = 0.0;
        for (HoaDon hd : hdd.selectAll1()) {
            tongTienHD = hd.getTongTien();
        }
        jLabeltien.setText(formatCurrency(tongTienHD));
        return tongTienHD;
    }

    private String formatCurrency(double amount) {
        Locale locale = new Locale("vi", "VN"); // Locale của Việt Nam
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        Font font = new Font("Arial", Font.BOLD, 16);
        jLabeltien.setFont(font);
        return currencyFormat.format(amount);
    }

    private void calculateRowTotals() {
        int rowCount = tblSanPhamDoi.getRowCount();
        int columnCount = tblSanPhamDoi.getColumnCount();

        // Duyệt qua từng hàng và tính tổng của từng hàng
        for (int row = 0; row < rowCount; row++) {
            double gia = Double.parseDouble(tblSanPhamDoi.getValueAt(row, 4).toString());
            double soluong = Double.parseDouble(tblSanPhamDoi.getValueAt(row, 5).toString());
            double rowTotal = gia * soluong;
            System.out.println(rowTotal);
            // Đặt giá trị tổng của từng hàng vào cột mới (ví dụ: cột 5)
//            tblSanPhamDoi.setValueAt(rowTotal, row, columnCount - 1);
        }
    }

    public double laytien() {
        int rowCount = tblSanPhamDoi.getRowCount();
        int columnCount = tblSanPhamDoi.getColumnCount();
        double totalSum = 0;

        // Duyệt qua cột mới chứa tổng của từng hàng và tính tổng của các tổng
        for (int row = 0; row < rowCount; row++) {
            totalSum += Double.parseDouble(tblSanPhamDoi.getValueAt(row, 4).toString()) * Double.parseDouble(tblSanPhamDoi.getValueAt(row, 5).toString());
        }
        return totalSum;
    }

    public void fillTableSanPham() {
            tableModel = (DefaultTableModel) tblSanPham.getModel();
            tableModel.setRowCount(0);
            try {
                List<ChonSPDoi> list = cspddao.selectAll();
                for (ChonSPDoi csp : list) {
                    Object[] rowData = new Object[]{
                        csp.getMaAoKhoac(),
                        csp.getTenAoKhoac(),
                        csp.getTenPhongCach(),
                        csp.getTenSize(),
                        csp.getGiaAoKhoac(),
                        csp.getSoLuong()
                    };
                    tableModel.addRow(rowData);
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu !");
            }
        
    }

    public void fillTableDoi() {
        if (soluongdoi != null && !soluongdoi.isEmpty()) {
            int soluong = Integer.parseInt(soluongdoi);
            soluongtru = soluong;
            int row = tblSanPham.getSelectedRow();
            if (row == -1) {
                return;
            }
            ChonSPDoi sp = cspddao.selectAll().get(row);
            DefaultTableModel dtm = (DefaultTableModel) tblSanPhamDoi.getModel();
            dtm.addRow(new Object[]{
                sp.getMaAoKhoac(),
                sp.getTenAoKhoac(),
                sp.getTenPhongCach(),
                sp.getTenSize(),
                soluong,
                sp.getGiaAoKhoac()
            });
            int col = tblSanPham.getSelectedColumn();
            int sl = Integer.parseInt(tblSanPham.getValueAt(row, 5).toString());
            String slNew = String.valueOf(sl - soluong).toString();
            tblSanPham.setValueAt(slNew, row, 5);
            totalSum = laytien();

            tienTru = 0.0;

            tienTru = tongtien() - totalSum;
            jLabeltien.setText(formatCurrency(tienTru));
            System.out.println(sp.getGiaAoKhoac() + tienTru);
            calculateRowTotals();

        }
    }

    public void fillTableHoaDon() {
        tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel.setRowCount(0);
        try {
            String keyword = txtTimKiemHD.getText();
            List<HoaDon> list = hdd.selectByKeyword(keyword);
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


    void fillAllHoaDon() {
        tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel.setRowCount(0);
        try {
            List<HoaDon> list = hdd.selectAll1();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnDoiHang = new javax.swing.JButton();
        txtTienDaTT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLyDoDoiHang = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiemHD = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTimKiemHD = new javax.swing.JButton();
        btnTatCa = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
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

        setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HOÀN TRẢ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel5.setText("Tiền Đã Thanh Toán");

        btnDoiHang.setText("Chọn Sản Phẩm");
        btnDoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiHangActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienDaTT, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                            .addComponent(txtLyDoDoiHang))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(btnDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(329, Short.MAX_VALUE))))
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
                .addGap(46, 46, 46)
                .addComponent(btnDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(14, 14, 14))
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
                        .addComponent(btnTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTatCa)
                .addGap(352, 352, 352))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnTimKiemHD))
                .addGap(11, 11, 11)
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
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rdoHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtthanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(txttongtien)
                                    .addComponent(txtmakh)
                                    .addComponent(txtmanv)
                                    .addComponent(rdoNgungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcNgayTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(14, 14, 14)
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
                .addGap(86, 86, 86))
        );

        tab.addTab("Tạo Phiếu Đổi", jPanel1);

        jLabeltien.setText("0.0 đ");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM ĐỔI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPhamDoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Áo Khoác", "Tên Áo Khoác", "Tên Phong Cách", "Size", "Số lượng", "Giá Áo Khoác"
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
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
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
                "Mã Áo Khoác", "Tên Áo Khoác", "Tên Phong Cách", "Size", "Giá sản phẩm", "Số lượng"
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
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1145, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
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
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabeltien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnxoa)
                            .addComponent(btnHoanThanh)))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1173, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tab.addTab("Chọn Sản Phẩm", jPanel2);

        add(tab, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiHangActionPerformed
        // TODO add your handling code here:
        String ma = txtmahd.getText();
        if(ma.equals("")){
            MsgBox.alert(this, "Vui lòng chọn hóa đơn !");
        }
        else{
            //            TabDoiHang.setSelectedIndex(1);
            new ChonSanPhamDoiJPanel().setVisible(true);
        }

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
        List<HoaDon> listhd = hdd.selectAll1();
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
        //        if (evt.getClickCount() == 1) {
            //            soluongdoi = JOptionPane.showInputDialog(this, "Nhập số lượng");
            //            fillTableSanPham();
            //        }
    }//GEN-LAST:event_tblSanPhamDoiMouseClicked

    private void tblSanPhamDoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDoiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamDoiMouseEntered

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        if (evt.getClickCount() == 1) {
            soluongdoi = MsgBox.prompt(this, "Nhập số lượng");
            fillTableDoi();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        //soluongdoi = JOptionPane.showInputDialog(this, "Nhập số lượng");
        DefaultTableModel dtm = (DefaultTableModel) tblSanPhamDoi.getModel();
        int rowsqd = this.tblSanPhamDoi.getSelectedRow();

        if (rowsqd == -1) {
            return;
        }

        if (rowsqd != -1) {
            dtm.removeRow(rowsqd);

        }
        jLabeltien.setText(formatCurrency(tongtien() - laytien()));
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

        if (txtLyDoDoiHang.getText().trim().isEmpty()) {
            MsgBox.alert(this, "Hãy nhập lý do đổi hàng !");
            tab.setSelectedIndex(0);
            return;
        }
        else {
            for (int row : tblHoaDon.getSelectedRows()) {
                String trangthai = (String) tblHoaDon.getValueAt(row, 6);

                PhieuDoi pd = new PhieuDoi();
                if (trangthai == "Đang Hoạt Động") {
                    pd.setMaHD((int) tblHoaDon.getValueAt(row, 0));
                    pd.setMaKH((int) tblHoaDon.getValueAt(row, 2));
                    pd.setTienThanhToan((float) tblHoaDon.getValueAt(row, 4));
                    pd.setLyDoDoiHang(txtLyDoDoiHang.getText());
                    pdd.insert(pd);
                } else {
                    MsgBox.alert(this, "Đơn hàng đã ngừng hoạt động !");

                }

            }

            pdd.UpdateTT(Integer.parseInt(txtmahd.getText()));
            fillTableHoaDon();
            fillTablePhieuDoi(); //Insert vào table Phiếu Đổi
        }
    }//GEN-LAST:event_btnHoanThanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiHang;
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnTatCa;
    private javax.swing.JButton btnTimKiemHD;
    private javax.swing.JButton btnTrangThai;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private com.toedter.calendar.JDateChooser jdcNgayTaoHD;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNgungHoatDong;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamDoi;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtLyDoDoiHang;
    private javax.swing.JTextField txtTienDaTT;
    private javax.swing.JTextField txtTimKiemHD;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtmakh;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtthanhtoan;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
