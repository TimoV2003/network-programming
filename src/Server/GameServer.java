package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static int PORT = 8888;
    private static final int MAX_CLIENTS = 2;
    private static int connectedClients = 0;
    private static Connection[] connections = new Connection[MAX_CLIENTS];

    private static GameServerState gameServerState = GameServerState.GAME_SELECTION;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                if (connectedClients < MAX_CLIENTS) {
                    Socket clientSocket = serverSocket.accept();
                    connections[connectedClients] = new Connection(clientSocket);
                    connectedClients++;

                } else {
                    switch (gameServerState) {
                        case GAME_SELECTION:
//                        System.out.println("Game selection");
                            if (connections[0].getGameSelected() && connections[1].getGameSelected()) {
                                System.out.println("Both players selected a game.");
                                int number = (int) Math.round(Math.random());
                                connections[0].sendPacket(new Packet.GameInnit(connections[0].getNickName(), connections[1].getNickName(), connections[number].getGame()));
                                connections[1].sendPacket(new Packet.GameInnit(connections[0].getNickName(), connections[1].getNickName(), connections[number].getGame()));

                                connections[0].setGameSelected(false);
                                connections[1].setGameSelected(false);

                                switch (connections[number].getGame()) {
                                    case CHESS:
                                        System.out.println("Chess");
                                        gameServerState = GameServerState.GAME_CHESS;

                                        // todo: start chess game
                                        break;
                                    case TIC_TAC_TOE:
                                        System.out.println("TicTacToe");
                                        gameServerState = GameServerState.GAME_TIC_TAC_TOE;
                                        // todo: start tic tac toe game
                                        break;
                                    case CONNECT_FOUR:
                                        System.out.println("ConnectFour");
                                        gameServerState = GameServerState.GAME_CONNECT_FOUR;
                                        // todo: start connect four game
                                        break;
                                }
                            }
                            break;
                        case GAME_CHESS:

                            break;
                        case GAME_TIC_TAC_TOE:

                            break;
                        case GAME_CONNECT_FOUR:

                            break;
                    }

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






