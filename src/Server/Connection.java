package Server;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private String nickName;

    public Connection(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.nickName = this.reader.readLine();
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

                writer.flush();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getNickName() {
        return nickName;
    }

}
