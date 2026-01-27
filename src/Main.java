import gui.LoginForm;

public class Main {
    public static void main(String[] args) {
        // Start the application at the Login screen
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}