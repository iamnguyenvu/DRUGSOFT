package nguyenvu.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePanel extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessagePanel(Frame owner, String title, String message) {
        super(owner, title, true);
        
        setLayout(new BorderLayout());
        setSize(300, 150);
        setLocationRelativeTo(owner);
        
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setPreferredSize(new Dimension(300, 60));
        
        JButton btnOK = new JButton("OK");
        btnOK.setFocusPainted(false);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        add(messageLabel, BorderLayout.CENTER);
        add(btnOK, BorderLayout.SOUTH);
        
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
