/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.AoKhoacMuaDongDAO;
import DAO.ChatLieuDAO;
import DAO.ChatLieuLoaiAoDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.LoaiAoDAO;
import DAO.MauSacDAO;
import DAO.SizeDAO;
import DAO.ThuongHieuChiTietDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import Entity.AoKhoacMuaDong;
import Entity.ChatLieu;
import Entity.ChatLieu_LoaiAo;
import Entity.ChiTietSanPham;
import Entity.LoaiAo;
import Entity.MauSac;
import Entity.Size;
import Entity.ThuongHieu;
import Entity.ThuongHieuChiTiet;
import Entity.XuatXu;
import Utils.XImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class FormSanPhamMoi extends javax.swing.JFrame {
 ImageIcon icon;
    List<ThuongHieuChiTiet> listthct = new ArrayList<>();
    AoKhoacMuaDongDAO akmdDao = new AoKhoacMuaDongDAO();
    ThuongHieuDAO thdao = new ThuongHieuDAO();
    LoaiAoDAO ladao = new LoaiAoDAO();
    XuatXuDAO xxdao = new XuatXuDAO();
    SizeDAO sizeDAO = new SizeDAO();
    MauSacDAO msdao = new MauSacDAO();
    ChatLieuDAO cldao = new ChatLieuDAO();
    ChatLieuLoaiAoDAO clladao = new ChatLieuLoaiAoDAO();
    ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();
    ThuongHieuChiTietDAO thctdao = new ThuongHieuChiTietDAO();
    List<ChatLieu_LoaiAo> listclla = new ArrayList<>();
    static int row = -1;
    static int row1 = -1;

    /**
     * Creates new form FormSanPhamMoi1
     */
    public FormSanPhamMoi() {
        initComponents();
        initTableSp();
        initTableSpct();
        fillTable();
        initCboTrangThaiSP();
        initcboThuongHieuSP();
        initcboXuatXuSP();
        initcboLoaiAoSP();
        initcboChatLieuSP();
        initcboThuongHieuLoc();
        initcboXuatXuLoc();
        initcboLoaiAoLoc();
        initcboChatLieuLoc();
        initcboMauSPCT();
        initcboSizeSPCT();
        initcboMauLoc();
        initcboSizeLoc();
        initCboTrangThaiSPCT();
    }

    private void initTableSp() {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPham1.getModel();
        DefaultTableModel tableModel1 = (DefaultTableModel) tblKhoiPhucSP.getModel();

        String[] colums = new String[]{
            "Mã sản phẩm", "Tên áo khoác", "Ngày nhập", "Ngày sửa", "Thương hiệu",
            "Xuất xứ", "Loại áo", "Chất liệu", "Tên phong cách", "Địa chỉ nhập hàng",
            "Ghi chú", "Trạng thái"
        };

        tableModel1.setColumnIdentifiers(colums);
        tableModel.setColumnIdentifiers(colums);
        tblSanPham1.setModel(tableModel);
        tblKhoiPhucSP.setModel(tableModel1);
    }

    private void initTableSpct() {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPhamChiTiet2.getModel();
        DefaultTableModel tableModel1 = (DefaultTableModel) tblSanPhamChiTiet2.getModel();
        String[] colums = new String[]{
            "Mã SPCT", "Tên size", "Tên màu sắc", "Số lượng", "Giá", "Tên ảnh", "Mô tả",
            "Trạng thái"
        };
        tableModel.setColumnIdentifiers(colums);
        tableModel1.setColumnIdentifiers(colums);
        tblSanPhamChiTiet2.setModel(tableModel);
        tblKhoiPhucSPCT2.setModel(tableModel1);

    }

    private void fillTable() {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPham1.getModel();
        tableModel.setRowCount(0);
        List<AoKhoacMuaDong> list = akmdDao.selectAll();
        for (AoKhoacMuaDong akmd : list) {
            ThuongHieuChiTiet thct = thctdao.selectById(akmd.getMaThuongHieuChiTiet());
            XuatXu xx = xxdao.selectById(thct.getMaXuatXu());
            ThuongHieu th = thdao.selectById(thct.getMaThuongHieu());
            ChatLieu_LoaiAo clla = clladao.selectById(akmd.getMaChatLieuLoaiAo());
            LoaiAo la = ladao.selectById(clla.getMaLoaiAo());
            ChatLieu cl = cldao.selectById(clla.getMaChatLieu());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            tableModel.addRow(new Object[]{
                akmd.getMaAoKhoac(), akmd, akmd.getNgayNhap(), akmd.getNgaySua(), th, xx, la, cl, akmd.getTenPhongCach(),
                akmd.getDiaChiNhapHang(), akmd.getGhiChu(), akmd.getTrangThai()
            });
        }
        tableModel.fireTableDataChanged();
    }

    private void fillTableKP(String maSp) {
        DefaultTableModel tableModel = (DefaultTableModel) tblKhoiPhucSP.getModel();
        tableModel.setRowCount(0);
        AoKhoacMuaDong akmd = akmdDao.selectByIdNHD(maSp);
        if (akmd == null) {
            return;
        }
        ThuongHieuChiTiet thct = thctdao.selectById(akmd.getMaThuongHieuChiTiet());
        XuatXu xx = xxdao.selectById(thct.getMaXuatXu());
        ThuongHieu th = thdao.selectById(thct.getMaThuongHieu());
        ChatLieu_LoaiAo clla = clladao.selectById(akmd.getMaChatLieuLoaiAo());
        LoaiAo la = ladao.selectById(clla.getMaLoaiAo());
        ChatLieu cl = cldao.selectById(clla.getMaChatLieu());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        tableModel.addRow(new Object[]{
            akmd.getMaAoKhoac(), akmd, akmd.getNgayNhap(), akmd.getNgaySua(), th, xx, la, cl, akmd.getTenPhongCach(),
            akmd.getDiaChiNhapHang(), akmd.getGhiChu(), akmd.getTrangThai()
        });
    }

    private void fillTableChiTiet(String maSP) {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPhamChiTiet2.getModel();
        tableModel.setRowCount(0);
        List<ChiTietSanPham> list = ctspdao.selectByMaSP(maSP);
        for (ChiTietSanPham ctsp : list) {
            Size sz = sizeDAO.selectById(ctsp.getMaSize());
            MauSac ms = msdao.selectById(ctsp.getMaMauSac());
            tableModel.addRow(new Object[]{
                ctsp.getMaSPCT(), sz, ms, ctsp.getSoLuongAK(), ctsp.getGiaAK(),
                ctsp.getTenAnh(), ctsp.getMoTa(), ctsp.isTrangThai() ? "Đang hoạt động" : "Ngừng hoạt động"
            });
        }
        tblSanPhamChiTiet2.setModel(tableModel);
    }

    private void fillTableKhoiPhucChiTiet(String maSP) {
        DefaultTableModel tableModel = (DefaultTableModel) tblKhoiPhucSPCT2.getModel();
        tableModel.setRowCount(0);
        ChiTietSanPham ctsp = ctspdao.selectById_NHD(maSP);
        if (ctsp == null) {
            return;
        }

        Size sz = sizeDAO.selectById(ctsp.getMaSize());
        MauSac ms = msdao.selectById(ctsp.getMaMauSac());
        tableModel.addRow(new Object[]{
            ctsp.getMaSPCT(), sz, ms, ctsp.getSoLuongAK(), ctsp.getGiaAK(),
            ctsp.getTenAnh(), ctsp.getMoTa(), ctsp.isTrangThai() ? "Đang hoạt động" : "Ngừng hoạt động"
        });

        tblKhoiPhucSPCT2.setModel(tableModel);
    }

    // thương hiệu
    private void initcboThuongHieuSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboThuongHieu1.getModel();
        boxModel.removeAllElements();
        List<ThuongHieu> list = thdao.selectAll();
        for (ThuongHieu th : list) {
            boxModel.addElement(th);
        }
        cboThuongHieu1.setModel(boxModel);
    }
