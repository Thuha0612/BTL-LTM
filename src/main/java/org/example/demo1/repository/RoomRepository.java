package org.example.demo1.repository;

import org.example.demo1.entity.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/memory_card?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public void insertRoom(Room room) {
        String insertRoomSql = "INSERT INTO Room (player1Id, player2Id, status) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertRoomSql)) {

            preparedStatement.setLong(1, room.getPlayer1Id());
            preparedStatement.setLong(2, room.getPlayer2Id());
            preparedStatement.setString(3, room.getStatus());

            preparedStatement.executeUpdate();
            System.out.println("Room inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
