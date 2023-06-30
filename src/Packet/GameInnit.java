package Packet;

import CommonAtributes.Game;

import java.io.Serializable;

public class GameInnit implements Serializable {
    String player1Name;
    String player2Name;

    Game game;

    public GameInnit(String player1Name, String player2Name, Game game) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

}
