import java.util.Random;
import java.util.Scanner;
// flips a coin
public class coinflip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("guess whether the coin flip is heads or tails (0 for heads, 1 for tails): ");
        int userguess = scanner.nextInt();
        int coin = random.nextInt(2);
        if (userguess == coin) {
            System.out.println("Your guess is correct!");
        } else {
            System.out.println("Your guess is incorrect.");
        }
        scanner.close();
    }
}