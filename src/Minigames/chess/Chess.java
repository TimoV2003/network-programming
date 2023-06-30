package Minigames.chess;//Author: ^-^ Veerle ^-^
import javax.swing.JFrame;
import java.io.ObjectOutputStream;

//Chess piece images from http://world.std.com/~wij/fixation/chess-sets.html

public class Chess extends JFrame
{
    String player1Name;
    String player2Name;
    String player;
    ObjectOutputStream objectOutputStream;

    chessGUI chessWindow = new chessGUI();

    public Chess(String player1Name, String player2Name, String player, ObjectOutputStream objectOutputStream)
    {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player = player;
        this.objectOutputStream = objectOutputStream;
    }
		
	public void launch() //With applications, you have to specify a main method (not with applets)
	{
		//JFrame.setDefaultLookAndFeelDecorated(true); //Make it look nice
        JFrame frame = new JFrame("Chess Game"); //Title
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        frame.setContentPane(chessWindow.createGUI(frame, player1Name, player2Name, player, objectOutputStream));
        frame.addWindowFocusListener(chessWindow);
        
        frame.setSize(550,650);
        frame.setResizable(false);
        frame.setVisible(true);  
        frame.pack();
    }

    public chessGUI getChessGUI() {
        return chessWindow;
    }
}