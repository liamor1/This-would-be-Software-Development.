import java.util.Date;

class Account {
    private int accountNumber;
    protected double balance;
    private double annualInterestRate;
    private Date dateCreated;

    public Account(int accountNumber, double balance, double annualInterestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return "Account Number: " + accountNumber + "\nBalance: $" + balance + "\nDate Created: " + dateCreated;
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(int accountNumber, double balance, double annualInterestRate) {
        super(accountNumber, balance, annualInterestRate);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            super.withdraw(amount);
        } else {
            System.out.println("Withdrawal denied. Insufficient funds in Savings Account.");
        }
    }

    @Override
    public String toString() {
        return "Savings Account\n" + super.toString();
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int accountNumber, double balance, double annualInterestRate, double overdraftLimit) {
        super(accountNumber, balance, annualInterestRate);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance + overdraftLimit >= amount) {
            balance -= amount;
        } else {
            System.out.println("Withdrawal denied. Exceeds overdraft limit.");
        }
    }

    @Override
    public String toString() {
        return "Checking Account\n" + super.toString() + "\nOverdraft Limit: $" + overdraftLimit;
    }
}

public class BankTest {
    public static void main(String[] args) {
        Account account = new Account(101, 1000, 1.5);
        SavingsAccount savings = new SavingsAccount(102, 1500, 2.0);
        CheckingAccount checking = new CheckingAccount(103, 500, 1.5, 200);

        System.out.println(account);
        System.out.println("--------------------");
        System.out.println(savings);
        System.out.println("--------------------");
        System.out.println(checking);

        System.out.println("\nTesting Withdrawals:");
        savings.withdraw(1600); // should deny
        checking.withdraw(600); // should allow (overdraft limit covers it)
    }
}
