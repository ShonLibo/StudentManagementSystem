module student.management.ui {
    requires java.desktop; // For Swing
    requires student.management.core;
    exports mysql.com; // Export the package containing UI classes
}