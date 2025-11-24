import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RandomTimeClock extends Application {
    private int hour;
    private int minute;

    @Override
    public void start(Stage stage) {

        hour = (int) (Math.random() * 12);
        minute = (int) (Math.random() * 2) * 30;

        ClockPane clock = new ClockPane(hour, minute, 0);

        clock.setSecondHandVisible(false);
        clock.setHourHandVisible(true);
        clock.setMinuteHandVisible(true);

        String timeDisplay = String.format("%02d:%02d:00", hour, minute);
        Label timeLabel = new Label(timeDisplay);

        BorderPane root = new BorderPane();
        root.setCenter(clock);
        root.setBottom(timeLabel);
        BorderPane.setAlignment(timeLabel, Pos.TOP_CENTER);

        Scene scene = new Scene(root, 300, 320);
        stage.setTitle("My First Random Clock");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;
    private double paneWidth = 250, paneHeight = 250;

    private boolean hourHandVisible = true;
    private boolean minuteHandVisible = true;
    private boolean secondHandVisible = true;

    public ClockPane() {
        setCurrentTime();
    }

    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        drawClock();
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        drawClock();
    }

    public boolean isHourHandVisible() {
        return hourHandVisible;
    }

    public void setHourHandVisible(boolean visible) {
        hourHandVisible = visible;
        drawClock();
    }

    public boolean isMinuteHandVisible() {
        return minuteHandVisible;
    }

    public void setMinuteHandVisible(boolean visible) {
        minuteHandVisible = visible;
        drawClock();
    }

    public boolean isSecondHandVisible() {
        return secondHandVisible;
    }

    public void setSecondHandVisible(boolean visible) {
        secondHandVisible = visible;
        drawClock();
    }

    public void setCurrentTime() {
        java.util.Calendar cal = new java.util.GregorianCalendar();
        hour = cal.get(java.util.Calendar.HOUR_OF_DAY) % 12;
        minute = cal.get(java.util.Calendar.MINUTE);
        second = cal.get(java.util.Calendar.SECOND);

        drawClock();
    }

    protected void drawClock() {

        double clockRadius = Math.min(paneWidth, paneHeight) * 0.4;
        double centerX = paneWidth / 2;
        double centerY = paneHeight / 2;

        Circle face = new Circle(centerX, centerY, clockRadius);
        face.setFill(Color.LIGHTGRAY);
        face.setStroke(Color.BLACK);

        Text t12 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
        Text t9 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
        Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
        Text t6 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

        getChildren().clear();
        getChildren().addAll(face, t12, t9, t3, t6);

        if (minuteHandVisible) {
            double length = clockRadius * 0.7;
            double angle = minute * (2 * Math.PI / 60);
            double x = centerX + length * Math.sin(angle);
            double y = centerY - length * Math.cos(angle);

            Line line = new Line(centerX, centerY, x, y);
            line.setStroke(Color.DARKBLUE);
            line.setStrokeWidth(3);
            getChildren().add(line);
        }

        if (hourHandVisible) {
            double length = clockRadius * 0.5;
            double angle = (hour % 12 + minute / 60.0) * (2 * Math.PI / 12);
            double x = centerX + length * Math.sin(angle);
            double y = centerY - length * Math.cos(angle);

            Line line = new Line(centerX, centerY, x, y);
            line.setStroke(Color.GREEN.darker());
            line.setStrokeWidth(5);
            getChildren().add(line);
        }

        if (secondHandVisible) {
            double length = clockRadius * 0.9;
            double angle = second * (2 * Math.PI / 60);
            double x = centerX + length * Math.sin(angle);
            double y = centerY - length * Math.cos(angle);

            Line line = new Line(centerX, centerY, x, y);
            line.setStroke(Color.RED);
            getChildren().add(line);
        }
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paneWidth = width;
        drawClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paneHeight = height;
        drawClock();
    }
}
