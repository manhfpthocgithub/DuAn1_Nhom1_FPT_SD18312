package View;

import DAO.PhieuGiaoHangDAO;
import DAO.PhieuGiaoHang_ChiTietDAO;
import Entity.PhieuGiaoHang;
import Entity.PhieuGiaoHang_ChiTiet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import utils.JDBCHelper;
import utils.MsgBox;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;

public class PhieuGiaoHang_JPanel extends javax.swing.JPanel {

    PhieuGiaoHangDAO PGH_DAO = new PhieuGiaoHangDAO();
    PhieuGiaoHang_ChiTietDAO PGH_ChiTietDAO = new PhieuGiaoHang_ChiTietDAO();
     PhieuGiaoHang_DaXoa_JPanel cc = new PhieuGiaoHang_DaXoa_JPanel();
     PGH_ChiTiet_JPanel cc1 = new PGH_ChiTiet_JPanel();
   
   

    // ... (Các thành phần và mã khác)

    public javax.swing.JTable getTblPhieuGiaoHang() {
        return tblPhieuGiaoHang;
    }
    
    int row = 0;
    PhieuGiaoHang model = new PhieuGiaoHang();

    public PhieuGiaoHang_JPanel() {
        initComponents();
        init();
      
    }

  private void handleRowSelection(int selectedRow) {
        // Thực hiện xử lý khi chọn dòng
        // Ví dụ: In ra số dòng được chọn
        System.out.println("Selected row: " + selectedRow);
    }

    void init() {
        fillTable();
        initCBO_DVVC();
        initCBO_HinhThucThanhToan();
        addlistenerCBO_DVVC();
        addlistenerCBOHTTT();
        addlistenerFilterNgayGiaoHang();
        addlistenerFilterNgayNhanHang();
        addlistenerCBO_TTGH();

    }

    void initCBO_DVVC() {
        // In your initialization code or constructor
        List<String> tenDonViList = PGH_DAO.selectAllTenDonViVanChuyen();
        Set<String> uniqueDonViSet = new HashSet<>(tenDonViList); // Use a set to remove duplicates

        for (String tenDonVi : uniqueDonViSet) {
            cboDonViVanChuyen.addItem(tenDonVi);
            cboDVVCSearch.addItem(tenDonVi);
        }

    }

    void initCBO_HinhThucThanhToan() {
        // Trong phần khởi tạo hoặc constructor của bạn
        List<Integer> HinhThucTT_list = PGH_DAO.selectAllHinhThuThanhToan();
        Set<Integer> uniqueDonViSet = new HashSet<>(HinhThucTT_list); //Loại bỏ các phần tử trùng nhau

        for (Integer hinhThucTT : uniqueDonViSet) {
            if (hinhThucTT.equals(1)) {
                cboSearchHTTT.addItem("Khi nhận hàng");
            }
            if (hinhThucTT.equals(0)) {
                cboSearchHTTT.addItem("Tại quầy");
            }

        }

    }

    private String mapMaDonViToTenDonVi(int maDonVi) {
        switch (maDonVi) {
            case 1:
                return "Giao hàng nhanh";
            case 2:
                return "Giao hàng tiết kiệm";
            case 3:
                return "Viettel Post";
            case 4:
                return "247 Express";
            case 5:
                return "J&T Express";

            default:
                return "Không xác định";
        }
    }

