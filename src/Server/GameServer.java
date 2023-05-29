package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 8888;
    private static final int MAX_CLIENTS = 2;
    private static int connectedClients = 0;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                if (connectedClients < MAX_CLIENTS) {
                    connectedClients++;
                    System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                    // TODO: Handle the client connection, manage game sessions, and communicate with clients

                    connectedClients--;
                } else {
                    System.out.println("Connection rejected. Maximum number of clients reached.");
                    // TODO: Optionally, you can inform the client that the connection is rejected.
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






