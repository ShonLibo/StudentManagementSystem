package mysql.com;

import javax.swing.*;
import java.awt.*;

public final class Utils {
    private Utils() {
        // Private constructor to prevent instantiation
    }

    public static void logError(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }

    public static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 30));
    }
}