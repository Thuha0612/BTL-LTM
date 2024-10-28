package org.example.demo1.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.example.demo1.MockWebSocketClient1;
import org.example.demo1.entity.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {
    @FXML
    private Label usernameLabel, starsLabel, timeLabel, scoreLabel, pairsFlippedLabel;
    @FXML
    private Button homeButton;
    @FXML
    private GridPane gameGrid;

    private int timeLeft = 300; // 5 minutes in seconds
    private int score = 0;
    private int pairsFlipped = 0;

    private List<Card> cards; // List to hold card information
    private Button firstCard = null;
    private Button secondCard = null;

    private MockWebSocketClient1 mockClient = new MockWebSocketClient1();

    public void initialize() {
        usernameLabel.setText("Player1");
        starsLabel.setText("Stars: 5");

        setupGameBoard();
        updateTimer();
    }

    private void updateTimer() {
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (timeLeft > 0) {
                timeLeft--;
                int minutes = timeLeft / 60;
                int seconds = timeLeft % 60;
                timeLabel.setText(String.format("Time Left: %d:%02d", minutes, seconds));
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void setupGameBoard() {
        gameGrid.getChildren().clear();
        cards = mockClient.getCardData(); // Get card data from the mock server
        Collections.shuffle(cards); // Shuffle the cards

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Button card = new Button();
                card.getStyleClass().add("button-card");
                card.setUserData(cards.get(i * 5 + j)); // Set the card data
                card.setOnAction(e -> handleCardClick(card));
                gameGrid.add(card, j, i);
            }
        }
    }

    private void handleCardClick(Button card) {
        if (firstCard == null) {
            firstCard = card;
            showCard(card);
        } else if (secondCard == null && card != firstCard) {
            secondCard = card;
            showCard(card);
            checkForMatch();
        }
    }

    private void showCard(Button card) {
        Card cardData = (Card) card.getUserData();
        card.setText(String.valueOf(cardData.getId())); // Show card id
        card.setDisable(true); // Disable card to prevent re-clicking
    }

    private void checkForMatch() {
        if (firstCard != null && secondCard != null) {
            if (firstCard.getUserData().equals(secondCard.getUserData())) {
                // If both cards match
                score += 10; // Increase score for a match
                pairsFlipped++;
                scoreLabel.setText("Score: " + score);
                pairsFlippedLabel.setText("Pairs Flipped: " + pairsFlipped);

                // Hide the second card after a short delay
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    firstCard.setVisible(false);
                    secondCard.setVisible(false);
                    firstCard = null;
                    secondCard = null;
                }));
                timeline.play();
            } else {
                // If cards do not match, hide them after a delay
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    hideCards(firstCard, secondCard);
                    firstCard = null;
                    secondCard = null;
                }));
                timeline.play();
            }
        }
    }

    private void hideCards(Button firstCard, Button secondCard) {
        if (firstCard != null) {
            firstCard.setText(""); // Reset the text to hide the card
            firstCard.setDisable(false); // Enable card for future clicks
        }
        if (secondCard != null) {
            secondCard.setText(""); // Reset the text to hide the card
            secondCard.setDisable(false); // Enable card for future clicks
        }
    }

    @FXML
    private void handleHomeAction() {
        System.out.println("Home button clicked");
    }
}