//xuất xứ

    private void initcboXuatXuSP() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboXuatXu1.getModel();
        boxModel1.removeAllElements();
        ThuongHieu th = (ThuongHieu) cboThuongHieu1.getSelectedItem();
        if (thctdao.getXuatXuCuaThuongHieu(th.getMaThuongHieu()) == null) {
            boxModel1.addElement("Chưa có xuất xứ .");
            return;
        }
        List<ThuongHieuChiTiet> listthct = thctdao.getXuatXuCuaThuongHieu(th.getMaThuongHieu());

        for (ThuongHieuChiTiet thct : listthct) {
            XuatXu xx = xxdao.selectById(thct.getMaXuatXu());
            boxModel1.addElement(xx);
        }
        cboXuatXu1.setModel(boxModel1);
    }
// Loại áo

    private void initcboLoaiAoSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboLoaiAo1.getModel();
        boxModel.removeAllElements();
        List<LoaiAo> list = ladao.selectAll();
        for (LoaiAo la : list) {
            boxModel.addElement(la);
        }
        cboLoaiAo1.setModel(boxModel);
    }
//chất liệu

    private void initcboChatLieuSP() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboChatLieu1.getModel();
        boxModel1.removeAllElements();
        LoaiAo la = (LoaiAo) cboLoaiAo1.getSelectedItem();
        if (clladao.getChatLieuCuaLoaiAo(la.getMaLoaiAo()) == null) {
            boxModel1.addElement("Chưa có chất liệu .");
            return;
        }
        listclla = clladao.getChatLieuCuaLoaiAo(la.getMaLoaiAo());
        for (ChatLieu_LoaiAo clla : listclla) {
            ChatLieu cl = cldao.selectById(clla.getMaChatLieu());
            boxModel1.addElement(cl);
        }
        cboChatLieu1.setModel(boxModel1);
    }

    // thương hiệu
    private void initcboThuongHieuLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboThuongHieuLoc.getModel();
        boxModel.removeAllElements();
        List<ThuongHieu> list = thdao.selectAllNoTT();
        if (list.isEmpty()) {
            boxModel.addElement("Chưa có thương hiệu .");
            return;
        }
        boxModel.addElement("All");
        for (ThuongHieu th : list) {
            boxModel.addElement(th);
        }
        cboThuongHieuLoc.setModel(boxModel);
    }
//xuất xứ

    private void initcboXuatXuLoc() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboXuatXuLoc.getModel();
        boxModel1.removeAllElements();
        List<XuatXu> list = xxdao.selectAllNoTT();
        if (list.isEmpty()) {
            boxModel1.addElement("Chưa có xuất xứ .");
            return;
        }
        boxModel1.addElement("All");
        for (XuatXu xx : list) {
            boxModel1.addElement(xx);
        }
        cboXuatXuLoc.setModel(boxModel1);
    }
// Loại áo

    private void initcboLoaiAoLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboLoaiAoLoc.getModel();
        boxModel.removeAllElements();
        List<LoaiAo> list = ladao.selectAllNoTT();
        if (list.isEmpty()) {
            boxModel.addElement("Chưa có loại áo .");
            return;
        }
        boxModel.addElement("All");
        for (LoaiAo la : list) {
            boxModel.addElement(la);
        }
        cboLoaiAoLoc.setModel(boxModel);
    }
//chất liệu

    private void initcboChatLieuLoc() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboChatLieuLoc.getModel();
        boxModel1.removeAllElements();
        List<ChatLieu> list = cldao.selectAllNoTT();

        if (list.isEmpty()) {
            boxModel1.addElement("Chưa có chất liệu .");
            return;
        }
        boxModel1.addElement("All");
        for (ChatLieu cl : list) {

            boxModel1.addElement(cl);
        }
        cboChatLieuLoc.setModel(boxModel1);
    }

    private void initCboTrangThaiSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboTrangThai1.getModel();
        boxModel.removeAllElements();
        String[] listCbo = new String[]{
            "Đang hoạt động", "Đang nhập hàng", "Đang hết hàng", "Ngừng họat động"
        };
        for (String tt : listCbo) {
            boxModel.addElement(tt);
        }
        cboTrangThai1.setModel(boxModel);
    }

    private void initcboMauSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboMauSac2.getModel();
        boxModel.removeAllElements();
        List<MauSac> list = msdao.selectAll();
        if (list == null) {
            boxModel.addElement("Chưa có màu sắc .");
        }
        for (MauSac ms : list) {
            boxModel.addElement(ms);
        }
        cboMauSac2.setModel(boxModel);
    }
// size

    private void initcboSizeSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboSize2.getModel();
        boxModel.removeAllElements();
        List<Size> list = sizeDAO.selectAll();
        if (list == null) {
            boxModel.addElement("Chưa có size .");
        }
        for (Size sz : list) {
            boxModel.addElement(sz);
        }
        cboSize2.setModel(boxModel);
    }

    private void initcboMauLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboMauSacLoc.getModel();
        boxModel.removeAllElements();
        List<MauSac> list = msdao.selectAll();
        if (list == null) {
            boxModel.addElement("Chưa có màu sắc .");
        }
        boxModel.addElement("All");
        for (MauSac ms : list) {
            boxModel.addElement(ms);
        }
        cboMauSacLoc.setModel(boxModel);
    }
