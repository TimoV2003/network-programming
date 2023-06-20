package Application;

import Minigames.chess.Chess;
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

    public Chess chess = new Chess();
    private String host;
    private final int PORT = 8888;
    private BufferedReader reader;
    private BufferedWriter writer;
    Scene gameSelectionScene;
    Scene connectToServerScene;
    Stage primaryStage;

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
                    this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


                    writer.write(nicknameField.getText() + "\n");
                    writer.flush();


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