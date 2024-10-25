package org.example.demo1.response;

import org.example.demo1.entity.Users;


public class LoginResponse {
    private boolean isSuccess;
    private Users user;

    public LoginResponse(boolean isSuccess, Users user) {
        this.isSuccess = isSuccess;
        this.user = user;
    }
}
