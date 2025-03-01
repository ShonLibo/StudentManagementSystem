module student.management.app {
    requires student.management.core; // Dependency on core module
    requires student.management.ui; // Dependency on UI module
    requires java.desktop;
    requires java.sql;
    requires JTattoo; // External library for look and feel
}