    private String mapTrangThaiPGH(String TrangThaiPGH) {
        switch (TrangThaiPGH) {
            case "DaGiao":
                return "Đã Giao";
            case "DangGiao":
                return "Đang Giao";
            case "ChuanBiHang":
                return "Chuẩn bị hàng";
            default:
                return "Không xác định";
        }
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblPhieuGiaoHang.getModel();
        model.setRowCount(0);
        try {

            List<PhieuGiaoHang> list = PGH_DAO.selectAll();
//            List<String> listTenDonViVanChuyenOfPGH = PGH_DAO.selectTenDonViVanChuyenOfPGH(); // Lấy danh sách tên đơn vị vận chuyển
            int index = 0; // Index để lấy tên đơn vị từ danh sách
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            for (PhieuGiaoHang phieugiaohang : list) {
                String ngayGiaoHangFormatted = sdf.format(phieugiaohang.getNgayGiaoHang());
                String ngayNhanHangFormatted = sdf.format(phieugiaohang.getNgayNhanHang());

                Object[] row = {
                    phieugiaohang.getMaPhieuGiaoHang(),
                    phieugiaohang.getMaHoaDon(),
                    mapMaDonViToTenDonVi(phieugiaohang.getMaDonViVanChuyen()),
                    phieugiaohang.getTenNguoiNhan(),
                    phieugiaohang.getSoDienThoaiNguoiNhan(),
                    phieugiaohang.getDiaChiNhanHang(),
                    phieugiaohang.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                    ngayGiaoHangFormatted,
                    ngayNhanHangFormatted,
                    mapTrangThaiPGH(phieugiaohang.getTrangThaiGiaoHang()),
                    String.format("%,.0f VNĐ", phieugiaohang.getTongGiaTriPGH()),
                    phieugiaohang.getGhiChuPGH()
                };
                model.addRow(row);
                index++; // Tăng index để lấy tên đơn vị vận chuyển tiếp theo
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void setForm(PhieuGiaoHang model) {
        txtMaPhieuGiaoHang.setText(String.valueOf(model.getMaPhieuGiaoHang()));
        txtMaHoaDon.setText(String.valueOf(model.getMaHoaDon()));
        if (model.getMaDonViVanChuyen() == 1) {
            cboDonViVanChuyen.setSelectedItem("Giao Hàng Nhanh");
        }
        if (model.getMaDonViVanChuyen() == 2) {
            cboDonViVanChuyen.setSelectedItem("Giao Hàng Tiết Kiệm");
        }
        if (model.getMaDonViVanChuyen() == 3) {
            cboDonViVanChuyen.setSelectedItem("Viettel Post");
        }
        if (model.getMaDonViVanChuyen() == 4) {
            cboDonViVanChuyen.setSelectedItem("247 Express");
        }
        if (model.getMaDonViVanChuyen() == 5) {
            cboDonViVanChuyen.setSelectedItem("J&T Express");
        }

        rdoKhiNhanHang.setSelected(model.isHinhThucThanhToan());
        rdoTaiQuay.setSelected(!model.isHinhThucThanhToan());
        txtTongGiaTri.setText(String.valueOf(model.getTongGiaTriPGH()));
        txtGhiChu.setText(model.getGhiChuPGH());
        txtNguoiNhan.setText(model.getTenNguoiNhan());
        txtDiaChiNguoiNhan.setText(model.getDiaChiNhanHang());
        txtSDTNguoiNhan.setText(model.getSoDienThoaiNguoiNhan());
        ChooseNgayGiaoHang.setDate(model.getNgayGiaoHang());
        ChooseNgayNhanHang.setDate(model.getNgayNhanHang());
        if ("DangGiao".equals(model.getTrangThaiGiaoHang())) {
            cboTrangThaiGiaoHang.setSelectedItem("Đang Giao");
        }
        if ("DaGiao".equals(model.getTrangThaiGiaoHang())) {
            cboTrangThaiGiaoHang.setSelectedItem("Đã Giao");
        }
        if ("ChuanBiHang".equals(model.getTrangThaiGiaoHang())) {
            cboTrangThaiGiaoHang.setSelectedItem("Chuẩn bị hàng");
        }
//        if (model.getTinhTrangPGH()) {
//            rdoHoatDong.setSelected(true);
//        }

    }

    PhieuGiaoHang getForm() {

        model.setMaHoaDon(Integer.parseInt(txtMaHoaDon.getText()));
        if ("Giao Hàng Nhanh".equals(cboDonViVanChuyen.getSelectedItem())) {
            model.setMaDonViVanChuyen(1);
        }
        if ("Giao Hàng Tiết Kiệm".equals(cboDonViVanChuyen.getSelectedItem())) {
            model.setMaDonViVanChuyen(2);
        }
        if ("Viettel Post".equals(cboDonViVanChuyen.getSelectedItem())) {
            model.setMaDonViVanChuyen(3);
        }
        if ("247 Express".equals(cboDonViVanChuyen.getSelectedItem())) {
            model.setMaDonViVanChuyen(4);
        }
        if ("J&T Express".equals(cboDonViVanChuyen.getSelectedItem())) {
            model.setMaDonViVanChuyen(5);
        }

        if (rdoKhiNhanHang.isSelected()) {
            model.setHinhThucThanhToan(true);
        } else {
            model.setHinhThucThanhToan(false);
        }
        model.setTongGiaTriPGH(Double.parseDouble(txtTongGiaTri.getText()));
        model.setGhiChuPGH(txtGhiChu.getText());
        model.setTenNguoiNhan(txtNguoiNhan.getText());
        model.setDiaChiNhanHang(txtDiaChiNguoiNhan.getText());
        model.setSoDienThoaiNguoiNhan(txtSDTNguoiNhan.getText());
        model.setNgayGiaoHang(ChooseNgayGiaoHang.getDate());
        model.setNgayNhanHang(ChooseNgayNhanHang.getDate());
        // Kiểm tra null trước khi thực hiện các thay đổi
        if (model != null) {
            if (cboTrangThaiGiaoHang.getSelectedItem() == "Chuẩn bị hàng") {
                model.setTrangThaiGiaoHang("ChuanBiHang");
            } else if (cboTrangThaiGiaoHang.getSelectedItem() == "Đang Giao") {
                model.setTrangThaiGiaoHang("DangGiao");
            } else if (cboTrangThaiGiaoHang.getSelectedItem() == "Đã Giao") {
                model.setTrangThaiGiaoHang("DaGiao");
            }
        }

//        if (rdoHoatDong.isSelected()) {
//            model.setTinhTrangPGH(true);
//        } else {
//            model.setTinhTrangPGH(false);
//        }
        return model;
    }

    void edit() {
        try {
            int phieuGiaoHang = (int) tblPhieuGiaoHang.getValueAt(this.row, 0);
            model = PGH_DAO.selectById(phieuGiaoHang);
            if (model != null) {
                setForm(model);
            } else {
                MsgBox.alert(this, "không tìm thấy");
            }
        } catch (Exception e) {

        }
    }

    void insert() {
        model = getForm();
        try {
            PGH_DAO.insert(model);
            this.fillTable();
            MsgBox.alert(this, "Thêm mới thành công !");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại do lỗi " + e);
        }
    }

    void update() {
        model = getForm();
        try {
            PGH_DAO.update(model);
            this.fillTable();
           
            cc.fillTablePGHOff();
            MsgBox.alert(this, "Sửa thành công !");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại do lỗi " + e);
        }
    }

    void stopWorking() {
        model = getForm();
        if (cboTrangThaiGiaoHang.getSelectedItem().equals("Chuẩn bị hàng") || cboTrangThaiGiaoHang.getSelectedItem().equals("Đang Giao")) {
            MsgBox.alert(this, "Bạn chỉ có thể xoá các phiếu giao hàng với trạng thái \"Đã Giao\" !");
        } else if (cboTrangThaiGiaoHang.getSelectedItem().equals("Đã Giao")) {
            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá PGH này ?", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                try {
                    PGH_DAO.changStatus(model);
                    this.fillTable();
                   
                    cc.fillTablePGHOff();
                    MsgBox.alert(this, "Phiếu giao hàng này đã được xoá.");
                } catch (Exception e) {
                    MsgBox.alert(this, "Lỗi " + e);
                }
            }

        }

    }

    public List<PhieuGiaoHang> findByName() {

        DefaultTableModel tableModel = (DefaultTableModel) tblPhieuGiaoHang.getModel();
        tableModel.setRowCount(0);
        String name = txtSearchNguoiNhan.getText();
        List<PhieuGiaoHang> lists = PGH_DAO.findByName(name);

//             Thêm dữ liệu kết quả vào bảng
        for (PhieuGiaoHang timTen : lists) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String ngayGiaoHangFormatted = sdf.format(timTen.getNgayGiaoHang());
            String ngayNhanHangFormatted = sdf.format(timTen.getNgayNhanHang());
            Object[] row = {
                timTen.getMaPhieuGiaoHang(),
                timTen.getMaHoaDon(),
                mapMaDonViToTenDonVi(timTen.getMaDonViVanChuyen()),
                timTen.getTenNguoiNhan(),
                timTen.getSoDienThoaiNguoiNhan(),
                timTen.getDiaChiNhanHang(),
                timTen.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                ngayGiaoHangFormatted,
                ngayNhanHangFormatted,
                timTen.getTrangThaiGiaoHang(),
                String.format("%,.0f VNĐ", timTen.getTongGiaTriPGH()),
                timTen.getGhiChuPGH()
            };
            tableModel.addRow(row);
        }
        return lists;
    } //Tìm theo tên theo các chữ cái nhập vào

