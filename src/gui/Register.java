package gui;

import db.Database;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class Register extends JFrame {
    private JTextField txtFullName, txtUsername, txtStudentID, txtCourseYear, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister, btnLogin;

    public Register() {
        setTitle("Student Portfolio - Sign Up");
        setSize(600, 750); // Upgraded size
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
        title.setBounds(centerX, 40, fieldWidth, 40); 
        title.setFont(new Font("Helvetica", Font.BOLD, 32));
        title.setForeground(new Color(0xf5e4d7));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        // --- Fields ---
        addLabelAndField("Full Name", 110, txtFullName = new JTextField());
        addLabelAndField("Student ID", 190, txtStudentID = new JTextField());
        addLabelAndField("Course & Year", 270, txtCourseYear = new JTextField());
        addLabelAndField("Email Address", 350, txtEmail = new JTextField());
        addLabelAndField("Username", 430, txtUsername = new JTextField());
        
        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(centerX, 510, 100, 20);
        lblPass.setForeground(new Color(0xf5e4d7));
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(centerX, 535, fieldWidth, 40);
        txtPassword.setBackground(new Color(0x94a899));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(txtPassword);

        // --- Buttons ---
        btnRegister = new JButton("SIGN UP");
        btnRegister.setBounds(centerX, 600, fieldWidth, 50);
        btnRegister.setBackground(new Color(0x73877b));
        btnRegister.setForeground(new Color(0xf5e4d7));
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.addActionListener(e -> handleRegistration());
        add(btnRegister);

        btnLogin = new JButton("Already have an account? Login");
        btnLogin.setBounds(centerX, 660, fieldWidth, 30);
        btnLogin.setForeground(new Color(0xf5e4d7));
        btnLogin.setContentAreaFilled(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });
        add(btnLogin);

        addWindowControls();
    }

    private void addLabelAndField(String labelText, int yPos, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setBounds(100, yPos, 200, 20);
        label.setForeground(new Color(0xf5e4d7));
        add(label);

        field.setBounds(100, yPos + 25, 400, 40);
        field.setBackground(new Color(0x94a899));
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(field);
    }

    private void handleRegistration() {
        String fullName = txtFullName.getText().trim();
        String studentID = txtStudentID.getText().trim();
        String courseYear = txtCourseYear.getText().trim();
        String email = txtEmail.getText().trim();
        String user = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword());

        if (fullName.isEmpty() || studentID.isEmpty() || courseYear.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            CustomDialog.show(this, "Please fill in all required fields!");
            return;
        }

        if (!email.contains("@")) {
            CustomDialog.show(this, "Please enter a valid email address!");
            return;
        }

        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO users (full_name, student_id, course_year, email, username, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, fullName);
            pst.setString(2, studentID);
            pst.setString(3, courseYear);
            pst.setString(4, email);
            pst.setString(5, user);
            pst.setString(6, pass);

            pst.executeUpdate();
            CustomDialog.show(this, "Registration Successful!");
            new LoginForm().setVisible(true);
            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            CustomDialog.show(this, "Error: " + ex.getMessage());
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