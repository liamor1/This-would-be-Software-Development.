
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ASCII code: ");
        int code = scanner.nextInt();
        if (code >= 0 && code <= 127) {
            char character = (char) code;
            System.out.println("the character for ASCII code " + code + " is: " + character);
        } else {
            System.out.println("enter a number between (0 - 127)");
        }
        scanner.close();
    }
}
