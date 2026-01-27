package gui;

import db.Database;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class Register extends JFrame {
    private JTextField txtFullName, txtUsername;
    private JPasswordField txtPassword;
    private JButton btnRegister, btnLogin;

    public Register() {
        setTitle("Student Portfolio - Sign Up");
        setSize(600, 600); // Upgraded size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Switching from GridLayout to Absolute
        setUndecorated(true);
        setResizable(false);
        getContentPane().setBackground(new Color(0x839788)); // Sage Green

        int centerX = 100; // (600 - 400) / 2
        int fieldWidth = 400;

        // --- Title ---
        JLabel title = new JLabel("CREATE ACCOUNT");
        title.setBounds(centerX, 60, fieldWidth, 50); 
        title.setFont(new Font("Helvetica", Font.BOLD, 32));
        title.setForeground(new Color(0xf5e4d7));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        // --- Full Name Section ---
        JLabel lblFull = new JLabel("Full Name");
        lblFull.setBounds(centerX, 140, 100, 20);
        lblFull.setForeground(new Color(0xf5e4d7));
        add(lblFull);

        txtFullName = new JTextField();
        txtFullName.setBounds(centerX, 165, fieldWidth, 40);
        txtFullName.setBackground(new Color(0x94a899)); 
        txtFullName.setForeground(Color.WHITE);
        txtFullName.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        add(txtFullName);

        // --- Username Section ---
        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(centerX, 225, 100, 20);
        lblUser.setForeground(new Color(0xf5e4d7));
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(centerX, 250, fieldWidth, 40);
        txtUsername.setBackground(new Color(0x94a899)); 
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        add(txtUsername);

        // --- Password Section ---
        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(centerX, 310, 100, 20);
        lblPass.setForeground(new Color(0xf5e4d7));
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(centerX, 335, fieldWidth, 40);
        txtPassword.setBackground(new Color(0x94a899));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        add(txtPassword);

        // --- Register Button ---
        btnRegister = new JButton("SIGN UP");
        btnRegister.setBounds(centerX, 410, fieldWidth, 50);
        btnRegister.setBackground(new Color(0x73877b)); 
        btnRegister.setForeground(new Color(0xf5e4d7));
        btnRegister.setFont(new Font("Helvetica", Font.BOLD, 18));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnRegister);

        // --- Back to Login Link ---
        btnLogin = new JButton("Already have an account? Login");
        btnLogin.setBounds(centerX, 475, fieldWidth, 30);
        btnLogin.setForeground(new Color(0xf5e4d7));
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnLogin);

        // Listeners
        btnRegister.addActionListener(e -> registerStudent());
        btnLogin.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });
        
        addWindowControls();
    }

    private void registerStudent() {
        String fullName = txtFullName.getText();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if(fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return;
        }

        try {
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO users (full_name, username, password) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, fullName);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Registered successfully!");
            
            // Auto-switch back to login after successful register
            new LoginForm().setVisible(true);
            dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void addWindowControls() {
        JLayeredPane lp = this.getLayeredPane();

        Color originalColor = new Color(0x839788); // Sage
        Color hoverColor = new Color(0x94a899);    // Light Sage
        Color closeHoverColor = new Color(0xc94c4c); // Red

        // -- CLOSE BUTTON --
        JButton closeBtn = new JButton("X");
        closeBtn.setBounds(545, 10, 45, 30); // Positioned for 600 width
        closeBtn.setBackground(originalColor);
        closeBtn.setForeground(Color.BLACK);
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusable(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        minBtn.setBounds(495, 10, 45, 30); // Positioned to the left of Close
        minBtn.setBackground(originalColor);
        minBtn.setForeground(Color.BLACK);
        minBtn.setBorderPainted(false);
        minBtn.setFocusable(false);
        minBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minBtn.addActionListener(e -> setState(Frame.ICONIFIED));
        
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

        lp.add(closeBtn, JLayeredPane.PALETTE_LAYER);
        lp.add(minBtn, JLayeredPane.PALETTE_LAYER);
    }

    public static void main(String[] args) {
        new Register().setVisible(true);
    }
}