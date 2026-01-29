package gui;

import db.Database;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.Main;

public class AdminDashboard extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;

    public AdminDashboard() {
        setTitle("Admin Panel - Student Portfolio System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        getContentPane().setBackground(Main.BG_COLOR);
        setLayout(null);

        // --- Sidebar ---
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 250, 650);
        sidebar.setBackground(Main.DARK_PANEL);
        sidebar.setLayout(null);
        add(sidebar);

        JPanel activeIndicator = new JPanel();
        activeIndicator.setBounds(0, 120, 5, 40);
        activeIndicator.setBackground(Main.ACCENT_COLOR); 
        sidebar.add(activeIndicator);

        JButton btnUsers = new JButton("Manage Users");
        btnUsers.setBounds(20, 120, 210, 40);
        btnUsers.setForeground(Color.WHITE);
        btnUsers.setContentAreaFilled(false);
        btnUsers.setFocusPainted(false);
        btnUsers.setBorderPainted(false);
        btnUsers.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUsers.setHorizontalAlignment(SwingConstants.LEFT);

        btnUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
               btnUsers.setForeground(Main.ACCENT_COLOR); // Turns red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnUsers.setForeground(Color.WHITE);
           }
        });

        sidebar.add(btnUsers);

        JLabel lblAdmin = new JLabel("ADMIN PORTAL");
        lblAdmin.setForeground(Color.WHITE);
        lblAdmin.setFont(new Font("Helvetica", Font.BOLD, 20));
        lblAdmin.setBounds(40, 50, 200, 30);
        sidebar.add(lblAdmin);

        // --- Sidebar Logout Button ---
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(20, 580, 210, 40); // Positioned near the bottom of the 650px height
        btnLogout.setForeground(new Color(0xECF0F1)); // Light Ice text
        btnLogout.setFont(new Font("Helvetica", Font.BOLD, 14));
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.setHorizontalAlignment(SwingConstants.LEFT);

        // Hover Effect
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
               btnLogout.setForeground(new Color(0xE74C3C)); // Turns red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnLogout.setForeground(new Color(0xECF0F1));
           }
        });

        // Logout Action
        btnLogout.addActionListener(e -> {
            // Call your new custom confirmation dialog
            boolean confirm = CustomDialog.showConfirm(this, "Are you sure you want to logout?");
    
            if (confirm) {
                // Show a quick "Goodbye" message using your standard success dialog
                CustomDialog.show(this, "Logged out successfully!", true);
            
                new LoginForm().setVisible(true);
                this.dispose();
            }
        });

        sidebar.add(btnLogout);

        // --- Main Content Area ---
        JLabel lblTitle = new JLabel("Registered Users Management");
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 28));
        lblTitle.setForeground(Main.TEXT_COLOR);
        lblTitle.setBounds(280, 40, 500, 40);
        add(lblTitle);

        JLabel lblSearch = new JLabel("Search by Name or ID:");
        lblSearch.setBounds(280, 95, 200, 20);
        lblSearch.setForeground(Main.TEXT_COLOR);
        lblSearch.setFont(new Font("Helvetica", Font.BOLD, 12));
        add(lblSearch);

        // Main Search Field
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(280, 120, 450, 35); 
        txtSearch.setBackground(Color.WHITE);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xD1D8E0), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtSearch.setBackground(Color.WHITE);
        add(txtSearch);

        JButton btnDelete = new JButton("DELETE USER");
        btnDelete.setBounds(840, 530, 120, 40);
        btnDelete.setBackground(new Color(0xE74C3C));              
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
               btnDelete.setBackground(Color.RED.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnDelete.setBackground(new Color(0xE74C3C));
           }
        });

        btnDelete.addActionListener(e -> deleteSelectedUser());
        add(btnDelete);

        // Setup Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Student ID", "Role", "Email"}, 0);
        userTable = new JTable(tableModel);
        userTable.setRowHeight(30);
        userTable.setSelectionBackground(Main.ACCENT_COLOR);
        

        // Initialize the Sorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        userTable.setRowSorter(sorter);

        // Add the "Live" Filter Logic
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
           public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    // Filters based on the "Name" (Index 1) or "Student ID" (Index 2) columns
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1, 2));
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(280, 180, 680, 330);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xD1D8E0)));
        add(scrollPane);

        loadUserData(); // Fetch from MySQL
        addWindowControls();
    }

    private void loadUserData() {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT id, full_name, student_id, role, email FROM users";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("id"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("student_id"));
                row.add(rs.getString("role"));
                row.add(rs.getString("email"));
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
           CustomDialog.show(this, "Please select a user first!", false);
            return;
        }

        // Get the ID from the first column of the selected row
        String userId = tableModel.getValueAt(selectedRow, 0).toString();
    
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            pst.executeUpdate();

            tableModel.removeRow(selectedRow);
        
            // Show success custom dialog
            CustomDialog.show(this, "User successfully removed.", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Show error custom dialog
            CustomDialog.show(this, "Database error: Could not delete user.", false);
        }
    }

    private void addWindowControls() {
        JLayeredPane lp = this.getLayeredPane();

        Color idleColor = Main.BG_COLOR; 
        Color minHover = new Color(0xD1D8E0); 
        Color closeHover = new Color(0xE74C3C);

        // -- CLOSE BUTTON --
        JButton closeBtn = new JButton("X");
        closeBtn.setBounds(950, 5, 45, 30);
        closeBtn.setBackground(idleColor);
        closeBtn.setForeground(Color.BLACK);
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusable(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> System.exit(0));
    
        closeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                closeBtn.setBackground(closeHover);
                closeBtn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                closeBtn.setBackground(idleColor);
                closeBtn.setForeground(Color.BLACK);
            }
        });

        // -- MINIMIZE BUTTON --
        JButton minBtn = new JButton("-");
        minBtn.setBounds(900, 5, 45, 30);
        minBtn.setBackground(idleColor);
        minBtn.setForeground(Color.BLACK);
        minBtn.setBorderPainted(false);
        minBtn.setFocusable(false);
        minBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minBtn.addActionListener(e -> setState(Frame.ICONIFIED));
        
        minBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                minBtn.setBackground(minHover);
                minBtn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                minBtn.setBackground(idleColor);
                minBtn.setForeground(Color.BLACK);
            }
        });

        lp.add(closeBtn, JLayeredPane.PALETTE_LAYER);
        lp.add(minBtn, JLayeredPane.PALETTE_LAYER);
    }
}
