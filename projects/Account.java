import java.util.Date;

public class Account {
    private int id;
    private double balance;
    private static double annualInterestRate;
    private Date dateCreated;

    public Account() {
        id = 0;
        balance = 0.0;
        dateCreated = new Date(); 
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        dateCreated = new Date();
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    
    public static double getAnnualInterestRate() { return annualInterestRate; }
    public static void setAnnualInterestRate(double rate) { annualInterestRate = rate; }
    
    public Date getDateCreated() { return dateCreated; }

    
    public double getMonthlyInterestRate() {
        return (annualInterestRate / 100.0) / 12.0;
    }

    
    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    
    public void withdraw(double amount) {
        if (amount > 0) balance -= amount;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }
}


class TestAccount {
    public static void main(String[] args) {
        
        Account myAccount = new Account(1122, 20000.0);
        Account.setAnnualInterestRate(4.5); 
        
        
        myAccount.withdraw(2500.0);
        myAccount.deposit(3000.0);
        
        
        System.out.printf("Updated balance: $%.2f%n", myAccount.getBalance());
        System.out.printf("Monthly interest: $%.3f%n", myAccount.getMonthlyInterest());
        System.out.println("Account created: " + myAccount.getDateCreated());
    }
}
