/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.AoKhoacMuaDongDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.HoaDonChiTietDAO;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.LoaiAoDAO;
import DAO.MauSacDAO;
import DAO.SanPhamHoaDonDAO;
import DAO.SizeDAO;
import Entity.AoKhoacMuaDong;
import Entity.ChiTietSanPham;
import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.KhachHang;
import Entity.LoaiAo;
import Entity.MauSac;
import Entity.PhieuGiamGia;
import Entity.SanPhamHoaDon;
import Entity.Size;
import Utils.Auth;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class HoaDonJpanel extends javax.swing.JPanel {

    SanPhamHoaDonDAO sphddao = new SanPhamHoaDonDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    KhachHangJdialog khj = new KhachHangJdialog(null, true);
    KhachHangDAO khdao = new KhachHangDAO();
    int rowhd = -1;
    int rowsp = -1;
    MauSacDAO msdao = new MauSacDAO();
    SizeDAO szdao = new SizeDAO();
    LoaiAoDAO ladao = new LoaiAoDAO();
    HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
    ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();
    AoKhoacMuaDongDAO akmddao = new AoKhoacMuaDongDAO();

    /**
     * Creates new form HoaDonJpanel
     */
    public HoaDonJpanel() {
        initComponents();
        fillTableSP();
        fillTableHD();
        initcboLoaiAoSP();
        initcboMauSPCT();
        initcboSizeSPCT();
    }

    private void fillTableSP() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDSSP.getModel();
        tableModel.setRowCount(0);
        List<SanPhamHoaDon> list = sphddao.getAll();
        System.out.println(list.size());
        for (SanPhamHoaDon sphd : list) {
            tableModel.addRow(new Object[]{
                sphd.getMaCTSP(), sphd.getTenAo(), sphd.getSoLuongTon(), sphd.getMauSac(), sphd.getSize(), sphd.getLoaiAo(), sphd.getGia(), sphd.getGiaDaGiam()
            });
        }
        tblDSSP.setModel(tableModel);
    }

    private void fillTableTKSP() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDSSP.getModel();
        tableModel.setRowCount(0);
        String timKiem = txtTimKiem.getText();
        System.out.println(sphddao.timKiemTheoTen(timKiem).size());
        if (sphddao.timKiemTheoTen(timKiem) == null) {
            return;
        }
        List<SanPhamHoaDon> list = sphddao.timKiemTheoTen(timKiem);
        for (SanPhamHoaDon sphd : list) {
            tableModel.addRow(new Object[]{
                sphd.getMaCTSP(), sphd.getTenAo(), sphd.getSoLuongTon(), sphd.getMauSac(), sphd.getSize(), sphd.getLoaiAo(), sphd.getGia(), sphd.getGiaDaGiam()
            });
        }

        tblDSSP.setModel(tableModel);
    }

    private void fillTableHD() {
        DefaultTableModel tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel.setRowCount(0);
        List<HoaDon> list = hddao.getAll();
        for (HoaDon hd : list) {
            KhachHang kh = khdao.selectById(hd.getMaKhachHang());
            tableModel.addRow(new Object[]{
                hd.getMaHoaDon(), hd.getMaKhachHang(), kh.getTenKhachHang(), hd.getMaNhanVien(), hd.getNgayTaoHoaDon(), hd.getTrangThaiHoaDon()
            });
        }
        tblHoaDon.setModel(tableModel);
    }

    private void fillTableHDCT(int maHD) {
        DefaultTableModel tableModel = (DefaultTableModel) tblHDCT.getModel();
        tableModel.setRowCount(0);
        List<HoaDonChiTiet> list = hdctdao.selectByMaHD(maHD);
        for (HoaDonChiTiet hdct : list) {
            AoKhoacMuaDong akmd = akmddao.getAoKhoacTheoCTSP(hdct.getMaSPCT());
            tableModel.addRow(new Object[]{
                hdct.getMaHD(), hdct.getMaSPCT(), akmd.getTenAoKhoac(), hdct.getSoLuongHDCT(), hdct.getDonGiaHDCT(), hdct.getThanhTien()
            });
        }
        tblHDCT.setModel(tableModel);
    }

    private void initcboMauSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboMauSac.getModel();
        boxModel.removeAllElements();
        List<MauSac> list = msdao.selectAllHD();
        if (list == null) {
            boxModel.addElement("Chưa có màu sắc .");
        }
        boxModel.addElement("All");
        for (MauSac ms : list) {
            boxModel.addElement(ms);
        }
        cboMauSac.setModel(boxModel);
    }