    //Lấy sự kiện và lọc theo cbo Đơn vị vận chuyển
    //Lấy sự kiện cbo
    void addlistenerCBO_DVVC() {
        cboDVVCSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String tenDVVC = (String) cboDVVCSearch.getSelectedItem();
                int maDVVC = 0;

                if (tenDVVC.equals("Giao Hàng Nhanh")) {
                    maDVVC = 1;
                }
                if (tenDVVC.equals("Giao Hàng Tiết Kiệm")) {
                    maDVVC = 2;
                }
                if (tenDVVC.equals("Viettel Post")) {
                    maDVVC = 3;
                }
                if (tenDVVC.equals("247 Express")) {
                    maDVVC = 4;
                }
                if (tenDVVC.equals("J&T Express")) {
                    maDVVC = 5;
                }

                filterByDVVC(maDVVC);
            }
        });

    }

    //Đổ ra bảng đối tượng vừa lọc
    public List<PhieuGiaoHang> filterByDVVC(Integer maDVVC) {

        DefaultTableModel tableModel = (DefaultTableModel) tblPhieuGiaoHang.getModel();
        tableModel.setRowCount(0);
        List<PhieuGiaoHang> lists = PGH_DAO.filterByDVVC(maDVVC);

//             Thêm dữ liệu kết quả vào bảng
        for (PhieuGiaoHang timTen : lists) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String ngayGiaoHangFormatted = sdf.format(timTen.getNgayGiaoHang());
            String ngayNhanHangFormatted = sdf.format(timTen.getNgayNhanHang());
            Object[] row = {
                timTen.getMaPhieuGiaoHang(),
                timTen.getMaHoaDon(),
                mapMaDonViToTenDonVi(timTen.getMaDonViVanChuyen()),
                timTen.getTenNguoiNhan(),
                timTen.getSoDienThoaiNguoiNhan(),
                timTen.getDiaChiNhanHang(),
                timTen.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                ngayGiaoHangFormatted,
                ngayNhanHangFormatted,
                timTen.getTrangThaiGiaoHang(),
                String.format("%,.0f VNĐ", timTen.getTongGiaTriPGH()),
                timTen.getGhiChuPGH()
            };
            tableModel.addRow(row);
        }
        return lists;
    }
