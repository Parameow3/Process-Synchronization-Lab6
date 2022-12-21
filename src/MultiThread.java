import java.util.concurrent.Phaser;

public class MultiThread {

    public static void main(String[] args) throws InterruptedException{

        // In the method, create a BankAccount object and named it account
        BankAccount account = new BankAccount(1000.0);

        // Using Phaser
        Phaser phaser = new Phaser();
        phaser.register();

        // Instantiate Father and Son object
        Father father = new Father(account, phaser);
        Son son = new Son(account, phaser);

        // Balance before father and son withdraw or deposit
        System.out.println("-------------------");
        System.out.println("Balance: " + account.getBalance() + "$  |");
        System.out.println("-------------------");

        // Start father and son object
        father.start();
        son.start();

        // simulation of some actual work
        Thread.sleep(10);

        // Now open the phaser barrier
        phaser.arriveAndAwaitAdvance();
        // okay

    }
}
