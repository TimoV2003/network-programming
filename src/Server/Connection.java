package Server;

import CommonAtributes.Game;
import Packet.ChessPacket;
import Packet.GameSelection;
import Packet.Login;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private Connection[] connections;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private String nickName;
    private Boolean gameSelected = false;
    private Game game;

    public Connection(Socket socket){
        this.socket = socket;
        try {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread t = new Thread(this::receiveData);
        t.start();
    }

    private void receiveData() {
        try {
            while (socket.isConnected()) {
                // todo: server logic here
                Object packet = objectInputStream.readObject();
                if(packet instanceof Login){
                    Login login = (Login) packet;
                    this.nickName = login.getUsername();
                    System.out.println("New client connected: " + nickName + " | " + socket.getInetAddress().getHostAddress());
                } else if (packet instanceof GameSelection){
                    GameSelection game = (GameSelection) packet;
                    System.out.println("Game selected: " + game.getGame().toString());
                    gameSelected = true;
                    this.game = game.getGame();
                } else if (packet instanceof ChessPacket){
                    ChessPacket chessPacket = (ChessPacket) packet;
                    for (Connection connection : connections) {
                        if (connection != this) {
                            connection.sendPacket(chessPacket);
                        }
                    }
                }

            }
        } catch (Exception e) {
            close();
//            throw new RuntimeException(e);
        }
    }

    public void sendPacket(Object packet) {
        try {
            System.out.println("Sending packet: " + packet.toString());
            objectOutputStream.writeObject(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if (objectOutputStream != null)
                objectOutputStream.close();
            if (objectInputStream != null)
                objectInputStream.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getNickName() {
        return nickName;
    }

    public Boolean getGameSelected() {
        return gameSelected;
    }

    public Game getGame() {
        return game;
    }

    public void setGameSelected(Boolean gameSelected) {
        this.gameSelected = gameSelected;
    }

    public void setConnections(Connection[] connections) {
        this.connections = connections;
    }
}
