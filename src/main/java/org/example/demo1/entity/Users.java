package org.example.demo1.entity;

import org.example.demo1.enums.UserState;

import java.time.LocalDateTime;
import java.util.Objects;

public class Users {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String name;  // Có thể là tiếng Việt
    private Long points;
    private UserState state;
    private LocalDateTime createdAt;  // Thời gian tạo
    private LocalDateTime updatedAt;  // Thời gian cập nhật

    public Users() {}

    public Users(Long id, String username, String password, String avatar, String name, Long points, String state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.name = name;
        this.points = points;
        this.state = UserState.valueOf(state);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Users(String username, String password, String avatar, String name, Long points, String state) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.name = name;
        this.points = points;
        this.state = UserState.valueOf(state);
        this.createdAt = LocalDateTime.now(); // Gán thời gian hiện tại
        this.updatedAt = LocalDateTime.now(); // Gán thời gian hiện tại
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users user = (Users) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(name, user.name) &&
                Objects.equals(points, user.points) &&
                Objects.equals(state, user.state) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, avatar, name, points, state, createdAt, updatedAt);
    }
}
