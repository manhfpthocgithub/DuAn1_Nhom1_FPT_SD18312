
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
import javax.swing.table.DefaultTableModel;
public class LichSuKhachHangJPanel extends javax.swing.JPanel {

     KhachHangDAO daokh = new KhachHangDAO();
    LichSuKhachHangDAO daolskh = new LichSuKhachHangDAO();
    TichDiemDAO daotd = new TichDiemDAO();
    EmailDAO daoem = new EmailDAO();
    PhieuGiamGiaDAO daopgg = new PhieuGiamGiaDAO();
    Email em;
    KhachHang kh;
    LichSuKhachHang lskh;
    HoaDon hd;
    
    int row = 0;
    
    public LichSuKhachHangJPanel() {
        initComponents();
      
        fillComboboxMaKH();
        fillComboboxMaHD();
        fillComboboxNgayTaoHD();
       
        addlistenerCBOLichSu();
      
        fillTableLSKH();

    }
 //LỊCH SỬ KHACH HÀNG
    void fillTableLSKH() {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) tblLichSuKhachHang.getModel();
        tblModel.setRowCount(0);
        try {
             String name = txtTimTenLSKH.getText();
            List<LichSuKhachHang> list = daolskh.selectLichSuKH();    
            for (LichSuKhachHang lskh : list) {
                Object[] row = new Object[6];
                row[0] = lskh.getMaKhachHang();
                row[1] = lskh.getTenKhachHang();
                row[2] = lskh.getMaHoaDon();
                row[3] = lskh.getThanhToan();
                row[4] = lskh.getTrangThaiHoaDon();
                row[5] = sd.format(lskh.getNgayTaoHoaDon());
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    void searchLichSu() {
        this.fillTableLSKH();
        this.row = 0;

    }

    void fillComboboxMaKH() {
        List<String> maKH = daolskh.selectMaKhachHangLSKH();
        Set<String> uniqueMaKH = new HashSet<>(maKH);
        for (String item : uniqueMaKH) {
            cboTimMaKH.addItem(item);
        }
    }

    public List<LichSuKhachHang> filterByMaKh(Integer maKH) {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tableModel = (DefaultTableModel) tblLichSuKhachHang.getModel();
        tableModel.setRowCount(0);
        List<LichSuKhachHang> lists = daolskh.filterByMaKH(maKH);
        for (LichSuKhachHang lskh : lists) {
            Object[] row = new Object[6];
            row[0] = lskh.getMaKhachHang();
            row[1] = lskh.getTenKhachHang();
            row[2] = lskh.getMaHoaDon();
            row[3] = lskh.getThanhToan();
            row[4] = lskh.getTrangThaiHoaDon();
            row[5] = sd.format(lskh.getNgayTaoHoaDon());
            tableModel.addRow(row);
        }
        return lists;
    }

    void fillComboboxMaHD() {
        List<String> maHD = daolskh.selectMaHoaDonLSKH();
        Set<String> uniqueMaHD = new HashSet<>(maHD);
        for (String item : uniqueMaHD) {
            cboTimMaHoaDon.addItem(item);
        }
    }

    public List<LichSuKhachHang> filterByMaHD(Integer maHD) {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tableModel = (DefaultTableModel) tblLichSuKhachHang.getModel();
        tableModel.setRowCount(0);
        List<LichSuKhachHang> lists = daolskh.filterByMaHD(maHD);
        for (LichSuKhachHang lskh : lists) {
            Object[] row = new Object[6];
            row[0] = lskh.getMaKhachHang();
            row[1] = lskh.getTenKhachHang();
            row[2] = lskh.getMaHoaDon();
            row[3] = lskh.getThanhToan();
            row[4] = lskh.getTrangThaiHoaDon();
            row[5] = sd.format(lskh.getNgayTaoHoaDon());
            tableModel.addRow(row);
        }
        return lists;
    }

    void fillComboboxNgayTaoHD() {
        List<String> ngayTaoHD = daolskh.selectNgayTaoHDLSKH();
        Set<String> uniquengayTaoHD = new HashSet<>(ngayTaoHD);
        for (String item : uniquengayTaoHD) {
            cboTimNgayTaoHD.addItem(item);
        }
    }

    public List<LichSuKhachHang> filterByNgayTaoHD(String ngayTaoHD) {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tableModel = (DefaultTableModel) tblLichSuKhachHang.getModel();
        tableModel.setRowCount(0);
        List<LichSuKhachHang> lists = daolskh.filterByNgayTaoHD(ngayTaoHD);
        for (LichSuKhachHang lskh : lists) {
            Object[] row = new Object[6];
            row[0] = lskh.getMaKhachHang();
            row[1] = lskh.getTenKhachHang();
            row[2] = lskh.getMaHoaDon();
            row[3] = lskh.getThanhToan();
            row[4] = lskh.getTrangThaiHoaDon();
            row[5] = sd.format(lskh.getNgayTaoHoaDon());
            tableModel.addRow(row);
        }
        return lists;
    }

    void addlistenerCBOLichSu() {
        //tìm loại khách hàng
        cboTimMaKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String maKH = (String) cboTimMaKH.getSelectedItem();
                Integer thuMaKH = null;

                if (maKH.equals("1")) {
                    thuMaKH = 1;
                }
                if (maKH.equals("2")) {
                    thuMaKH = 2;
                }
                if (maKH.equals("3")) {
                    thuMaKH = 3;
                }
                if (maKH.equals("4")) {
                    thuMaKH = 4;
                }
                if (maKH.equals("5")) {
                    thuMaKH = 5;
                }
                if (maKH.equals("6")) {
                    thuMaKH = 6;
                }
                if (maKH.equals("7")) {
                    thuMaKH = 7;
                }
                if (maKH.equals("8")) {
                    thuMaKH = 8;
                }
                if (maKH.equals("9")) {
                    thuMaKH = 9;
                }
                 if (maKH.equals("10")) {
                    thuMaKH = 10;
                }
                filterByMaKh(thuMaKH);
            }
        });
        //////////////////////
        cboTimMaHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String maHD = (String) cboTimMaHoaDon.getSelectedItem();
                Integer thuMaHD = null;

