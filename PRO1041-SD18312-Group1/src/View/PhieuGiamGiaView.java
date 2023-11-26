/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;


import DAO.LichSuPGGDAO;
import DAO.PhieuGiamGiaDAO;
import Entity.LichSuPGG;
import Entity.PhieuGiamGia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;


public class PhieuGiamGiaView extends javax.swing.JFrame {
    PhieuGiamGiaDAO pggDAO = new PhieuGiamGiaDAO(); 
    LichSuPGGDAO lsDAO = new LichSuPGGDAO();
    List<PhieuGiamGia> list = pggDAO.selectAll();
    int soluong = 1;
    int row = -1;     
    
    public PhieuGiamGiaView(java.awt.Frame parent, boolean modal) {
        
        initComponents();
        init();
        FillTable();
        FillTableLichSu();       
    }
 
    void init(){
        setLocationRelativeTo(this);
        txtSoLuong.setText(String.valueOf(soluong)); 
        setTitle("Quản Lý Phiếu Giảm Giá - BeLing");
        eventCBOTrangThai();
        initCBOTrangThai();
        eventCBONhanVien();
        initCBONhanVien();       
        lblDieuKien.setToolTipText("Nhập giá trị để hóa đơn có giá trị tiền tương ứng có thể áp dụng thanh toán!");
    }
    
    void FillTable(){        
        DefaultTableModel tblModel = (DefaultTableModel) tblPhieuGiamGia.getModel();
        tblModel.setRowCount(0);        
        try {
            List<PhieuGiamGia> list = pggDAO.selectAll();
            for (PhieuGiamGia pgg : list) {
                Object[] row = new Object[9];
                row[0] = pgg.getMaPhieuGiamGia();
                row[1] = pgg.getTenPGG();
                row[2] = pgg.GiaTriPGG();
                row[3] = String.format("%,.0f VNĐ", pgg.getTongTienHang());
                row[4] = pgg.isTrangThaiPGG()?"Kích Hoạt":"Chưa Kích Hoạt";
                row[5] = pgg.getNgayTao();
                row[6] = pgg.getNgayHetHan();
                row[7] = pgg.getGhiChu();
                row[8] = pgg.getMaNV(); 
                tblModel.addRow(row);
            }           
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Dữ Liệu!");
            e.printStackTrace();
        }
    }
    void FillTableTimKiem(){        
        DefaultTableModel tblModel = (DefaultTableModel) tblPhieuGiamGia.getModel();
        tblModel.setRowCount(0);        
        try {
            String ten = txtTimKiem.getText();
            List<PhieuGiamGia> list = pggDAO.searchByName(ten);
            for (PhieuGiamGia pgg : list) {
                Object[] row = new Object[9];
                row[0] = pgg.getMaPhieuGiamGia();
                row[1] = pgg.getTenPGG();
                row[2] = pgg.GiaTriPGG();
                row[3] = String.format("%,.0f VNĐ", pgg.getTongTienHang());
                row[4] = pgg.isTrangThaiPGG()?"Kích Hoạt":"Chưa Kích Hoạt";
                row[5] = pgg.getNgayTao();
                row[6] = pgg.getNgayHetHan();
                row[7] = pgg.getGhiChu();
                row[8] = pgg.getMaNV(); 
                tblModel.addRow(row);
            }           
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Dữ Liệu!");
            e.printStackTrace();
        }
    }
    
