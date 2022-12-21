
public class BankAccount {

    // Create a private double variable named balance
    private double balance;

    // Create a non-default constructor that takes a double parameter named initialBalance
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Create a public synchronized void method named deposit that takes a double parameter named amount
    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount){
        this.balance -= amount;
    }

    // getter method for balance
    public double getBalance() {
        return this.balance;
    }
}
