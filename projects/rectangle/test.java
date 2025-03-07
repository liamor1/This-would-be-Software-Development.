public class test {
    public static void main(String[] args) {
        
        rectangle rect1 = new rectangle(4, 40);
        rectangle rect2 = new rectangle(3.5, 35.9);

        System.out.println("Rectangle 1:");
        System.out.println("Width: " + rect1.width());
        System.out.println("Height: " + rect1.height());
        System.out.println("Area: " + rect1.area());
        System.out.println("Perimeter: " + rect1.perimeter());

        System.out.println();

        System.out.println("Rectangle 2:");
        System.out.println("Width: " + rect2.width());
        System.out.println("Height: " + rect2.height());
        System.out.println("Area: " + rect2.area());
        System.out.println("Perimeter: " + rect2.perimeter());
    }
}

