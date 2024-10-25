package org.example.demo1.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    protected void handleLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Xử lý logic đăng nhập ở đây
        if (isValidUsernameAndPassword(username, password)) {
            // Hiển thị thông báo đăng nhập thành công
            showLoginSuccessMessage();
            // Chuyển sang giao diện chính
            switchToMainView();
        } else {
            // Hiển thị thông báo đăng nhập thất bại
            showLoginFailureMessage();
        }
    }

    @FXML
    protected void handleBackButtonClick() {
        // Chuyển sang giao diện chính
        switchToMainView();
    }

    private boolean isValidUsernameAndPassword(String username, String password) {
        // Kiểm tra tính hợp lệ của username và password ở đây
        return username.equals("admin") && password.equals("password");
    }

    private void showLoginSuccessMessage() {
        // Hiển thị thông báo đăng nhập thành công
        System.out.println("Đăng nhập thành công!");
    }

    private void showLoginFailureMessage() {
        // Hiển thị thông báo đăng nhập thất bại
        System.out.println("Đăng nhập thất bại!");
    }

    private void switchToMainView() {
        try {
            // Tải FXML giao diện chính
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo1/main_view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Giao Diện Đăng Nhập/Đăng Ký");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}