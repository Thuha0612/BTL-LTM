package org.example.demo1.repository;

import org.example.demo1.response.LeaderBoardResponse;

import java.sql.*;

public class LeaderBoardRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/memory_card?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public LeaderBoardResponse getLeaderBoard() {
        String sql = "select * from leader_board";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            LeaderBoardResponse leaderBoardResponse = new LeaderBoardResponse();
            while (resultSet.next()) {
                LeaderBoardResponse.LeaderBoardUser user = new LeaderBoardResponse.LeaderBoardUser(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getDate(5));
                leaderBoardResponse.getUsers().add(user);
            }
            return leaderBoardResponse;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
