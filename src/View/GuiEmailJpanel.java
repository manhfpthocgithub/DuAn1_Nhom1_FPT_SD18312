/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
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

public class GuiEmailJpanel extends javax.swing.JPanel {

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
    public GuiEmailJpanel() {
        initComponents();
        
        fillTableEmail();
        fillTableEmailTichDiem();
        fillPhieuGiamGia();
    }

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
                row[2] = pgg.GiaTriPGG();
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
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmailGui, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhauGui, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaKhachHangEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhieuGiamGiaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTieuDeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane8))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1211, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(57, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatKhauGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauGuiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauGuiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearFormEmail();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guiEmail();
        insertEmail();
        clearFormEmail();
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

    private void tblTichDiem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTichDiem1MouseClicked
        int chon = tblTichDiem1.getSelectedRow();
        txtMaKhachHangEmail.setText(tblTichDiem1.getValueAt(chon, 0).toString());
        txtEmailNhan.setText((String) tblTichDiem1.getValueAt(chon, 2));
    }//GEN-LAST:event_tblTichDiem1MouseClicked

    private void tblTichDiem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTichDiem1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTichDiem1MousePressed

    private void tblPhieuGiamGiaKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiamGiaKHMouseClicked
        int chon = tblPhieuGiamGiaKH.getSelectedRow();
        txtMaPhieuGiamGiaKH.setText(tblPhieuGiamGiaKH.getValueAt(chon, 0).toString());
    }//GEN-LAST:event_tblPhieuGiamGiaKHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable tblEmail;
    private javax.swing.JTable tblPhieuGiamGiaKH;
    private javax.swing.JTable tblTichDiem1;
    private javax.swing.JTextField txtEmailGui;
    private javax.swing.JTextField txtEmailNhan;
    private javax.swing.JTextField txtMaKhachHangEmail;
    private javax.swing.JTextField txtMaPhieuGiamGiaKH;
    private javax.swing.JPasswordField txtMatKhauGui;
    private javax.swing.JTextArea txtNoiDungEmail;
    private javax.swing.JTextField txtTieuDeEmail;
    // End of variables declaration//GEN-END:variables
}
