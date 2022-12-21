import java.time.Instant;
import java.util.concurrent.Phaser;

public class Son extends Thread {
    // Using the Phaser Class to sync the thread process
    private Phaser phaser;

    // Create a private BankAccount variable named account
    private BankAccount account;


    // Create a non-default constructor that takes a BankAccount parameter named account
    public Son(BankAccount account, Phaser phaser) {
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
                    --------------------Son insert card to the ATM-----------------------
                    [ %s ] come to withdraw...\s
                    """, "Son");
            System.out.println("---------------------He input amount----------------------\n" +
                    "Balance that son saw: " + account.getBalance()  + "$ before withdraw");

            phaser.arriveAndAwaitAdvance();

            System.out.printf("""
                    ---------------------Son Confirmed withdraw----------------------
                    [ %s ] starts withdraw at: %s
                    """, "Son", Instant.now());


            // do actual work here...
            account.withdraw(500.0);
            System.out.println("--------------------Son Billing out-----------------------\n" +
                    "Balance that son saw: " + account.getBalance()  + "$ after withdraw");

        } catch (IllegalStateException e) {
            // handle exception
        }
    }
}
