package Minigames.Gwent;

import java.util.ArrayList;
import java.util.List;

class GameBoard {
    private List<Card> player1MeleeBoard;
    private List<Card> player1RangedBoard;
    private List<Card> player1SiegeBoard;
    private List<Card> player2MeleeBoard;
    private List<Card> player2RangedBoard;
    private List<Card> player2SiegeBoard;

    public GameBoard() {
        this.player1MeleeBoard = new ArrayList<>();
        this.player2MeleeBoard = new ArrayList<>();
        this.player1RangedBoard = new ArrayList<>();
        this.player2RangedBoard = new ArrayList<>();
        this.player1SiegeBoard = new ArrayList<>();
        this.player2SiegeBoard = new ArrayList<>();
    }

    public void playCard(Card card, Player player) {
        // Implement playing a card to the appropriate player's board
        if (player.getName().equals("Player 1")) {
            if (card.getFaction().equals("Melee")) {
                player1MeleeBoard.add(card);
            } else if (card.getFaction().equals("Ranged")) {
                player1RangedBoard.add(card);
            } else if (card.getFaction().equals("Siege")) {
                player1SiegeBoard.add(card);
            }
        } else if (player.getName().equals("Player 2")) {
            if (card.getFaction().equals("Melee")) {
                player2MeleeBoard.add(card);
            } else if (card.getFaction().equals("Ranged")) {
                player2RangedBoard.add(card);
            } else if (card.getFaction().equals("Siege")) {
                player2SiegeBoard.add(card);
            }
        }
    }

    // Other methods for managing the game state
}