// size

    private void initcboSizeSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboSize.getModel();
        boxModel.removeAllElements();
        List<Size> list = szdao.selectAllHD();
        if (list == null) {
            boxModel.addElement("Chưa có size .");
        }
        boxModel.addElement("All");
        for (Size sz : list) {
            boxModel.addElement(sz);
        }
        cboSize.setModel(boxModel);
    }

    private void initcboLoaiAoSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboLoaiAo.getModel();
        boxModel.removeAllElements();
        List<LoaiAo> list = ladao.selectAllHD();
        boxModel.addElement("All");
        for (LoaiAo la : list) {
            boxModel.addElement(la);
        }
        cboLoaiAo.setModel(boxModel);
    }

    private HoaDon getModel() {
        int maKh = Integer.parseInt(txtMaKhachHang.getText());
        Date date = new Date();
        return new HoaDon(0, Auth.user.getMaNhanVien(), maKh, date, "Chưa thanh toán");
    }

    private void addHoaDon() {
        if (hddao.insert(getModel()) > 0) {
            JOptionPane.showMessageDialog(this, "Thêm mới hóa đơn thành công .");
            fillTableHD();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm mới hóa đơn mới không thành công ,");
        }
    }

    void addHDCT() {
        int maHD;
        String maSPCT;
        int soLuongThem = 0;
        int soLuongSanPham;
        float donGia;
        float thanhTien;
        int soLuongTon;
        maHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
        maSPCT = tblDSSP.getValueAt(rowsp, 0).toString();
        donGia = Float.parseFloat(tblDSSP.getValueAt(rowsp, 7).toString());
        String check = JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm muốn thêm .");
        soLuongSanPham = Integer.parseInt(tblDSSP.getValueAt(rowsp, 2).toString());
        try {
            soLuongThem = Integer.parseInt(check);
            if (soLuongThem < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập phải lớn hơn 0 .");
                return;
            }

            if (soLuongThem > soLuongSanPham) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập lớn hơn số lượng tồn .");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng chỉ được chứa số .");
        }

        List<HoaDonChiTiet> list = hdctdao.selectByMaHD(maHD);
        thanhTien = donGia * soLuongThem;
        soLuongTon = soLuongSanPham - soLuongThem;
        for (HoaDonChiTiet hdct : list) {
            if (hdct.getMaSPCT() == maSPCT) {
                int soLuongTrung = hdct.getSoLuongHDCT() + soLuongThem;
                float thanhTien2 = soLuongTrung * donGia;
                hdctdao.updateHDCTSP(soLuongTrung, thanhTien2, hdct.getMaHDCT());
                fillTableHDCT(maHD);
                ctspdao.updateCTSPSL(soLuongTon, maSPCT);
                fillTableSP();
                // đoạn này sẽ tính tổng tiền lại
                return;
            }
        }
        HoaDonChiTiet hdct = new HoaDonChiTiet(maHD, maSPCT, soLuongThem, donGia, thanhTien, true);
        hdctdao.insert(hdct);
        fillTableHDCT(maHD);
        ctspdao.updateCTSPSL(soLuongTon, maSPCT);
        fillTableSP();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboLoaiAo = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        btnChonKhach = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        btnChonGiamGia = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTKT = new javax.swing.JTextField();
        txtTienThua = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMaKHDH = new javax.swing.JTextField();
        txtTenKHDH = new javax.swing.JTextField();
        btnChonKHDatHang = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtMaHDDH = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTTDH = new javax.swing.JTextField();
        btnDatHang = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Mã CTSP", "Tên áo khoác", "Số Lượng", "Đơn Giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Tên Áo", "Số Lượng Tồn", "Màu Sắc", "Size", "Loại áo", "Giá", "Giá đã giảm"
            }
        ));
        tblDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDSSP);

        jLabel1.setText("Tìm Kiếm");

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseExited(evt);
            }
        });

        jLabel2.setText("Màu Sắc");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Size");

        jLabel4.setText("Loại Áo");

        cboLoaiAo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(cboLoaiAo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cboLoaiAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Mã Khách Hàng", "Tên Khách Hàng", "Mã Nhân Viên", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setText("Hủy Hóa Đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh Toán");

        jLabel5.setText("Mã Khách Hàng");

        jLabel6.setText("Tên Khách Hàng");

        btnChonKhach.setText("Chọn Khách Hàng");
        btnChonKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(txtTenKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnChonKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnChonKhach)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel7.setText("Mã Hóa Đơn");

        jLabel8.setText("Tổng Tiền");

        jLabel9.setText("Giảm Giá");

        btnChonGiamGia.setText("Chọn Giảm Giá");
        btnChonGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonGiamGiaActionPerformed(evt);
            }
        });

        jLabel10.setText("Thanh Toán");

        jLabel11.setText("Tiền Khách Trả");

        jLabel12.setText("Tiền Thừa");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongTien)
                            .addComponent(txtGiamGia)
                            .addComponent(txtTT)
                            .addComponent(txtTKT, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(txtTienThua)
                            .addComponent(txtMaHD))
                        .addGap(18, 18, 18)
                        .addComponent(btnChonGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonGiamGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel5);

        jLabel13.setText("Mã Khách Hàng");

        jLabel14.setText("Tên Khách Hàng");

        btnChonKHDatHang.setText("Chọn Khách Hàng");
        btnChonKHDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHDatHangActionPerformed(evt);
            }
        });

        jLabel15.setText("Mã hóa đơn :");

        jLabel16.setText("Tổng tiền :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTTDH, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtMaHDDH, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtMaKHDH)
                    .addComponent(txtTenKHDH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChonKHDatHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMaKHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKHDatHang))
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTenKHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtMaHDDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTTDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnDatHang.setText("Giao Hàng");
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDatHang)
                .addGap(202, 202, 202))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDatHang)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đặt Hàng", jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThanhToan)
                .addGap(27, 27, 27)
                .addComponent(btnHuyHoaDon)
                .addGap(27, 27, 27)
                .addComponent(btnTaoHoaDon)
                .addGap(20, 20, 20))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        ThongTinGiaoHangJdialog ttgh = new ThongTinGiaoHangJdialog(null, true);
        ttgh.setVisible(true);
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void btnChonKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachActionPerformed
        // TODO add your handling code here:
        KhachHangJdialog khj = new KhachHangJdialog(null, true);
        khj.setVisible(true);
        int row = khj.row;
        List<KhachHang> list = khj.fillTable();
        if (row < 0) {
            return;
        }
        txtMaKhachHang.setText(String.valueOf(list.get(row).getMaKhachHang()));
        txtTenKhachHang.setText(list.get(row).getTenKhachHang());
    }//GEN-LAST:event_btnChonKhachActionPerformed

    private void btnChonGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonGiamGiaActionPerformed
        // TODO add your handling code here:
        PhieuGiamGiaApDung pggad = new PhieuGiamGiaApDung(null, true);
        pggad.setVisible(true);
        int row = pggad.row;
        List<PhieuGiamGia> list = pggad.FillTable();
        if (row < 0) {
            return;
        }
        txtGiamGia.setText(String.valueOf(list.get(row)));
    }//GEN-LAST:event_btnChonGiamGiaActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        addHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnChonKHDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHDatHangActionPerformed
        // TODO add your handling code here:
        KhachHangJdialog khj = new KhachHangJdialog(null, true);
        khj.setVisible(true);
        int row = khj.row;
        List<KhachHang> list = khj.fillTable();
        if (row < 0) {
            return;
        }
        txtMaKHDH.setText(String.valueOf(list.get(row).getMaKhachHang()));
        txtTenKHDH.setText(list.get(row).getTenKhachHang());
    }//GEN-LAST:event_btnChonKHDatHangActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        // TODO add your handling code here:
        if (rowhd < 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn hóa đơn để hủy .");
        } else {
            HoaDon hd = getModel();
            hd.setMaHoaDon(Integer.parseInt(txtMaHD.getText()));
            hd.setTrangThaiHoaDon("Đã hủy");
            if (hddao.update(hd) > 0) {
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn thành công .");
                fillTableHD();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn không thành công .");
            }
        }

    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        rowhd = tblHoaDon.getSelectedRow();
        txtMaHD.setText(tblHoaDon.getValueAt(rowhd, 0).toString());
        txtMaKhachHang.setText(tblHoaDon.getValueAt(rowhd, 1).toString());
        txtTenKhachHang.setText(tblHoaDon.getValueAt(rowhd, 2).toString());

        if (rowhd < 0) {
            return;
        }
        int maHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
        fillTableHDCT(maHD);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemMouseExited

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
        fillTableTKSP();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        String ms = "";
        String sz = "";
        String la = "";
        Object selectMs = cboMauSac.getSelectedItem();
        if (selectMs instanceof MauSac) {
            System.out.println("a");
            ms = (String) cboMauSac.getSelectedItem().toString();
        }
        Object selectSz = cboSize.getSelectedItem();
        if (selectSz instanceof Size) {
            sz = (String) cboSize.getSelectedItem().toString();
        }
        Object selectLa = cboLoaiAo.getSelectedItem();
        if (selectLa instanceof LoaiAo) {
            la = (String) cboLoaiAo.getSelectedItem().toString();
        }

        DefaultTableModel tableModel = (DefaultTableModel) tblDSSP.getModel();
        tableModel.setRowCount(0);
        List<SanPhamHoaDon> list = sphddao.locSanPham(ms, sz, la);
        for (SanPhamHoaDon sphd : list) {
            tableModel.addRow(new Object[]{
                sphd.getMaCTSP(), sphd.getTenAo(), sphd.getSoLuongTon(), sphd.getMauSac(), sphd.getSize(), sphd.getLoaiAo(), sphd.getGia(), sphd.getGiaDaGiam()
            });
        }

        tblDSSP.setModel(tableModel);
    }//GEN-LAST:event_btnLocActionPerformed

    private void tblDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSPMouseClicked
        // TODO add your handling code here:
        rowsp = tblDSSP.getSelectedRow();
        if (rowsp < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng hóa đơn được chọn .");
        } else {
            addHDCT();
        }
    }//GEN-LAST:event_tblDSSPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonGiamGia;
    private javax.swing.JButton btnChonKHDatHang;
    private javax.swing.JButton btnChonKhach;
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cboLoaiAo;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHDDH;
    private javax.swing.JTextField txtMaKHDH;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtTKT;
    private javax.swing.JTextField txtTT;
    private javax.swing.JTextField txtTTDH;
    private javax.swing.JTextField txtTenKHDH;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
