import java.util.Scanner;
// checks if number is palindrome
public class palindromechecker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("enter a three digit number: ");
            int num = scanner.nextInt();
            if (num < 0) {
                num = Math.abs(num);
            }
            if (num < 100 || num > 999) {
                System.out.println("error: please enter a valid three digit number.");
                return;
            }
            if (num / 100 == num % 10) {
                System.out.println("yes, " + num + " is a palindrome!");
            } else {
                System.out.println("no, " + num + " is not a palindrome.");
            }
        }
    }
}
