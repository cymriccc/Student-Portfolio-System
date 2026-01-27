package gui;

import java.awt.*;
import javax.swing.*;

public class MainContent {
    private DashboardPanel dashboard;

    public MainContent(myFrame frameObject, String studentName, String courseYear, String username) {
        JPanel container = frameObject.getContainer();

        dashboard = new DashboardPanel(studentName, courseYear);

        container.add(dashboard, "DASHBOARD");
        container.add(new PortfolioPanel(), "PORTFOLIO"); 
        container.add(new SettingsPanel(), "SETTINGS");
        container.add(new ProfilePanel(frameObject, username, dashboard), "PROFILE");

        addWindowControls(frameObject);
    }

    private void addWindowControls(myFrame frameObject) {
        JFrame actualFrame = frameObject.getFrame();
        JLayeredPane lp = actualFrame.getLayeredPane();

        Color originalColor = new Color(0x839788);
        Color hoverColor = new Color(0x94a899);
        Color closeHoverColor = new Color(0xc94c4c);

        // Add Minimize and Close Buttons

        // -- CLOSE BUTTON --
        JButton closeBtn = new JButton("X");
        closeBtn.setBounds(1310, 10, 45, 30);
        closeBtn.setBackground(originalColor);
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusable(false);
        closeBtn.addActionListener(e -> System.exit(0));
        closeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                closeBtn.setBackground(closeHoverColor);
                closeBtn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                closeBtn.setBackground(originalColor);
                closeBtn.setForeground(Color.BLACK);
            }
        });

        // -- MINIMIZE BUTTON --
        JButton minBtn = new JButton("-");
        minBtn.setBounds(1260, 10, 45, 30);
        minBtn.setBackground(originalColor);
        minBtn.setBorderPainted(false);
        minBtn.setFocusable(false);
        minBtn.addActionListener(e -> actualFrame.setState(Frame.ICONIFIED));
        minBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { 
                minBtn.setBackground(hoverColor);
                minBtn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                minBtn.setBackground(originalColor);
                minBtn.setForeground(Color.BLACK);
            }
        });

        // Add buttons to layered pane
        lp.add(closeBtn, JLayeredPane.PALETTE_LAYER);
        lp.add(minBtn, JLayeredPane.PALETTE_LAYER);
    }
}