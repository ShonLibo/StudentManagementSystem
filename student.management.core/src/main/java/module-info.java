module student.management.core {
    requires java.sql; // For JDBC
    exports mysql.com; // Export the package containing core classes
}