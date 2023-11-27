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

/**
 *
 * @author admin
 */
public class KhachHangDialog extends javax.swing.JDialog {

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

    public KhachHangDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillComboboxGioiTinhKH();
        fillComboboxTrangThaiKH();
        fillComboboxLoaiKH();
        fillComboboxMaKH();
        fillComboboxMaHD();
        fillComboboxNgayTaoHD();
        addlistenerCBO();
        addlistenerCBOLichSu();
        fillTable();
        fillTableLSKH();
        fillTableEmail();
        fillTableEmailTichDiem();
        fillPhieuGiamGia();

        setLocationRelativeTo(this);
    }

    //đổ dữ liệu lên table
    void fillTable() {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) tblKhachHang.getModel();
        tblModel.setRowCount(0);
        try {
            String keyword = txtTimTen.getText();
            List<KhachHang> ds = daokh.selectByKeyword(keyword); // đọc dữ liệu từ csdl
            for (KhachHang kh : ds) {
                Object[] row = new Object[10];
                row[0] = kh.getMaKhachHang();
                row[1] = kh.getTenKhachHang();
                row[2] = kh.getLoaiKhachHang();
                row[3] = kh.isGioiTinhKH() ? "Nam" : "Nữ";
                row[4] = sd.format(kh.getNgaySinhKH());
                row[5] = kh.getSoDienThoaiKH();
                row[6] = kh.getEmailKH();
                row[7] = kh.getDiaChiKH();
                row[8] = kh.isTrangThaiKH() ? "Đang hoạt động" : "Ngừng hoạt động";
                row[9] = kh.getGhiChuKH();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.confirm(this, "Lỗi truy vấn dữ liệu");
        }
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

    // tìm theo tên
    void searchKhachHang() {
        this.fillTable();
        this.clearForm();
        this.row = 0;

    }

    public boolean checkValidate() {
        if (txtTenKH.getText().isBlank()) {
            MsgBox.alert(this, "Họ tên khách hàng không được để trống");
            return false;
        }
        if (rdoNamKH.isSelected() == false && rdoNuKH.isSelected() == false) {
            MsgBox.alert(this, "Hãy chọn giới tính của khách hàng");
            return false;
        }
//        if (dcNgaySinhKH.getText().isBlank()) {
//            MsgBox.alert(this, "Ngày sinh không được để trống");
//            return false;
//        }
        if (txtSoDienThoaiKH.getText().isBlank()) {
            MsgBox.alert(this, "Số điện thoại không được để trống");
            return false;
        }
        if (txtEmailKH.getText().isBlank()) {
            MsgBox.alert(this, "Email không được để trống");
            return false;
        }
        if (txtDiaChiKH.getText().isBlank()) {
            MsgBox.alert(this, "Địa chỉ không được để trống");
            return false;
        }
        if (rdoDHD.isSelected() == false && rdoNHD.isSelected() == false) {
            MsgBox.alert(this, "Hãy chọn trạng thái");
            return false;
        }
        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //combobox
    void fillComboboxLoaiKH() {
        List<String> loaiKH = daokh.selectAllLoaiKH();
        Set<String> uniqueLoaiKH = new HashSet<>(loaiKH);
        for (String item : uniqueLoaiKH) {
            cboTimLoaiKH.addItem(item);
        }
    }

    void fillComboboxGioiTinhKH() {
        List<String> gioiTinhKH = daokh.selectAllGioiTinhKH();
        Set<String> uniqueGioiTinhKH = new HashSet<>(gioiTinhKH);
        for (String item1 : uniqueGioiTinhKH) {
            cboTimGioiTinh.addItem(item1);
        }
    }

    void fillComboboxTrangThaiKH() {
        List<String> trangThaiKH = daokh.selectAllTrangThaiKH();
        Set<String> uniqueTrangThaiKH = new HashSet<>(trangThaiKH);
        for (String item2 : uniqueTrangThaiKH) {
            cboTimTrangThai.addItem(item2);
        }
    }

    //tìm kiếm theo combobox Loại Khách Hàng
    public List<KhachHang> filterByLoaiKH(String loaiKhachHang) {

        DefaultTableModel tableModel = (DefaultTableModel) tblKhachHang.getModel();
        tableModel.setRowCount(0);
        List<KhachHang> lists = daokh.filterByLoaiKH(loaiKhachHang);
        for (KhachHang kh : lists) {
            Object[] row = new Object[10];
            row[0] = kh.getMaKhachHang();
            row[1] = kh.getTenKhachHang();
            row[2] = kh.getLoaiKhachHang();
            row[3] = kh.isGioiTinhKH() ? "Nam" : "Nữ";
            row[4] = kh.getNgaySinhKH();
            row[5] = kh.getSoDienThoaiKH();
            row[6] = kh.getEmailKH();
            row[7] = kh.getDiaChiKH();
            row[8] = kh.isTrangThaiKH() ? "Đang hoạt động" : "Ngừng hoạt động";
            row[9] = kh.getGhiChuKH();
            tableModel.addRow(row);
        }
        return lists;

    }

    void addlistenerCBO() {
        //tìm loại khách hàng
        cboTimLoaiKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String loaiKH = (String) cboTimLoaiKH.getSelectedItem();
                String thuTuLoaiKH = null;

                if (loaiKH.equals("Khách thường mua")) {
                    thuTuLoaiKH = "Khách thường mua";
                }
                if (loaiKH.equals("Khách thân thiết")) {
                    thuTuLoaiKH = "Khách thân thiết";
                }
                if (loaiKH.equals("Khách VIP")) {
                    thuTuLoaiKH = "Khách VIP";
                }
                filterByLoaiKH(thuTuLoaiKH);
            }
        });
        //tìm trang thai
        cboTimTrangThai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String trangThaiKH = (String) cboTimTrangThai.getSelectedItem();
                Integer thuTuTrangThai = null;

                if (trangThaiKH.equals("Đang hoạt động")) {
                    thuTuTrangThai = 1;
                }
                if (trangThaiKH.equals("Ngừng hoạt động")) {
                    thuTuTrangThai = 0;
                }

                filterByTrangThaiKH(thuTuTrangThai);
            }
        });
        // tìm giới tinh
        cboTimGioiTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String gioiTinhKH = (String) cboTimGioiTinh.getSelectedItem();
                Integer thuTuGioiTinh = null;

                if (gioiTinhKH.equals("Nam")) {
                    thuTuGioiTinh = 1;
                }
                if (gioiTinhKH.equals("Nữ")) {
                    thuTuGioiTinh = 0;
                }
