//package org.example.demo1.ui;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class LoginUI extends Application {
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        // Load file FXML cho giao diện đăng nhập
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/demo1/login-view.fxml"));
//        GridPane root = fxmlLoader.load();
//
//        // Tạo scene và hiển thị
//        Scene scene = new Scene(root, 300, 200);
//        stage.setTitle("Login Page");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
