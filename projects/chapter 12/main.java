import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int[] numbers = new int[100];
        Random rand = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(1000) + 1;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("enter an index (0-99): ");

        try {
            int index = scanner.nextInt();

            if (index < 0 || index >= numbers.length) {
                System.out.println("out of bounds");
            } else {
                System.out.println("element at index " + index + ": " + numbers[index]);
            }
        } catch (Exception e) {
            System.out.println("invalid. please enter a number.");
        } finally {
            scanner.close();
        }
    }
}
