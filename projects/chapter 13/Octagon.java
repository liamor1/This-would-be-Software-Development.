// octagon class that extends geometricobject and implements comparable and cloneable
public class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
    private double side;

    // constructor
    public Octagon(double side) {
        this.side = side;
    }

    // getter and setter for side
    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    // calculate the area using the formula: area = 2(1 + sqrt(2)) * side^2
    @Override
    public double getArea() {
        return 2 * (1 + Math.sqrt(2)) * side * side;
    }

    // calculate the perimeter
    @Override
    public double getPerimeter() {
        return 8 * side;
    }

    // compare this octagon to another based on area
    @Override
    public int compareTo(Octagon o) {
        return Double.compare(this.getArea(), o.getArea());
    }

    // clone this octagon
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy is fine since only primitive fields
    }
}
