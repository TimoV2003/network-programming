package Minigames.tictactoe;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.io.ObjectOutputStream;

public class TicTacToe extends JFrame {

    TicTacToeLogic tttl;

    String player1;
    String player2;
    String player;

    ObjectOutputStream objectOutputStream;

    public TicTacToe(String player1, String player2, String player, ObjectOutputStream objectOutputStream) {
        this.player1 = player1;
        this.player2 = player2;
        this.player = player;
        this.objectOutputStream = objectOutputStream;
    }
    public void launch() {
        JFrame frame = new JFrame("Tic Tac Toe");
        tttl = new TicTacToeLogic(player1, player2, player, objectOutputStream);


        startTicTacToe(tttl);

        frame.setSize(5000, 5000);
        frame.setResizable(false);

        JFXPanel fxPanel = new JFXPanel(); // Required for JavaFX-Swing integration
        fxPanel.setScene(tttl.setup());

        frame.getContentPane().add(fxPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void startTicTacToe(TicTacToeLogic tttlogic){
        tttlogic.tictactoeLogic();
//        sendPacket.sendPacket(tttlogic);
        System.out.println("Package has been sent");
    }

    public void setTttl(TicTacToeLogic tttl) {
        this.tttl = tttl;
        startTicTacToe(this.tttl);
    }

    public TicTacToeLogic getTicTacToeLogic() {
        return tttl;
    }
}
