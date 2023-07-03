package Packet;

import Minigames.chess.ObjCellMatrix;

import java.io.Serializable;

public class ChessPacket implements Serializable {
    public ObjCellMatrix cellMatrix;
    public int currentPlayer;

    public ChessPacket(ObjCellMatrix cellMatrix, int currentPlayer) {
        this.cellMatrix = cellMatrix;
        this.currentPlayer = currentPlayer;
    }

    public ObjCellMatrix getCellMatrix() {
        return cellMatrix;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
