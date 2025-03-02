package student.management.ui.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class DatabaseTest {
    private Database database;

    // Correctly using @BeforeEach without fully qualifying it
    @BeforeEach
    public void setUp() {
        database = new Database(); // Initialize the Database object
    }

    @Test
    public void getConnection() {
        // Test that calling getConnection does not throw an exception
        assertDoesNotThrow(() -> {
            Object connection = database.getConnection();
            assertNotNull(connection, "Connection should not be null"); // Ensure connection is not null
        });
    }

    @Test
    public void closeConnection() {

    }

}
