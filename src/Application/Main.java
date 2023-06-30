package Application;

import Minigames.chess.Chess;
import Packet.Login;
import Minigames.connectFour.ConnectFour;
import Minigames.tictactoe.TicTacToe;
import Packet.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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



    // Connection
    private String host;
    private final int PORT = 8888;
    private Socket socket;


    // Attributes
//    private Game selectedGame;
    private String username;

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
    public void start(Stage primaryStage) throws Exception {
        gameSelectionScene = gameSelectionScene();
        connectToServerScene = connectToServerScene();
        this.primaryStage = primaryStage;


        this.primaryStage.setScene(connectToServerScene);
        this.primaryStage.setTitle("Minigames App");
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }

    private Scene gameSelectionScene() {
        ObservableList<String> gameOptions = FXCollections.observableArrayList(
                "Tic Tac Toe",
                "Chess"
        );
        ListView<String> listView = new ListView<>(gameOptions);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected game: " + newValue);
                // TODO: Launch the selected game or perform any other action
                if (newValue.equals("Chess")) {
//                    chess.launch();

                }
            }
        });

        VBox root = new VBox(listView);

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
                    this.objectInputStream = new ObjectInputStream(socket.getInputStream());
                    this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                    this.sendPacket(new Login(nicknameField.getText()));
                    Thread t = new Thread(this::receiveData);
                    t.start();

                    String username = nicknameField.getText();
                    this.username = username;
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

    private void sendPacket(Object packet) {
        try {
            objectOutputStream.writeObject(packet);
        } catch (IOException e) {

        }
    }

    private void receiveData() {
        try {
            while (socket.isConnected()) {
                // todo: server logic here
                Object packet = objectInputStream.readObject();
                if(packet instanceof GameInnit){
                    GameInnit gameInnit = (GameInnit) packet;
                    String player;
                    if (gameInnit.getPlayer1Name().equals(username)){
                        player = "Player 1";
                    } else {
                        player = "Player 2";
                    }

                    switch (gameInnit.getGame()){
                        case CHESS:
                            Chess chess = new Chess(gameInnit.getPlayer1Name(), gameInnit.getPlayer2Name(), player);
                            break;
                        case TIC_TAC_TOE:
                            TicTacToe ticTacToe = new TicTacToe(gameInnit.getPlayer1Name(), gameInnit.getPlayer2Name(), player);
                            break;
                        case CONNECT_FOUR:
                            ConnectFour connectFour = new ConnectFour();
                            break;
                    }

                } else if (packet instanceof ChessPacket){

                } else if (packet instanceof ConnectFourPacket){

                } else if (packet instanceof TicTacToePacket){

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}