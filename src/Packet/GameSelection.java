package Packet;

import java.io.Serializable;

public class GameSelection implements Serializable {
    private final Game game;

    public GameSelection(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
