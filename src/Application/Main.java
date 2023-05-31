package Application;

import Minigames.chess.Chess;
import Minigames.connectFour.ConnectFour;
import Minigames.tictactoe.TicTacToe;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private final Chess chess = new Chess();
    private final TicTacToe ticTacToe = new TicTacToe();
    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<String> gameOptions = FXCollections.observableArrayList(
                "Tic Tac Toe",
                "Chess",
                "Connect 4"
        );
        ListView<String> listView = new ListView<>(gameOptions);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected game: " + newValue);
                // TODO: Launch the selected game or perform any other action
                if(newValue.equals("Chess")){
                    chess.launch();
                } else if (newValue.equals("Tic Tac Toe")) {
                    ticTacToe.launch();
                } else if (newValue.equals("Connect 4")) {
                    ConnectFour connectFour = new ConnectFour();
                }
            }
        });

        VBox root = new VBox(listView);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Minigames App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}