package Packet;

import java.io.Serializable;

public class Login implements Serializable {
    String username;

    public Login(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
