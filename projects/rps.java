import java.util.Random;
import java.util.Scanner;
// rock paper scissors
public class rps {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int computer_choice = random.nextInt(3);
        System.out.print("enter your choice (0 for scissor, 1 for rock, 2 for paper): ");
        int user_choice = scanner.nextInt();
        if (user_choice == 0) {
            System.out.println("you chose: scissor");
        } else if (user_choice == 1) {
            System.out.println("you chose: rock");
        } else if (user_choice == 2) {
            System.out.println("you chose: paper");
        } else {
            System.out.println("invalid choice.");
            scanner.close();
            return;
        }
        if (computer_choice == 0) {
            System.out.println("computer chose: scissor");
        } else if (computer_choice == 1) {
            System.out.println("computer chose: rock");
        } else if (computer_choice == 2) {
            System.out.println("computer chose: paper");
        }
        if (user_choice == computer_choice) {
            System.out.println("its a draw");
        } else if ((user_choice == 0 && computer_choice == 2) || (user_choice == 1 && computer_choice == 0) || (user_choice == 2 && computer_choice == 1)) {
            System.out.println("you win");
        } else {
            System.out.println("you lose");
        }
        scanner.close();
    }
}
