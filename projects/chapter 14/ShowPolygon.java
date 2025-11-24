import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Label;

public class ShowPolygon extends Application {
	@Override
	public void start(Stage primaryStage) {
		StackPane pane = new StackPane();
		MyPolygon polygon = new MyPolygon();
		
		Label stopText = new Label("STOP");
		stopText.setTextFill(Color.WHITE);
		stopText.setFont(Font.font("Arial", FontWeight.BOLD, 80));
		pane.setAlignment(stopText, Pos.CENTER);

		pane.getChildren().addAll(polygon, stopText);

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("STOP Sign");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class MyPolygon extends StackPane {
	private void paint() {
		Polygon polygon = new Polygon();
		polygon.setFill(Color.RED);
		polygon.setStroke(Color.BLACK);
		ObservableList<Double> list = polygon.getPoints();
		
		double centerX = getWidth() / 2, centerY = getHeight() / 2;
		double radius = Math.min(getWidth(), getHeight()) * 0.4;

		int s = 8;
		
		for (int i = 0; i < s; i++) {
			list.add(centerX + radius * Math.cos(2 * i * Math.PI / s)); 
			list.add(centerY - radius * Math.sin(2 * i * Math.PI / s));
		}     
		
		polygon.setRotate(22.5);
		
		getChildren().clear();
		getChildren().add(polygon); 
	}
	
	@Override
	public void setWidth(double width) {
		super.setWidth(width);
		paint();
	}
	
	@Override
	public void setHeight(double height) {
		super.setHeight(height);
		paint();
	}
}