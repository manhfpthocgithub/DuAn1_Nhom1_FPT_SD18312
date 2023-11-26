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
import Utils.Auth;
import Utils.MsgBox;
public class PhieuGiamGiaJPanel extends javax.swing.JPanel {
    PhieuGiamGiaDAO pggDAO = new PhieuGiamGiaDAO(); 
    List<PhieuGiamGia> list = pggDAO.selectAll();
    int row = -1;  
    int soluong = 1;
    public PhieuGiamGiaJPanel() {
        initComponents();     
        init();
        Date d = new Date();
        txtNgayBatDau.setDate(d);
//      FillTable();        
    }
    
    void init(){        
        txtSoLuong.setText(String.valueOf(soluong)); 
        eventCBOTrangThai();
        initCBOTrangThai();
        eventCBONhanVien();
        initCBONhanVien();       
        pggDAO.updateStatus();
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
                row[2] = pgg.getGiaTriPGG();
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
                row[2] = pgg.getGiaTriPGG();
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
                row[2] = pgg.getGiaTriPGG();
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
                row[2] = pgg.getGiaTriPGG();
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
        txtGiaTriGiam.setText(String.valueOf(pgg.getGiaTriPGG()));
        txtTongTienHang.setText(String.valueOf(pgg.getTongTienHang()));
        txtNgayBatDau.setDate(pgg.getNgayTao());
        txtNgayKetThuc.setDate(pgg.getNgayHetHan());
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
        pgg.setNgayTao((txtNgayBatDau.getDate()));
        pgg.setNgayHetHan((txtNgayKetThuc.getDate()));
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
        rdoKichHoat.setSelected(true);
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
    
    void Update(){
        PhieuGiamGia pgg = getModel(); 
        try {
            pggDAO.updateStatus();
            pggDAO.update(pgg);
            this.FillTable();
            
            MsgBox.alert(this, "Sửa Thành Công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Sửa Thất Bại!");
        }
    }
    
    void Xoa(){
        PhieuGiamGia pgg = getModel();
        try {
            pggDAO.delete((Integer) tblPhieuGiamGia.getValueAt(row, 0));
            this.FillTable();
            MsgBox.alert(this, "Xóa Thành Công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Xóa Thất Bại!");
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
        Tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenPGG = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdoKichHoat = new javax.swing.JRadioButton();
        rdoChuaKichHoat = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtMaPhieuGiamGia = new javax.swing.JTextField();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnSua = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtGiaTriGiam = new javax.swing.JTextField();
        hienthi = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        a = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhieuGiamGia = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cboMaNV = new javax.swing.JComboBox<>();
        btnHienThi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("Mã Phiếu Giảm Giá");
        jLabel2.setEnabled(false);

        jLabel3.setText("Tên Phiếu Giảm Giá");
        jLabel3.setEnabled(false);

        txtTenPGG.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Ngày Bắt Đầu");
        jLabel4.setEnabled(false);

        jLabel11.setText("Trạng Thái");
        jLabel11.setEnabled(false);

        buttonGroup1.add(rdoKichHoat);
        rdoKichHoat.setText("Kích Hoạt");

        buttonGroup1.add(rdoChuaKichHoat);
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

        txtNgayBatDau.setDateFormatString("dd-MM-yyyy");

        txtNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel6.setText("Giá Trị Phiếu Giảm Giá");
        jLabel6.setEnabled(false);

        txtGiaTriGiam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGiaTriGiam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtGiaTriGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTriGiamActionPerformed(evt);
            }
        });

        hienthi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        hienthi.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        a.setText("Hạn Mức Chi Tiêu");
        a.setEnabled(false);

        txtTongTienHang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongTienHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jButton11.setText("-");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel17.setText("Số Lượng Phiếu Giảm Giá:");
        jLabel17.setEnabled(false);

        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12))
                    .addComponent(a)
                    .addComponent(jLabel17)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(txtTenPGG)
                                .addComponent(txtMaPhieuGiamGia)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(rdoKichHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rdoChuaKichHoat))
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel15)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel3))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenPGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoKichHoat)
                            .addComponent(rdoChuaKichHoat))
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnReset))
                .addContainerGap(45, Short.MAX_VALUE))
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
                "Mã PGG", "Tên PGG", "Giá Trị", "Hạn Mức Chi Tiêu", "Trạng Thái PGG", "Ngày Tạo", "Ngày Kết Thúc", "Ghi Chú", "Mã NV"
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

        btnTimKiem.setText("Tìm Kiếm ");
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

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--" }));

        jLabel18.setText("Người Tạo:");
        jLabel18.setEnabled(false);

        cboMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--" }));

        btnHienThi.setText("Hiển thị danh sách");
        btnHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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
                        .addGap(0, 32, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addComponent(btnTimKiem)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnHienThi)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa)))))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnHienThi))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        Tabs.addTab("Danh Sách Phiếu Giảm Giá", jPanel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Phiếu Giảm Giá");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        soluong = Integer.parseInt(txtSoLuong.getText());

        for (int i = 0; i < soluong; i++) {
            Insert();
        }
        MsgBox.alert(this, "Thêm Thành Công!");
        Tabs.setSelectedIndex(1);
    }//GEN-LAST:event_btnThemActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        soluong = Integer.parseInt(txtSoLuong.getText());
        if(soluong == 1 ){
            JOptionPane.showMessageDialog(this, "Số Lượng Nhập Phải Lớn Hơn 0");
        }else{
            soluong = soluong - 1;
            txtSoLuong.setText(String.valueOf(soluong));
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        soluong = Integer.parseInt(txtSoLuong.getText());
        soluong = soluong + 1;
        txtSoLuong.setText(String.valueOf(soluong));
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
            Update();
            Tabs.setSelectedIndex(1);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtGiaTriGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTriGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTriGiamActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
       if(txtTimKiem.getText().isEmpty()){           
        }else{
            FillTableTimKiem();
        }

    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
       if(txtTimKiem.getText().isEmpty()){           
        }else{
            FillTableTimKiem();
        }

    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void tblPhieuGiamGiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiamGiaMousePressed
        if(evt.getClickCount() == 2){
            this.row = tblPhieuGiamGia.getSelectedRow();
            try {
                setModel(pggDAO.selectById((Integer) tblPhieuGiamGia.getValueAt(row, 0)));
            } catch (ParseException ex) {
                Logger.getLogger(PhieuGiamGiaJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            Tabs.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblPhieuGiamGiaMousePressed

    private void tblPhieuGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiamGiaMouseClicked
        this.row = tblPhieuGiamGia.getSelectedRow();
        try {
            setModel(pggDAO.selectById((Integer) tblPhieuGiamGia.getValueAt(row, 0)));
        } catch (ParseException ex) {
            Logger.getLogger(PhieuGiamGiaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblPhieuGiamGiaMouseClicked

    private void btnHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiActionPerformed
              FillTable();
    }//GEN-LAST:event_btnHienThiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        Xoa();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JLabel a;
    private javax.swing.JButton btnHienThi;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboMaNV;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel hienthi;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoChuaKichHoat;
    private javax.swing.JRadioButton rdoKichHoat;
    private javax.swing.JTable tblPhieuGiamGia;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaTriGiam;
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
