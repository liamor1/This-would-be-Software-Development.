public class rectangle {
    private double width;
    private double height;

    public rectangle() {
        this.width = 1.0;
        this.height = 1.0;
    }

    public rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public double width() {
        return width;
    }

    public double height() {
        return height;
    }
}
