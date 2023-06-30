package Packet;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class TicTacToePacket {
    boolean xTurn;
    ArrayList<Button> buttons;

    public TicTacToePacket(boolean xTurn, ArrayList<Button> buttons) {
        this.xTurn = xTurn;
        this.buttons = buttons;
    }
}
