module student.management.ui {
    requires java.desktop; // For Swing
    requires student.management.core;// Dependency on core module
    requires java.sql;
    exports mysql.com;
    // Export the package containing UI classes
}