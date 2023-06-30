package Packet;

import Minigames.chess.objCellMatrix;

import java.io.Serializable;

public class ChessPacket implements Serializable {
    public objCellMatrix cellMatrix;
    public int currentPlayer;

    public ChessPacket(objCellMatrix cellMatrix, int currentPlayer) {
        this.cellMatrix = cellMatrix;
        this.currentPlayer = currentPlayer;
    }

    public objCellMatrix getCellMatrix() {
        return cellMatrix;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
