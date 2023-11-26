package View;

import View.PhieuGiaoHang_JPanel;
import DAO.PhieuGiaoHangDAO;
import DAO.PhieuGiaoHang_ChiTietDAO;
import Entity.PhieuGiaoHang;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;

public class PhieuGiaoHang_DaXoa_JPanel extends javax.swing.JPanel {

    PhieuGiaoHangDAO PGH_DAO = new PhieuGiaoHangDAO();
    PhieuGiaoHang_ChiTietDAO PGH_ChiTietDAO = new PhieuGiaoHang_ChiTietDAO();
  
    int row = 0;
    PhieuGiaoHang model = new PhieuGiaoHang();

    public PhieuGiaoHang_DaXoa_JPanel() {
        initComponents();
        fillTablePGHOff();
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

    void PGH_restore() {
        int selectedRow = tblPGHNgungHoatDong.getSelectedRow();

        if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
            // Lấy mã phiếu giao hàng từ cột đầu tiên trong dòng được chọn
            int maPhieuGiaoHang = (int) tblPGHNgungHoatDong.getValueAt(selectedRow, 0);

            try {
                // Thực hiện cập nhật trong cơ sở dữ liệu để khôi phục phiếu giao hàng
                PGH_DAO.restorePhieuGiaoHang(String.valueOf(maPhieuGiaoHang));
                // Sau khi cập nhật thành công, cập nhật lại bảng và hiển thị thông báo
                fillTablePGHOff();
//                fillTable();
                MsgBox.alert(btnKhoiPhucPGH, "Khôi phục thành công!");
            } catch (Exception ex) {
                System.out.println(ex);
                MsgBox.alert(btnKhoiPhucPGH, "Khôi phục thất bại!");
            }
        } else {
            MsgBox.alert(btnKhoiPhucPGH, "Vui lòng chọn một phiếu để khôi phục!");
        }
    }
   

   public void fillTablePGHOff() {
        DefaultTableModel model = (DefaultTableModel) tblPGHNgungHoatDong.getModel();
        model.setRowCount(0);
        try {
            List<PhieuGiaoHang> list = PGH_DAO.selectPGH_Off();
            List<String> listTenDonViVanChuyenPGH_Off = PGH_DAO.selectTenDonViVanChuyenPGH_Off(); // Lấy danh sách tên đơn vị vận chuyển
            int index = 0; // Index để lấy tên đơn vị từ danh sách
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            for (PhieuGiaoHang phieugiaohang_OFF : list) {
                String ngayGiaoHangFormatted = sdf.format(phieugiaohang_OFF.getNgayGiaoHang());
                String ngayNhanHangFormatted = sdf.format(phieugiaohang_OFF.getNgayNhanHang());
                Object[] row = {
                    phieugiaohang_OFF.getMaPhieuGiaoHang(),
                    phieugiaohang_OFF.getMaHoaDon(),
                    mapMaDonViToTenDonVi(phieugiaohang_OFF.getMaDonViVanChuyen()),
                    phieugiaohang_OFF.getTenNguoiNhan(),
                    phieugiaohang_OFF.getSoDienThoaiNguoiNhan(),
                    phieugiaohang_OFF.getDiaChiNhanHang(),
                    phieugiaohang_OFF.isHinhThucThanhToan() ? "Khi nhận hàng" : "Tại quầy",
                    ngayGiaoHangFormatted,
                    ngayNhanHangFormatted,
                    mapTrangThaiPGH(phieugiaohang_OFF.getTrangThaiGiaoHang()),
                    String.format("%,.0f VNĐ", phieugiaohang_OFF.getTongGiaTriPGH()),
                    phieugiaohang_OFF.getGhiChuPGH()
                };
                model.addRow(row);
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg2 = new javax.swing.JPanel();
        bgDanhSachPGH1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPGHNgungHoatDong = new javax.swing.JTable();
        btnKhoiPhucPGH = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        bgDanhSachPGH1.setBackground(new java.awt.Color(255, 255, 255));
        bgDanhSachPGH1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu giao hàng đã ngừng hoạt động", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        bgDanhSachPGH1.setForeground(new java.awt.Color(255, 255, 255));

        tblPGHNgungHoatDong.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPGHNgungHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPGHNgungHoatDongMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblPGHNgungHoatDong);

        btnKhoiPhucPGH.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnKhoiPhucPGH.setText("Khôi Phục");
        btnKhoiPhucPGH.setBorder(new javax.swing.border.MatteBorder(null));
        btnKhoiPhucPGH.setBorderPainted(false);
        btnKhoiPhucPGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucPGHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgDanhSachPGH1Layout = new javax.swing.GroupLayout(bgDanhSachPGH1);
        bgDanhSachPGH1.setLayout(bgDanhSachPGH1Layout);
        bgDanhSachPGH1Layout.setHorizontalGroup(
            bgDanhSachPGH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
            .addGroup(bgDanhSachPGH1Layout.createSequentialGroup()
                .addGap(446, 446, 446)
                .addComponent(btnKhoiPhucPGH, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgDanhSachPGH1Layout.setVerticalGroup(
            bgDanhSachPGH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgDanhSachPGH1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhoiPhucPGH, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bg2Layout = new javax.swing.GroupLayout(bg2);
        bg2.setLayout(bg2Layout);
        bg2Layout.setHorizontalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bgDanhSachPGH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bg2Layout.setVerticalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bgDanhSachPGH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bg2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tblPGHNgungHoatDongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPGHNgungHoatDongMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPGHNgungHoatDongMousePressed

    private void btnKhoiPhucPGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucPGHActionPerformed
        // TODO add your handling code here:
        PGH_restore();
    }//GEN-LAST:event_btnKhoiPhucPGHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg2;
    private javax.swing.JPanel bgDanhSachPGH1;
    private javax.swing.JButton btnKhoiPhucPGH;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblPGHNgungHoatDong;
    // End of variables declaration//GEN-END:variables
}
