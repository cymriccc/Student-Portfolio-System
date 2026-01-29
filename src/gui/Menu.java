package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu {
    public Menu(myFrame frameObject) {
        JPanel menuPanel = frameObject.getMenu();

        // --- LOGO IMAGE ---
        try {
            ImageIcon logoIcon = new ImageIcon("logo.png");
            int logoSize = 180;
            Image image = logoIcon.getImage().getScaledInstance(logoSize, logoSize, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(image);
            JLabel logoLabel = new JLabel(logoIcon);
            logoLabel.setBounds(85, 10, logoSize, logoSize); // Centered in 350 width
            menuPanel.add(logoLabel);
        } catch (Exception e) {
            System.out.println("Logo file not found. Check your project folder.");
        }

        // --- TITLE LABEL ---
        JLabel titleLabel = new JLabel("Student Portfolio");
        titleLabel.setBounds(0, 190, 350, 40); // Adjusted Y to sit below logo
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0xf5e4d7));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(titleLabel);

        // --- NAVIGATION BUTTONS ---
        menuPanel.add(createMenuButton("Dashboard", 260, frameObject, "DASHBOARD"));
        menuPanel.add(createMenuButton("My Portfolio", 320, frameObject, "PORTFOLIO"));
        menuPanel.add(createMenuButton("Settings", 380, frameObject, "SETTINGS"));

        // BOTTOM PROFILE SECTION 
        JPanel bottomSection = new JPanel();
        bottomSection.setBounds(0, 528, 350, 200); 
        bottomSection.setBackground(new Color(0x73877b)); 
        bottomSection.setLayout(null);

        // Tiny Profile Preview in Sidebar
        frameObject.sidebarProfileImg.setBounds(30, 50, 60, 60);
        frameObject.sidebarProfileImg.setBorder(BorderFactory.createLineBorder(new Color(0xf5e4d7), 1));
        bottomSection.add(frameObject.sidebarProfileImg);

        // My Profile Button 
        JButton profileBtn = new JButton("My Profile");
        profileBtn.setBounds(100, 50, 250, 60); // Adjusted width to fit section
        profileBtn.setBackground(new Color(0x73877b)); 
        profileBtn.setForeground(new Color(0xf5e4d7));
        profileBtn.setFont(new Font("Helvetica", Font.BOLD, 18));
        profileBtn.setHorizontalAlignment(SwingConstants.LEFT);
        profileBtn.setBorderPainted(false);
        profileBtn.setFocusPainted(false);
        profileBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Navigation Logic
        profileBtn.addActionListener(e -> frameObject.getCardLayout().show(frameObject.getContainer(), "PROFILE"));
        
        // Hover Logic for Profile Button
        profileBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { profileBtn.setBackground(new Color(0x839788)); }
            public void mouseExited(MouseEvent e) { profileBtn.setBackground(new Color(0x73877b)); }
        });
        bottomSection.add(profileBtn);

        // Logout Button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(50, 140, 250, 40);
        logoutBtn.setBackground(new Color(0xc94c4c)); 
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Helvetica", Font.BOLD, 14));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
        logoutBtn.addActionListener(e -> {
            // 1. Get the current window to use as the parent for the dialog
           Window currentWindow = SwingUtilities.getWindowAncestor(logoutBtn);
    
           // 2. Call your custom Yes/No dialog
           // Casting currentWindow to Frame since showConfirm expects a Frame
           boolean confirm = gui.CustomDialog.showConfirm((Frame)currentWindow, "Are you sure you want to logout?");
    
           if (confirm) {
               // 3. Open the Login window
               new gui.LoginForm().setVisible(true);

              // 4. Close the current Dashboard window
              if (currentWindow != null) {
                  currentWindow.dispose();
               }
           }
        }); 

        // Hover Logic for Logout Button
        logoutBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { logoutBtn.setBackground(new Color(0xd95c5c)); } 
            public void mouseExited(MouseEvent e) { logoutBtn.setBackground(new Color(0xc94c4c)); }
        });
        
        bottomSection.add(logoutBtn);
        menuPanel.add(bottomSection);
    }

    // Helper method to create styled menu buttons
    private JButton createMenuButton(String text, int yPos, myFrame frameObject, String cardName) {
        JButton btn = new JButton(text);
        btn.setBounds(0, yPos, 350, 50);
        btn.setBackground(new Color(0x73877b));
        btn.setForeground(new Color(0xf5e4d7));
        btn.setFont(new Font("Helvetica", Font.PLAIN, 18));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMargin(new Insets(0, 50, 0, 0));

        // CardLayout Trigger
        btn.addActionListener(e -> {
            frameObject.getCardLayout().show(frameObject.getContainer(), cardName);
        });
        
        // Hover Effects
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0x839788));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0x73877b));
            }
        });
        return btn;
    }
}