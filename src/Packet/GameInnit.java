package Packet;

import CommonAtributes.Game;

public class GameInnit {
    private String player1Name;
    private String player2Name;
    private Game game;


    public GameInnit(String player1Name, String player2Name, Game game) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.game = game;
    }

}
