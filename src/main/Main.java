package main;

import Utils.Auth;
import View.DangNhapJDialog;
import View.DoiMatKhauJPanel;
import View.DotGiamGia_JPanel;
import View.GuiEmailJpanel;
import View.HoaDonJpanel;
import View.KhachHangJpanel;
import View.LichSuKhachHangJPanel;
import View.LichSuSuDungJPanel;
import View.NhanVienInVaBanDuocSLPSJPanel;
import View.NhanVienJPanel;
import View.PhieuGiamGiaJPanel;
import View.PhieuGiaoHang_DaXoa_JPanel;
import View.PhieuGiaoHang_JPanel;
import View.QuanLyPhieuDoiJPanel;
import View.SanPhamMoiJPanel;
import View.TaoPhieuDoiJPanel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import menu.MenuEvent;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        menu2.setEvent(new MenuEvent() {
            @Override
            public void selected(int index, int subIndex) {
                // Sản Phẩm
                switch (index) {
                    case 0: {
                        if (subIndex == 1) {
                            showForm(new SanPhamMoiJPanel());
                        }
                        break;
                    }
                    case 1: {
                         if(subIndex == 1){
                             showForm(new HoaDonJpanel());                   
                         }
                        break;
                    }

                    case 2: {
                        //Khách Hàng
                        if (subIndex == 1) {
                            showForm(new KhachHangJpanel());
                        } else if (subIndex == 2) {
                            showForm(new LichSuKhachHangJPanel());
                        } else if (subIndex == 3) {
                            showForm(new GuiEmailJpanel());
                        }
                        break;
                    }
                    case 3: {
                        if (!Auth.isQuanLy()) {
                            JOptionPane.showMessageDialog(null, "Chú không đủ quyền");

                        } else {
                            if (subIndex == 1) {
                                showForm(new NhanVienJPanel());
                            } else if (subIndex == 2) {
                                showForm(new NhanVienInVaBanDuocSLPSJPanel());
                            }
                        }
                        break;
                    }
                    case 4: {
///Phiếu Giảm Giá
                        if (subIndex == 1) {
                            showForm(new PhieuGiamGiaJPanel());
                            return;
                        }
                        // Mẫu phân quyền cho thống kê
                        if (subIndex == 2 && Auth.isQuanLy()) {
                            showForm(new LichSuSuDungJPanel());
                            return;
                        } else {
                            JOptionPane.showMessageDialog(null, "Bạn không có quyền xem .");
                        }
                        break;
                    }
                    case 5: {
///Đợt Giảm Giá
                        if (index == 5) {
                            showForm(new DotGiamGia_JPanel());
                        }
                        break;
                    }
                    case 6: {
////Phiếu Giao Hàng
                        if (subIndex == 1) {
                            showForm(new PhieuGiaoHang_JPanel());
                        } else if (subIndex == 2) {
                            showForm(new PhieuGiaoHang_DaXoa_JPanel());
                        }
                        break;
                    }
                    case 7: {
///Phiếu Đổi Hàng
                        if (subIndex == 1) {
                            showForm(new TaoPhieuDoiJPanel());
                        } else if (subIndex == 2) {
                            showForm(new QuanLyPhieuDoiJPanel());
                        }
                        break;
                    }
                    // Thống kê
                    case 8: {

                        break;
                    }
                    // đổi mật khẩu
                    case 9: {
                        showForm(new DoiMatKhauJPanel());
                        break;
                    }
                    default: {
                        int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không ");
                        if (chon == 0) {
                            dispose();
                            DangNhapJDialog dnjd = new DangNhapJDialog(null, true);
                            dnjd.setVisible(true);
                        } else {
                            showForm(new SanPhamMoiJPanel());
                        }

                        break;
                    }
                }
//                if (index == 0) {
//                    showForm(new SanPhamMoiJPanel());
//                } else {
//                    showForm(new DefaultForm("Form : " + index + "" + subIndex));
//                }

//                //Khách Hàng
//                if (index == 2 && subIndex == 1) {
//                    showForm(new KhachHangJpanel());
//                } else if (index == 2 && subIndex == 2) {
//                    showForm(new LichSuKhachHangJPanel());
//                } else if (index == 2 && subIndex == 3) {
//                    showForm(new GuiEmailJpanel());
//                }
//                ////Phiếu Giao Hàng
//                if (index == 6 && subIndex == 1) {
//                    showForm(new PhieuGiaoHang_JPanel());
//                } else if (index == 6 && subIndex == 2) {
//                    showForm(new PhieuGiaoHang_DaXoa_JPanel());
//                }
//                ///Đợt Giảm Giá
//                if (index == 5) {
//                    showForm(new DotGiamGia_JPanel());
//                }
                ///Nhân Viên
//                if (!Auth.isQuanLy()) {
//                    JOptionPane.showMessageDialog(null, "Chú không đủ quyền");
//
//                } else {
//                    if (index == 3 && subIndex == 1) {
//                        showForm(new NhanVienJPanel());
//                    } else if (index == 3 && subIndex == 2) {
//                        showForm(new NhanVienBanDuocSLSPJPanel());
//                    }
//                }
//                ///Phiếu Đổi Hàng
//                if (index == 7 && subIndex == 1) {
//                    showForm(new TaoPhieuDoiJPanel());
//                } else if (index == 7 && subIndex == 2) {
//                    showForm(new QuanLyPhieuDoiJPanel());
//                }
            }
        });

    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollPaneWin111 = new raven.scroll.win11.ScrollPaneWin11();
        menu2 = new menu.Menu();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(210, 210, 210));

        scrollPaneWin111.setBackground(new java.awt.Color(111, 91, 91));
        scrollPaneWin111.setViewportView(menu2);

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JPanel jPanel1;
    private menu.Menu menu2;
    private raven.scroll.win11.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
