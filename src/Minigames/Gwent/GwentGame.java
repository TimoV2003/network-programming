package Minigames.Gwent;
import java.util.ArrayList;
import java.util.List;

public class GwentGame {
    public static void main(String[] args) {
        // Create players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Create game board
        GameBoard gameBoard = new GameBoard();

        // Simulate gameplay
        player1.drawCard();
        player1.playCard(player1.getHand().get(0));
        gameBoard.playCard(player1.getBoard().get(0), player1);

        // Continue implementing the game logic
    }
}

