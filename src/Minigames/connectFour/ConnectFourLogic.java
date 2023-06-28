package Minigames.connectFour;

import Minigames.tictactoe.TicTacToeLogic;

public class ConnectFourLogic {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static final int CONNECT = 4;

    private int[][] board;
    private int currentPlayer;
    private boolean gameOver;
    private int winner;
    private int[][] buttonColors; // Stores the color for each button

    public ConnectFourLogic() {
        board = new int[ROWS][COLS];
        buttonColors = new int[ROWS][COLS];
        initializeButtonColors();
    }

    public void initializeGame() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = 0;
            }
        }

        currentPlayer = 1;
        gameOver = false;
        winner = 0;
        initializeButtonColors();
    }

    private void initializeButtonColors() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                buttonColors[row][col] = 0; // Set initial color to 0 (no color)
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinner() {
        return winner;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getValidRow(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                return row;
            }
        }
        return -1;
    }

    public boolean isValidMove(int col) {
        int row = getValidRow(col);
        return row != -1;
    }

    public void makeMove(int col) {
        int row = getValidRow(col);
        if (row != -1) {
            board[row][col] = currentPlayer;

            if (checkWin(row, col)) {
                gameOver = true;
                winner = currentPlayer;
            } else if (isBoardFull()) {
                gameOver = true;
                winner = 0; // It's a draw
            } else {
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
    }

    private boolean checkWin(int row, int col) {
        int player = board[row][col];

        // Check vertical
        int count = 1;
        for (int r = row + 1; r < ROWS && board[r][col] == player; r++) {
            count++;
        }
        if (count >= CONNECT) {
            return true;
        }

        // Check horizontal
        count = 1;
        for (int c = col + 1; c < COLS && board[row][c] == player; c++) {
            count++;
        }
        for (int c = col - 1; c >= 0 && board[row][c] == player; c--) {
            count++;
        }
        if (count >= CONNECT) {
            return true;
        }

        // Check diagonal (top-left to bottom-right)
        count = 1;
        for (int r = row + 1, c = col + 1; r < ROWS && c < COLS && board[r][c] == player; r++, c++) {
            count++;
        }
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0 && board[r][c] == player; r--, c--) {
            count++;
        }
        if (count >= CONNECT) {
            return true;
        }

        // Check diagonal (top-right to bottom-left)
        count = 1;
        for (int r = row + 1, c = col - 1; r < ROWS && c >= 0 && board[r][c] == player; r++, c--) {
            count++;
        }
        for (int r = row - 1, c = col + 1; r >= 0 && c < COLS && board[r][c] == player; r--, c++) {
            count++;
        }
        if (count >= CONNECT) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getButtonColor(int row, int col) {
        return buttonColors[row][col];
    }

    public void setButtonColor(int row, int col, int color) {
        buttonColors[row][col] = color;
    }

    public void updateButtonColors() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] != 0) {
                    buttonColors[row][col] = board[row][col];
                }
            }
        }
    }
}
