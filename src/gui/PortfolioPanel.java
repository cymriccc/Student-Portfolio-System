package gui;

import java.awt.*;
import javax.swing.*;

public class PortfolioPanel extends JPanel {
    public PortfolioPanel() {
        // MATCHING DASHBOARD COLOR
        setLayout(null);
        setBackground(new Color(0x839788)); 

        JLabel title = new JLabel("My Portfolio Projects");
        title.setBounds(40, 60, 400, 50);
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        title.setForeground(new Color(0xf5e4d7)); 
        
        add(title);
    }
}