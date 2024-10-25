package org.example.demo1.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainViewController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    protected void handleLoginButton() {
        switchToLoginView();
    }

    @FXML
    protected void handleRegisterButton() {
        switchToRegisterView();
    }

    private void switchToLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo1/login-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Giao Diện Đăng Nhập");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToRegisterView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo1/register_view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Giao Diện Đăng Ký");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
