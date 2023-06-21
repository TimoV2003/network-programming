package Minigames.connectFour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnectFour extends JFrame {
    private ConnectFourLogic gameLogic;
    private JButton[][] boardButtons;

    public ConnectFour() {
        gameLogic = new ConnectFourLogic();
        boardButtons = new JButton[ConnectFourLogic.ROWS][ConnectFourLogic.COLS];

        setTitle("Connect Four");

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(ConnectFourLogic.ROWS, ConnectFourLogic.COLS));

        for (int row = 0; row < ConnectFourLogic.ROWS; row++) {
            for (int col = 0; col < ConnectFourLogic.COLS; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(80, 80));
                button.setEnabled(false); // Disabled until it's the player's turn
                button.addActionListener(new ButtonListener(row, col));
                boardButtons[row][col] = button;
                boardPanel.add(button);
            }
        }

        add(boardPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        launch();
    }

    public void launch() {
        gameLogic.initializeGame();

        while (!gameLogic.isGameOver()) {
            int currentPlayer = gameLogic.getCurrentPlayer();
            setTitle("Connect Four - Player " + currentPlayer);
            enableValidButtons(currentPlayer);

            // Wait for the player to make a move
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int winner = gameLogic.getWinner();
        if (winner == 0) {
            JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        // Restart the game
        int option = JOptionPane.showConfirmDialog(this, "Play again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetBoard();
            launch();
        } else {
            dispose();
        }
    }

    private void enableValidButtons(int player) {
        for (int col = 0; col < ConnectFourLogic.COLS; col++) {
            int row = gameLogic.getValidRow(col);
            if (row != -1) {
                boardButtons[row][col].setEnabled(true);
                if (gameLogic.getCurrentPlayer() == player) {
                    boardButtons[row][col].setBackground(player == 1 ? Color.RED : Color.YELLOW);
                }
            }
        }
    }

    private void resetBoard() {
        for (int row = 0; row < ConnectFourLogic.ROWS; row++) {
            for (int col = 0; col < ConnectFourLogic.COLS; col++) {
                boardButtons[row][col].setEnabled(false);
                boardButtons[row][col].setBackground(null);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gameLogic.makeMove(col);
            boardButtons[row][col].setEnabled(false);

            int currentPlayer = gameLogic.getCurrentPlayer();
            boardButtons[row][col].setBackground(currentPlayer == 1 ? Color.RED : Color.YELLOW);

            if (gameLogic.isGameOver()) {
                int winner = gameLogic.getWinner();
                if (winner == 0) {
                    JOptionPane.showMessageDialog(ConnectFour.this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ConnectFour.this, "Player " + winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }

                // Restart the game
                int option = JOptionPane.showConfirmDialog(ConnectFour.this, "Play again?", "Restart", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    resetBoard();
                    launch();
                } else {
                    dispose();
                }
            }
        }
    }
}

