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

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                if (connectedClients < MAX_CLIENTS) {
                    connections[connectedClients] = new Connection(clientSocket);

                    System.out.println("New client connected: " + connections[connectedClients].getNickName() + " | " + clientSocket.getInetAddress().getHostAddress());

                    // TODO: Handle the client connection, manage game sessions, and communicate with clients


                    connectedClients++;
                } else {
                    System.out.println("Connection rejected. Maximum number of clients reached.");
                    // TODO: Optionally, you can inform the client that the connection is rejected.
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






