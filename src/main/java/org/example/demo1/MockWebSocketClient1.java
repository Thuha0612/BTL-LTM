package org.example.demo1;

import org.example.demo1.entity.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockWebSocketClient1 {
    private List<Card> cardData;
    private List<String> players;

    public MockWebSocketClient1() {
        this.cardData = generateCardData();
        this.players = generatePlayers();
    }

    private List<Card> generateCardData() {
        List<Card> cards = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) { // Giả lập 10 cặp thẻ
            String cardName = "Card " + i; // Tên thẻ giả lập
            String imageUrl = "http://example.com/card/" + i + ".png"; // URL giả lập
            Card card = new Card(i, cardName, imageUrl); // Tạo thẻ với ID, tên và URL
            cards.add(card);
            cards.add(card); // Thêm cặp thẻ
        }
        return cards;
    }

    private List<String> generatePlayers() {
        List<String> players = new ArrayList<>();
        players.add("Player1");
        players.add("Player2");
        players.add("Player3");
        return players;
    }

    public List<Card> getCardData() {
        return cardData;
    }

    public List<String> getPlayers() {
        return players;
    }
}
