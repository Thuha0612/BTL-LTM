package org.example.demo1.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class LeaderBoard {
    private Long userId;
    private String name;
    private String avatar;
    private Long points;
    private LocalDateTime updatedAt;

    public LeaderBoard() {}

    public LeaderBoard(Long userId, String name, String avatar, Long points, LocalDateTime updatedAt) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
        this.points = points;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
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
        if (!(o instanceof LeaderBoard)) return false;
        LeaderBoard that = (LeaderBoard) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(points, that.points) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, avatar, points, updatedAt);
    }
}