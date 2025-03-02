package student.management.ui.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Initialize the User object before each test
        user = new User("user@example.com", "SecurePassword123");
    }

    @Test
    void getEmail() {
        // Ensure getEmail() does not throw an exception
        assertDoesNotThrow(() -> user.getEmail());

        // Ensure getEmail() returns a String instance
        assertInstanceOf(String.class, user.getEmail(), "Email should be a String");

        // Check if the email is correct using assertLinesMatch
        assertLinesMatch(List.of("user@example.com"), List.of(user.getEmail()));
    }

    @Test
    void getPassword() {
        // Ensure getPassword() does not throw an exception
        assertDoesNotThrow(() -> user.getPassword());

        // Ensure getPassword() returns a String instance
        assertInstanceOf(String.class, user.getPassword(), "Password should be a String");

        // Check if the password matches using assertLinesMatch
        assertLinesMatch(List.of("SecurePassword123"), List.of(user.getPassword()));

        // Example use of assertArrayEquals: Checking individual characters of the password
        char[] expectedPassword = "SecurePassword123".toCharArray();
        char[] actualPassword = user.getPassword().toCharArray();
        assertArrayEquals(expectedPassword, actualPassword, "Password characters should match expected values");
    }
}
