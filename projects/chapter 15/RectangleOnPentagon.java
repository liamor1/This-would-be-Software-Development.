import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RectangleOnPentagon extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);

        
        Polygon pentagon = new Polygon();
        double centerX = 200;
        double centerY = 200;
        double radius = 100;
        for (int i = 0; i < 5; i++) {
            double angle = Math.toRadians(90 + i * 72); 
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            pentagon.getPoints().addAll(x, y);
        }
        pentagon.setStroke(Color.BLACK);
        pentagon.setFill(null);

        
        Rectangle rect = new Rectangle(20, 20, Color.RED);
        rect.setArcWidth(5);
        rect.setArcHeight(5);

        
        Path path = new Path();
        path.getElements().add(new MoveTo(pentagon.getPoints().get(0), pentagon.getPoints().get(1)));
        for (int i = 2; i < pentagon.getPoints().size(); i += 2) {
            path.getElements().add(new LineTo(pentagon.getPoints().get(i), pentagon.getPoints().get(i + 1)));
        }
        path.getElements().add(new LineTo(pentagon.getPoints().get(0), pentagon.getPoints().get(1))); 

        
        PathTransition pt = new PathTransition(Duration.seconds(5), path, rect);
        pt.setCycleCount(PathTransition.INDEFINITE);
        pt.setAutoReverse(false);
        pt.play();

        
        pt.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            double progress = newTime.toMillis() / pt.getDuration().toMillis();
            rect.setOpacity(0.5 + 0.5 * Math.sin(progress * 2 * Math.PI));
        });

        
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                pt.pause();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                pt.play();
            }
        });

        pane.getChildren().addAll(pentagon, rect);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rectangle Moving on Pentagon");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
