package Server;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final String nickName = "";

    public Connection(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write("Connection accepted. Welcome to the server.");
//            this.nickName = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getNickName() {
        return nickName;
    }

}