//đổ dữ liệu tìm theo Hình thức giao hàng

    void addlistenerCBOHTTT() {

        cboSearchHTTT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String hinhThucThanhToan = (String) cboSearchHTTT.getSelectedItem();
                int HTTT = 3;

                if (hinhThucThanhToan.equals("Khi nhận hàng")) {
                    HTTT = 1;
                }
                if (hinhThucThanhToan.equals("Tại quầy")) {
                    HTTT = 0;
                }

                filterByHTTT(HTTT);
            }
        });
    }

    public List<PhieuGiaoHang> filterByHTTT(Integer HTTT) {

        DefaultTableModel tableModel = (DefaultTableModel) tblPhieuGiaoHang.getModel();
        tableModel.setRowCount(0);
        List<PhieuGiaoHang> lists = PGH_DAO.filterByHTTT(HTTT);

//             Thêm dữ liệu kết quả vào bảng
        for (PhieuGiaoHang timTen : lists) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String ngayGiaoHangFormatted = sdf.format(timTen.getNgayGiaoHang());
            String ngayNhanHangFormatted = sdf.format(timTen.getNgayNhanHang());
            Object[] row = {
                timTen.getMaPhieuGiaoHang(),
                timTen.getMaHoaDon(),
                mapMaDonViToTenDonVi(timTen.getMaDonViVanChuyen()),
                timTen.getTenNguoiNhan(),
                timTen.getSoDienThoaiNguoiNhan(),
                timTen.getDiaChiNhanHang(),
                timTen.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                ngayGiaoHangFormatted,
                ngayNhanHangFormatted,
                timTen.getTrangThaiGiaoHang(),
                String.format("%,.0f VNĐ", timTen.getTongGiaTriPGH()),
                timTen.getGhiChuPGH()
            };
            tableModel.addRow(row);
        }
        return lists;
    }

    //Lọc theo ngày giao hàng
    //Lấy sự kiện chọn ngày của người dùng
    void addlistenerFilterNgayGiaoHang() {
        // Giả sử filterNgayGiaoHang là thành phần JDateChooser của bạn
        DataChooserNgayGiaoHang.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Phương thức này sẽ được gọi khi ngày trong JDateChooser thay đổi

                if (evt.getNewValue() instanceof Date) {
                    Date selectedDate = (Date) evt.getNewValue();

                    filterByNgayGiaoHang(selectedDate);

                }

            }
        });
    }

    public List<PhieuGiaoHang> filterByNgayGiaoHang(Date ngayGiaoHang) {
        DefaultTableModel tableModel = (DefaultTableModel) tblPhieuGiaoHang.getModel();
        tableModel.setRowCount(0);

        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date sqlNgayGiaoHang = new java.sql.Date(ngayGiaoHang.getTime());

        List<PhieuGiaoHang> lists = PGH_DAO.filterByNGH(sqlNgayGiaoHang);

        for (PhieuGiaoHang ngh : lists) {
            String ngayGiaoHangFormatted = sdfOutput.format(ngh.getNgayGiaoHang());
            String ngayNhanHangFormatted = sdfOutput.format(ngh.getNgayNhanHang());
            Object[] row = {
                ngh.getMaPhieuGiaoHang(),
                ngh.getMaHoaDon(),
                mapMaDonViToTenDonVi(ngh.getMaDonViVanChuyen()),
                ngh.getTenNguoiNhan(),
                ngh.getSoDienThoaiNguoiNhan(),
                ngh.getDiaChiNhanHang(),
                ngh.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                ngayGiaoHangFormatted,
                ngayNhanHangFormatted,
                ngh.getTrangThaiGiaoHang(),
                String.format("%,.0f VNĐ", ngh.getTongGiaTriPGH()),
                ngh.getGhiChuPGH()
            };
            tableModel.addRow(row);
        }

        return lists;
    }
