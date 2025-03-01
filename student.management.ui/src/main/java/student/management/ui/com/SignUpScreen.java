package student.management.ui.com;

import student.management.ui.com.SignInScreen;
import student.management.ui.core.Database;
import student.management.ui.core.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public final class SignUpScreen extends JFrame {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;
    private final Connection connection;

    public SignUpScreen() throws SQLException {
        this.connection = Database.getConnection();
        setTitle("Student Management System");
        setSize(500, 400); // Slightly larger to accommodate the form
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Panel with Icon and Subtitle
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 0, 128));

        JLabel subtitleLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setForeground(new Color(0, 128, 0));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0)); // Padding for subtitle

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(subtitleLabel, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);

        // Form Panel with GridBagLayout for better control
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the form

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Email Field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(Utils.createLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20); // Increase field size
        emailField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size
        formPanel.add(emailField, gbc);

        // Password Field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(Utils.createLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20); // Increase field size
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size
        formPanel.add(passwordField, gbc);

        // Confirm Password Field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(Utils.createLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(20); // Increase field size
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size
        formPanel.add(confirmPasswordField, gbc);

        // Buttons with padding from the top
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add padding from the top

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
        Utils.styleButton(signUpButton, new Color(0, 128, 0)); // Green
        Utils.styleButton(backButton, new Color(128, 0, 0)); // Red

        signUpButton.addActionListener(e -> {
            if (validateSignUp()) {
                JOptionPane.showMessageDialog(this, "Sign Up Successful! Please Sign In.");
                try {
                    new SignInScreen().setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Passwords do not match or fields are empty.");
            }
        });

        backButton.addActionListener(e -> {
            try {
                new SignInScreen().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        buttonPanel.add(signUpButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private boolean validateSignUp() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }
        if (!password.equals(confirmPassword)) {
            return false;
        }
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password) VALUES (?, ?)");
            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Utils.logError("Error: Database error occurred.", e);
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}