package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File; // to handle file selection
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter; // to filter image file types

public class ProfilePanel extends JPanel {
    private JTextField nameField, courseField, emailField, idField, contactField; // this is added so that the information inputted can be stored and retrieved 
    private JLabel imageLabel;
    private String selectedImagePath = ""; // Stores the path of the new photo

    public ProfilePanel(myFrame frameObject) {
        setLayout(null); // absolute positioning
        setBackground(new Color(0x839788));

        // HEADER
        JLabel title = new JLabel("User Profile Settings");
        title.setBounds(40, 40, 400, 50);
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        title.setForeground(new Color(0xf5e4d7));
        add(title);

        JPanel underline = new JPanel(); // for design purposes
        underline.setBounds(40, 85, 80, 4);
        underline.setBackground(new Color(0xf5e4d7));
        add(underline);

        // PROFILE IMAGE 
        JPanel imgShadow = new JPanel(); // shadow effect
        imgShadow.setBounds(695, 95, 190, 190);
        imgShadow.setBackground(new Color(0x73877b)); 
        add(imgShadow);

        imageLabel = new JLabel("No Photo", SwingConstants.CENTER);
        imageLabel.setBounds(700, 100, 180, 180);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(0x94a899));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(0xf5e4d7), 2));
        imageLabel.setForeground(Color.WHITE);
        add(imageLabel);

        // Change Photo Button
        JButton uploadBtn = createStyledButton("UPDATE AVATAR", 700, 300, 180, 35);
        uploadBtn.addActionListener(e -> chooseImage()); // once clicked, open file explorer
        add(uploadBtn);

        // PERSONAL DETAILS
        int x = 40, y = 120, w = 380, h = 35;
        
        // Use a decorative card background for inputs
        add(createSectionHeader("Personal Information", x, y));
        
        add(createLabel("Full Name", x, y + 40));
        nameField = createField(x, y + 65, w, h); 
        add(nameField);

        add(createLabel("Student ID Number", x, y + 110));
        idField = createField(x, y + 135, w, h); 
        add(idField);

        add(createSectionHeader("Academic & Contact", x, y + 200));

        add(createLabel("Course & Year Level", x, y + 240));
        courseField = createField(x, y + 265, w, h); 
        add(courseField);

        add(createLabel("Official Email", x, y + 310));
        emailField = createField(x, y + 335, w, h); 
        add(emailField);

        // BIOGRAPHY SECTION
        add(createLabel("Short Bio", x, y + 375));
        JTextArea bioArea = new JTextArea();
        bioArea.setBounds(x, y + 400, w, 120); 
        bioArea.setBackground(new Color(0x94a899)); // Coordinated color with TextFields
        bioArea.setForeground(Color.WHITE);
        bioArea.setLineWrap(true); // enable line wrapping
        bioArea.setWrapStyleWord(true); // enable word wrapping
        bioArea.setOpaque(true);
        bioArea.setCaretColor(Color.WHITE);
        bioArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(bioArea);

        // SUBMIT BUTTON
        JButton saveBtn = createStyledButton("FINALIZE PROFILE", 700, 600, 200, 55);
        saveBtn.setFont(new Font("Helvetica", Font.BOLD, 14));
        saveBtn.addActionListener(e -> {
            if (!selectedImagePath.isEmpty()) {
                updateSidebarImage(frameObject, selectedImagePath);
            } 
            JOptionPane.showMessageDialog(this, "Your student profile has been synced.");
        });
        add(saveBtn);
    }
    // helper to create section headers
    private JLabel createSectionHeader(String text, int x, int y) {
        JLabel l = new JLabel(text.toUpperCase());
        l.setBounds(x, y, 300, 25);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Helvetica", Font.BOLD, 13));
        return l;
    }

    // to open file explorer once the button is clicked
    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();

        // Filter to accept only image files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImagePath = selectedFile.getAbsolutePath();
            
            ImageIcon icon = new ImageIcon(selectedImagePath);
            Image img = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setText(""); 
        }
    }

    // Helper to update the sidebar icon in the frame
    private void updateSidebarImage(myFrame frame, String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        frame.sidebarProfileImg.setIcon(new ImageIcon(img));
    }

    private JLabel createLabel(String txt, int x, int y) {
        JLabel l = new JLabel(txt);
        l.setBounds(x, y, 200, 20);
        l.setForeground(new Color(0xf5e4d7));
        l.setFont(new Font("Helvetica", Font.BOLD, 14));
        return l;
    }

    private JTextField createField(int x, int y, int w, int h) {
        JTextField f = new JTextField(); //
        f.setBounds(x, y, w, h);
        f.setBackground(new Color(0x94a899));
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return f;
    }

    // Helper to create buttons with the same design as your menu
    private JButton createStyledButton(String text, int x, int y, int w, int h) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setBackground(new Color(0x73877b));
        btn.setForeground(new Color(0xf5e4d7));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(0xf5e4d7), 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(0x839788)); }
            public void mouseExited(MouseEvent e) { btn.setBackground(new Color(0x73877b)); }
        });
        return btn;
    }
}