//Lọc theo ngày nhận hàng
    //Lấy sự kiện chọn ngày của người dùng

    void addlistenerFilterNgayNhanHang() {
        // Giả sử filterNgayGiaoHang là thành phần JDateChooser của bạn
        DataChooserNgayNhanHang.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Phương thức này sẽ được gọi khi ngày trong JDateChooser thay đổi

                if (evt.getNewValue() instanceof Date) {
                    Date selectedDate = (Date) evt.getNewValue();

                    filterByNgayNhanHang(selectedDate);
                }

            }
        });
    }

    public List<PhieuGiaoHang> filterByNgayNhanHang(Date ngayGiaoHang) {
        DefaultTableModel tableModel = (DefaultTableModel) tblPhieuGiaoHang.getModel();
        tableModel.setRowCount(0);

        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date sqlNgayGiaoHang = new java.sql.Date(ngayGiaoHang.getTime());

        List<PhieuGiaoHang> lists = PGH_DAO.filterByNNH(sqlNgayGiaoHang);
//        lists = 

        for (PhieuGiaoHang ngh : lists) {
            String ngayGiaoHangFormatted = sdfOutput.format(ngh.getNgayGiaoHang());
            String ngayNhanHangFormatted = sdfOutput.format(ngh.getNgayNhanHang());
            Object[] row = {
                ngh.getMaPhieuGiaoHang(),
                ngh.getMaHoaDon(),
                mapMaDonViToTenDonVi(ngh.getMaDonViVanChuyen()),
                ngh.getTenNguoiNhan(),
                ngh.getSoDienThoaiNguoiNhan(),
                ngh.getDiaChiNhanHang(),
                ngh.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                ngayGiaoHangFormatted,
                ngayNhanHangFormatted,
                ngh.getTrangThaiGiaoHang(),
                String.format("%,.0f VNĐ", ngh.getTongGiaTriPGH()),
                ngh.getGhiChuPGH()
            };
            tableModel.addRow(row);
        }

        return lists;
    }
//Lọc theo trạng thái giao hàng (Chuẩn bị hàng/Đang giao/Đã giao)
    //Lấy sự kiện từ CBO 

    void addlistenerCBO_TTGH() {
        cboSearchTTGH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Phương thức này sẽ được gọi khi người dùng chọn một phần tử trong JComboBox
                String TTGH = (String) cboSearchTTGH.getSelectedItem();
                String tenTTGH = null;

                if (TTGH.equals("Đang Giao")) {
                    tenTTGH = "DangGiao";
                }
                if (TTGH.equals("Đã Giao")) {
                    tenTTGH = "DaGiao";
                }
                if (TTGH.equals("Chuẩn bị hàng")) {
                    tenTTGH = "ChuanBiHang";
                }

                filterByTTGH(tenTTGH);
            }
        });

    }
    //Đổ ra bảng đối tượng vừa lọc

    public List<PhieuGiaoHang> filterByTTGH(String TTGH) {

        DefaultTableModel tableModel = (DefaultTableModel) tblPhieuGiaoHang.getModel();
        tableModel.setRowCount(0);
        List<PhieuGiaoHang> lists = PGH_DAO.filterByTTGH(TTGH);

//             Thêm dữ liệu kết quả vào bảng
        for (PhieuGiaoHang timTen : lists) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String ngayGiaoHangFormatted = sdf.format(timTen.getNgayGiaoHang());
            String ngayNhanHangFormatted = sdf.format(timTen.getNgayNhanHang());
            Object[] row = {
                timTen.getMaPhieuGiaoHang(),
                timTen.getMaHoaDon(),
                mapMaDonViToTenDonVi(timTen.getMaDonViVanChuyen()),
                timTen.getTenNguoiNhan(),
                timTen.getSoDienThoaiNguoiNhan(),
                timTen.getDiaChiNhanHang(),
                timTen.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                ngayGiaoHangFormatted,
                ngayNhanHangFormatted,
                mapTrangThaiPGH(timTen.getTrangThaiGiaoHang()),
                String.format("%,.0f VNĐ", timTen.getTongGiaTriPGH()),
                timTen.getGhiChuPGH()
            };
            tableModel.addRow(row);
        }
        return lists;
    }
