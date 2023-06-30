package Packet;

import java.io.Serializable;

public class ConnectFourPacket implements Serializable {
    int [][] board;
    int [][] buttonColor;
    int currentPlayer;
}
