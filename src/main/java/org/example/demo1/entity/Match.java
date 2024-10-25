package org.example.demo1.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match {
    private Long id;
    private Long roomId;
    private Long player1Id;
    private Long player2Id;
    private Long player1Score;
    private Long player2Score;  // Sửa thành player2Score
    private Long winnerId;
    private LocalDateTime createdAt;  // Thay đổi thành kiểu dữ liệu LocalDateTime
    private LocalDateTime updatedAt;  // Thay đổi thành kiểu dữ liệu LocalDateTime

    public Match() {}

    public Match(Long id, Long roomId, Long player1Id, Long player2Id, Long player1Score, Long player2Score, Long winnerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.roomId = roomId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.winnerId = winnerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Match(Long roomId, Long player1Id, Long player2Id, Long player1Score, Long player2Score, Long winnerId) {
        this.roomId = roomId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.winnerId = winnerId;
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Long player1Id) {
        this.player1Id = player1Id;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Long player2Id) {
        this.player2Id = player2Id;
    }

    public Long getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(Long player1Score) {
        this.player1Score = player1Score;
    }

    public Long getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(Long player2Score) {
        this.player2Score = player2Score;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
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
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) &&
                Objects.equals(roomId, match.roomId) &&
                Objects.equals(player1Id, match.player1Id) &&
                Objects.equals(player2Id, match.player2Id) &&
                Objects.equals(player1Score, match.player1Score) &&
                Objects.equals(player2Score, match.player2Score) &&
                Objects.equals(winnerId, match.winnerId) &&
                Objects.equals(createdAt, match.createdAt) &&
                Objects.equals(updatedAt, match.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, player1Id, player2Id, player1Score, player2Score, winnerId, createdAt, updatedAt);
    }
}
