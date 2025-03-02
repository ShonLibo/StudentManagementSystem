package mysql;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import student.management.ui.com.SignInScreen;
import student.management.ui.core.Utils;

import javax.swing.*;
import java.sql.SQLException;

public final class AppLauncher {
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