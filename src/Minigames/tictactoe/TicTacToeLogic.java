package Minigames.tictactoe;

import Packet.ChessPacket;
import Packet.TicTacToePacket;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sun.plugin2.liveconnect.ArgumentHelper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeLogic implements Serializable {
    private final String player1;
    private final String player2;
    private final String player;

    private final ObjectOutputStream objectOutputStream;
    private Label status;
    private boolean xTurn = true;
    private boolean playerIsX = true;
    private final ArrayList<Button> buttons;
    private ArrayList<String> strings;
    private final HBox row1 = new HBox();
    private final HBox row2 = new HBox();
    private final HBox row3 = new HBox();
    private final VBox board = new VBox();

    public TicTacToeLogic(String player1, String player2, String player, ObjectOutputStream objectOutputStream) {
        this.player1 = player1;
        this.player2 = player2;
        this.player = player;
        this.objectOutputStream = objectOutputStream;
        buttons = new ArrayList<>();
        strings = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            strings.add(" ");
            Button button = new Button(strings.get(i));
            button.setStyle("-fx-font-size: 24px; -fx-min-width: 80px; -fx-min-height: 80px;");
            buttons.add(button);
        }
        if (player.equals("Player 1")) {
            playerIsX = true;
        } else if (player.equals("Player 2")) {
            playerIsX = false;
        }
    }

    public void tictactoeLogic() {
        for (int i = 0; i < strings.size(); i++) {
            final int index = i;
            Button button = buttons.get(i);
            button.setOnAction(e -> {
                if (strings.get(index).contains(" ")) {
                    if (xTurn && playerIsX) {
                        strings.set(index, "X");
                        button.setText(strings.get(index));
                        xTurn = !xTurn;
                        sendPacket();
                        checkGameStatus();
                    } else if (!xTurn && !playerIsX) {
                        strings.set(index, "O");
                        button.setText(strings.get(index));
                        xTurn = !xTurn;
                        sendPacket();
                        checkGameStatus();
                    } else{
                        System.out.println("not your turn");
                    }
                }
            });
        }
    }

    public void sendPacket() {
        try {
            objectOutputStream.writeObject(new TicTacToePacket(xTurn, strings));
            System.out.println("Package has been sent with succes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkGameStatus() {
        String[] positions = new String[9];
        for (int i = 0; i < 9; i++) {
            positions[i] = buttons.get(i).getText();
        }

        if (checkWinCondition(positions, "X")) {
            status.setText(player1 + " wins!");
            disableButtons();
        } else if (checkWinCondition(positions, "O")) {
            status.setText(player2 + " wins!");
            disableButtons();
        } else if (isBoardFull(positions)) {
            status.setText("It's a draw!");
            disableButtons();
        }
    }

    private boolean checkWinCondition(String[] positions, String player) {
        List<List<String>> lines = new ArrayList<>();

        // Add rows
        lines.add(Arrays.asList(positions[0], positions[1], positions[2]));
        lines.add(Arrays.asList(positions[3], positions[4], positions[5]));
        lines.add(Arrays.asList(positions[6], positions[7], positions[8]));

        // Add columns
        lines.add(Arrays.asList(positions[0], positions[3], positions[6]));
        lines.add(Arrays.asList(positions[1], positions[4], positions[7]));
        lines.add(Arrays.asList(positions[2], positions[5], positions[8]));

        // Add diagonals
        lines.add(Arrays.asList(positions[0], positions[4], positions[8]));
        lines.add(Arrays.asList(positions[2], positions[4], positions[6]));

        return lines.stream()
                .anyMatch(line -> line.stream().allMatch(cell -> cell.equals(player)));
    }


    private boolean isBoardFull(String[] positions) {
        for (String position : positions) {
            if (position.contains(" ")) {
                return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    public Scene setup() {
        status = new Label("");

        row1.getChildren().addAll(buttons.get(0), buttons.get(1), buttons.get(2));
        row2.getChildren().addAll(buttons.get(3), buttons.get(4), buttons.get(5));
        row3.getChildren().addAll(buttons.get(6), buttons.get(7), buttons.get(8));
        board.getChildren().addAll(status, row1, row2, row3);

        for (int i = 0; i < strings.size(); i++) {
            buttons.get(i).setText(strings.get(i));
        }

        return new Scene(board);
    }

    public void setxTurn(boolean xTurn) {
        this.xTurn = xTurn;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;

        Platform.runLater(() -> {
            for (int i = 0; i < this.strings.size(); i++) {
                buttons.get(i).setText(this.strings.get(i));
            }
            checkGameStatus();
        });
    }
}
