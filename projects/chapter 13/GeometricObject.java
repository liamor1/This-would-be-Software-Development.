// abstract base class for geometric objects
public abstract class GeometricObject {
    private String color = "white";
    private boolean filled;

    // default constructor
    public GeometricObject() {}

    // constructor with color and filled properties
    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    // getters and setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    // abstract methods to be implemented by subclasses
    public abstract double getArea();
    public abstract double getPerimeter();
}
