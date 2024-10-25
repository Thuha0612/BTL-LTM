package org.example.demo1.repository;

import java.sql.*;
import org.example.demo1.entity.Card;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/memory_card?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public List<Card> getCards(int numOfCards) throws SQLException {
        List<Card> cards = new ArrayList<Card>();
        String randomCard = "select * from cards order by RAN() limit " + numOfCards;

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(randomCard)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                cards.add(card);
            }
            return cards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

}
