package nguyenvu.menu;

import java.awt.Color;




import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import gui.BanHang;
import gui.BaoCaoDoanhThu;
import gui.BaoCaoSanPham;
import gui.DoiTra;
import gui.HoaDon;
import gui.NhapHang;
import gui.DonDoiTra;
import gui.SanPham;
import gui.SanPhamDoiTra;
import gui.ThongKeDoanhThu_GUI;
import gui.ThongKeSanPham_GUI;
import gui.ThongKeSanPham_GUI;
import gui.TrangChu;
import nguyenvu.model.ModelUser;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.header.SimpleHeaderStyle;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.SimpleMenuStyle;
import raven.drawer.component.menu.data.Item;
import raven.drawer.component.menu.data.MenuItem;
import raven.swing.AvatarIcon;

/**
 *
 * @author nguyenvu
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder {
    private ModelUser user;
    private final ThemesChange themesChange;
    private String avatarPath;
    private String des;
    private String name;
    private Integer role;
    
    private BanHang banHang;
    private DoiTra doiTra;
    private SanPhamDoiTra sanPhamDoiTra;
    private TrangChu trangChu;

    public void setUser(ModelUser user) {
        this.user = user;
        trangChu = new TrangChu(user);
        banHang = new BanHang(user);
        doiTra = new DoiTra(user);
        sanPhamDoiTra = new SanPhamDoiTra();
        SimpleHeaderData headerData = header.getSimpleHeaderData();
        headerData.setTitle(user.getName());
        AvatarIcon icon = new AvatarIcon(getClass().getResource(user.getAvatarPath()), 60, 60, 999);
        icon.setBorder(2);
        headerData.setIcon(icon);
        des = "Chức vụ: ";
        des += (this.user.getRole() == 1) ? "Người quản lý" : "Nhân viên";
        headerData.setDescription(des);
        header.setSimpleHeaderData(headerData);
        rebuildMenu();
    }

    public MyDrawerBuilder() {
        themesChange = new ThemesChange();
    }

    @Override
    public Component getFooter() {
        return themesChange;
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        des = "Chức vụ: ";
        if (this.user == null) {
            AvatarIcon icon = new AvatarIcon(getClass().getResource(""), 60, 60, 999);
            icon.setBorder(2);
            return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("")
                .setDescription("")
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#E1E1E1");
                    }
                });
        }
        
        des += (this.user.getRole() == 1) ? "Người quản lý" : "Nhân viên";
        role = user.getRole();
        
        AvatarIcon icon = new AvatarIcon(getClass().getResource(this.user.getAvatarPath()), 60, 60, 999);
        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle(this.user.getName())
                .setDescription("Chức vụ: " + ((role == 1) ? "Người quản lý" : "Nhân viên") + "ff")
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#E1E1E1");
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        MenuItem items[] = new MenuItem[]{
            new Item("Trang chủ", "dashboard.svg"),
            new Item.Label("QUẢN LÝ"),
            new Item("Bán hàng", "email.svg"),
            new Item("Đổi trả", "chat.svg"),
            new Item("Nhập Hàng", "chat.svg"),
            new Item("Quản Lý Nhập Hàng", "chat.svg"),
            new Item("Sản phẩm", "calendar.svg")
            .subMenu("Sản phẩm bán hàng")
            .subMenu("Sản phẩm đổi trả"),
            new Item("Hóa đơn", "forms.svg"),
            new Item("Khách hàng", "ui.svg"),
            new Item("Nhân viên", "icon.svg"),        
            new Item("Tài khoản", "key.svg"),
            new Item("Thống kê", "chart.svg")
            .subMenu("Thống kê doanh thu")
            .subMenu("Thống kê sản phẩm")
            .subMenu("Thống kê tổng quan"),
            new Item("Báo cáo", "page.svg")
            .subMenu("Báo cáo doanh thu")
            .subMenu("Báo cáo sản phẩm"),
            new Item.Label("KHÁC"),
            new Item("Đăng xuất", "logout.svg")
        };

        SimpleMenuOption simpleMenuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FlatSVGIcon icon = new FlatSVGIcon(path, scale);
                FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
                colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
                icon.setColorFilter(colorFilter);
                return icon;
            }
        };

        simpleMenuOption.setMenuValidation(new MenuValidation() {

            private boolean checkMenu(int[] index, int[] indexHide) {
                if (index.length == indexHide.length) {
                    for (int i = 0; i < index.length; i++) {
                        if (index[i] != indexHide[i]) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }

            @Override
            public boolean menuValidation(int[] index) {
                if (user == null) {
                    return false;
                }
                if (user.getRole() != 1) {
                    // non user admin going to hide
                    boolean act
                            = 
                            checkMenu(index, new int[]{6}) && 
                            checkMenu(index, new int[]{7}) &&
                            checkMenu(index, new int[]{8, 0})&&
                            checkMenu(index, new int[]{8, 2})&&
                            checkMenu(index, new int[]{9, 0});
                    return act;
                }
                return true;
            }
        });

        simpleMenuOption.setMenuStyle(new SimpleMenuStyle() {
            @Override
            public void styleMenuItem(JButton menu, int[] index) {
                menu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, ""
                        + "background:$Drawer.background");
            }

            @Override
            public void styleLabel(JLabel label) {
                label.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:darken(#FAFAFA,15%);"
                        + "[dark]foreground:darken($Label.foreground,30%)");
            }
        });
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] index) {
                if (index.length == 1) {
                    if (index[0] == 0) {
                    	FormManager.showForm(trangChu);
                    }
                    
                    if (index[0] == 1) {
                        FormManager.showForm(banHang);
                    }
                    
                    if (index[0] == 2) {
                        FormManager.showForm(doiTra);
//                    	FormManager.showForm(new NhapHang());
                    }
                    if (index[0] == 3) {
                    	FormManager.showForm(new NhapHang());
                    }
                    
                    if (index[0] == 6) {
                        FormManager.showForm(new HoaDon());
                    }
                    
                    if (index[0] == 7) {
                    	FormManager.showForm(new gui.KhachHang_GUI());
                    }
                    
                    if (index[0] == 8) {
                    	FormManager.showForm(new gui.NhanVien());
                    }
                    
                    if (index[0] == 9) {
                    	FormManager.showForm(new gui.TaiKhoan_GUI());
                    }
                    
                    if (index[0] == 12) {
                        // logout
                        FormManager.logout();
                    }
                } 
                else if (index.length == 2) {
                    if (index[0] == 5) {
                        if (index[1] == 0) {
                            FormManager.showForm(new SanPham());
                        }
                        if (index[1] == 1) {
                            FormManager.showForm(sanPhamDoiTra);
                        }
                    }
                    
                    if (index[0] == 10) {
                        if (index[1] == 1) {
                            FormManager.showForm(new ThongKeSanPham_GUI());
                        }
                        if (index[1] == 0) {
                            FormManager.showForm(new ThongKeDoanhThu_GUI());
                        }
                        if (index[1] == 2) {
                        }
                    }
                    if (index[0] == 11) {
                        if (index[1] == 0) {
                            FormManager.showForm(new BaoCaoDoanhThu());
                        }
                        if (index[1] == 1) {
                            FormManager.showForm(new BaoCaoSanPham());
                        }
                    }
                }
            }
        });

        simpleMenuOption.setMenus(items)
                .setBaseIconPath("nguyenvu/resources/menu")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }

    @Override

    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");
    }

    @Override
    public int getDrawerWidth() {
        return 270;
    }

}