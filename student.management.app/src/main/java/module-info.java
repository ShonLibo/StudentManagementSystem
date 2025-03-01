module student.management.app {
    requires student.management.core; // Dependency on core module
    requires student.management.ui; // Dependency on UI module
    requires com.jtattoo;
    requires java.desktop;
    requires java.sql; // External library for look and feel
}