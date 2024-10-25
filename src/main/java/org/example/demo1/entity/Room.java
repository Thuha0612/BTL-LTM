package org.example.demo1.entity;

import java.util.Objects;

public class Room {
    private Long id;
    private Long player1Id;
    private Long player2Id;
    private String status;

    public Room() {}

    public Room(Long id, Long player1Id, Long player2Id, String status) {
        this.id = id;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.status = status;
    }

    public Room(Long player1Id, Long player2Id, String status) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.status = status;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(player1Id, room.player1Id) &&
                Objects.equals(player2Id, room.player2Id) &&
                Objects.equals(status, room.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player1Id, player2Id, status);
    }
}
