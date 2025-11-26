import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MoveBall extends Application {
    private static final double paneWidth = 400;
    private static final double paneHeight = 300;
    private static final double ballRadius = 20;
    
    private BallPane ballPane;

    @Override
    public void start(Stage primaryStage) {
        ballPane = new BallPane(paneWidth, paneHeight, ballRadius);
        
        Label instructions = new Label("Use A (left), S (down), D (right), W (up) keys to move the ball");
        instructions.setPadding(new Insets(10));
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(ballPane);
        borderPane.setBottom(instructions);
        BorderPane.setAlignment(instructions, Pos.CENTER);
        
        Scene scene = new Scene(borderPane, paneWidth, paneHeight + 60);
        
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    ballPane.moveLeft();
                    break;
                case D:
                    ballPane.moveRight();
                    break;
                case W:
                    ballPane.moveUp();
                    break;
                case S:
                    ballPane.moveDown();
                    break;
                default:
                    break;
            }
        });
        
        primaryStage.setTitle("Move the Ball - ASWD Controls");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class BallPane extends Pane {
    private Circle circle;
    private double paneWidth;
    private double paneHeight;
    private double ballRadius;
    private static final double moveDistance = 10;
    
    public BallPane(double width, double height, double radius) {
        this.paneWidth = width;
        this.paneHeight = height;
        this.ballRadius = radius;
        
        setPrefSize(paneWidth, paneHeight);
        setStyle("-fx-border-color: black; -fx-border-width: 2;");
        
        circle = new Circle(paneWidth / 2, paneHeight / 2, ballRadius);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);
        
        getChildren().add(circle);
    }
    
    public void moveLeft() {
        if (circle.getCenterX() - ballRadius - moveDistance >= 0) {
            circle.setCenterX(circle.getCenterX() - moveDistance);
        }
    }
    
    public void moveRight() {
        if (circle.getCenterX() + ballRadius + moveDistance <= paneWidth) {
            circle.setCenterX(circle.getCenterX() + moveDistance);
        }
    }
    
    public void moveUp() {
        if (circle.getCenterY() - ballRadius - moveDistance >= 0) {
            circle.setCenterY(circle.getCenterY() - moveDistance);
        }
    }
    
    public void moveDown() {
        if (circle.getCenterY() + ballRadius + moveDistance <= paneHeight) {
            circle.setCenterY(circle.getCenterY() + moveDistance);
        }
    }
    
    public void setBallPosition(double x, double y) {
        x = Math.max(ballRadius, Math.min(x, paneWidth - ballRadius));
        y = Math.max(ballRadius, Math.min(y, paneHeight - ballRadius));
        
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
    
    public Circle getCircle() {
        return circle;
    }
}