package student.management.ui.core;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void createLabel() {
        // Ensure createLabel() does not throw an exception
        assertDoesNotThrow(() -> Utils.createLabel("Test Label"));

        // Ensure it returns a JLabel instance
        JLabel label = Utils.createLabel("Test Label");
        assertInstanceOf(JLabel.class, label, "Returned object should be a JLabel");

        // Check if label text matches
        assertLinesMatch(List.of("Test Label"), List.of(label.getText()));
    }

    @Test
    void styleButton() {
        JButton button = new JButton("Click Me");
        Color expectedColor = Color.BLUE;

        // Ensure styleButton() does not throw an exception
        assertDoesNotThrow(() -> Utils.styleButton(button, expectedColor));

        // Check if button is styled correctly
        assertArrayEquals(new int[]{expectedColor.getRGB(), Color.WHITE.getRGB()},
                new int[]{button.getBackground().getRGB(), button.getForeground().getRGB()},
                "Button background and foreground colors should match expected values");

        // Check if button size is correctly set
        assertEquals(new Dimension(120, 30), button.getPreferredSize(), "Button size should match expected dimensions");
    }
}
