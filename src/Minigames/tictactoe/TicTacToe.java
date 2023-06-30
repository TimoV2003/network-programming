package Minigames.tictactoe;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;

public class TicTacToe extends JFrame {

    TicTacToeLogic tttl;

    String player1;
    String player2;
    String player;

    public TicTacToe(String player1, String player2, String player) {
        this.player1 = player1;
        this.player2 = player2;
        this.player = player;
    }
    public void launch() {
        JFrame frame = new JFrame("Tic Tac Toe");
        tttl = new TicTacToeLogic(player1, player2, player);

        tttl.tictactoeLogic();

        frame.setSize(5000, 5000);
        frame.setResizable(false);

        JFXPanel fxPanel = new JFXPanel(); // Required for JavaFX-Swing integration
        fxPanel.setScene(tttl.setup());

        frame.getContentPane().add(fxPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
