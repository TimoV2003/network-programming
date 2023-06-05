package Minigames.Gwent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Player {
    private String name;
    Random random = new Random();
    private String faction;
    private List<Card> hand;
    private List<Card> deck;
    private List<Card> graveyard;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
        this.deck = new ArrayList<>();
        this.graveyard = new ArrayList<>();
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getGraveyard() {
        return graveyard;
    }

    public void drawCard() {
        // Implement drawing a card from the deck to the hand
        int randomIndex = random.nextInt(deck.size());
        hand.add(deck.get(randomIndex));
        deck.remove(randomIndex);
    }

    public void playCard(Card card) {
        // Implement playing a card from the hand to the board

    }

    public void discardCard(Card card) {
        // Implement discarding a card from the hand to the deck and replacing it with a new one
        // (can be done to two cards at the start of the game)
        hand.remove(card);
        deck.add(card);

        int randomIndex = random.nextInt(deck.size());
        hand.add(deck.get(randomIndex));
        deck.remove(randomIndex);
    }

    public List<Object> getBoard() {
        return null;
    }
}
