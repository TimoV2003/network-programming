package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 8888;
    private static final int MAX_CLIENTS = 2;
    private static int connectedClients = 0;
    private static final Connection[] connections = new Connection[MAX_CLIENTS];

    private static Boolean gameServerState = true;

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
                    if (gameServerState) {
//                        System.out.println("Game selection");
                        if (connections[0].getGameSelected() && connections[1].getGameSelected()) {
                            System.out.println("Both players selected a game.");
                            int number = (int) Math.round(Math.random());
                            connections[0].sendPacket(new Packet.GameInnit(connections[0].getNickName(), connections[1].getNickName(), connections[number].getGame()));
                            connections[1].sendPacket(new Packet.GameInnit(connections[0].getNickName(), connections[1].getNickName(), connections[number].getGame()));

                            connections[0].setGameSelected(false);
                            connections[1].setGameSelected(false);

                            connections[0].setConnections(connections);
                            connections[1].setConnections(connections);

                            gameServerState = false;
                        }
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






