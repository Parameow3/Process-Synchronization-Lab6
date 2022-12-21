import java.time.Instant;
import java.util.concurrent.Phaser;

public class Father extends Thread {
    // Using the Phaser Class to sync the thread process
    private Phaser phaser;

    // Create a private BankAccount variable named account
    private BankAccount account;

    // Create a non-default constructor that takes a BankAccount parameter named account
    public Father(BankAccount account, Phaser phaser) {
        // In the constructor, initialize the account variable with the account parameter
        this.phaser = phaser;
        phaser.register();
        this.account = account;
    }

    // Override the run method
    @Override
    public void run() {
        try {
            System.out.printf("""
                    --------------------Father insert card to the ATM-----------------------
                    [ %s ] come to deposit...\s
                    """, "Father");
            System.out.println("---------------------He put the money in----------------------\n" +
                    "Balance that father saw: " + account.getBalance() + "$ before deposit");

            phaser.arriveAndAwaitAdvance();

            System.out.printf("""
                    --------------------Father Confirmed deposit-----------------------
                    [ %s ] starts deposit at: %s
                    """, "Father", Instant.now());

            // do actual work here...
            account.deposit(1000.0);
            System.out.println("---------------------Father Billing out----------------------\n" +
                    "Balance that father saw: " + account.getBalance() + "$ after deposit");

        } catch (IllegalStateException e) {
            // handle exception
        }
    }
}
