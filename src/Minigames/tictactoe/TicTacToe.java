package Minigames.tictactoe;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;

public class TicTacToe extends JFrame {
    public void launch() {
        JFrame frame = new JFrame("Tic Tac Toe");
        TicTacToeLogic tttl = new TicTacToeLogic("Monke", "Monkey");

        tttl.tictactoeLogic();

        frame.setResizable(false);

        JFXPanel fxPanel = new JFXPanel(); // Required for JavaFX-Swing integration
        fxPanel.setScene(tttl.setup());

        frame.getContentPane().add(fxPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
