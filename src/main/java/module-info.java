module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base; // Thêm dòng này nếu chưa có

    opens org.example.demo1.entity to javafx.base; // Mở package cho javafx.base

    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires java.sql;
    requires Java.WebSocket;

    opens org.example.demo1 to javafx.fxml;
    exports org.example.demo1;
    exports org.example.demo1.ui;
    opens org.example.demo1.ui to javafx.fxml;
}