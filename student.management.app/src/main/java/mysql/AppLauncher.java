package mysql.com;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import javax.swing.*;

public final class AppLauncher {
    private AppLauncher() {
        // Private constructor to prevent instantiation
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new AluminiumLookAndFeel());
            SwingUtilities.invokeLater(() -> {
                try {
                    new SignInScreen().setVisible(true);
                } catch (SQLException e) {
                    Utils.logError("Error: Database error occurred.", e);
                }
            });
        } catch (Exception e) {
            Utils.logError("Error: Failed to set look and feel.", e);
        }
    }
}