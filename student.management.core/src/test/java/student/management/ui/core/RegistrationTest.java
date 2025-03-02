package student.management.ui.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {
private  Registration registration;
    @BeforeEach
    void setUp() {
        registration = new Registration("test@example.com", "John Doe", "Computer Science", "Male", "Reading, Coding");

    }

    @Test
    void getEmail() {
        assertEquals("", registration.getEmail());

    }

    @Test
    void getName() {


    }

    @Test
    void getCourse() {

    }

    @Test
    void getGender() {

    }

    @Test
    void getHobbies() {

    }
}