package org.example.demo1.entity;

public class Card {
    private int id;
    private String cardName;
    private String image;

    public Card(int id, String cardName, String image) {
        this.id = id;
        this.cardName = cardName;
        this.image = image;
    }

    public Card(String cardName, String image) {
        this.cardName = cardName;
        this.image = image;
    }


    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
