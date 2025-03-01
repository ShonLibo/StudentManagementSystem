module student.management.core {
    requires java.sql;
    requires java.desktop; // For JDBC
    exports mysql.com; // Export the package containing core classes
}