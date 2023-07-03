package Packet;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class TicTacToePacket implements Serializable {
    boolean xTurn;
    ArrayList<Button> buttons;

    public TicTacToePacket(boolean xTurn, ArrayList<Button> buttons) {
        this.xTurn = xTurn;
        this.buttons = buttons;
    }

    public boolean isxTurn() {
        return xTurn;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }
}
