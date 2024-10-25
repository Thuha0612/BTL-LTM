package org.example.demo1.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleRegister() {
        // Logic xử lý đăng ký
        System.out.println("Đăng ký với tên: " + usernameField.getText());
    }

    @FXML
    protected void handleBack() {
        // Quay lại giao diện chính
        switchToMainView();
    }

    private void switchToMainView() {
        try {
            // Tải FXML giao diện chính
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo1/main_view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) usernameField).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Giao Diện Đăng Nhập/Đăng Ký");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
