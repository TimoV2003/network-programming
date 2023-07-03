package Minigames.chess;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import javax.swing.*;
import javax.swing.JOptionPane;

public class ChessGUI implements WindowFocusListener {

    private WindowChessBoard mainChessBoard;
    private ObjCreateAppletImage createImage;
    private JLabel lblPlayerOne, lblPlayerTwo;
    //	private String[] strRedPieces = {"redPawn.gif","redRock.gif","redKnight.gif","redBishop.gif","redQueen.gif","redKing.gif"};
    private String[] strRedPieces = {"whitePawn.gif", "whiteRock.gif", "whiteKnight.gif", "whiteBishop.gif", "whiteQueen.gif", "whiteKing.gif"};
    //	private String[] strBluePieces = {"bluePawn.gif","blueRock.gif","blueKnight.gif","blueBishop.gif","blueQueen.gif","blueKing.gif"};
    private String[] strBluePieces = {"blackPawn.gif", "blackRock.gif", "blackKnight.gif", "blackBishop.gif", "blackQueen.gif", "blackKing.gif"};
    private Color clrBlue = new Color(75, 141, 221);
    private MediaTracker mt;

    public void chessGUI() {
    }

    public Container createGUI(JFrame mainApp, String player1Name, String player2Name, String player, ObjectOutputStream objectOutputStream) {

        JPanel panRoot = new JPanel(new BorderLayout());
        panRoot.setOpaque(true);
        panRoot.setPreferredSize(new Dimension(550, 650));

        mainChessBoard = new WindowChessBoard(player1Name, player2Name, player, objectOutputStream);
        createImage = new ObjCreateAppletImage();

        mainChessBoard.setSize(new Dimension(500, 500));
        mainChessBoard.newGame();

        lblPlayerOne = new JLabel("    ", JLabel.RIGHT);
        lblPlayerTwo = new JLabel("    ", JLabel.RIGHT);

        try {
            Image[] imgRed = new Image[6];
            Image[] imgBlue = new Image[6];
            mt = new MediaTracker(mainApp);

            for (int i = 0; i < 6; i++) {
                imgRed[i] = createImage.getImage(this, "/" + strRedPieces[i], 5000);
                imgBlue[i] = createImage.getImage(this, "/" + strBluePieces[i], 5000);
                mt.addImage(imgRed[i], 0);
                mt.addImage(imgBlue[i], 0);
            }

            try {
                mt.waitForID(0);
            } catch (InterruptedException e) {
            }

            mainChessBoard.setupImages(imgRed, imgBlue);

        } catch (NullPointerException e) {

            JOptionPane.showMessageDialog(null, "Unable to load images. There should be a folder called images with all the chess pieces in it. Try downloading this programme again", "Unable to load images", JOptionPane.WARNING_MESSAGE);

        }

        JPanel panBottomHalf = new JPanel(new BorderLayout());
        JPanel panNameArea = new JPanel(new GridLayout(3, 1, 2, 2));
        JPanel panPlayerOne = new JPanel();
        JPanel panPlayerTwo = new JPanel();
        JPanel panNameButton = new JPanel();
        JPanel panNewGame = new JPanel();

        panRoot.add(mainChessBoard, BorderLayout.NORTH);
        panRoot.add(panBottomHalf, BorderLayout.SOUTH);
        panBottomHalf.add(panNameArea, BorderLayout.WEST);
        panNameArea.add(panPlayerOne);
        panPlayerOne.add(lblPlayerOne);
        panNameArea.add(panPlayerTwo);
        panPlayerTwo.add(lblPlayerTwo);
        panNameArea.add(panNameButton);
        panBottomHalf.add(panNewGame, BorderLayout.SOUTH);

        panRoot.setBackground(clrBlue);
        panBottomHalf.setBackground(clrBlue);
        panNameArea.setBackground(clrBlue);
        panPlayerOne.setBackground(clrBlue);
        panPlayerTwo.setBackground(clrBlue);
        panNameButton.setBackground(clrBlue);
        panNewGame.setBackground(clrBlue);

        lblPlayerOne.setBackground(new Color(236, 17, 17)); //red
        lblPlayerTwo.setBackground(new Color(17, 27, 237)); //blue

        return panRoot;

    }

    public void windowGainedFocus(WindowEvent e) {
        mainChessBoard.gotFocus();
    }

    public void windowLostFocus(WindowEvent e) {
    }

    public WindowChessBoard getMainChessBoard() {
        return mainChessBoard;
    }
}