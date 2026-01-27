package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel() {
        setLayout(null);
        setBackground(new Color(0x839788)); 

        JLabel title = new JLabel("Settings (Developers)");
        title.setBounds(40, 40, 400, 50);
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        title.setForeground(new Color(0xf5e4d7)); 
        add(title);

        JLabel badge = new JLabel("STUDENT PORTFOLIO SYSTEM BY SANA-OL", SwingConstants.CENTER);
        badge.setBounds(40, 85, 340, 25);
        badge.setOpaque(true); // to show background color
        badge.setBackground(new Color(0x73877b));
        badge.setForeground(new Color(0xf5e4d7));
        badge.setFont(new Font("Helvetica", Font.BOLD, 10));
        add(badge);

        JLabel subTitle = new JLabel("MEET THE DEVELOPERS");
        subTitle.setBounds(40, 150, 400, 30);
        subTitle.setForeground(Color.WHITE);
        subTitle.setFont(new Font("Helvetica", Font.BOLD, 16));
        add(subTitle);

        int startX = 40;
        int startY = 200;
        int cardWidth = 460;
        int cardHeight = 200; 
        int padding = 10;

        // developers with descriptions
        add(createMemberCard("Main Coder", "Carlo Sebastian Dingle", 
            "Developed the system architecture, including the main frame design and integrated cards. Designed and populated the dashboard overview and portfolio sections with dynamic components. Implemented the backend database. Improve design in user interface for login and sign-up.", startX, startY, cardWidth, cardHeight));
        add(createMemberCard("Assistant Coder", "Lianne Jhulzen Guerrero", 
            "Created and implemented the secure central authentication system, featuring integrated login and sign-up functionalities. Created the backend logic to validate user credentials and manage account creation processes effectively.", startX + cardWidth + padding, startY, cardWidth, cardHeight));
            
        add(createMemberCard("Assistant Coder", "Kristine Joice Borres", 
            "Created and designed the comprehensive settings and user profile sections within the primary system frame. Enhanced the graphical user interface (GUI) by optimizing layout symmetry, refining color schemes, and implementing modern aesthetic standards for a seamless user experience.", startX, startY + cardHeight + padding, cardWidth, cardHeight));
            
        add(createMemberCard("Documentation", "Chelsie Chavez", 
            "Curated all system logs and developed the comprehensive project manual, ensuring detailed documentation of the entire development process. Managed technical specifications and maintained the system’s operational records to provide a clear and organized project archive.", startX + cardWidth + padding, startY + cardHeight + padding, cardWidth, cardHeight));

        JLabel footer = new JLabel("© 2026 Student Portfolio System | Developed by SANA-OL for Academic Purposes");
        footer.setBounds(0, 660, 1016, 30);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setForeground(new Color(0x94a899));
        footer.setFont(new Font("Helvetica", Font.ITALIC, 12));
        add(footer);
    }
    
    private JPanel createMemberCard(String role, String name, String desc, int x, int y, int w, int h) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, w, h);
        card.setBackground(new Color(0x94a899)); 
        card.setBorder(BorderFactory.createLineBorder(new Color(0xf5e4d7), 1));

        // 1. Role Label 
        JLabel roleLabel = new JLabel(role.toUpperCase());
        roleLabel.setBounds(25, 15, 300, 20);
        roleLabel.setForeground(new Color(0x4A5D50)); // Darker sage for better readability
        roleLabel.setFont(new Font("Verdana", Font.BOLD, 10)); 
        card.add(roleLabel);

        // 2. Name Label 
        JLabel nameLabel = new JLabel(name);
        nameLabel.setBounds(25, 35, 400, 35);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 22));
        card.add(nameLabel);

        // 3. Description Label 
        JLabel descLabel = new JLabel("<html><body style='width: 300PX;'>" +
                                      "<p style='line-height: 1.4;'>" + desc + "</p>" +
                                      "</body></html>");
        descLabel.setBounds(25, 75, 390, 100);
        descLabel.setForeground(new Color(0xf5e4d7));
        descLabel.setFont(new Font("SansSerif", Font.ITALIC, 13)); 
        descLabel.setVerticalAlignment(SwingConstants.TOP);
        card.add(descLabel);

        // Hover Effect
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(0x73877b));
                roleLabel.setForeground(new Color(0xf5e4d7));
                descLabel.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                card.setBackground(new Color(0x94a899));
                roleLabel.setForeground(new Color(0x4A5D50));
                descLabel.setForeground(new Color(0xf5e4d7));
            }
        });

        return card;
    }
}
