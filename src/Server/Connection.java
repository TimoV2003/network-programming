package Server;

import Packet.Login;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private String nickName;

    public Connection(Socket socket) {
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
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getNickName() {
        return nickName;
    }

    private void sendPacket(Object packet) {
        try {
            objectOutputStream.writeObject(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