//                if (gioiTinhKH.equals("All")) {
//                    fillTable();
//                }

                filterByGioiTinh(thuTuGioiTinh);
            }
        });
    }

    //tìm kiếm theo combobox Giới Tính
    public List<KhachHang> filterByGioiTinh(Integer gioiTinhKH) {

        DefaultTableModel tableModel = (DefaultTableModel) tblKhachHang.getModel();
        tableModel.setRowCount(0);
        List<KhachHang> lists1 = daokh.filterByGioiTinhKH(gioiTinhKH);
        for (KhachHang kh : lists1) {
            Object[] row = new Object[10];
            row[0] = kh.getMaKhachHang();
            row[1] = kh.getTenKhachHang();
            row[2] = kh.getLoaiKhachHang();
            row[3] = kh.isGioiTinhKH() ? "Nam" : "Nữ";
            row[4] = kh.getNgaySinhKH();
            row[5] = kh.getSoDienThoaiKH();
            row[6] = kh.getEmailKH();
            row[7] = kh.getDiaChiKH();
            row[8] = kh.isTrangThaiKH() ? "Đang hoạt động" : "Ngừng hoạt động";
            row[9] = kh.getGhiChuKH();
            tableModel.addRow(row);
        }

        return lists1;
    }

    //tìm kiếm theo combobox Trạng Thái
    public List<KhachHang> filterByTrangThaiKH(Integer trangThaiKH) {

        DefaultTableModel tableModel = (DefaultTableModel) tblKhachHang.getModel();
        tableModel.setRowCount(0);
        List<KhachHang> lists2 = daokh.filterByTrangThaiKH(trangThaiKH);
        for (KhachHang kh : lists2) {
            Object[] row = new Object[10];
            row[0] = kh.getMaKhachHang();
            row[1] = kh.getTenKhachHang();
            row[2] = kh.getLoaiKhachHang();
            row[3] = kh.isGioiTinhKH() ? "Nam" : "Nữ";
            row[4] = kh.getNgaySinhKH();
            row[5] = kh.getSoDienThoaiKH();
            row[6] = kh.getEmailKH();
            row[7] = kh.getDiaChiKH();
            row[8] = kh.isTrangThaiKH() ? "Đang hoạt động" : "Ngừng hoạt động";
            row[9] = kh.getGhiChuKH();
            tableModel.addRow(row);
        }

        return lists2;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public List<KhachHang> filterByLoaiVaGioiTinh(String loaiKhachHang, Integer gioiTinhKH) {

        DefaultTableModel tableModel = (DefaultTableModel) tblKhachHang.getModel();
        tableModel.setRowCount(0);
        List<KhachHang> lists3 = daokh.filterByLoaiVaGioiTinh(loaiKhachHang, gioiTinhKH);
        for (KhachHang kh : lists3) {
            Object[] row = new Object[10];
            row[0] = kh.getMaKhachHang();
            row[1] = kh.getTenKhachHang();
            row[2] = kh.getLoaiKhachHang();
            row[3] = kh.isGioiTinhKH() ? "Nam" : "Nữ";
            row[4] = kh.getNgaySinhKH();
            row[5] = kh.getSoDienThoaiKH();
            row[6] = kh.getEmailKH();
            row[7] = kh.getDiaChiKH();
            row[8] = kh.isTrangThaiKH() ? "Đang hoạt động" : "Ngừng hoạt động";
            row[9] = kh.getGhiChuKH();
            tableModel.addRow(row);
        }
        return lists3;

    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //LỊCH SỬ KHACH HÀNG
    void fillTableLSKH() {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) tblLichSuKhachHang.getModel();
        tblModel.setRowCount(0);
        try {
            String name = txtTimTenLSKH.getText();
            List<LichSuKhachHang> list = daolskh.searchByName(name);
            for (LichSuKhachHang lskh : list) {
                Object[] row = new Object[7];
                row[0] = lskh.getMaKhachHang();
                row[1] = lskh.getMaKhachHang();
                row[2] = lskh.getTenKhachHang();
                row[3] = lskh.getMaHoaDon();
                row[4] = lskh.getThanhToan();
                row[5] = lskh.getTrangThaiHoaDon();
                row[6] = sd.format(lskh.getNgayTaoHoaDon());
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
            Object[] row = new Object[7];
            row[0] = lskh.getMaKhachHang();
            row[1] = lskh.getTenKhachHang();
            row[2] = lskh.getMaHoaDon();
            row[3] = lskh.getThanhToan();
            row[4] = lskh.getMaKhachHang();
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
            Object[] row = new Object[7];

            row[0] = lskh.getMaKhachHang();
            row[1] = lskh.getTenKhachHang();
            row[2] = lskh.getMaHoaDon();
            row[3] = lskh.getThanhToan();
            row[4] = lskh.getMaKhachHang();
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
            Object[] row = new Object[7];

            row[0] = lskh.getMaKhachHang();
            row[1] = lskh.getTenKhachHang();
            row[2] = lskh.getMaHoaDon();
            row[3] = lskh.getThanhToan();
            row[4] = lskh.getMaKhachHang();
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //EMAIL

    void fillTableEmail() {
        DefaultTableModel tblModel = (DefaultTableModel) tblEmail.getModel();
        tblModel.setRowCount(0);
        try {
            List<Email> list = daoem.selectAll();
            for (Email em : list) {
                Object[] row = new Object[5];
                row[0] = em.getMaEmail();
                row[1] = em.getMaKhachHang();
                row[2] = em.getMaPhieuGiamGia();
                row[3] = em.getTieuDeEmail();
                row[4] = em.getNoiDungEmail();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void fillTableEmailTichDiem() {
        DefaultTableModel tblModel = (DefaultTableModel) tblTichDiem1.getModel();
        tblModel.setRowCount(0);
        try {
            List<Email> list1 = daoem.selectEmailTichDiem();
            for (Email em : list1) {
                Object[] row = new Object[5];
                row[0] = em.getMaKhachHang();
                row[1] = em.getTenKhachHang();
                row[2] = em.getEmail();
                row[3] = em.getTongMua();
                row[4] = em.getTichDiem();
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void guiEmail() {
        final String username = txtEmailGui.getText();
        final String password = String.valueOf(txtMatKhauGui.getPassword());

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        // authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        //tạo phiên làm việc       
        Session session = Session.getInstance(prop, auth);
        //gửi email
        final String diaChi = txtEmailNhan.getText();
        final String tieuDe = txtTieuDeEmail.getText();
        final String noiDung = txtNoiDungEmail.getText();
        //tạo 1 tin nhắn
        MimeMessage msg = new MimeMessage(session);
        try {
            //kiểu nột dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            //người gửi
            msg.setFrom(username);
            //người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(diaChi, false));
            //tiêu đề
            msg.setSubject(tieuDe);
            //nội dung
            msg.setText(noiDung);
            Transport.send(msg);
            MsgBox.alert(this, "Gửi email thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Gửi email thất bại");
        }

    }

    void insertEmail() {
        Email em = getModelEmail();
        try {
            daoem.insert(em);
            this.fillTableEmail();
//            this.clearForm();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void clearFormEmail() {
        txtMaKhachHangEmail.setText("");
        txtMaPhieuGiamGiaKH.setText("");
        txtEmailGui.setText("");
        txtMatKhauGui.setText("");
        txtEmailNhan.setText("");
        txtTieuDeEmail.setText("");
        txtNoiDungEmail.setText("");
    }

    public Email getModelEmail() {
        Email em = new Email();
//         if (!txtMaEmail.getText().trim().equals("")) {
//            em.setMaKhachHang(Integer.parseInt(txtMaEmail.getText()));
//        }
        em.setMaKhachHang(Integer.parseInt(txtMaKhachHangEmail.getText()));
        em.setMaPhieuGiamGia(Integer.parseInt(txtMaPhieuGiamGiaKH.getText()));
        em.setTieuDeEmail(txtTieuDeEmail.getText().toString());
        em.setNoiDungEmail(txtNoiDungEmail.getText().toString());
        return em;
    }

    void setModelEmail(Email em) {
        txtMaKhachHangEmail.setText(em.getMaKhachHang() + "");
        txtMaPhieuGiamGiaKH.setText(em.getMaPhieuGiamGia() + "");
        txtTieuDeEmail.setText(em.getTieuDeEmail() + "");
        txtNoiDungEmail.setText(em.getNoiDungEmail() + "");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Phiếu Giảm Giá Khách HÀngg
    void fillPhieuGiamGia() {
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel tblModel = (DefaultTableModel) tblPhieuGiamGiaKH.getModel();
        tblModel.setRowCount(0);
        try {

            List<PhieuGiamGia> list = daopgg.selectAll();
            for (PhieuGiamGia pgg : list) {
                Object[] row = new Object[7];
                row[0] = pgg.getMaPhieuGiamGia();
                row[1] = pgg.getTenPGG();
                row[2] = pgg.getGiaTriPGG();
                row[3] = String.format("%,.0f VNĐ", pgg.getTongTienHang());
                row[4] = pgg.isTrangThaiPGG() ? "Kích Hoạt" : "Chưa Kích Hoạt";
                row[5] = sd.format(pgg.getNgayTao());
                row[6] = sd.format(pgg.getNgayHetHan());

                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Dữ Liệu!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        txtTimTen = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboTimGioiTinh = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboTimTrangThai = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cboTimLoaiKH = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
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
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
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
        btnCapNhat1 = new javax.swing.JButton();
        dcNgaySinhKH = new com.toedter.calendar.JDateChooser();
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
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtEmailGui = new javax.swing.JTextField();
        txtMatKhauGui = new javax.swing.JPasswordField();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtEmailNhan = new javax.swing.JTextField();
        txtTieuDeEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNoiDungEmail = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtMaKhachHangEmail = new javax.swing.JTextField();
        txtMaPhieuGiamGiaKH = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEmail = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTichDiem1 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblPhieuGiamGiaKH = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(15, 15, 15))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jButton5.setText("TÌM KIẾM");

        txtTimTen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimTenCaretUpdate(evt);
            }
        });
        txtTimTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimTenActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giới Tính");

        cboTimGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboTimGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimGioiTinhActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Trạng Thái");

        cboTimTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboTimTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimTrangThaiActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tên");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Loại Khách Hàng");

        cboTimLoaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboTimLoaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimLoaiKHActionPerformed(evt);
            }
        });

        jButton6.setText("HỦY");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTimLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTimGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboTimTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel18))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTimGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTimTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTimLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addContainerGap())))
        );

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

        buttonGroup1.add(rdoNamKH);
        rdoNamKH.setText("Nam");

        buttonGroup1.add(rdoNuKH);
        rdoNuKH.setText("Nữ");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel19.setText("Loại Khách Hàng");

        txtMaKH.setEnabled(false);

        cboLoaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách thường mua", "Khách thân thiết", "Khách VIP" }));

        jLabel12.setText("Trạng Thái");

        buttonGroup2.add(rdoDHD);
        rdoDHD.setText("Đang hoạt động");

        buttonGroup2.add(rdoNHD);
        rdoNHD.setText("Ngừng hoạt động");

        btnCapNhat1.setText("Thêm ");
        btnCapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat1ActionPerformed(evt);
            }
        });

        dcNgaySinhKH.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                            .addComponent(txtMaKH))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(rdoNamKH, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(35, 35, 35)
                                                .addComponent(rdoNuKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtEmailKH, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addComponent(rdoDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rdoNHD))
                                                    .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(dcNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 24, Short.MAX_VALUE))))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdoNamKH)
                    .addComponent(rdoNuKH))
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dcNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmailKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rdoDHD)
                    .addComponent(rdoNHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông Tin Khách Hàng", jPanel1);

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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
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
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140))
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

        jTabbedPane1.addTab("Lịch Sử Mua Hàng", jPanel2);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Người Gửi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel13.setText("Email Gửi");

        jLabel14.setText("Mật Khẩu");

        txtEmailGui.setText("shopbeling4953@gmail.com");

        txtMatKhauGui.setText("tbksyusrquxodbex");
        txtMatKhauGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauGuiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMatKhauGui)
                    .addComponent(txtEmailGui, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMatKhauGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Người Nhận", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel15.setText("Email Nhận");

        jLabel16.setText("Nội Dung");

        txtNoiDungEmail.setColumns(20);
        txtNoiDungEmail.setRows(5);
        jScrollPane5.setViewportView(txtNoiDungEmail);

        jLabel17.setText("Tiêu Đề");

        jButton1.setText("HỦY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("GỬI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel20.setText("Mã Khách Hàng");

        jLabel21.setText("Mã Phiếu Giảm Giá");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaKhachHangEmail)
                            .addComponent(txtEmailNhan)
                            .addComponent(txtTieuDeEmail)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(txtMaPhieuGiamGiaKH))
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtMaPhieuGiamGiaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtMaKhachHangEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtEmailNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTieuDeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        tblEmail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Email", "Mã Khách Hàng", "Mã Phiếu Giảm Giá", "Tiêu Đề", "Nội Dung"
            }
        ));
        jScrollPane6.setViewportView(tblEmail);

        tblTichDiem1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Email", "Tổng Mua", "Tích Điểm"
            }
        ));
        tblTichDiem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTichDiem1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTichDiem1MousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tblTichDiem1);
        if (tblTichDiem1.getColumnModel().getColumnCount() > 0) {
            tblTichDiem1.getColumnModel().getColumn(0).setMinWidth(1);
            tblTichDiem1.getColumnModel().getColumn(0).setPreferredWidth(-10);
            tblTichDiem1.getColumnModel().getColumn(4).setMinWidth(1);
            tblTichDiem1.getColumnModel().getColumn(4).setPreferredWidth(-10);
        }

        tblPhieuGiamGiaKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Giảm Giá", "Tên Phiếu Giảm Giá", "Giá Trị", "Điều Kiện Sử Dụng", "Trạng Thái", "Ngày Bắt Đầu", "Ngày Kết Thúc"
            }
        ));
        tblPhieuGiamGiaKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuGiamGiaKHMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblPhieuGiamGiaKH);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(138, 138, 138))
        );

        jTabbedPane1.addTab("Gửi Email", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        if (evt.getClickCount() == 1) {
            this.row = tblKhachHang.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void txtTimTenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimTenCaretUpdate
        searchKhachHang();
    }//GEN-LAST:event_txtTimTenCaretUpdate

    private void txtTimTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimTenActionPerformed

    private void cboTimGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimGioiTinhActionPerformed

    private void cboTimLoaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimLoaiKHActionPerformed

    }//GEN-LAST:event_cboTimLoaiKHActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (txtMaKH.getText().isBlank()) {
            MsgBox.alert(this, "Mã khách hàng trống");
        } else {
            deleteKhachHang(Integer.parseInt(txtMaKH.getText()));
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (checkValidate()) {
            updateKhachHang();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnCapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat1ActionPerformed
        if (checkValidate()) {
            insertKhachHang();

        }
    }//GEN-LAST:event_btnCapNhat1ActionPerformed

    private void cboTimTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimTrangThaiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guiEmail();
        insertEmail();
        clearForm();
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(txtEmailNhan.getText()));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse(txtEmailGui.getText())
//            );
//            message.setSubject(txtTieuDe.getText());
//            message.setText(txtNoiDung.getText());
//
//            Transport.send(message);
//            MsgBox.alert(this, "Hoàn Thành");
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cboTimGioiTinh.setSelectedItem("All");
        cboTimLoaiKH.setSelectedItem("All");
        cboTimTrangThai.setSelectedItem("All");
        txtTimTen.setText("");
        fillTable();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearFormEmail();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblLichSuKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuKhachHangMouseClicked
        int row = tblLichSuKhachHang.getSelectedRow();
        fillTableTĐ(Integer.parseInt(tblLichSuKhachHang.getValueAt(row, 1).toString()));
        tabDanhSach.setSelectedIndex(1);
    }//GEN-LAST:event_tblLichSuKhachHangMouseClicked

    private void txtMatKhauGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauGuiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauGuiActionPerformed

    private void tblPhieuGiamGiaKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiamGiaKHMouseClicked
        int chon = tblPhieuGiamGiaKH.getSelectedRow();
        txtMaPhieuGiamGiaKH.setText(tblPhieuGiamGiaKH.getValueAt(chon, 0).toString());
    }//GEN-LAST:event_tblPhieuGiamGiaKHMouseClicked

    private void tblTichDiem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTichDiem1MouseClicked
        int chon = tblTichDiem1.getSelectedRow();
        txtMaKhachHangEmail.setText(tblTichDiem1.getValueAt(chon, 0).toString());
        txtEmailNhan.setText((String) tblTichDiem1.getValueAt(chon, 2));

    }//GEN-LAST:event_tblTichDiem1MouseClicked

    private void tblTichDiem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTichDiem1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTichDiem1MousePressed

    private void tblTichDiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTichDiemMousePressed
        if (evt.getClickCount() == 1) {
            edit();
        }    }//GEN-LAST:event_tblTichDiemMousePressed

    private void txtTimTenLSKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimTenLSKHCaretUpdate
        searchLichSu();
    }//GEN-LAST:event_txtTimTenLSKHCaretUpdate

    private void txtTimTenLSKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimTenLSKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimTenLSKHActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cboTimMaKH.setSelectedIndex(0);
        cboTimMaHoaDon.setSelectedIndex(0);
        cboTimNgayTaoHD.setSelectedIndex(0);
        fillTableLSKH();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cboTimNgayTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimNgayTaoHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimNgayTaoHDActionPerformed

    private void cboTimMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimMaHoaDonActionPerformed

    private void cboTimMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimMaKHActionPerformed

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
            java.util.logging.Logger.getLogger(KhachHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhachHangDialog dialog = new KhachHangDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhat1;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLoaiKH;
    private javax.swing.JComboBox<String> cboTimGioiTinh;
    private javax.swing.JComboBox<String> cboTimLoaiKH;
    private javax.swing.JComboBox<String> cboTimMaHoaDon;
    private javax.swing.JComboBox<String> cboTimMaKH;
    private javax.swing.JComboBox<String> cboTimNgayTaoHD;
    private javax.swing.JComboBox<String> cboTimTrangThai;
    private com.toedter.calendar.JDateChooser dcNgaySinhKH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoDHD;
    private javax.swing.JRadioButton rdoNHD;
    private javax.swing.JRadioButton rdoNamKH;
    private javax.swing.JRadioButton rdoNuKH;
    private javax.swing.JTabbedPane tabDanhSach;
    private javax.swing.JTable tblEmail;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblLichSuKhachHang;
    private javax.swing.JTable tblPhieuGiamGiaKH;
    private javax.swing.JTable tblTichDiem;
    private javax.swing.JTable tblTichDiem1;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtEmailGui;
    private javax.swing.JTextField txtEmailKH;
    private javax.swing.JTextField txtEmailNhan;
    private javax.swing.JTextArea txtGhiChuKH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaKhachHangEmail;
    private javax.swing.JTextField txtMaPhieuGiamGiaKH;
    private javax.swing.JPasswordField txtMatKhauGui;
    private javax.swing.JTextArea txtNoiDungEmail;
    private javax.swing.JTextField txtSoDienThoaiKH;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTieuDeEmail;
    private javax.swing.JTextField txtTimTen;
    private javax.swing.JTextField txtTimTenLSKH;
    // End of variables declaration//GEN-END:variables
}
