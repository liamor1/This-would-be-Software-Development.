import java.util.Scanner;

public class ATMMachine {
    public static void main(String[] args) {

        Account[] accounts = new Account[10];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, 100);
        }

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.print("Enter an id: ");
            int id = input.nextInt();

            if (id < 0 || id >= 10) {
                System.out.println("Invalid id. Please enter a correct id.");
                continue;
            }

            while (true) {
                System.out.println("\nMain menu");
                System.out.println("1: Check balance");
                System.out.println("2: Withdraw");
                System.out.println("3: Deposit");
                System.out.println("4: Exit");
                System.out.print("Enter a choice: ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("The balance is $" + accounts[id].getBalance());
                        break;
                    case 2:
                        System.out.print("Enter an amount to withdraw: ");
                        double withdraw_am = input.nextDouble();
                        accounts[id].withdraw(withdraw_am);
                        break;
                    case 3:
                        System.out.print("Enter an amount to deposit: ");
                        double depositAmount = input.nextDouble();
                        accounts[id].deposit(depositAmount);
                        break;
                    case 4:
                        System.out.println("Exiting the menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                if (choice == 4) {
                    break;
                }
            }
        }
    }
}

class Account {
    private int id;
    private double balance;

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }
}