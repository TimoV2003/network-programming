package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
                Socket clientSocket = serverSocket.accept();


                if (connectedClients < MAX_CLIENTS) {
                    connections[connectedClients] = new Connection(clientSocket);

                    // TODO: Handle the client connection, manage game sessions, and communicate with clients


                    connectedClients++;

                }
                if (connectedClients == MAX_CLIENTS) {
                    System.out.println("Maximum number of clients reached.");
                    break;
                }
            }

            while (true) {
                switch (gameServerState) {
                    case GAME_SELECTION:

                        break;
                    case GAME_CHESS:

                        break;
                    case GAME_TICTACTOE:

                        break;
                    case GAME_CONNECTFOUR:

                        break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






