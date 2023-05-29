package Application;

import Minigames.chess.Chess;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public Chess chess = new Chess();
    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<String> gameOptions = FXCollections.observableArrayList(
                "Tic Tac Toe",
                "Chess"
        );
        ListView<String> listView = new ListView<>(gameOptions);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected game: " + newValue);
                // TODO: Launch the selected game or perform any other action
                if(newValue.equals("Chess")){
                    chess.launch();
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