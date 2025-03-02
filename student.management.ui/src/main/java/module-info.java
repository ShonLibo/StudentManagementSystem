module student.management.ui {
    requires java.desktop; // For Swing
    requires student.management.core;
    requires java.sql;
    requires jcalendar;// Dependency on core module
    exports student.management.ui.com;// Export the package containing UI classes
}