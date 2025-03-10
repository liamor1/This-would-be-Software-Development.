
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("enter string 1:");
        String first = scanner.nextLine();

        System.out.print("enter string 2: ");
        String second = scanner.nextLine();

        if (first.contains(second)) {
            System.out.println("\"" + second + "\" is a substring of \"" + first + "\"");
        } else {
            System.out.println("\"" + second + "\" is not a substring of \"" + first + "\"");
        }
    }
}