    void FillXoaTrangThai(){
        DefaultTableModel tblModel = (DefaultTableModel) tblPhieuGiamGia.getModel();
        tblModel.setRowCount(0);   
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {              
            List<PhieuGiamGia> list = pggDAO.deleteTrangThai();
            for (PhieuGiamGia pgg : list) {              
                Object[] row = new Object[9];
                row[0] = pgg.getMaPhieuGiamGia();
                row[1] = pgg.getTenPGG();
                row[2] = pgg.GiaTriPGG();
                row[3] = String.format("%,.0f VNĐ", pgg.getTongTienHang());
                row[4] = pgg.isTrangThaiPGG()?"Kích Hoạt":"Chưa Kích Hoạt";
                row[5] = pgg.getNgayTao();
                row[6] = pgg.getNgayHetHan();
                row[7] = pgg.getGhiChu();
                row[8] = pgg.getMaNV(); 
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Dữ Liệu!");
        }
    }
    
    void FillTableLichSu(){
        DefaultTableModel tblModel = (DefaultTableModel) tblLichSuPgg.getModel();
        tblModel.setRowCount(0);
        try {
            List<LichSuPGG> list = lsDAO.selectAll();
            for (LichSuPGG ls : list) {
                Object[] row = new Object[8];
                row[0] = ls.getMaLichSu();
                row[1] = ls.getMaphieugiamgia();
                row[2] = ls.getGiatripgg();
                row[3] = String.format("%,.0f VNĐ", ls.getTongtienhang());
                row[4] = ls.getNgaySuDung();
                row[5] = ls.isTrangThaiLS()?"Đã Được Sử Dụng":"Chưa Sử Dụng";               
                row[6] = ls.getMaHoaDon();
                row[7] = ls.getGhichu();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Dữ Liệu!");
        }
    } 
    
    public List<PhieuGiamGia> FillCboTrangThai(Integer tt){
        DefaultTableModel tblModel = (DefaultTableModel) tblPhieuGiamGia.getModel();
        tblModel.setRowCount(0);   
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<PhieuGiamGia> list = pggDAO.fillTrangThai(tt);
        try {                          
            for (PhieuGiamGia pgg : list) {              
                Object[] row = new Object[9];
                row[0] = pgg.getMaPhieuGiamGia();
                row[1] = pgg.getTenPGG();
                row[2] = pgg.GiaTriPGG();
                row[3] = String.format("%,.0f VNĐ", pgg.getTongTienHang());
                row[4] = pgg.isTrangThaiPGG()?"Kích Hoạt":"Chưa Kích Hoạt";
                row[5] = pgg.getNgayTao();
                row[6] = pgg.getNgayHetHan();
                row[7] = pgg.getGhiChu();
                row[8] = pgg.getMaNV(); 
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Dữ Liệu!");
        }
        return list;
    }
    
    public List<PhieuGiamGia> FillCboNhanVien(String maNV){
        DefaultTableModel tblModel = (DefaultTableModel) tblPhieuGiamGia.getModel();
        tblModel.setRowCount(0);   
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<PhieuGiamGia> list = pggDAO.fillNhanVien(maNV);
        try {                          
            for (PhieuGiamGia pgg : list) {              
                Object[] row = new Object[9];
                row[0] = pgg.getMaPhieuGiamGia();
                row[1] = pgg.getTenPGG();
                row[2] = pgg.GiaTriPGG();
                row[3] = String.format("%,.0f VNĐ", pgg.getTongTienHang());
                row[4] = pgg.isTrangThaiPGG()?"Kích Hoạt":"Chưa Kích Hoạt";
                row[5] = pgg.getNgayTao();
                row[6] = pgg.getNgayHetHan();
                row[7] = pgg.getGhiChu();
                row[8] = pgg.getMaNV(); 
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Dữ Liệu!");
        }
        return list;
    }
    
   
    void setModel(PhieuGiamGia pgg) throws ParseException{    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtMaPhieuGiamGia.setText(String.valueOf(pgg.getMaPhieuGiamGia()));
        System.out.println(String.valueOf(pgg.getMaPhieuGiamGia()));
        txtTenPGG.setText(pgg.getTenPGG());
        if(pgg.getGiaTriPGG() <= 100){   
            hienthi.setText("%");
            txtGiaTriGiam.setText(String.valueOf(pgg.getGiaTriPGG()));
            rdoPhanTramGiam.setSelected(true);
        }
        else{  
            hienthi.setText("đ");
            txtGiaTriGiam.setText(String.valueOf(pgg.getGiaTriPGG()));
            rdoTienGiam.setSelected(true);
        }       
        txtTongTienHang.setText(String.valueOf(pgg.getTongTienHang()));
        txtNgayBatDau.setDate(sdf.parse(pgg.getNgayTao()));
        txtNgayKetThuc.setDate(sdf.parse(pgg.getNgayHetHan()));
        txtGhiChu.setText(pgg.getGhiChu());
        txtMaNV.setText(pgg.getMaNV());
        rdoKichHoat.setSelected(pgg.isTrangThaiPGG());
        rdoChuaKichHoat.setSelected(!pgg.isTrangThaiPGG());        
    }
    
    public PhieuGiamGia getModel(){    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PhieuGiamGia pgg = new PhieuGiamGia();
        if(!txtMaPhieuGiamGia.getText().trim().equals("")){
            pgg.setMaPhieuGiamGia(Integer.parseInt(txtMaPhieuGiamGia.getText()));
        }
        pgg.setTenPGG(txtTenPGG.getText());
        pgg.setGiaTriPGG(Integer.parseInt(txtGiaTriGiam.getText()));
        pgg.setTongTienHang(Double.parseDouble(txtTongTienHang.getText()));
        pgg.setNgayTao(sdf.format(txtNgayBatDau.getDate()));
        pgg.setNgayHetHan(sdf.format(txtNgayKetThuc.getDate()));
        pgg.setGhiChu(txtGhiChu.getText());
        pgg.setTrangThaiPGG(rdoKichHoat.isSelected());
        pgg.setMaNV(txtMaNV.getText());
        return pgg;      
    }
    void clearForm(){
        txtMaPhieuGiamGia.setText("");
        txtTenPGG.setText("");
        txtMaNV.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayKetThuc.setDate(null);
        txtTongTienHang.setText("");
        txtGiaTriGiam.setText("");
        txtGhiChu.setText("");
        rdoChuaKichHoat.setSelected(true);
        rdoTienGiam.setSelected(true);
        this.row = -1;
    }
    
    void Insert(){
        PhieuGiamGia pgg = getModel();
        
        try {
            pggDAO.insert(pgg);
            this.FillTable();
            
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm Thất Bại!");
        }
    }
    
    
    void initCBOTrangThai(){
        List<Integer> list = pggDAO.selectTrangThai();
        Set<Integer> unique = new HashSet<>(list);
        for (Integer integer : unique) {
            if(integer.equals(0)){
                cboTrangThai.addItem("Kích Hoạt");
            }
            if(integer.equals(1)){
                cboTrangThai.addItem("Chưa Kích Hoạt");
            }
        }
    }
    
    void initCBONhanVien(){
        List<String> list = pggDAO.selectNhanVien();
        Set<String> unique = new LinkedHashSet<>(list);
        for (String string : unique) {
            cboMaNV.addItem(string);    
        }
    }
    
    void eventCBOTrangThai(){
        cboTrangThai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trangthai = (String) cboTrangThai.getSelectedItem();
                int tt = -1;
                if(trangthai.equals("Kích Hoạt")){
                    tt = 1;
                }
                if(trangthai.equals("Chưa Kích Hoạt")){
                    tt = 0;
                }
                FillCboTrangThai(tt);
            }            
        });
    }
    
    void eventCBONhanVien(){
        cboMaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                String nv = (String) cboMaNV.getSelectedItem();               
                String maNV = "";            
                FillCboNhanVien(nv);
                }
        });
    }
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jMenu1 = new javax.swing.JMenu();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        Tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenPGG = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        rdoTienGiam = new javax.swing.JRadioButton();
        rdoPhanTramGiam = new javax.swing.JRadioButton();
        txtGiaTriGiam = new javax.swing.JTextField();
        a = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        rdoKichHoat = new javax.swing.JRadioButton();
        rdoChuaKichHoat = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtMaPhieuGiamGia = new javax.swing.JTextField();
        hienthi = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        lblDieuKien = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhieuGiamGia = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cboGiaTri = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cboNgayTao = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cboMaNV = new javax.swing.JComboBox<>();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSuPgg = new javax.swing.JTable();
        txtLichSu = new javax.swing.JTextField();
        btnTimKiemLS = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Phiếu Giảm Giá");

        jLabel2.setText("Mã Phiếu Giảm Giá");
        jLabel2.setEnabled(false);

        jLabel3.setText("Tên Phiếu Giảm Giá");
        jLabel3.setEnabled(false);

        txtTenPGG.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Ngày Bắt Đầu");
        jLabel4.setEnabled(false);

        jLabel6.setText("Giá Trị Phiếu Giảm Giá");
        jLabel6.setEnabled(false);

        buttonGroup1.add(rdoTienGiam);
        rdoTienGiam.setText("Số Tiền Giảm");
        rdoTienGiam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTienGiamMouseClicked(evt);
            }
        });
        rdoTienGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTienGiamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoPhanTramGiam);
        rdoPhanTramGiam.setText("Phần Trăm Giảm");
        rdoPhanTramGiam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoPhanTramGiamMouseClicked(evt);
            }
        });

        txtGiaTriGiam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGiaTriGiam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtGiaTriGiam.setEnabled(false);

        a.setText("Hạn Mức Chi Tiêu");
        a.setEnabled(false);

        txtTongTienHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Trạng Thái");
        jLabel11.setEnabled(false);

        buttonGroup2.add(rdoKichHoat);
        rdoKichHoat.setText("Kích Hoạt");

        buttonGroup2.add(rdoChuaKichHoat);
        rdoChuaKichHoat.setText("Chưa Kích Hoạt");

        jLabel12.setText("Ghi Chú");
        jLabel12.setEnabled(false);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnThem.setText("Tạo");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel15.setText("Ngày Kết Thúc");
        jLabel15.setEnabled(false);

        jLabel17.setText("Số Lượng Phiếu Giảm Giá:");
        jLabel17.setEnabled(false);

        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton11.setText("-");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("+");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyMouseClicked(evt);
            }
        });
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel7.setText("Mã NV");
        jLabel7.setEnabled(false);

        txtMaNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMaPhieuGiamGia.setEnabled(false);

        hienthi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        hienthi.setForeground(new java.awt.Color(255, 0, 0));

        txtNgayBatDau.setDateFormatString("dd-MM-yyyy");

        txtNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        lblDieuKien.setText("?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(txtTenPGG, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(txtMaPhieuGiamGia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(62, 62, 62))
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoPhanTramGiam)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton12))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(347, 347, 347)
                                        .addComponent(jLabel17))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoKichHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoChuaKichHoat))
                                    .addComponent(jLabel11))
                                .addGap(240, 240, 240)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(114, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(rdoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(a)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDieuKien)))
                        .addGap(64, 64, 64))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel15))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenPGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoTienGiam))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoPhanTramGiam)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton12)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton11))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a)
                            .addComponent(lblDieuKien))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoKichHoat)
                    .addComponent(rdoChuaKichHoat)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuy))
                .addGap(27, 27, 27))
        );

        Tabs.addTab("Tạo Phiếu Giảm Giá", jPanel1);

        tblPhieuGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã PGG", "Tên PGG", "Giá Trị", "Hạn Mức Chi Tiêu", "Trạng Thái PGG", "Ngày Tạo", "Ngày Hết Hạn", "Ghi Chú", "Mã NV"
            }
        ));
        tblPhieuGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuGiamGiaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhieuGiamGiaMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhieuGiamGia);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        btnTimKiem.setText("Tìm Kiếm Mã");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel5.setText("Trạng Thái :");
        jLabel5.setEnabled(false);

        jLabel13.setText("Giá Trị:");
        jLabel13.setEnabled(false);

        jLabel14.setText("Ngày Tạo:");
        jLabel14.setEnabled(false);

        jLabel18.setText("Người Tạo:");
        jLabel18.setEnabled(false);

        btnFirst.setText("<<");

        btnPrev.setText("|<");

        btnNext.setText(">|");

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel18))
                            .addComponent(txtTimKiem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cboGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(cboNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(cboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(btnSua))
                .addContainerGap(195, Short.MAX_VALUE))
        );

        Tabs.addTab("Danh Sách Phiếu Giảm Giá", jPanel2);

        tblLichSuPgg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Lịch Sử", "Mã Phiếu Giảm Giá", "Giá Trị PGG", "Hạn Mực Chi Tiêu", "Ngày Sử Dụng", "Trạng Thái", "Ghi Chú"
            }
        ));
        jScrollPane3.setViewportView(tblLichSuPgg);

        btnTimKiemLS.setText("Tìm Kiếm");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Tổng tiền hàng", "Trạng Thái", "Ngày Thanh Toán", "Ghi Chú"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimKiemLS, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(458, 458, 458)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemLS))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        Tabs.addTab("Lịch Sử Sử Dụng", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        soluong = Integer.parseInt(txtSoLuong.getText());
        if(soluong == 1 ){
        JOptionPane.showMessageDialog(this, "Số Lượng Nhập Phải Lớn Hơn 0");
    }else{        
        soluong = soluong - 1;
        txtSoLuong.setText(String.valueOf(soluong));
        }
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        soluong = Integer.parseInt(txtSoLuong.getText());
        soluong = soluong + 1;
        txtSoLuong.setText(String.valueOf(soluong));
    }//GEN-LAST:event_jButton12MouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
       
    }//GEN-LAST:event_btnLastActionPerformed

    private void rdoTienGiamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTienGiamMouseClicked
        txtGiaTriGiam.setEnabled(true);       
        txtGiaTriGiam.setText("");
        hienthi.setText("đ");
    }//GEN-LAST:event_rdoTienGiamMouseClicked

    private void rdoPhanTramGiamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoPhanTramGiamMouseClicked
        txtGiaTriGiam.setEnabled(true);      
        txtGiaTriGiam.setText("");
        hienthi.setText("%");
    }//GEN-LAST:event_rdoPhanTramGiamMouseClicked

    private void rdoTienGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTienGiamActionPerformed
        
    }//GEN-LAST:event_rdoTienGiamActionPerformed

    private void tblPhieuGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiamGiaMouseClicked
        
    }//GEN-LAST:event_tblPhieuGiamGiaMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        soluong = Integer.parseInt(txtSoLuong.getText());
        
        for (int i = 0; i < soluong; i++) {
            Insert();
        }
        MsgBox.alert(this, "Thêm Thành Công!");
        Tabs.setSelectedIndex(1);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnHuyMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.row = tblPhieuGiamGia.getSelectedRow();
        if(list.size() == 0){
            JOptionPane.showMessageDialog(this, "Không có dữ liệu để sửa hãy nhập thêm Phiếu Giảm Giá!");
        }else if(row == -1){
            JOptionPane.showMessageDialog(this, "Hãy Chọn Phiếu Giảm Giá để sửa!");
        }else{                        
            try {
                SuaPhieuGiamGia Sua = new SuaPhieuGiamGia(this, rootPaneCheckingEnabled); 
                Sua.setEditPGG(pggDAO.selectById((Integer) tblPhieuGiamGia.getValueAt(row, 0)));
                Sua.setVisible(true); 
            } catch (ParseException ex) {
                Logger.getLogger(PhieuGiamGia.class.getName()).log(Level.SEVERE, null, ex);
            }                       
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        FillTable();
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        FillXoaTrangThai();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblPhieuGiamGiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiamGiaMousePressed
        if(evt.getClickCount() == 2){
            this.row = tblPhieuGiamGia.getSelectedRow();
        try {
            setModel(pggDAO.selectById((Integer) tblPhieuGiamGia.getValueAt(row, 0)));                    
        } catch (ParseException ex) {
            Logger.getLogger(PhieuGiamGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tabs.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblPhieuGiamGiaMousePressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        FillTable();  
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
//        FillTableTimKiem();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    
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
            java.util.logging.Logger.getLogger(PhieuGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhieuGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhieuGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhieuGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PhieuGiamGiaView dialog = new PhieuGiamGiaView(new javax.swing.JFrame(), true);
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
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JLabel a;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemLS;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboGiaTri;
    private javax.swing.JComboBox<String> cboMaNV;
    private javax.swing.JComboBox<String> cboNgayTao;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel hienthi;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDieuKien;
    private javax.swing.JRadioButton rdoChuaKichHoat;
    private javax.swing.JRadioButton rdoKichHoat;
    private javax.swing.JRadioButton rdoPhanTramGiam;
    private javax.swing.JRadioButton rdoTienGiam;
    private javax.swing.JTable tblLichSuPgg;
    private javax.swing.JTable tblPhieuGiamGia;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtLichSu;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPhieuGiamGia;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenPGG;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTienHang;
    // End of variables declaration//GEN-END:variables
}
