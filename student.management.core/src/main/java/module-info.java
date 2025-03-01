module student.management.core {
    requires java.sql;
    requires java.desktop; // For JDBC
    exports student.management.ui.com; // Export the package containing core classes
}