void fillTable_ChiTiet_PGH(Integer ma) {
        DefaultTableModel model = (DefaultTableModel) tblPGH_ChiTiet1.getModel();
        model.setRowCount(0);
        PhieuGiaoHang_ChiTiet pghct = new PhieuGiaoHang_ChiTiet();
        try {

            pghct = (PhieuGiaoHang_ChiTiet) PGH_ChiTietDAO.selectByMaHoaDon(ma);

            Object[] row = {
                pghct.getMaHoaDon(),
                pghct.getTenAoKhoac(),
                pghct.getSoLuongHDCT(),
                String.format("%,.0f VNĐ", pghct.getDonGia()),
                pghct.getDVVC(),
                pghct.isHinhThucThanhToan() ? "Hoả tốc" : "Thường"
            };
            model.addRow(row);
            if (pghct.isHinhThucThanhToan()) {
                model.setValueAt("30k", model.getRowCount() - 1, 6);

            } else {
                model.setValueAt("15k", model.getRowCount() - 1, 6);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrHinhThucThanhToan = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        background1 = new javax.swing.JPanel();
        bgThongTin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel12 = new javax.swing.JLabel();
        txtMaPhieuGiaoHang = new javax.swing.JTextField();
        txtMaHoaDon = new javax.swing.JTextField();
        cboDonViVanChuyen = new javax.swing.JComboBox<>();
        txtTongGiaTri = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rdoKhiNhanHang = new javax.swing.JRadioButton();
        rdoTaiQuay = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextPane();
        txtNguoiNhan = new javax.swing.JTextField();
        txtDiaChiNguoiNhan = new javax.swing.JTextField();
        txtSDTNguoiNhan = new javax.swing.JTextField();
        ChooseNgayGiaoHang = new com.toedter.calendar.JDateChooser();
        ChooseNgayNhanHang = new com.toedter.calendar.JDateChooser();
        cboTrangThaiGiaoHang = new javax.swing.JComboBox<>();
        bgChucNang = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        bgTimKiemVaBoLoc = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtSearchNguoiNhan = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cboDVVCSearch = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cboSearchHTTT = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        DataChooserNgayGiaoHang = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        DataChooserNgayNhanHang = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        cboSearchTTGH = new javax.swing.JComboBox<>();
        bgDanhSachPGH = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuGiaoHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPGH_ChiTiet1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        background1.setBackground(new java.awt.Color(204, 204, 204));

        bgThongTin.setBackground(new java.awt.Color(255, 255, 255));
        bgThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phiếu giao hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã hoá đơn");
        jLabel1.setAlignmentY(2.0F);

        jLabel2.setText("Đơn vị vận chuyển");
        jLabel2.setAlignmentY(2.0F);

        jLabel3.setText("SĐT người nhận");

        jLabel4.setText("Địa chỉ người nhận");

        jLabel5.setText("Tên người nhận");

        jLabel6.setText("Hình thức thanh toán");

        jLabel7.setText("Ngày nhận hàng");

        jLabel8.setText("Trạng thái giao hàng");

        jLabel9.setText("Ngày giao hàng");

        jLabel10.setText("Tổng giá trị");

        jLabel11.setText("Ghi chú");

        jLabel12.setText("Mã phiếu giao hàng");
        jLabel12.setAlignmentY(2.0F);

        txtMaPhieuGiaoHang.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("VND");

        rdoKhiNhanHang.setText("Khi nhận hàng");

        rdoTaiQuay.setText("Tại quầy");

        jScrollPane2.setViewportView(txtGhiChu);

        txtSDTNguoiNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTNguoiNhanActionPerformed(evt);
            }
        });

        ChooseNgayGiaoHang.setDateFormatString("dd-MM-yyyy");

        ChooseNgayNhanHang.setDateFormatString("dd-MM-yyyy");

        cboTrangThaiGiaoHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chuẩn bị hàng", "Đang Giao", "Đã Giao" }));

        javax.swing.GroupLayout bgThongTinLayout = new javax.swing.GroupLayout(bgThongTin);
        bgThongTin.setLayout(bgThongTinLayout);
        bgThongTinLayout.setHorizontalGroup(
            bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgThongTinLayout.createSequentialGroup()
                .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(rdoKhiNhanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10)
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(75, 75, 75)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addComponent(txtTongGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel1)))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDonViVanChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPhieuGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChiNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ChooseNgayNhanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(ChooseNgayGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(39, 39, 39)
                        .addComponent(cboTrangThaiGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        bgThongTinLayout.setVerticalGroup(
            bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgThongTinLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtMaPhieuGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboDonViVanChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rdoKhiNhanHang)
                            .addComponent(rdoTaiQuay))
                        .addGap(40, 40, 40)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTongGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(34, 34, 34)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDiaChiNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel7))
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addComponent(ChooseNgayGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ChooseNgayNhanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboTrangThaiGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        bgChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        bgChucNang.setToolTipText("");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Xoá");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnReload.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReload.setText("ReloadData");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Clear ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgChucNangLayout = new javax.swing.GroupLayout(bgChucNang);
        bgChucNang.setLayout(bgChucNangLayout);
        bgChucNangLayout.setHorizontalGroup(
            bgChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnReload)
                .addGap(42, 42, 42)
                .addComponent(jButton4)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        bgChucNangLayout.setVerticalGroup(
            bgChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(btnReload)
                    .addComponent(jButton4))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        bgTimKiemVaBoLoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm & Bộ lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tìm theo tên người nhận:");

        txtSearchNguoiNhan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchNguoiNhanCaretUpdate(evt);
            }
        });
        txtSearchNguoiNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchNguoiNhanActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setText("Tìm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("Bộ lọc");

        cboDVVCSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDVVCSearchActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Đơn vị vận chuyển");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Hình thức thanh toán");

        cboSearchHTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSearchHTTTActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Ngày giao hàng");

        DataChooserNgayGiaoHang.setDateFormatString("dd-MM-yyyy\n");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Ngày nhận hàng");

        DataChooserNgayNhanHang.setDateFormatString("dd-MM-yyyy\n");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Trạng thái giao hàng");

        cboSearchTTGH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chuẩn bị hàng", "Đang Giao", "Đã Giao" }));

        javax.swing.GroupLayout bgTimKiemVaBoLocLayout = new javax.swing.GroupLayout(bgTimKiemVaBoLoc);
        bgTimKiemVaBoLoc.setLayout(bgTimKiemVaBoLocLayout);
        bgTimKiemVaBoLocLayout.setHorizontalGroup(
            bgTimKiemVaBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgTimKiemVaBoLocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgTimKiemVaBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgTimKiemVaBoLocLayout.createSequentialGroup()
                        .addGroup(bgTimKiemVaBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboSearchTTGH, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DataChooserNgayNhanHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSearchHTTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchNguoiNhan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDVVCSearch, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DataChooserNgayGiaoHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgTimKiemVaBoLocLayout.createSequentialGroup()
                                .addGroup(bgTimKiemVaBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgTimKiemVaBoLocLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(btnTimKiem))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(21, 21, 21))
                    .addGroup(bgTimKiemVaBoLocLayout.createSequentialGroup()
                        .addGroup(bgTimKiemVaBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel14))
                        .addGap(0, 1, Short.MAX_VALUE))))
        );
        bgTimKiemVaBoLocLayout.setVerticalGroup(
            bgTimKiemVaBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgTimKiemVaBoLocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(12, 12, 12)
                .addComponent(txtSearchNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel16)
                .addGap(12, 12, 12)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDVVCSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSearchHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataChooserNgayGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel20)
                .addGap(12, 12, 12)
                .addComponent(DataChooserNgayNhanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSearchTTGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bgDanhSachPGH.setBackground(new java.awt.Color(255, 255, 255));
        bgDanhSachPGH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu giao hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        bgDanhSachPGH.setForeground(new java.awt.Color(255, 255, 255));

        tblPhieuGiaoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã PGH", "Mã Hoá đơn", "Đơn vị vận chuyển", "Tên người nhận", "Số điện thoại người nhận", "Địa chỉ nhận hàng", "Hình thức thanh toán", "Ngày giao hàng", "Ngày nhận hàng", "Trạng thái giao hàng", "Tổng giá trị", "Ghi chú"
            }
        ));
        tblPhieuGiaoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhieuGiaoHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuGiaoHang);

        javax.swing.GroupLayout bgDanhSachPGHLayout = new javax.swing.GroupLayout(bgDanhSachPGH);
        bgDanhSachPGH.setLayout(bgDanhSachPGHLayout);
        bgDanhSachPGHLayout.setHorizontalGroup(
            bgDanhSachPGHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        bgDanhSachPGHLayout.setVerticalGroup(
            bgDanhSachPGHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgDanhSachPGHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bgDanhSachPGH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(bgTimKiemVaBoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bgThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bgChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addComponent(bgChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(bgThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bgTimKiemVaBoLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(bgDanhSachPGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Quản lý phiếu giao hàng", background1);

        tblPGH_ChiTiet1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hoá Đơn", "Tên Áo", "Số lượng áo", "Đơn giá", "Đơn vị vận chuyển", "Phương thức giao hàng", "Phí vận chuyển"
            }
        ));
        jScrollPane5.setViewportView(tblPGH_ChiTiet1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1036, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(186, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Chi tiết phiếu giao hàng", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTNguoiNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTNguoiNhanActionPerformed

    }//GEN-LAST:event_txtSDTNguoiNhanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        stopWorking();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtMaPhieuGiaoHang.setText("");
        txtMaHoaDon.setText("");
        btnGrHinhThucThanhToan.clearSelection();
        txtTongGiaTri.setText("");
        txtGhiChu.setText("");
        txtNguoiNhan.setText("");
        txtDiaChiNguoiNhan.setText("");
        txtSDTNguoiNhan.setText("");
        ChooseNgayGiaoHang.setDate(null);
        ChooseNgayNhanHang.setDate(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSearchNguoiNhanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchNguoiNhanCaretUpdate
        // TODO add your handling code here:
        findByName();
    }//GEN-LAST:event_txtSearchNguoiNhanCaretUpdate

    private void txtSearchNguoiNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchNguoiNhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchNguoiNhanActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cboDVVCSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDVVCSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDVVCSearchActionPerformed

    private void cboSearchHTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSearchHTTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSearchHTTTActionPerformed

    private void tblPhieuGiaoHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiaoHangMousePressed
         if (evt.getClickCount() == 1) {
            this.row = tblPhieuGiaoHang.rowAtPoint(evt.getPoint());
            edit();
        } else if (evt.getClickCount() == 2) {
            int row = tblPhieuGiaoHang.getSelectedRow();
            int MaHoaDon = (int) tblPhieuGiaoHang.getValueAt(row, 1);
            System.out.println(MaHoaDon);
            jTabbedPane1.setSelectedComponent(jPanel2);
            jPanel2.setVisible(true);
            fillTable_ChiTiet_PGH(MaHoaDon);
        }
    }//GEN-LAST:event_tblPhieuGiaoHangMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser ChooseNgayGiaoHang;
    private com.toedter.calendar.JDateChooser ChooseNgayNhanHang;
    private com.toedter.calendar.JDateChooser DataChooserNgayGiaoHang;
    private com.toedter.calendar.JDateChooser DataChooserNgayNhanHang;
    private javax.swing.JPanel background1;
    private javax.swing.JPanel bgChucNang;
    private javax.swing.JPanel bgDanhSachPGH;
    private javax.swing.JPanel bgThongTin;
    private javax.swing.JPanel bgTimKiemVaBoLoc;
    private javax.swing.ButtonGroup btnGrHinhThucThanhToan;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboDVVCSearch;
    private javax.swing.JComboBox<String> cboDonViVanChuyen;
    private javax.swing.JComboBox<String> cboSearchHTTT;
    private javax.swing.JComboBox<String> cboSearchTTGH;
    private javax.swing.JComboBox<String> cboTrangThaiGiaoHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoKhiNhanHang;
    private javax.swing.JRadioButton rdoTaiQuay;
    private javax.swing.JTable tblPGH_ChiTiet1;
    private javax.swing.JTable tblPhieuGiaoHang;
    private javax.swing.JTextField txtDiaChiNguoiNhan;
    private javax.swing.JTextPane txtGhiChu;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaPhieuGiaoHang;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextField txtSDTNguoiNhan;
    private javax.swing.JTextField txtSearchNguoiNhan;
    private javax.swing.JTextField txtTongGiaTri;
    // End of variables declaration//GEN-END:variables
}
