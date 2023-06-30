package Minigames.tictactoe;

import Server.SendPacket;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;

public class TicTacToe extends JFrame {
    TicTacToeLogic tttl;
    SendPacket sendPacket = new SendPacket();
    public void launch() {
        JFrame frame = new JFrame("Tic Tac Toe");
        tttl = new TicTacToeLogic("Monke", "Monkey");

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
}
