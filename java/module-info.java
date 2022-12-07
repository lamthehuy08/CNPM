module com.example.nhankhaumanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens com.example.nhankhaumanager to javafx.fxml;
    exports com.example.nhankhaumanager;
    exports Controller;
    exports Infomation;
    opens Controller to javafx.fxml;
}