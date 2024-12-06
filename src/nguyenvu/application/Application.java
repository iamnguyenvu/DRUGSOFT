package nguyenvu.application;

import com.formdev.flatlaf.FlatClientProperties;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.util.UIScale;
import gui.TrangChu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
import nguyenvu.components.Background;
import nguyenvu.menu.FormManager;
import nguyenvu.model.ModelUser;
import raven.popup.GlassPanePopup;

/**
 *
 * @author nguyenvu
 */
public class Application extends JFrame {

    private final boolean UNDECORATED = !true;
    private ModelUser user;
    
    private TrangChu trangChu;

    public Application() {
        init();
    }

    private void init() {
    	trangChu = new TrangChu(user);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        if (UNDECORATED) {
            setUndecorated(UNDECORATED);
            setBackground(new Color(0, 0, 0, 0));
        } else {
            getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        }
        setContentPane(new Background(UNDECORATED));
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GlassPanePopup.install(this);
        FormManager.install(this, UNDECORATED);
        FormManager.showForm(trangChu);
        FormManager.logout();
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("nguyenvu.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatIntelliJLaf.setup();
        EventQueue.invokeLater(() -> new Application().setVisible(true));
    }
}
