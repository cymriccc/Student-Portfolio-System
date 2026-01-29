package gui;

import java.awt.*;
import javax.swing.*;
import main.Main;

public class CustomDialog extends JDialog {
    public CustomDialog(JFrame parent, String message, Color themeColor) {
        super(parent, true); // Modal: blocks interaction with the main window
        setUndecorated(true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        
        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Main.BG_COLOR);

        panel.setBorder(BorderFactory.createLineBorder(themeColor, 2));
        add(panel);

        // Message Text
        JLabel lblMessage = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        lblMessage.setBounds(20, 40, 360, 60);
        lblMessage.setFont(new Font("Helvetica", Font.PLAIN, 18));
        lblMessage.setForeground(Main.TEXT_COLOR);
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblMessage);

        // Button color changes to match theme
        JButton btnOk = new JButton("GOT IT");
        btnOk.setBounds(150, 130, 100, 40);
        btnOk.setBackground(themeColor);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Helvetica", Font.BOLD, 14));
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnOk.addActionListener(e -> dispose());
        
        // Hover Effect
        btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnOk.setBackground(themeColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnOk.setBackground(themeColor);
            }
        });

        panel.add(btnOk);
    }

    // Static helper method to call it easily
    public static void show(JFrame parent, String message, boolean isSuccess) {
        Color themeColor = isSuccess ? Main.ACCENT_COLOR : new Color(0xE74C3C); // Red for errors

        new CustomDialog(parent, message, themeColor).setVisible(true);
    }
}