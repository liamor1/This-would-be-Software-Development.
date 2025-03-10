
import java.util.Scanner;

public class polygon {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("enter number of sides: ");
            int n = scanner.nextInt();
            System.out.print("enter length of a side: ");
            double s = scanner.nextDouble();
            double area = (n * Math.pow(s,2)) / (4*Math.tan(Math.PI/n));
            System.out.printf("area of the polygon is %.14f\n", area);
        }
    }
}