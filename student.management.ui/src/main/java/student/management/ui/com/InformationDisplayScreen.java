package student.management.ui.com;

import student.management.ui.core.Database;
import student.management.ui.core.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class InformationDisplayScreen extends JFrame {
    private final Connection connection;
    private final String email;

    public InformationDisplayScreen(String email) throws SQLException {
        this.connection = Database.getConnection();
        this.email = email;
        setTitle("Student Management System");
        setSize(500, 450); // Increased size to accommodate birthdate
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Panel with Icon and Subtitle
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 0, 128));

        JLabel subtitleLabel = new JLabel("Information Display", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(0, 0, 128));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding for subtitle

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(subtitleLabel, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.append("Email: " + email + "\n");

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name, course, gender, hobbies, birthdate FROM registrations WHERE email = ?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                infoArea.append("Name: " + resultSet.getString("name") + "\n");
                infoArea.append("Course: " + resultSet.getString("course") + "\n");
                infoArea.append("Gender: " + resultSet.getString("gender") + "\n");
                infoArea.append("Hobbies: " + resultSet.getString("hobbies") + "\n");
                infoArea.append("Birthdate: " + resultSet.getDate("birthdate") + "\n"); // Display birthdate
            } else {
                infoArea.append("No registration details found.\n");
            }
        } catch (SQLException e) {
            Utils.logError("Error fetching registration details.", e);
        }

        JScrollPane scrollPane = new JScrollPane(infoArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        Utils.styleButton(backButton, new Color(128, 0, 0)); // Red

        backButton.addActionListener(e -> {
            try {
                new RegistrationScreen(email).setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Utils.logError("Error: Database error occurred.", ex);
            }
        });

        add(backButton, BorderLayout.SOUTH);
    }
}