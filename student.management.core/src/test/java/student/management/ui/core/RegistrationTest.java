package student.management.ui.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class RegistrationTest {

    private Registration registration;

    @BeforeEach
    void setUp() {
        // Initialize the Registration object before each test
        registration = new Registration("test@example.com", "John Doe", "Computer Science", "Male", "Reading, Coding");
    }

    @Test
    void getEmail() {
        // Ensure getEmail() does not throw any exception
        assertDoesNotThrow(() -> registration.getEmail());

        // Ensure getEmail() returns a String instance
        assertInstanceOf(String.class, registration.getEmail(), "Email should be a String");

        // Check if the email is correct using assertLinesMatch (Single-line list comparison)
        assertLinesMatch(List.of("test@example.com"), List.of(registration.getEmail()));
    }

    @Test
    void getName() {
        assertDoesNotThrow(() -> registration.getName());
        assertInstanceOf(String.class, registration.getName(), "Name should be a String");
        assertLinesMatch(List.of("John Doe"), List.of(registration.getName()));
    }

    @Test
    void getCourse() {
        assertDoesNotThrow(() -> registration.getCourse());
        assertInstanceOf(String.class, registration.getCourse(), "Course should be a String");
        assertLinesMatch(List.of("Computer Science"), List.of(registration.getCourse()));
    }

    @Test
    void getGender() {
        assertDoesNotThrow(() -> registration.getGender());
        assertInstanceOf(String.class, registration.getGender(), "Gender should be a String");
        assertLinesMatch(List.of("Male"), List.of(registration.getGender()));
    }

    @Test
    void getHobbies() {
        assertDoesNotThrow(() -> registration.getHobbies());
        assertInstanceOf(String.class, registration.getHobbies(), "Hobbies should be a String");

        // Splitting hobbies into an array and verifying using assertArrayEquals
        String[] expectedHobbies = {"Reading", "Coding"};
        String[] actualHobbies = registration.getHobbies().split(", ");
        assertArrayEquals(expectedHobbies, actualHobbies, "Hobbies should match expected values");
    }
}
