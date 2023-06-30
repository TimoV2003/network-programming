package Application;

import CommonAtributes.Game;
import Minigames.chess.Chess;
import Minigames.tictactoe.TicTacToe;
import Packet.GameSelection;
import Packet.Login;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Main extends Application {

    // Games
    public Chess chess = new Chess();
    private TicTacToe ticTacToe = new TicTacToe();


    // Connection
    private String host;
    private final int PORT = 8888;


    // Attributes
    private Game selectedGame;

    // GUI
    private Label usernameLabel = new Label();

    // Streams
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    // Scenes
    private Stage primaryStage;
    private Scene connectToServerScene;
    private Scene gameSelectionScene;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        connectToServerScene = connectToServerScene();
        gameSelectionScene = gameSelectionScene();

        this.primaryStage.setScene(connectToServerScene);
        this.primaryStage.setTitle("Minigames App");
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }

    private Scene gameSelectionScene() {

        ListView<String> listView = new ListView<>(FXCollections.observableArrayList(
                Game.TIC_TAC_TOE.toString(),
                Game.CHESS.toString(),
                Game.CONNECT_FOUR.toString()
        ));

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedGame = Game.valueOf(newValue);
                System.out.println(selectedGame);
            }
        });

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            if (selectedGame != null) {
                // todo: send packet to server
                try {
                    switch (selectedGame) {
                        case CHESS:
                            System.out.println("Play: Chess");
                            objectOutputStream.writeObject(new GameSelection(Game.CHESS));
                            break;
                        case TIC_TAC_TOE:
                            System.out.println("Play: TicTacToe");
                            objectOutputStream.writeObject(new GameSelection(Game.TIC_TAC_TOE));
                            break;
                        case CONNECT_FOUR:
                            System.out.println("Play: ConnectFour");
                            objectOutputStream.writeObject(new GameSelection(Game.CONNECT_FOUR));
                            break;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                listView.setDisable(true);
                playButton.setDisable(true);
            }
        });

        VBox root = new VBox(usernameLabel, listView, playButton);

        Scene scene = new Scene(root, 300, 300);
        return scene;
    }

    private Scene connectToServerScene() {
        VBox root = new VBox();

        HBox hostBox = new HBox();
        Label hostLabel = new Label("Host: ");
        TextField hostField = new TextField();
        hostBox.getChildren().addAll(hostLabel, hostField);

        HBox nicknameBox = new HBox();
        Label nicknameLabel = new Label("Nickname: ");
        TextField nicknameField = new TextField();
        nicknameBox.getChildren().addAll(nicknameLabel, nicknameField);


        Button connectButton = new Button("Connect");

        connectButton.setOnAction(e -> {
            host = hostField.getText();
            if (host != null) {
                try {
                    Socket socket = new Socket(host, PORT);
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                    String username = nicknameField.getText();
                    objectOutputStream.writeObject(new Login(username));
                    usernameLabel.setText("Username: " + username);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Connected to server: " + host);
                primaryStage.setScene(gameSelectionScene);
                System.out.println("Switched to game selection scene");
            }
        });

        root.getChildren().addAll(hostBox, nicknameBox, connectButton);


        Scene scene = new Scene(root, 300, 300);
        return scene;
    }

}