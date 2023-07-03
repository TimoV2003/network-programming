package Packet;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class TicTacToePacket implements Serializable {
    boolean xTurn;
    ArrayList<String> strings;

    public TicTacToePacket(boolean xTurn, ArrayList<String> strings) {
        this.xTurn = xTurn;
        this.strings = strings;
    }

    public boolean isxTurn() {
        return xTurn;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }
}
