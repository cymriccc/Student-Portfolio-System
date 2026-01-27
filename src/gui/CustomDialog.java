package gui;

import java.awt.*;
import javax.swing.*;

public class CustomDialog extends JDialog {
    public CustomDialog(JFrame parent, String message) {
        super(parent, true); // Modal: blocks interaction with the main window
        setUndecorated(true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        
        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0x839788)); // Sage Green
        panel.setBorder(BorderFactory.createLineBorder(new Color(0xf5e4d7), 2)); // Cream Border
        add(panel);

        // Message Text
        JLabel lblMessage = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        lblMessage.setBounds(20, 40, 360, 60);
        lblMessage.setFont(new Font("Helvetica", Font.PLAIN, 18));
        lblMessage.setForeground(new Color(0xf5e4d7)); // Cream
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblMessage);

        // Styled "OK" Button
        JButton btnOk = new JButton("GOT IT");
        btnOk.setBounds(150, 130, 100, 40);
        btnOk.setBackground(new Color(0x73877b)); // Darker Sage
        btnOk.setForeground(new Color(0xf5e4d7));
        btnOk.setFont(new Font("Helvetica", Font.BOLD, 14));
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnOk.addActionListener(e -> dispose());
        
        // Hover Effect
        btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnOk.setBackground(new Color(0x94a899));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnOk.setBackground(new Color(0x73877b));
            }
        });

        panel.add(btnOk);
    }

    // Static helper method to call it easily
    public static void show(JFrame parent, String message) {
        new CustomDialog(parent, message).setVisible(true);
    }
}