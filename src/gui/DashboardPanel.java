package gui;

import db.Database;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import main.Main;

public class DashboardPanel extends JPanel {
    private int userId;
    private JLabel lblWelcome, lblCourse, lblTotalProjects;
    private JProgressBar progressPortfolio;
    private JPanel recentProjectsContainer;

    public DashboardPanel(int userId, String studentName, String courseYear) {
        this.userId = userId;
        setLayout(null);
        setBackground(Main.BG_COLOR);

        // --- ORIGINAL HEADER STYLE ---
        lblWelcome = new JLabel("Welcome back, " + studentName + "!");
        lblWelcome.setBounds(40, 40, 500, 30);
        lblWelcome.setFont(new Font("Helvetica", Font.PLAIN, 18));
        lblWelcome.setForeground(Main.TEXT_COLOR);
        add(lblWelcome);

        lblCourse = new JLabel(courseYear != null ? courseYear : "No Course Set");
        lblCourse.setBounds(40, 65, 500, 20);
        lblCourse.setFont(new Font("Helvetica", Font.ITALIC, 14));
        lblCourse.setForeground(Main.TEXT_COLOR);
        add(lblCourse);

        JLabel title = new JLabel("Dashboard Overview");
        title.setBounds(40, 90, 500, 50);
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        title.setForeground(Main.TEXT_COLOR);
        add(title);

        // --- STAT CARDS (STAYING JUST LIKE BEFORE) ---
        add(createStatCard("Total Projects", 40, 160));
        add(createProgressCard("Overall Portfolio Progress", 260, 160));

        // --- RECENT PROJECTS (MAXIMIZED SPACE) ---
        JLabel recentTitle = new JLabel("RECENT PROJECTS");
        recentTitle.setBounds(40, 300, 300, 20);
        recentTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
        recentTitle.setForeground(Main.TEXT_COLOR);
        add(recentTitle);

        recentProjectsContainer = new JPanel();
        recentProjectsContainer.setLayout(null);
        recentProjectsContainer.setBackground(Main.BG_COLOR);
        recentProjectsContainer.setBounds(40, 330, 930, 350); // Maximized width
        add(recentProjectsContainer);

        refreshData();
    }

    public void refreshData() {
        // 1. Sync User Name/Course from DB
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT full_name, course_year FROM users WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lblWelcome.setText("Welcome back, " + rs.getString("full_name") + "!");
                lblCourse.setText(rs.getString("course_year"));
            }

            // 2. Sync Project Count
            String countSql = "SELECT COUNT(*) FROM portfolios WHERE user_id = ?";
            PreparedStatement pst2 = conn.prepareStatement(countSql);
            pst2.setInt(1, userId);
            ResultSet rs2 = pst2.executeQuery();
            if (rs2.next()) {
                int count = rs2.getInt(1);
                lblTotalProjects.setText(String.valueOf(count));
                progressPortfolio.setValue(Math.min(count * 10, 100)); // 10 projects = 100%
            }
        } catch (Exception e) { e.printStackTrace(); }

        loadRecentProjects();
    }

    private void loadRecentProjects() {
        recentProjectsContainer.removeAll();
        String sql = "SELECT project_name, upload_date FROM portfolios WHERE user_id = ? ORDER BY upload_date DESC LIMIT 3";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            int y = 0;
            while (rs.next()) {
                JPanel item = new JPanel(null);
                item.setBounds(0, y, 930, 60);
                item.setBackground(Color.WHITE);
                item.setBorder(BorderFactory.createLineBorder(new Color(0xD1D8E0)));

                JLabel name = new JLabel(rs.getString("project_name"));
                name.setBounds(20, 15, 500, 30);
                name.setFont(new Font("Helvetica", Font.BOLD, 15));
                item.add(name);

                JLabel date = new JLabel("Added: " + rs.getString("upload_date").substring(0, 10));
                date.setBounds(750, 15, 150, 30);
                date.setHorizontalAlignment(SwingConstants.RIGHT);
                item.add(date);

                recentProjectsContainer.add(item);
                y += 70;
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        recentProjectsContainer.revalidate();
        recentProjectsContainer.repaint();
    }

    private JPanel createStatCard(String title, int x, int y) {
        JPanel card = new JPanel(null);
        card.setBounds(x, y, 200, 100);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(0xD1D8E0), 1));

        JLabel t = new JLabel(title);
        t.setBounds(15, 15, 170, 20);
        t.setFont(new Font("Helvetica", Font.BOLD, 12));
        card.add(t);

        lblTotalProjects = new JLabel("0");
        lblTotalProjects.setBounds(15, 40, 170, 45);
        lblTotalProjects.setFont(new Font("Helvetica", Font.BOLD, 36));
        lblTotalProjects.setForeground(Main.ACCENT_COLOR);
        card.add(lblTotalProjects);
        return card;
    }

    private JPanel createProgressCard(String title, int x, int y) {
        JPanel card = new JPanel(null);
        card.setBounds(x, y, 420, 100);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(0xD1D8E0), 1));

        JLabel t = new JLabel(title.toUpperCase());
        t.setBounds(15, 15, 300, 20);
        t.setFont(new Font("Helvetica", Font.BOLD, 12));
        card.add(t);

        progressPortfolio = new JProgressBar(0, 100);
        progressPortfolio.setBounds(15, 45, 390, 30);
        progressPortfolio.setForeground(Main.ACCENT_COLOR);
        progressPortfolio.setStringPainted(true);
        card.add(progressPortfolio);
        return card;
    }
}