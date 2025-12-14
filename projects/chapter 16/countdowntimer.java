import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class countdowntimer extends Application {
    private Label displayLabel;
    private Timeline timeline;
    private int remainingSeconds;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        TextField inputField = new TextField("Enter seconds");
        displayLabel = new Label();

        inputField.setOnAction(event -> {
            try {
                remainingSeconds = Integer.parseInt(inputField.getText());
                displayLabel.setText(String.valueOf(remainingSeconds));
                startCountdown();
            } catch (NumberFormatException e) {
                displayLabel.setText("Invalid input");
            }
        });

        VBox root = new VBox(10, inputField, displayLabel);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Countdown Timer");
        primaryStage.show();
    }

    private void startCountdown() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            displayLabel.setText(String.valueOf(remainingSeconds));
            if (remainingSeconds <= 0) {
                timeline.stop();
                playMusic();
            }
        }));
        timeline.setCycleCount(remainingSeconds);
        timeline.play();
    }

    private void playMusic() {
        String url = "https://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3";
        Media media = new Media(url);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}