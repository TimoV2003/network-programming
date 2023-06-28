package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendPacket {
    ObjectOutputStream objectOutputStream;
    private String host = "host";
    private final int PORT = 8888;
    private Socket socket;
    public void sendPacket(Object packet) {
//        try {
////            socket = new Socket(host, PORT);
////            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
////            objectOutputStream.writeObject(packet);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