// size

    private void initcboSizeLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboSizeLoc.getModel();
        boxModel.removeAllElements();
        List<Size> list = sizeDAO.selectAll();
        if (list == null) {
            boxModel.addElement("Chưa có size .");
        }
        boxModel.addElement("All");

        for (Size sz : list) {
            boxModel.addElement(sz);
        }
        cboSizeLoc.setModel(boxModel);
    }

    private void initCboTrangThaiSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboTrangThai2.getModel();
        boxModel.removeAllElements();
        String[] listCbo = new String[]{
            "Đang hoạt động", "Ngừng họat động"
        };

        for (String tt : listCbo) {
            boxModel.addElement(tt);
        }
        cboTrangThai2.setModel(boxModel);
    }

    private void chonAnh() throws IOException {
        JFileChooser jfc = new JFileChooser("D:\\logos\\");
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            XImage.save(file); // Lưu hình vào thư mục logos
            // ĐỌc hình từ logos
            ImageIcon icon = XImage.read(file.getName());
            int height = lblHinhAnh2.getHeight();
            int width = lblHinhAnh2.getWidth();
            Image i;
            try {
                i = ImageIO.read(file).getScaledInstance(width, height, 0);
                icon.setImage(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            lblHinhAnh2.setIcon(icon);
            String tenFileChon = file.getName();
            lblHinhAnh2.setToolTipText(tenFileChon);
        }
    }

    private boolean validateSp() {
        String maSp = txtMaSP1.getText().trim();
        if (maSp.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống .");
            return false;
        }

        String tenAoKhoac = txtTenAoKhoac1.getText().trim();
        if (tenAoKhoac.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống .");
            return false;
        }
        Object selectedXx = cboXuatXu1.getSelectedItem();
        if (selectedXx instanceof String && selectedXx.equals("Chưa có xuất xứ .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm xuất xứ cho thương hiệu .");
            return false;
        }
        Object selectedCl = cboChatLieu1.getSelectedItem();
        if (selectedCl instanceof String && selectedCl.equals("Chưa có chất liệu .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm chất liệu cho loại áo.");
            return false;
        }
        String tenPhongCach = txtTenPhongCach1.getText().trim();
        if (tenPhongCach.equals(null)) {
            JOptionPane.showMessageDialog(this, "Tên phong cách không được để trống .");
            return false;
        }
        String diaChiNhapHang = txtDiaChiNhapHang1.getText().trim();
        if (diaChiNhapHang.equals(null)) {
            JOptionPane.showMessageDialog(this, "Địa chỉ nhập hàng không được để trống .");
            return false;
        }

        String ghiChu = txtGhiChu1.getText().trim();
        if (ghiChu.equals(null)) {
            JOptionPane.showMessageDialog(this, "Ghi chú không được để trống .");
            return false;
        }
        return true;
    }

    private boolean validateSpct() {
        String maSpct = txtMaSPCT2.getText().trim();
        if (maSpct.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm chi tiết không được để trống .");
            return false;
        }
        Object selectedSz = cboSize2.getSelectedItem();
        if (selectedSz instanceof String && selectedSz.equals("Chưa có size .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm size .");
            return false;
        }
        Object selectedMs = cboMauSac2.getSelectedItem();
        if (selectedMs instanceof String && selectedMs.equals("Chưa có màu sắc .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm màu sắc .");
            return false;
        }
        String chuoiSoLuong = txtSoLuong2.getText().trim();
        if (chuoiSoLuong.equals("")) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống .");
            return false;
        }
        try {
            int soLuong = Integer.parseInt(chuoiSoLuong);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn không .");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng chỉ được chứa số .");
            return false;
        }

        String chuoiGia = txtGia2.getText().trim();
        if (chuoiGia.equals("")) {
            JOptionPane.showMessageDialog(this, "Giá không được để trống .");
            return false;
        }
        try {
            double gia = Double.parseDouble(chuoiGia);
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Giá phải lớn hơn không .");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá chỉ được chứa số .");
            return false;
        }
        String moTa = txtMoTa2.getText().trim();
        if (moTa.equals("")) {
            JOptionPane.showMessageDialog(this, "Mô tả không được để trống .");
            return false;
        }
        String chuoiAnh = lblHinhAnh2.getToolTipText();
        if (chuoiAnh.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh cho sản phẩm .");
            return false;
        }
        return true;
    }

    private AoKhoacMuaDong getModelSP() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        ThuongHieu th = (ThuongHieu) cboThuongHieu1.getSelectedItem();
        XuatXu xx = (XuatXu) cboXuatXu1.getSelectedItem();
        System.out.println(th.getMaThuongHieu() + xx.getMaXuatXu());
        ThuongHieuChiTiet thct = thctdao.getThuongHieuChiTiet(th.getMaThuongHieu(), xx.getMaXuatXu());
        LoaiAo la = (LoaiAo) cboLoaiAo1.getSelectedItem();
        ChatLieu cl = (ChatLieu) cboChatLieu1.getSelectedItem();
        ChatLieu_LoaiAo clla = clladao.getChatLieuLoaiAo(cl.getMaChatLieu(), la.getMaLoaiAo());
        return new AoKhoacMuaDong(txtMaSP1.getText().trim(), txtTenAoKhoac1.getText().trim(), dateString, dateString, thct.getMaThuongHieuChiTiet(), clla.getMaChatLieuLoaiAo(), txtTenPhongCach1.getText().trim(), txtDiaChiNhapHang1.getText().trim(), txtGhiChu1.getText().trim(), cboTrangThai1.getSelectedItem().toString());
    }

    private ChiTietSanPham getModelSPCT() {
        Size sz = (Size) cboSize2.getSelectedItem();
        MauSac ms = (MauSac) cboMauSac2.getSelectedItem();
        return new ChiTietSanPham(txtMaSPCT2.getText().trim(), txtMaSP2.getText(), Double.parseDouble(txtGia2.getText()), Integer.parseInt(txtSoLuong2.getText()), sz.getMaSize(), ms.getMaMau(), lblHinhAnh2.getToolTipText(), txtMoTa2.getText(), cboTrangThai2.getSelectedItem() == "Đang hoạt động" ? true : false);
    }

    private void clearSp() {
        txtMaSP1.setText("");
        txtTenAoKhoac1.setText("");
        dcNgayNhap1.setDate(null);
        dcNgaySua1.setDate(null);
        cboThuongHieu1.setSelectedIndex(0);
        cboLoaiAo1.setSelectedIndex(0);
        txtTenPhongCach1.setText("");
        txtDiaChiNhapHang1.setText("");
        txtGhiChu1.setText("");
        cboTrangThai1.setSelectedIndex(0);
        row = -1;
    }

    private void clearSpCt() {
        txtMaSP2.setText("");
        txtMaSPCT2.setText("");
        cboSize2.setSelectedIndex(0);
        cboMauSac2.setSelectedIndex(0);
        txtSoLuong2.setText("");
        txtGia2.setText("");
        lblHinhAnh2.setToolTipText("");
        lblHinhAnh2.setIcon(null);
        txtMoTa2.setText("");
        cboTrangThai2.setSelectedIndex(0);
        row1 = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtMaSP1 = new javax.swing.JTextField();
        txtTenAoKhoac1 = new javax.swing.JTextField();
        dcNgayNhap1 = new com.toedter.calendar.JDateChooser();
        dcNgaySua1 = new com.toedter.calendar.JDateChooser();
        cboThuongHieu1 = new javax.swing.JComboBox<>();
        cboXuatXu1 = new javax.swing.JComboBox<>();
        btnThemThuongHieu = new javax.swing.JButton();
        btnThemXuatXu = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cboLoaiAo1 = new javax.swing.JComboBox<>();
        cboChatLieu1 = new javax.swing.JComboBox<>();
        txtTenPhongCach1 = new javax.swing.JTextField();
        txtDiaChiNhapHang1 = new javax.swing.JTextField();
        txtGhiChu1 = new javax.swing.JTextField();
        cboTrangThai1 = new javax.swing.JComboBox<>();
        btnThemLoaiAo = new javax.swing.JButton();
        btnThemChatLieu = new javax.swing.JButton();
        jPanel = new javax.swing.JPanel();
        pnKhoiPhucSp = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhoiPhucSP = new javax.swing.JTable();
        btnKhoiPhucSP = new javax.swing.JButton();
        btnThoatKhoiPhucSp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham1 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        btnCapNhatSPCT = new javax.swing.JButton();
        btnChiTietSP1 = new javax.swing.JButton();
        btnThemSPCT = new javax.swing.JButton();
        btnXoaSPCT1 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        cboThuongHieuLoc = new javax.swing.JComboBox<>();
        cboXuatXuLoc = new javax.swing.JComboBox<>();
        cboLoaiAoLoc = new javax.swing.JComboBox<>();
        cboChatLieuLoc = new javax.swing.JComboBox<>();
        btnTimKiemSp3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        txtMaSP2 = new javax.swing.JTextField();
        txtMaSPCT2 = new javax.swing.JTextField();
        cboSize2 = new javax.swing.JComboBox<>();
        cboMauSac2 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtSoLuong2 = new javax.swing.JTextField();
        txtGia2 = new javax.swing.JTextField();
        txtMoTa2 = new javax.swing.JTextField();
        cboTrangThai2 = new javax.swing.JComboBox<>();
        lblTenSP2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet2 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        btnCapNhatSPCT2 = new javax.swing.JButton();
        btnXoaSPCT2 = new javax.swing.JButton();
        btnThemSPCT2 = new javax.swing.JButton();
        pnKhoiPhucSPCT = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhoiPhucSPCT2 = new javax.swing.JTable();
        btnThoatKHSPCT = new javax.swing.JButton();
        btnKhoiPhucSPCT = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        lblHinhAnh2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cboMauSacLoc = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cboSizeLoc = new javax.swing.JComboBox<>();
        btnTimKiemSPCT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        jPanel4.setLayout(new java.awt.GridLayout(6, 1, 0, 20));

        jLabel2.setText("Mã sản phẩm");
        jPanel4.add(jLabel2);

        jLabel3.setText("Tên áo khoác :");
        jPanel4.add(jLabel3);

        jLabel4.setText("Ngày nhập :");
        jPanel4.add(jLabel4);

        jLabel5.setText("Ngày sửa :");
        jPanel4.add(jLabel5);

        jLabel6.setText("Thương Hiệu :");
        jPanel4.add(jLabel6);

        jLabel7.setText("Xuất xứ :");
        jPanel4.add(jLabel7);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(txtMaSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 261, -1));
        jPanel5.add(txtTenAoKhoac1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 261, -1));

        dcNgayNhap1.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcNgayNhap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 260, -1));

        dcNgaySua1.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcNgaySua1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 126, 261, -1));

        cboThuongHieu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboThuongHieu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThuongHieu1ActionPerformed(evt);
            }
        });
        jPanel5.add(cboThuongHieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 168, 220, -1));

        cboXuatXu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(cboXuatXu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 220, -1));

        btnThemThuongHieu.setText("+");
        btnThemThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuongHieuActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 170, 40, -1));

        btnThemXuatXu.setText("+");
        btnThemXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXuatXuActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 210, 40, -1));

        jPanel6.setLayout(new java.awt.GridLayout(6, 1, 0, 20));

        jLabel8.setText("Loại áo :");
        jPanel6.add(jLabel8);

        jLabel9.setText("Chất liệu :");
        jPanel6.add(jLabel9);

        jLabel10.setText("Tên phong cách :");
        jPanel6.add(jLabel10);

        jLabel11.setText("Địa chỉ nhập hàng :");
        jPanel6.add(jLabel11);

        jLabel12.setText("Ghi chú :");
        jPanel6.add(jLabel12);

        jLabel13.setText("Trạng thái :");
        jPanel6.add(jLabel13);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboLoaiAo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiAo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiAo1ActionPerformed(evt);
            }
        });
        jPanel7.add(cboLoaiAo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, -1));

        cboChatLieu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cboChatLieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 220, -1));
        jPanel7.add(txtTenPhongCach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 259, -1));
        jPanel7.add(txtDiaChiNhapHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 126, 259, -1));
        jPanel7.add(txtGhiChu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 168, 259, -1));

        cboTrangThai1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cboTrangThai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 259, -1));

        btnThemLoaiAo.setText("+");
        btnThemLoaiAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiAoActionPerformed(evt);
            }
        });
        jPanel7.add(btnThemLoaiAo, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 0, 40, -1));

        btnThemChatLieu.setText("+");
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });
        jPanel7.add(btnThemChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 40, 40, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng sản phẩm "));

        pnKhoiPhucSp.setBorder(javax.swing.BorderFactory.createTitledBorder("Khôi phục sản phẩm"));
        pnKhoiPhucSp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKhoiPhucSP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblKhoiPhucSP);

        pnKhoiPhucSp.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 37, 1000, 120));

        btnKhoiPhucSP.setText("Khôi phục");
        btnKhoiPhucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucSPActionPerformed(evt);
            }
        });
        pnKhoiPhucSp.add(btnKhoiPhucSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, -1, -1));

        btnThoatKhoiPhucSp.setText("Thoát");
        btnThoatKhoiPhucSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatKhoiPhucSpActionPerformed(evt);
            }
        });
        pnKhoiPhucSp.add(btnThoatKhoiPhucSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 75, -1));

        tblSanPham1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPham1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham1);

        btnCapNhatSPCT.setText("Cập nhật");
        btnCapNhatSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPCTActionPerformed(evt);
            }
        });

        btnChiTietSP1.setText("Chi tiết sản phẩm");
        btnChiTietSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSP1ActionPerformed(evt);
            }
        });

        btnThemSPCT.setText("Thêm");
        btnThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCTActionPerformed(evt);
            }
        });

        btnXoaSPCT1.setText("Xóa");
        btnXoaSPCT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPCT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhatSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChiTietSP1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChiTietSP1)
                    .addComponent(btnThemSPCT)
                    .addComponent(btnCapNhatSPCT)
                    .addComponent(btnXoaSPCT1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(585, 585, 585)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnKhoiPhucSp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnKhoiPhucSp, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));

        jPanel28.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jLabel34.setText("Thương hiệu :");
        jPanel28.add(jLabel34);

        jLabel35.setText("Xuất xứ :");
        jPanel28.add(jLabel35);

        jLabel36.setText("Loại áo :");
        jPanel28.add(jLabel36);

        jLabel37.setText("Chất liệu :");
        jPanel28.add(jLabel37);

        jPanel29.setLayout(new java.awt.GridLayout(4, 1, 0, 25));

        cboThuongHieuLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboThuongHieuLoc);

        cboXuatXuLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboXuatXuLoc);

        cboLoaiAoLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboLoaiAoLoc);

        cboChatLieuLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboChatLieuLoc);

        btnTimKiemSp3.setText("Tìm kiếm ");
        btnTimKiemSp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSp3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTimKiemSp3))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(63, 63, 63))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiemSp3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        tabs.addTab("Sản phẩm", jPanel1);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sản phẩm "));

        jPanel11.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jLabel14.setText("Mã sản phẩm :");
        jPanel11.add(jLabel14);

        jLabel15.setText("Mã SPCT :");
        jPanel11.add(jLabel15);

        jLabel16.setText("Size :");
        jPanel11.add(jLabel16);

        jLabel17.setText("Màu sắc :");
        jPanel11.add(jLabel17);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaSP2.setEnabled(false);
        jPanel12.add(txtMaSP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 261, 25));
        jPanel12.add(txtMaSPCT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 261, 25));

        cboSize2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel12.add(cboSize2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 220, 25));

        cboMauSac2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel12.add(cboMauSac2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 135, 220, 25));

        jButton5.setText("+");
        jPanel12.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 130, 40, 30));

        jButton6.setText("+");
        jPanel12.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 90, 40, 30));

        jPanel13.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jLabel18.setText("Số lượng :");
        jPanel13.add(jLabel18);

        jLabel19.setText("Giá :");
        jPanel13.add(jLabel19);

        jLabel20.setText("Mô tả :");
        jPanel13.add(jLabel20);

        jLabel21.setText("Trạng thái :");
        jPanel13.add(jLabel21);

        jPanel14.setLayout(new java.awt.GridLayout(4, 1, 0, 20));
        jPanel14.add(txtSoLuong2);
        jPanel14.add(txtGia2);
        jPanel14.add(txtMoTa2);

        cboTrangThai2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(cboTrangThai2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lblTenSP2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng sản phẩm chi tiết "));

        tblSanPhamChiTiet2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPhamChiTiet2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTiet2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamChiTiet2);

        btnCapNhatSPCT2.setText("Cập nhật");
        btnCapNhatSPCT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPCT2ActionPerformed(evt);
            }
        });

        btnXoaSPCT2.setText("Xóa");
        btnXoaSPCT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPCT2ActionPerformed(evt);
            }
        });

        btnThemSPCT2.setText("Thêm");
        btnThemSPCT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCT2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhatSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSPCT2)
                    .addComponent(btnThemSPCT2)
                    .addComponent(btnCapNhatSPCT2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnKhoiPhucSPCT.setBorder(javax.swing.BorderFactory.createTitledBorder("Khôi phục sản phẩm chi tiết"));
        pnKhoiPhucSPCT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKhoiPhucSPCT2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhoiPhucSPCT2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoiPhucSPCT2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhoiPhucSPCT2);

        pnKhoiPhucSPCT.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 39, 1015, 120));

        btnThoatKHSPCT.setText("Thoát");
        btnThoatKHSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatKHSPCTActionPerformed(evt);
            }
        });
        pnKhoiPhucSPCT.add(btnThoatKHSPCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(951, 180, 75, -1));

        btnKhoiPhucSPCT.setText("Khôi phục");
        btnKhoiPhucSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucSPCTActionPerformed(evt);
            }
        });
        pnKhoiPhucSPCT.add(btnKhoiPhucSPCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(848, 180, -1, -1));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnKhoiPhucSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnKhoiPhucSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Ảnh "));

        lblHinhAnh2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnh2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinhAnh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(lblHinhAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thuộc tính màu sách"));

        jLabel22.setText("Màu sắc :");

        cboMauSacLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setText("Size :");

        cboSizeLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTimKiemSPCT.setText("Tìm kiếm");
        btnTimKiemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboMauSacLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSizeLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiemSPCT)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cboMauSacLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cboSizeLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemSPCT))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTenSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tabs.addTab("Sản phẩm chi tiết", jPanel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Sản Phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(519, 519, 519))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboThuongHieu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThuongHieu1ActionPerformed
        // TODO add your handling code here:
        if (cboThuongHieu1.getSelectedItem() != null) {
            initcboXuatXuSP();
        }
    }//GEN-LAST:event_cboThuongHieu1ActionPerformed

    private void cboLoaiAo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiAo1ActionPerformed
        // TODO add your handling code here:
        if (cboLoaiAo1.getSelectedItem() != null) {
            initcboChatLieuSP();
        }
    }//GEN-LAST:event_cboLoaiAo1ActionPerformed

    private void tblSanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPham1MouseClicked
        // TODO add your handling code here:
        row = tblSanPham1.getSelectedRow();
        txtMaSP1.setText(tblSanPham1.getValueAt(row, 0).toString());
        txtTenAoKhoac1.setText(tblSanPham1.getValueAt(row, 1).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dcNgayNhap1.setDate(sdf.parse(tblSanPham1.getValueAt(row, 2).toString()));
            dcNgaySua1.setDate(sdf.parse(tblSanPham1.getValueAt(row, 3).toString()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Object selectedTh = tblSanPham1.getValueAt(row, 4);
        DefaultComboBoxModel<ThuongHieu> cbBoxModel = (DefaultComboBoxModel) cboThuongHieu1.getModel();
        for (int i = 0; i < cbBoxModel.getSize(); i++) {
            ThuongHieu thuongHieu = cbBoxModel.getElementAt(i);
            if (thuongHieu.toString().equals(selectedTh.toString())) {
                cboThuongHieu1.setSelectedItem(thuongHieu);
                break;
            }
        }
        Object selectedXx = tblSanPham1.getValueAt(row, 5);
        DefaultComboBoxModel<XuatXu> cbBoxModel1 = (DefaultComboBoxModel) cboXuatXu1.getModel();
        for (int i = 0; i < cbBoxModel1.getSize(); i++) {
            XuatXu xuatXu = cbBoxModel1.getElementAt(i);
            if (xuatXu.toString().equals(selectedXx.toString())) {
                cboXuatXu1.setSelectedItem(xuatXu);
                break;
            }
        }
        Object selectedLa = tblSanPham1.getValueAt(row, 6);
        DefaultComboBoxModel<LoaiAo> cbBoxModel2 = (DefaultComboBoxModel) cboLoaiAo1.getModel();
        for (int i = 0; i < cbBoxModel2.getSize(); i++) {
            LoaiAo loaiAo = cbBoxModel2.getElementAt(i);
            if (loaiAo.toString().equals(selectedLa.toString())) {
                cboLoaiAo1.setSelectedItem(loaiAo);
                break;
            }
        }
        Object selectedCl = tblSanPham1.getValueAt(row, 7);
        DefaultComboBoxModel<ChatLieu> cbBoxModel3 = (DefaultComboBoxModel) cboChatLieu1.getModel();
        for (int i = 0; i < cbBoxModel3.getSize(); i++) {
            ChatLieu chatLieu = cbBoxModel3.getElementAt(i);
            if (chatLieu.toString().equals(selectedCl.toString())) {
                cboChatLieu1.setSelectedItem(chatLieu);
                break;
            }
        }
        txtTenPhongCach1.setText(tblSanPham1.getValueAt(row, 8).toString());
        txtDiaChiNhapHang1.setText(tblSanPham1.getValueAt(row, 9).toString());
        txtGhiChu1.setText(tblSanPham1.getValueAt(row, 10).toString());

        if (tblSanPham1.getValueAt(row, 11).toString().equals("Đang hoạt động")) {
            cboTrangThai1.setSelectedIndex(0);
        } else if (tblSanPham1.getValueAt(row, 11).toString().equals("Đang nhập hàng")) {
            cboTrangThai1.setSelectedIndex(1);
        } else if (tblSanPham1.getValueAt(row, 11).toString().equals("Đang hết hàng")) {
            cboTrangThai1.setSelectedIndex(2);
        } else {
            cboTrangThai1.setSelectedIndex(3);
        }
    }//GEN-LAST:event_tblSanPham1MouseClicked

    private void btnCapNhatSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPCTActionPerformed
        // TODO add your handling code here:
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng sản phẩm được chọn .");
        } else {
            if (validateSp()) {
                if (akmdDao.update(getModelSP()) > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm áo khoác mùa đông thành công .");
                    fillTable();
                    clearSp();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm áo khoác mùa đông không thành công .");
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatSPCTActionPerformed

    private void btnChiTietSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSP1ActionPerformed
        // TODO add your handling code here:
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng sản phẩm được chọn .");
        } else {
            fillTableChiTiet(tblSanPham1.getValueAt(row, 0).toString());
            txtMaSP2.setText(tblSanPham1.getValueAt(row, 0).toString());
            lblTenSP2.setText(tblSanPham1.getValueAt(row, 1).toString());
            tabs.setSelectedIndex(1);

        }
    }//GEN-LAST:event_btnChiTietSP1ActionPerformed

    private void btnThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCTActionPerformed
        // TODO add your handling code here:
        String maSp = txtMaSP1.getText().trim();
        if (akmdDao.selectByIdNHD(maSp) != null) {
            int chon = JOptionPane.showConfirmDialog(this, "Mã sản phẩm đã ngừng hoạt động bạn có muốn khôi phục lại nó .", "Khôi phục sản phẩm đã tồn tại .", JOptionPane.OK_CANCEL_OPTION);

            if (chon == 0) {
                fillTableKP(maSp);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1050; i++) {
                            for (int j = 0; j < 240; j++) {
                                pnKhoiPhucSp.setSize(i, j);
                            }
                        }
                    }
                }).start();
            }
            return;
        }
        if (akmdDao.selectById(maSp) != null) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại trong bảng áo khoác mùa đông .");
            return;
        }
        if (validateSp()) {
            if (akmdDao.insert(getModelSP()) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm áo khoác mùa đông mới thành công .");
                fillTable();
                clearSp();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm áo khoác mùa đông không thành công .");
            }
        }
    }//GEN-LAST:event_btnThemSPCTActionPerformed

    private void btnXoaSPCT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPCT1ActionPerformed
        // TODO add your handling code here:
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng sản phẩm được chọn .");
        } else {
            int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này .", "Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
            System.out.println(luaChon);
            if (luaChon == 0) {
                AoKhoacMuaDong akmd = getModelSP();
                akmd.setTrangThai("Ngừng hoạt động");
                if (akmdDao.update(akmd) > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm áo khoác mùa đông thành công .");
                    fillTable();
                    clearSp();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm áo khoác mùa đông không thành công .");
                }
            }
        }
    }//GEN-LAST:event_btnXoaSPCT1ActionPerformed

    private void tblSanPhamChiTiet2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTiet2MouseClicked
        // TODO add your handling code here:
        row1 = tblSanPhamChiTiet2.getSelectedRow();
        txtMaSPCT2.setText(tblSanPhamChiTiet2.getValueAt(row1, 0).toString());
        Object selectedSz = tblSanPhamChiTiet2.getValueAt(row1, 1);
        DefaultComboBoxModel<Size> cbBoxModel = (DefaultComboBoxModel) cboSize2.getModel();
        for (int i = 0; i < cbBoxModel.getSize(); i++) {
            Size size = cbBoxModel.getElementAt(i);
            if (size.toString().equals(selectedSz.toString())) {
                cboSize2.setSelectedItem(size);
                break;
            }
        }
        Object selectedMs = tblSanPhamChiTiet2.getValueAt(row1, 2);
        DefaultComboBoxModel<MauSac> cbBoxModel1 = (DefaultComboBoxModel) cboMauSac2.getModel();
        for (int i = 0; i < cbBoxModel1.getSize(); i++) {
            MauSac mauSac = cbBoxModel1.getElementAt(i);
            if (mauSac.toString().equals(selectedMs.toString())) {
                cboMauSac2.setSelectedItem(mauSac);
                break;
            }
        }

        txtSoLuong2.setText(tblSanPhamChiTiet2.getValueAt(row1, 3).toString());
        txtGia2.setText(tblSanPhamChiTiet2.getValueAt(row1, 4).toString());
        
        try {
           
            icon = XImage.read(tblSanPhamChiTiet2.getValueAt(row1, 5).toString());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        int height = lblHinhAnh2.getHeight();
        int width = lblHinhAnh2.getWidth();
        File file = new File("D:\\logos\\" + tblSanPhamChiTiet2.getValueAt(row1, 5).toString());
        System.out.println(tblSanPhamChiTiet2.getValueAt(row1, 5).toString());
        Image i;
        try {
//            ImageIcon icon = null;
            i = ImageIO.read(file).getScaledInstance(width, height, 0);
            icon.setImage(i);
            lblHinhAnh2.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        lblHinhAnh2.setToolTipText(tblSanPhamChiTiet2.getValueAt(row1, 5).toString());
        txtMoTa2.setText(tblSanPhamChiTiet2.getValueAt(row1, 6).toString());
        if (tblSanPhamChiTiet2.getValueAt(row1, 7).toString().equals("Đang hoạt động")) {
            cboTrangThai2.setSelectedIndex(0);
        } else {
            cboTrangThai2.setSelectedIndex(1);
        }

    }//GEN-LAST:event_tblSanPhamChiTiet2MouseClicked

    private void btnCapNhatSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPCT2ActionPerformed
        // TODO add your handling code here:
        if (row1 < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong báng sản phẩm được chọn .");
        } else {
            if (validateSpct()) {
                if (ctspdao.update(getModelSPCT()) > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật chi tiết sản phẩm thành công .");
                    fillTableChiTiet(txtMaSP2.getText());
                    clearSpCt();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật chi tiết sản phẩm không thành công .");
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatSPCT2ActionPerformed

    private void btnXoaSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPCT2ActionPerformed
        // TODO add your handling code here:
        if (row1 < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong báng sản phẩm được chọn .");
        } else {
            ChiTietSanPham ctsp = getModelSPCT();
            ctsp.setTrangThai(false);
            if (ctspdao.update(ctsp) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa chi tiết sản phẩm thành công .");
                fillTableChiTiet(txtMaSP2.getText());
                clearSpCt();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa chi tiết sản phẩm không thành công .");
            }
        }
    }//GEN-LAST:event_btnXoaSPCT2ActionPerformed

    private void btnThemSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCT2ActionPerformed
        // TODO add your handling code here:
        String maSpct = txtMaSPCT2.getText().trim();
        if (ctspdao.selectById_NHD(maSpct) != null) {
            int chon = JOptionPane.showConfirmDialog(this, "Mã sản phẩm chi tiết đã ngừng hoạt động bạn có muốn khôi phục lại nó .", "Khôi phục sản phẩm chi tiết đã tồn tại .", JOptionPane.OK_CANCEL_OPTION);
            if (chon == 0) {
                fillTableKhoiPhucChiTiet(maSpct);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1055; i++) {
                            for (int j = 0; j < 230; j++) {
                                pnKhoiPhucSPCT.setSize(i, j);
                            }
                        }
                    }
                }).start();
            }
            return;
        }
        if (ctspdao.selectById(maSpct) != null) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm chi tiết đã tồn tại trong bảng sản phẩm chi tiết .");
            return;
        }
        if (validateSpct()) {
            if (ctspdao.insert(getModelSPCT()) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm chi tiết thành công .");
                fillTableChiTiet(txtMaSP2.getText());
                clearSpCt();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm chi tiết không thành công .");

            }
        }
    }//GEN-LAST:event_btnThemSPCT2ActionPerformed

    private void lblHinhAnh2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnh2MouseClicked
     try {
         // TODO add your handling code here:
         chonAnh();
     } catch (IOException ex) {
         java.util.logging.Logger.getLogger(FormSanPhamMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_lblHinhAnh2MouseClicked

    private void btnTimKiemSp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSp3ActionPerformed
        // TODO add your handling code here:
        String tenThuongHieu = "";
        String tenXuatXu = "";
        String tenLoaiAo = "";
        String tenChatLieu = "";
        Object selectedTh = cboThuongHieuLoc.getSelectedItem();
        if (selectedTh instanceof ThuongHieu) {
            tenThuongHieu = (String) cboThuongHieuLoc.getSelectedItem().toString();
        }
        Object selectedXx = cboXuatXuLoc.getSelectedItem();
        if (selectedXx instanceof XuatXu) {
            tenXuatXu = (String) cboXuatXuLoc.getSelectedItem().toString();
        }
        Object selectedLa = cboLoaiAoLoc.getSelectedItem();
        if (selectedLa instanceof LoaiAo) {
            tenLoaiAo = (String) cboLoaiAoLoc.getSelectedItem().toString();
        }
        Object selectedCl = cboChatLieuLoc.getSelectedItem();
        if (selectedCl instanceof ChatLieu) {
            tenChatLieu = (String) cboChatLieuLoc.getSelectedItem().toString();
        }
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPham1.getModel();
        tableModel.setRowCount(0);
        List<AoKhoacMuaDong> list = akmdDao.getTheoThuocTinh(tenThuongHieu, tenXuatXu, tenLoaiAo, tenChatLieu);
        for (AoKhoacMuaDong akmd : list) {
            ThuongHieuChiTiet thct = thctdao.selectById(akmd.getMaThuongHieuChiTiet());
            XuatXu xx = xxdao.selectById(thct.getMaXuatXu());
            ThuongHieu th = thdao.selectById(thct.getMaThuongHieu());
            ChatLieu_LoaiAo clla = clladao.selectById(akmd.getMaChatLieuLoaiAo());
            LoaiAo la = ladao.selectById(clla.getMaLoaiAo());
            ChatLieu cl = cldao.selectById(clla.getMaChatLieu());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            tableModel.addRow(new Object[]{
                akmd.getMaAoKhoac(), akmd, akmd.getNgayNhap(), akmd.getNgaySua(), th, xx, la, cl, akmd.getTenPhongCach(),
                akmd.getDiaChiNhapHang(), akmd.getGhiChu(), akmd.getTrangThai()
            });
        }
        tableModel.fireTableDataChanged();
    }//GEN-LAST:event_btnTimKiemSp3ActionPerformed

    private void btnTimKiemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPCTActionPerformed
        // TODO add your handling code here:
        String tenMauSac = "";
        String tenSize = "";
        Object selectedMs = cboMauSacLoc.getSelectedItem();
        if (selectedMs instanceof MauSac) {
            tenMauSac = (String) cboMauSacLoc.getSelectedItem().toString();
        }
        Object selectedSz = cboSizeLoc.getSelectedItem();
        if (selectedSz instanceof Size) {
            tenSize = (String) cboSizeLoc.getSelectedItem().toString();
        }
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPhamChiTiet2.getModel();
        tableModel.setRowCount(0);
        List<ChiTietSanPham> list = ctspdao.getTheoThuocTinh(tenMauSac, tenSize);
        for (ChiTietSanPham ctsp : list) {
            Size sz = sizeDAO.selectById(ctsp.getMaSize());
            MauSac ms = msdao.selectById(ctsp.getMaMauSac());
            tableModel.addRow(new Object[]{
                ctsp.getMaSPCT(), sz, ms, ctsp.getSoLuongAK(), ctsp.getGiaAK(),
                ctsp.getTenAnh(), ctsp.getMoTa(), ctsp.isTrangThai() ? "Đang hoạt động" : "Ngừng hoạt động"
            });
        }
        tblSanPhamChiTiet2.setModel(tableModel);

    }//GEN-LAST:event_btnTimKiemSPCTActionPerformed

    private void btnThemThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuongHieuActionPerformed
        // TODO add your handling code here:
        DialogThuongHieuChiTiet dthct = new DialogThuongHieuChiTiet(null, true);
        dthct.setVisible(true);
        initcboThuongHieuSP();
        initcboXuatXuSP();
    }//GEN-LAST:event_btnThemThuongHieuActionPerformed

    private void btnThemXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXuatXuActionPerformed
        // TODO add your handling code here:
        DialogThuongHieuChiTiet dthct = new DialogThuongHieuChiTiet(null, true);
        dthct.setVisible(true);
        initcboThuongHieuSP();
        initcboXuatXuSP();
    }//GEN-LAST:event_btnThemXuatXuActionPerformed

    private void btnThemLoaiAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiAoActionPerformed
        // TODO add your handling code here:
        DialogChatLieu_LoaiAo dclla = new DialogChatLieu_LoaiAo();
        dclla.setVisible(true);
        initcboChatLieuSP();
        initcboLoaiAoSP();
    }//GEN-LAST:event_btnThemLoaiAoActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemChatLieuActionPerformed

    private void btnThoatKhoiPhucSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatKhoiPhucSpActionPerformed
        // TODO add your handling code here:
        pnKhoiPhucSp.setSize(0, 0);
    }//GEN-LAST:event_btnThoatKhoiPhucSpActionPerformed

    private void btnKhoiPhucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucSPActionPerformed
        // TODO add your handling code here:
        String maSp = tblKhoiPhucSP.getValueAt(0, 0).toString().trim();
        if (akmdDao.updateTT(maSp) > 0) {
            JOptionPane.showMessageDialog(this, "Khôi phục thông tin sản phẩm thành công .");
            fillTable();
            fillTableKP(maSp);
        } else {
            JOptionPane.showMessageDialog(this, "Khôi phục thông tin sản phẩm không thành công .");

        }
    }//GEN-LAST:event_btnKhoiPhucSPActionPerformed

    private void tblKhoiPhucSPCT2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoiPhucSPCT2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKhoiPhucSPCT2MouseClicked

    private void btnThoatKHSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatKHSPCTActionPerformed
        // TODO add your handling code here:
        pnKhoiPhucSPCT.setSize(0, 0);
    }//GEN-LAST:event_btnThoatKHSPCTActionPerformed

    private void btnKhoiPhucSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucSPCTActionPerformed
        // TODO add your handling code here:
        String maSpct = tblKhoiPhucSPCT2.getValueAt(0, 0).toString().trim();
        if (ctspdao.updateTT(maSpct) > 0) {
            JOptionPane.showMessageDialog(this, "Khôi phục thông tin sản phẩm chi tiết thành công .");
            fillTableKhoiPhucChiTiet(txtMaSP2.getText().trim());
            fillTableKhoiPhucChiTiet(txtMaSP2.getText().trim());
        } else {
            JOptionPane.showMessageDialog(this, "Khôi phục thông tin sản phẩm chi tiết không thành công .");
        }
    }//GEN-LAST:event_btnKhoiPhucSPCTActionPerformed

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
            java.util.logging.Logger.getLogger(FormSanPhamMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamMoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSanPhamMoi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatSPCT;
    private javax.swing.JButton btnCapNhatSPCT2;
    private javax.swing.JButton btnChiTietSP1;
    private javax.swing.JButton btnKhoiPhucSP;
    private javax.swing.JButton btnKhoiPhucSPCT;
    private javax.swing.JButton btnThemChatLieu;
    private javax.swing.JButton btnThemLoaiAo;
    private javax.swing.JButton btnThemSPCT;
    private javax.swing.JButton btnThemSPCT2;
    private javax.swing.JButton btnThemThuongHieu;
    private javax.swing.JButton btnThemXuatXu;
    private javax.swing.JButton btnThoatKHSPCT;
    private javax.swing.JButton btnThoatKhoiPhucSp;
    private javax.swing.JButton btnTimKiemSPCT;
    private javax.swing.JButton btnTimKiemSp3;
    private javax.swing.JButton btnXoaSPCT1;
    private javax.swing.JButton btnXoaSPCT2;
    private javax.swing.JComboBox<String> cboChatLieu1;
    private javax.swing.JComboBox<String> cboChatLieuLoc;
    private javax.swing.JComboBox<String> cboLoaiAo1;
    private javax.swing.JComboBox<String> cboLoaiAoLoc;
    private javax.swing.JComboBox<String> cboMauSac2;
    private javax.swing.JComboBox<String> cboMauSacLoc;
    private javax.swing.JComboBox<String> cboSize2;
    private javax.swing.JComboBox<String> cboSizeLoc;
    private javax.swing.JComboBox<String> cboThuongHieu1;
    private javax.swing.JComboBox<String> cboThuongHieuLoc;
    private javax.swing.JComboBox<String> cboTrangThai1;
    private javax.swing.JComboBox<String> cboTrangThai2;
    private javax.swing.JComboBox<String> cboXuatXu1;
    private javax.swing.JComboBox<String> cboXuatXuLoc;
    private com.toedter.calendar.JDateChooser dcNgayNhap1;
    private com.toedter.calendar.JDateChooser dcNgaySua1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblHinhAnh2;
    private javax.swing.JLabel lblTenSP2;
    private javax.swing.JPanel pnKhoiPhucSPCT;
    private javax.swing.JPanel pnKhoiPhucSp;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblKhoiPhucSP;
    private javax.swing.JTable tblKhoiPhucSPCT2;
    private javax.swing.JTable tblSanPham1;
    private javax.swing.JTable tblSanPhamChiTiet2;
    private javax.swing.JTextField txtDiaChiNhapHang1;
    private javax.swing.JTextField txtGhiChu1;
    private javax.swing.JTextField txtGia2;
    private javax.swing.JTextField txtMaSP1;
    private javax.swing.JTextField txtMaSP2;
    private javax.swing.JTextField txtMaSPCT2;
    private javax.swing.JTextField txtMoTa2;
    private javax.swing.JTextField txtSoLuong2;
    private javax.swing.JTextField txtTenAoKhoac1;
    private javax.swing.JTextField txtTenPhongCach1;
    // End of variables declaration//GEN-END:variables
}
