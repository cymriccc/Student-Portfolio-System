package main;

import gui.LoginForm;
import java.awt.Color;

public class Main {
    public static final Color BG_COLOR = new Color(0xF5F7FA);
    public static final Color DARK_PANEL = new Color(0x1E272E);
    public static final Color ACCENT_COLOR = new Color(0x575FCF);

    public static void main(String[] args) {
        // Start the application at the Login screen
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}