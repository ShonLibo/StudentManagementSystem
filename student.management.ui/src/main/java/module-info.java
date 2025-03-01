module student.management.ui {
    requires java.desktop; // For Swing
    requires student.management.core;
    requires java.sql; // Dependency on core module
    exports mysql.com; // Export the package containing UI classes
}