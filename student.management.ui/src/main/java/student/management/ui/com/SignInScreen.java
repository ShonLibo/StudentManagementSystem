package student.management.ui.com;

import student.management.ui.core.Database;
import student.management.ui.core.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SignInScreen extends JFrame {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final Connection connection;

    public SignInScreen() throws SQLException {
        this.connection = Database.getConnection();
        setTitle("Student Management System");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Panel with Icon and Subtitle
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 0, 128));

        JLabel subtitleLabel = new JLabel("Sign In", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setForeground(new Color(0, 128, 0));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Padding for subtitle
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

        // Buttons with padding from the top
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add padding from the top

        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Don't have an account?");
        Utils.styleButton(signInButton, new Color(0, 128, 0)); // Green
        Utils.styleButton(signUpButton, new Color(0, 0, 128)); // Blue

        signInButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    if (validateUser(email, password)) {
                        JOptionPane.showMessageDialog(this, "Sign In Successful!");
                        new RegistrationScreen(email).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Email or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Utils.logError("Error: Database error occurred.", ex);
                }
            }
        });

        signUpButton.addActionListener(e -> {
            try {
                new student.management.ui.com.SignUpScreen().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private boolean validateUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if a matching user is found
            }
        }
    }
}