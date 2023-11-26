package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
//import menu.MenuAnimation;

public class Menu extends JComponent {

    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }

    private MenuEvent event;
    private MigLayout layout;
    private String[][] menuItems = new String[][]{
        {"Khách hàng "},
        {"Nhân viên", "Inbox", "Read", "Compost"},
        {"Áo khoác mùa đông","Chi tiết áo khoác"},
        {"Hoá đơn", "Apex", "Flot", "Peity", "Sparkline"},
        {"Phiếu giảm giá"},
        {"Đợt giảm giá", "Accordion", "Alerts", "Badges", "Breadcrumbs", "Buttons", "Button group"},
        {"Phiếu giao hàng", "Cropper", "Owl Carousel", "Sweet Alert"},
        {"Phiếu đổi", "Basic Elements", "Advanced Elements", "SEditors", "Wizard"},
        {"Thống kê", "Basic Tables", "Data Table"},
       };

    public Menu() {
        init();
    }

    private void init() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);
        //  Init MenuItem
        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);
        }

    }

    private Icon getIcon(int index) {
        URL url = getClass().getResource("/menu/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    private void addMenu(String menuName, int index) {
        int length = menuItems[index].length;
        MenuItem item = new MenuItem(menuName, index, length > 1);
        Icon icon = getIcon(index);
        if (icon != null) {
            item.setIcon(icon);
        }
        //Chỉnh cỡ chữ menu cha
        Font originalFont = item.getFont();
        Font boldFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() + 2);
        item.setFont(boldFont);
        //Chỉnh màu chữ menu con
        item.setForeground(Color.decode("#C6E2FF"));  // Đặt màu chữ là màu trắng
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (length > 1) {
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        addSubMenu(item, index, length, getComponentZOrder(item));
                    } else {
                        //  Hide menu
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    if (event != null) {
                        event.selected(index, 0);
                    }
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addSubMenu(MenuItem item, int index, int length, int indexZorder) {
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setBackground(new Color(18, 99, 63));

        for (int i = 1; i < length; i++) {
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (event != null) {
                        event.selected(index, subItem.getIndex());
                    }
                }
            });
            // Chỉnh cỡ chữ ở đây
            Font originalFont = subItem.getFont();
            Font biggerFont = new Font(originalFont.getName(), originalFont.getStyle(), originalFont.getSize() + 2);
            subItem.setFont(biggerFont);
            //Chỉnh màu chữ menu con
            subItem.setForeground(Color.decode("#E8E8E8")); // Đặt màu chữ là màu trắng
            subItem.initSubMenu(i, length);
            panel.add(subItem);
        }
        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel, item, layout, true);
    }

    private void hideMenu(MenuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(21, 110, 71));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }

}
