package student.management.ui.com;

import student.management.ui.core.Database;
import student.management.ui.core.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public final class RegistrationScreen extends JFrame {
    private final JTextField nameField;
    private final JComboBox<String> courseComboBox;
    private final JRadioButton maleRadioButton, femaleRadioButton;
    private final JTree hobbiesTree;
    private final Connection connection;
    private final String email;

    public RegistrationScreen(String email) throws SQLException {
        this.connection = Database.getConnection();
        this.email = email;
        setTitle("Student Management System");
        setSize(500, 400); // Increased size to accommodate the JTree
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Panel with Icon and Subtitle
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 0, 128));

        JLabel subtitleLabel = new JLabel("Registration", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(0, 0, 128));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding for subtitle

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(subtitleLabel, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Increased rows for JTree
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(Utils.createLabel("Email:"));
        formPanel.add(Utils.createLabel(email));

        formPanel.add(Utils.createLabel("Name:"));
        nameField = new JTextField(15);
        formPanel.add(nameField);

        formPanel.add(Utils.createLabel("Course:"));
        courseComboBox = new JComboBox<>(new String[]{"Computer Science", "Information Technology", "Software Engineering", "CyberSecurity"});
        formPanel.add(courseComboBox);

        formPanel.add(Utils.createLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        formPanel.add(genderPanel);

        formPanel.add(Utils.createLabel("Hobbies:"));
        hobbiesTree = createHobbiesTree();
        formPanel.add(new JScrollPane(hobbiesTree));

        JButton saveButton = new JButton("Save");
        JButton viewButton = new JButton("View");
        JButton backButton = new JButton("Back");
        Utils.styleButton(saveButton, new Color(0, 128, 0)); // Green
        Utils.styleButton(viewButton, new Color(0, 0, 128)); // Blue
        Utils.styleButton(backButton, new Color(128, 0, 0)); // Red

        // Save Button Action
        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String course = (String) courseComboBox.getSelectedItem();
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";
            String hobbies = getSelectedHobbies();

            if (saveRegistration(email, name, course, gender, hobbies)) {
                JOptionPane.showMessageDialog(this, "Registration Saved!");
            } else {
                JOptionPane.showMessageDialog(this, "Error saving registration!");
            }
        });

        // View Button Action
        viewButton.addActionListener(e -> {
            try {
                new InformationDisplayScreen(email).setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Utils.logError("Error: Database error occurred.", ex);
            }
        });

        // Back Button Action
        backButton.addActionListener(e -> {
            try {
                new SignInScreen().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        // Add buttons to the form panel
        formPanel.add(saveButton);
        formPanel.add(viewButton);
        formPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private JTree createHobbiesTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Hobbies");

        DefaultMutableTreeNode sportsNode = new DefaultMutableTreeNode("Sports");
        sportsNode.add(new DefaultMutableTreeNode("Football"));
        sportsNode.add(new DefaultMutableTreeNode("Basketball"));
        sportsNode.add(new DefaultMutableTreeNode("Tennis"));

        DefaultMutableTreeNode artsNode = new DefaultMutableTreeNode("Arts");
        artsNode.add(new DefaultMutableTreeNode("Painting"));
        artsNode.add(new DefaultMutableTreeNode("Music"));
        artsNode.add(new DefaultMutableTreeNode("Dancing"));

        DefaultMutableTreeNode techNode = new DefaultMutableTreeNode("Technology");
        techNode.add(new DefaultMutableTreeNode("Programming"));
        techNode.add(new DefaultMutableTreeNode("Gaming"));
        techNode.add(new DefaultMutableTreeNode("Robotics"));

        root.add(sportsNode);
        root.add(artsNode);
        root.add(techNode);

        return new JTree(root);
    }

    private String getSelectedHobbies() {
        StringBuilder hobbies = new StringBuilder();
        TreePath[] paths = hobbiesTree.getSelectionPaths();

        if (paths != null) {
            for (TreePath path : paths) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                hobbies.append(node.getUserObject().toString()).append(", ");
            }
        }

        // Remove the trailing comma and space
        if (hobbies.length() > 0) {
            hobbies.setLength(hobbies.length() - 2);
        }

        return hobbies.toString();
    }

    private boolean saveRegistration(String email, String name, String course, String gender, String hobbies) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO registrations (email, name, course, gender, hobbies) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, course);
            statement.setString(4, gender);
            statement.setString(5, hobbies);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Utils.logError("Error: Database error occurred.", e);
            return false;
        }
    }
}