package org.example.demo1;

import org.example.demo1.entity.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockWebSocketClient1 {
    private List<Card> cardData;
    private List<String> players;
    private int player1Score;
    private int player2Score;

    public MockWebSocketClient1() {
        this.cardData = generateCardData();
        this.players = generatePlayers();
        this.player1Score = 0;
        this.player2Score = 0;
    }

    private List<Card> generateCardData() {
        List<Card> cards = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String cardName = "Card " + i;
            String imageUrl = "http://example.com/card/" + i + ".png";
            Card card = new Card(i, cardName, imageUrl);
            cards.add(card);
            cards.add(card);
        }
        return cards;
    }

    private List<String> generatePlayers() {
        List<String> players = new ArrayList<>();
        players.add("Player1");
        players.add("Player2");
        return players;
    }

    public List<Card> getCardData() {
        return cardData;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void updatePlayerScore(String player, int score) {
        if (player.equals("Player1")) {
            player1Score += score;
        } else {
            player2Score += score;
        }
    }

    public int getPlayerScore(String player) {
        return player.equals("Player1") ? player1Score : player2Score;
    }

    public String checkGameStatus() {
        if (player1Score >= 100) return "Player1 Wins!";
        if (player2Score >= 100) return "Player2 Wins!";
        return player1Score > player2Score ? "Player1 is leading" : "Player2 is leading";
    }
}
