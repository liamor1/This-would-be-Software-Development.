import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class movingtextcolor extends Application {
    private Text messageText;
    private Pane textPane;
    private double moveAmount = 10;

    @Override
    public void start(Stage primaryStage) {
        messageText = new Text("Welcome to Java");
        messageText.setX(150);
        messageText.setY(100);
        messageText.setFill(Color.BLACK);

        textPane = new Pane();
        textPane.getChildren().add(messageText);

        Button leftButton = new Button("<- Left");
        Button rightButton = new Button("Right ->");

        HBox buttonBox = new HBox(10, leftButton, rightButton);
        buttonBox.setAlignment(Pos.CENTER);

        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbBlue = new RadioButton("Blue");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlack = new RadioButton("Black");
        rbBlack.setSelected(true);

        ToggleGroup colorGroup = new ToggleGroup();
        rbRed.setToggleGroup(colorGroup);
        rbYellow.setToggleGroup(colorGroup);
        rbBlue.setToggleGroup(colorGroup);
        rbGreen.setToggleGroup(colorGroup);
        rbBlack.setToggleGroup(colorGroup);

        HBox radioBox = new HBox(10, rbRed, rbYellow, rbBlue, rbGreen, rbBlack);
        radioBox.setAlignment(Pos.CENTER);

        leftButton.setOnAction(e -> moveText(-moveAmount));
        rightButton.setOnAction(e -> moveText(moveAmount));

        rbRed.setOnAction(e -> messageText.setFill(Color.RED));
        rbYellow.setOnAction(e -> messageText.setFill(Color.YELLOW));
        rbBlue.setOnAction(e -> messageText.setFill(Color.BLUE));
        rbGreen.setOnAction(e -> messageText.setFill(Color.GREEN));
        rbBlack.setOnAction(e -> messageText.setFill(Color.BLACK));

        BorderPane root = new BorderPane();
        root.setCenter(textPane);
        root.setBottom(buttonBox);
        root.setTop(radioBox);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Moving Text with Colors");
        primaryStage.setScene(scene);
        primaryStage.show();

        textPane.widthProperty().addListener((obs, oldVal, newVal) -> {
        });
    }

    private void moveText(double deltaX) {
        double newX = messageText.getX() + deltaX;
        double textWidth = messageText.getBoundsInLocal().getWidth();
        double paneWidth = textPane.getWidth();

        if (newX >= 0 && newX + textWidth <= paneWidth) {
            messageText.setX(newX);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}