                if (maHD.equals("1")) {
                    thuMaHD = 1;
                }
                if (maHD.equals("2")) {
                    thuMaHD = 2;
                }
                if (maHD.equals("3")) {
                    thuMaHD = 3;
                }
                if (maHD.equals("4")) {
                    thuMaHD = 4;
                }
                if (maHD.equals("5")) {
                    thuMaHD = 5;
                }
                if (maHD.equals("21")) {
                    thuMaHD = 21;
                }
                if (maHD.equals("22")) {
                    thuMaHD = 22;
                }
                if (maHD.equals("23")) {
                    thuMaHD = 23;
                }
                if (maHD.equals("24")) {
                    thuMaHD = 24;
                }
                if (maHD.equals("25")) {
                    thuMaHD = 25;
                }
                if (maHD.equals("26")) {
                    thuMaHD = 26;
                }
                filterByMaHD(thuMaHD);
            }
        });
        ////////////////////////////////////////////
        cboTimNgayTaoHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String ngayTaoHD = (String) cboTimNgayTaoHD.getSelectedItem();
                String thuTuNgayTaoHD = null;

                if (ngayTaoHD.equals("2023-09-02")) {
                    thuTuNgayTaoHD = "2023-09-02";
                }
                if (ngayTaoHD.equals("2022-09-11")) {
                    thuTuNgayTaoHD = "2022-09-11";
                }
                if (ngayTaoHD.equals("2023-10-02")) {
                    thuTuNgayTaoHD = "2023-10-02";
                }
                if (ngayTaoHD.equals("2022-11-11")) {
                    thuTuNgayTaoHD = "2022-11-11";
                }
                if (ngayTaoHD.equals("2023-09-02")) {
                    thuTuNgayTaoHD = "2023-09-02";
                }
                if (ngayTaoHD.equals("2023-10-02")) {
                    thuTuNgayTaoHD = "2023-10-02";
                }

                filterByNgayTaoHD(thuTuNgayTaoHD);
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TÍCH ĐIỂM
    void fillTableTĐ(int MaKhachHang) {
        DefaultTableModel tblModel = (DefaultTableModel) tblTichDiem.getModel();
        tblModel.setRowCount(0);
        try {
            List<TichDiem> list = daotd.selectTichDiem(MaKhachHang);
            for (TichDiem td : list) {
                Object[] row = new Object[6];
                row[0] = td.getMaKhachHang();
                row[1] = td.getTenKhachHang();
                row[2] = td.getSoDienThoai();
                row[3] = td.getDiaChi();
                row[4] = td.getTongTienMua();
                row[5] = td.getTichDiem();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        tabDanhSach = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSuKhachHang = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTichDiem = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        txtTimTenLSKH = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cboTimMaHoaDon = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cboTimNgayTaoHD = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cboTimMaKH = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch Sử Mua Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblLichSuKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lịch sử", "Mã Khách Hàng", "Tên Khách Hàng", "Mã Hóa Đơn", "Thanh Toán", "Trạng Thái Hóa Đơn", "Ngày Tạo Hóa Đơn"
            }
        ));
        tblLichSuKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuKhachHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLichSuKhachHang);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        tabDanhSach.addTab("Danh Sách Khách Hàng", jPanel9);

        tblTichDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Địa Chỉ", "Tổng Tiền ", "Tích Điểm"
            }
        ));
        tblTichDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTichDiemMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblTichDiem);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tabDanhSach.addTab("Tích Điểm Khách Hàng", jPanel10);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabDanhSach)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabDanhSach)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jButton7.setText("TÌM KIẾM");

        txtTimTenLSKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimTenLSKHCaretUpdate(evt);
            }
        });
        txtTimTenLSKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimTenLSKHActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Mã Hóa Đơn");

        cboTimMaHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboTimMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimMaHoaDonActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Ngày Tạo Hóa Đơn");

        cboTimNgayTaoHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboTimNgayTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimNgayTaoHDActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Tên Khách Hàng");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Mã Khách Hàng");

        cboTimMaKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboTimMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimMaKHActionPerformed(evt);
            }
        });

        jButton8.setText("HỦY");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimTenLSKH, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(43, 43, 43)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTimMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTimMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(45, 45, 45)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimNgayTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimTenLSKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(cboTimMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimNgayTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(176, 176, 176))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblLichSuKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuKhachHangMouseClicked
        int row = tblLichSuKhachHang.getSelectedRow();
        fillTableTĐ(Integer.parseInt(tblLichSuKhachHang.getValueAt(row, 0).toString()));
        tabDanhSach.setSelectedIndex(1);
    }//GEN-LAST:event_tblLichSuKhachHangMouseClicked

    private void tblTichDiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTichDiemMousePressed
        
    }//GEN-LAST:event_tblTichDiemMousePressed

    private void txtTimTenLSKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimTenLSKHCaretUpdate
        searchLichSu();
    }//GEN-LAST:event_txtTimTenLSKHCaretUpdate

    private void txtTimTenLSKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimTenLSKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimTenLSKHActionPerformed

    private void cboTimMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimMaHoaDonActionPerformed

    private void cboTimNgayTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimNgayTaoHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimNgayTaoHDActionPerformed

    private void cboTimMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimMaKHActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cboTimMaKH.setSelectedIndex(0);
        cboTimMaHoaDon.setSelectedIndex(0);
        cboTimNgayTaoHD.setSelectedIndex(0);
        fillTableLSKH();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboTimMaHoaDon;
    private javax.swing.JComboBox<String> cboTimMaKH;
    private javax.swing.JComboBox<String> cboTimNgayTaoHD;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane tabDanhSach;
    private javax.swing.JTable tblLichSuKhachHang;
    private javax.swing.JTable tblTichDiem;
    private javax.swing.JTextField txtTimTenLSKH;
    // End of variables declaration//GEN-END:variables
}
