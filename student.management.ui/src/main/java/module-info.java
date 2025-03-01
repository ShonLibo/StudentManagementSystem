module student.management.ui {
    requires java.desktop; // For Swing
    requires student.management.core;// Dependency on core module
    requires java.sql; // Dependency on core module
    exports mysql.com;// Export the package containing UI classes
}