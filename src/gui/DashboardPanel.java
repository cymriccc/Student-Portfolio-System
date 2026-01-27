package gui;

import java.awt.*;
import javax.swing.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel(String studentName) {
        setLayout(null);
        setBackground(new Color(0x839788));

        JLabel welcomeUser = new JLabel("Welcome back, " + studentName + "!");
        welcomeUser.setBounds(40, 60, 400, 30);
        welcomeUser.setFont(new Font("Helvetica", Font.PLAIN, 18));
        welcomeUser.setForeground(new Color(0xf5e4d7));
        add(welcomeUser);

        JLabel welcomeLabel = new JLabel("Dashboard Overview");
        welcomeLabel.setBounds(40, 100, 400, 50);
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(0xf5e4d7));
        add(welcomeLabel);

        add(createStatCard("Total Projects", "12", 40, 160));
        add(createStatCard("Skills", "8", 260, 160));
        add(createProgressCard("Overall Progress", 75, 40, 280));
    }

    private JPanel createProgressCard(String title, int value, int x, int y) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 420, 100); 
        card.setBackground(new Color(0x94a899));

        JLabel t = new JLabel(title);
        t.setBounds(15, 10, 180, 20);
        t.setForeground(Color.WHITE);
        card.add(t);

        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(value);
        bar.setBounds(15, 45, 390, 25);


        bar.setForeground(new Color(0xdd622d));
        bar.setBackground(new Color(0x73877b)); 

        bar.setBorderPainted(false);
        bar.setStringPainted(true); 
        bar.setFont(new Font("Helvetica", Font.BOLD, 12));
        card.add(bar);
        return card;
    }

    private JPanel createStatCard(String title, String value, int x, int y) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 200, 100);
        card.setBackground(new Color(0x94a899));
        card.setBorder(BorderFactory.createLineBorder(new Color(0xf5e4d7), 1));
        JLabel t = new JLabel(title);
        t.setBounds(10, 10, 180, 20);
        t.setForeground(Color.WHITE);
        JLabel v = new JLabel(value);
        v.setBounds(10, 40, 180, 40);
        v.setForeground(new Color(0xf5e4d7));
        v.setFont(new Font("Helvetica", Font.BOLD, 30));
        card.add(t); card.add(v);
        return card;
    }
}