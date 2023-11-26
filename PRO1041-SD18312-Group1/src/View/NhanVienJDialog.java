/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
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
import utils.JDBCHelper;
import utils.MsgBox;

/**
 *
 * @author Pico123
 */
public class NhanVienJDialog extends javax.swing.JDialog {

    NhanVienDao dao = new NhanVienDao();
    int row = 0;
    LichLamViecDao LLVDao = new LichLamViecDao();
    List<LichLamViec> list = new ArrayList<>();
    /**
     * Creates new form NhanVienJDialog
     */
    public NhanVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        fillComboboxGioiTinhKH();
        addlisst();
        fillTable();
        fillTable2();
    }
    
    
    //HIỂN THỊ DANH SÁCH BÊN BẢNG tblNhanVien
    void fillTable(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblNhanVien.getModel();
        tblModel.setRowCount(0);
        try {
//            String keyword = txtTimKiem.getText();
            //List<NhanVien> ds = dao.selectByKeyword(keyword); 
            List<NhanVien> ds = dao.selectAll();
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
    
    public NhanVien getModel(){
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        nv.setTenNV(txtTenNV.getText());
        if(rdoNam.isSelected()){
            nv.setGioitinh(true);
        }else{
            nv.setGioitinh(false);
        }
        nv.setNgaysinh(txtNgaySinh.getDate());
        nv.setSoCCCD(txtSoCccd.getText());
        nv.setSoDT(txtSodt.getText());
        nv.setEmail(txtEmail.getText());
        nv.setDiachi(txtDiachi.getText());
        if(rdoHD.isSelected()){
            nv.setTrangthai(true);
        }else{
            nv.setTrangthai(false);
        }
        nv.setGhichu(txtGhichu.getText());
        //nv.setSoluong(Integer.parseInt(txtSoLuongHoaDon.getText()));
        return nv;
    }
    
    public void setModel(NhanVien nv){
        txtMaNV.setText(nv.getMaNV());
        txtTenNV.setText(nv.getTenNV());
//        rdoNam.setSelected(nv.isGioitinh());
//        rdoNu.setSelected(nv.isGioitinh());
        if(nv.isGioitinh()){
            rdoNam.setSelected(true);
        }else{
            rdoNu.setSelected(true);
        }
        txtNgaySinh.setDate(nv.getNgaysinh());
        txtSoCccd.setText(nv.getSoCCCD());
        txtSodt.setText(nv.getSoDT());
        txtEmail.setText(nv.getEmail());
        txtDiachi.setText(nv.getDiachi());
//        rdoHD.setSelected(nv.isTrangthai());
//        rdoNHD.setSelected(nv.isTrangthai());
        if(nv.isTrangthai()){
            rdoHD.setSelected(true);
        }else{
            rdoNHD.setSelected(true);
        }
        txtGhichu.setText(nv.getGhichu());
        //txtSoLuongHoaDon.setText(String.valueOf(nv.getSoluong()));
        
    }
    
    //INSERT NHÂN VIÊN
    void insertNV(){
        NhanVien nv = getModel();
        try {
            dao.insert(nv);
            this.fillTable();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
        }
    }
    
    //UPDATE NHÂN VIÊN
    void updateNV(){
        NhanVien nv = getModel();
        try {
            dao.update(nv);
            this.fillTable();
            MsgBox.alert(this, "Update thành công");
        } catch (Exception e) {
        }
    }
    
    //CLICK VÀO BẲNG tblNhanVien
    void edit(){
        try {
            String maNV = (String) tblNhanVien.getValueAt(this.row, 0);
            NhanVien nv = dao.selectById(maNV);
            if (nv != null) {
                setModel(nv);

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
        List<NhanVien> lists1 = dao.filterByGioiTinhKH(gioiTinhKH);
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
    
    //TÌM KIẾM BÊN tblNhanVien
    void fillTableTimKiem(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblNhanVien.getModel();
        tblModel.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<NhanVien> ds = dao.selectByKeyword(keyword); 
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
    
    void search() {
        this.fillTableTimKiem();
       
        this.row = 0;

    }
    
    
    //HIỂN THỊ DANH SÁCH BÊN SỐ LƯỢNG HÓA ĐƠN
    void fillTable2(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblSLHDin.getModel();
        tblModel.setRowCount(0);
        try {
//            String keyword = txtTimKiem.getText();
            //List<NhanVien> ds = dao.selectByKeyword(keyword); 
            List<NhanVien> ds = dao.selectAll2();
            for (NhanVien nv : ds) {
                Object[] row = new Object[7];
                row[0] = nv.getMaNV();
                row[1] = nv.getTenNV();
                row[2] = nv.isGioitinh() ? "Nam" : "Nữ";
                row[3] = sd.format(nv.getNgaysinh());
                row[4] = nv.getSoDT();
                row[5] = nv.isTrangthai()? "Đang làm" : "Đã nghỉ";
                row[6] = nv.getSoluong();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    //HIỂN THỊ TÌM KIẾM Ở BẢNG tblSoLuongHoaDon
    void fillTableTimKiem2(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblSLHDin.getModel();
        tblModel.setRowCount(0);
        try {
            String keyword = txtTimNV.getText();
            List<NhanVien> ds = dao.selectByKeyword2(keyword); 
            for (NhanVien nv : ds) {
                Object[] row = new Object[7];
                row[0] = nv.getMaNV();
                row[1] = nv.getTenNV();
                row[2] = nv.isGioitinh() ? "Nam" : "Nữ";
                row[3] = sd.format(nv.getNgaysinh());
                row[4] = nv.getSoDT();
                row[5] = nv.isTrangthai()? "Đang làm" : "Đã nghỉ";
                row[6] = nv.getSoluong();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    //HIỂN THỊ LỊCH LÀM VIỆC
    void fillTableLichLamViec(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblNhanVien.getModel();
        tblModel.setRowCount(0);
        try { 
              list = LLVDao.selectAll();
            for (LichLamViec llv : list) {
                Object[] row = new Object[5];
                row[0] = llv.getID();
                row[1] = llv.getMaNv();
                row[2] = sd.format(llv.getNgayBD());
                row[3] = sd.format(llv.getNgayKT());
                row[4] = llv.getCalam();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    void fillTableLichLamViec2(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) this.tblLicLamViec.getModel();
        tblModel.setRowCount(0);
        try { 
              list = LLVDao.selectLLV(tblNhanVien.getValueAt(row, 0).toString());
            for (LichLamViec llv : list) {
                Object[] row = new Object[5];
                row[0] = llv.getID();
                row[1] = llv.getMaNv();
                row[2] = sd.format(llv.getNgayBD());
                row[3] = sd.format(llv.getNgayKT());
                row[4] = llv.getCalam();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    
    
    
    public LichLamViec getModelLLV(){
        LichLamViec llv = new LichLamViec();
//        llv.setID(Integer.parseInt(txtID.getText()));
        llv.setMaNv(txtNV.getText());
        llv.setNgayBD(txtNgauBD.getDate());
        llv.setNgayKT(txtNgayKT.getDate());
        llv.setCalam((String) cboCalam.getSelectedItem());
        return llv;
    }
    
    void insertLLV(){
        LichLamViec llv = getModelLLV();
        try {
            LLVDao.insert(llv);
            this.fillTableLichLamViec2();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
        }
    }
    
    void updateLLV(){
        LichLamViec llv = getModelLLV();
        try {
            LLVDao.update(llv);
            this.fillTableLichLamViec2();
            MsgBox.alert(this, "Update thành công");
        } catch (Exception e) {
        }
    }
    
    public void setModelLLV(LichLamViec llv){
        txtID.setText(String.valueOf(llv.getID()));
        txtNV.setText(llv.getMaNv());
        txtNgauBD.setDate(llv.getNgayBD());
        txtNgayKT.setDate(llv.getNgayKT());
        cboCalam.setSelectedItem(llv.getCalam());
    }
    
    void edit2(){
        try {
            Integer ID = (Integer) tblLicLamViec.getValueAt(this.row, 0);
            LichLamViec llv = LLVDao.selectById(ID);
            if (llv != null) {
                setModelLLV(llv);

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    void deleteLLV(){
        Integer xoaLLv = Integer.parseInt(txtID.getText());
        try {
            LLVDao.delete(xoaLLv);
            this.fillTableLichLamViec2();
            MsgBox.alert(this, "Delete thành công");
        } catch (Exception e) {
        }
    }
      
           
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtSoCccd = new javax.swing.JTextField();
        txtSodt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        rdoHD = new javax.swing.JRadioButton();
        rdoNHD = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhichu = new javax.swing.JTextArea();
        btnThemNV = new javax.swing.JButton();
        btnCapnhat = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLicLamViec = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNV = new javax.swing.JTextField();
        txtNgauBD = new com.toedter.calendar.JDateChooser();
        cboCalam = new javax.swing.JComboBox<>();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        txtID = new javax.swing.JTextField();
        btnThemLLV = new javax.swing.JButton();
        btnSuaLLV = new javax.swing.JButton();
        btnXoaLLV = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        cboTimgioitinh = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSLHDin = new javax.swing.JTable();
        btnCapnhatLai = new javax.swing.JButton();
        cboTimTT = new javax.swing.JComboBox<>();
        txtTimNV = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 153));
        jLabel1.setText("NHÂN VIÊN");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Tên nhân viên");

        jLabel4.setText("Giới tính");

        jLabel5.setText("Ngày sinh");

        jLabel6.setText("Số CCCD");

        jLabel7.setText("Số điện thoại");

        jLabel8.setText("Email");

        jLabel9.setText("Địa chỉ");

        jLabel10.setText("Trạng thái");

        jLabel11.setText("Ghi chú");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        buttonGroup2.add(rdoHD);
        rdoHD.setText("Đang làm");

        buttonGroup2.add(rdoNHD);
        rdoNHD.setText("Đã nghỉ");

        txtGhichu.setColumns(20);
        txtGhichu.setRows(5);
        jScrollPane2.setViewportView(txtGhichu);

        btnThemNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemNV.setText("+ Thêm NV");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnCapnhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapnhat.setText("Cập nhật");
        btnCapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatActionPerformed(evt);
            }
        });

        btnXoaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        txtNgaySinh.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(txtMaNV)
                    .addComponent(txtTenNV)
                    .addComponent(txtSoCccd)
                    .addComponent(txtSodt)
                    .addComponent(txtEmail)
                    .addComponent(txtDiachi)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnThemNV)
                .addGap(41, 41, 41)
                .addComponent(btnCapnhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaNV)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoCccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSodt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rdoHD)
                    .addComponent(rdoNHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV)
                    .addComponent(btnCapnhat)
                    .addComponent(btnXoaNV))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách lịch làm việc"));

        tblLicLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã nhân viên", "Ngày bắt đầu", "Ngày kết thúc", "Ca làm"
            }
        ));
        tblLicLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblLicLamViecMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblLicLamViec);

        jLabel12.setText("ID");

        jLabel13.setText("Mã NV");

        jLabel14.setText("Ngày bắt đầu");

        jLabel15.setText("Ca làm");

        jLabel16.setText("Ngày kết thúc");

        txtNgauBD.setDateFormatString("dd-MM-yyyy");

        cboCalam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca Sáng", "Ca Tối" }));

        txtNgayKT.setDateFormatString("dd-MM-yyyy");

        txtID.setEnabled(false);

        btnThemLLV.setText("+ Thêm");
        btnThemLLV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLLVActionPerformed(evt);
            }
        });

        btnSuaLLV.setText("Sửa");
        btnSuaLLV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLLVActionPerformed(evt);
            }
        });

        btnXoaLLV.setText("Xóa");
        btnXoaLLV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLLVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtID)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgauBD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCalam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemLLV)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaLLV)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaLLV)
                        .addGap(61, 61, 61))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemLLV)
                    .addComponent(btnSuaLLV)
                    .addComponent(btnXoaLLV))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16)
                        .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboCalam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgauBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
                        .addGap(0, 280, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cboTimgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Danh sách nhân viên", jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblSLHDin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Trạng thái", "Số lượng SP bán được"
            }
        ));
        tblSLHDin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSLHDinMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSLHDin);

        btnCapnhatLai.setText("Update");
        btnCapnhatLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatLaiActionPerformed(evt);
            }
        });

        cboTimTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtTimNV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimNVCaretUpdate(evt);
            }
        });

        jLabel17.setText("Tìm kiếm tên nhân viên");
        jLabel17.setToolTipText("");
        jLabel17.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTimTT, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151)
                .addComponent(btnCapnhatLai)
                .addGap(14, 14, 14))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTimTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapnhatLai)
                    .addComponent(jLabel17)
                    .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhân viên in số lượng hóa đơn", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(642, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(467, 467, 467))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jTabbedPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        insertNV();
    }//GEN-LAST:event_btnThemNVActionPerformed

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
        DefaultTableModel tblModel = (DefaultTableModel) this.tblLicLamViec.getModel();
        tblModel.setRowCount(0);
        int row = tblNhanVien.getSelectedRow();
        list = LLVDao.selectLLV(tblNhanVien.getValueAt(row, 0).toString());
        try {
//            String keyword = txtTimNV.getText();
//            List<NhanVien> ds = dao.selectByKeyword2(keyword); 
              
            for (LichLamViec llv : list) {
                Object[] row1 = new Object[5];
                row1[0] = llv.getID();
                row1[1] = llv.getMaNv();
                row1[2] = sd.format(llv.getNgayBD());
                row1[3] = sd.format(llv.getNgayKT());
                row1[4] = llv.getCalam();
                tblModel.addRow(row1);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnCapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatActionPerformed
        // TODO add your handling code here:
        updateNV();
        
    }//GEN-LAST:event_btnCapnhatActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        // TODO add your handling code here:
//        updateNV();
//        MsgBox.alert(this, "Xóa thành công");
        
        dao.updateTT(txtMaNV.getText());
        MsgBox.alert(this, "Xóa thành công");
        fillTable();
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void tblNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.row = tblNhanVien.rowAtPoint(evt.getPoint());
            edit();
        }
        
    }//GEN-LAST:event_tblNhanVienMousePressed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnCapnhatLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatLaiActionPerformed
        // TODO add your handling code here:
        fillTable2();
    }//GEN-LAST:event_btnCapnhatLaiActionPerformed

    private void txtTimNVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimNVCaretUpdate
        // TODO add your handling code here:
        fillTableTimKiem2();
    }//GEN-LAST:event_txtTimNVCaretUpdate

    private void tblSLHDinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSLHDinMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblSLHDinMouseClicked

    private void btnThemLLVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLLVActionPerformed
        // TODO add your handling code here:
        insertLLV();
    }//GEN-LAST:event_btnThemLLVActionPerformed

    private void btnSuaLLVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLLVActionPerformed
        // TODO add your handling code here:
        updateLLV();
    }//GEN-LAST:event_btnSuaLLVActionPerformed

    private void tblLicLamViecMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLicLamViecMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.row = tblLicLamViec.rowAtPoint(evt.getPoint());
            edit2();
        }
    }//GEN-LAST:event_tblLicLamViecMousePressed

    private void btnXoaLLVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLLVActionPerformed
        // TODO add your handling code here:
        deleteLLV();
    }//GEN-LAST:event_btnXoaLLVActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhanVienJDialog dialog = new NhanVienJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCapnhat;
    private javax.swing.JButton btnCapnhatLai;
    private javax.swing.JButton btnSuaLLV;
    private javax.swing.JButton btnThemLLV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnXoaLLV;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboCalam;
    private javax.swing.JComboBox<String> cboTimTT;
    private javax.swing.JComboBox<String> cboTimgioitinh;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoNHD;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblLicLamViec;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblSLHDin;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhichu;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNV;
    private com.toedter.calendar.JDateChooser txtNgauBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSoCccd;
    private javax.swing.JTextField txtSodt;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimNV;
    // End of variables declaration//GEN-END:variables

    private String Sting(int soluong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
